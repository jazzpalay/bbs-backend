# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

作業ログをMarkdown形式で記録・管理するアプリのバックエンド（Java 21 / Spring Boot 3.2.5）。フロントエンドは別リポジトリ（`bbs-frontend`）。

## Commands

### Setup
```
# .env を作成（MYSQL_ROOT_PASSWORD, MYSQL_DATABASE, MYSQL_USER, MYSQL_PASSWORD, DB_URL, DB_USERNAME,
# DB_PASSWORD, JWT_SECRET, JWT_EXPIRATION_MS, CORS_ALLOWED_ORIGINS, COOKIE_HTTP_ONLY, COOKIE_SECURE,
# COOKIE_SAME_SITE, S3_REGION, S3_BUCKET_NAME, S3_LOGS_IMAGE_PREFIX, CLOUDFRONT_URL を設定)

docker compose up -d          # MySQL + Adminer(http://localhost:8081) を起動
./gradlew bootRun             # アプリ起動（起動時にFlywayマイグレーションが自動適用される）
```

### Build / Test
```
./gradlew build                                                          # ビルド + テスト
./gradlew test                                                           # 全テスト実行
./gradlew test --tests "com.ryoga.bbs.infrastructure.database.command.user.UserMapperTest"   # クラス単体
./gradlew test --tests "*UserMapperTest\$findIdById"                     # ネストクラス単体（@Nested）
```
Mapperテストは `@MybatisTest` + Testcontainers(MySQL) を使うため、実行にDockerが必要。

## Architecture

DDDを意識したレイヤードアーキテクチャ。**「Scenario（ユースケース）」層が集約横断の調整とトランザクション境界を担い、Infrastructure層はCommand（更新系・MyBatis）とQuery（参照系）を分離**しているのが特徴。新機能を追加する際はこの流れを踏襲する。

```
Controller  →  Scenario(@Transactional)  →  Domain(Service+集約+値オブジェクト)  →  Repository(interface)
                     ↓                                                                  ↓
              他のScenario/Serviceを協調呼び出し                          Infrastructure実装
                                                                    - command/*  : MyBatis Mapper + RecordEntity
                                                                    - query/*    : QueryService実装（一覧/詳細取得専用）
                                                                    - fileStorage: S3実装
```

- **Controller** (`controller/api/**`): `Authentication` からJWT subject(userId)を取得し、Form→Commandに変換してScenarioを呼ぶだけの薄い層。
- **Scenario** (`scenario/**`): ユースケース単位。存在チェック等の整合性検証、複数Domain Serviceのオーケストレーション、`@Transactional`境界。例: `LogScenario` は `UserService`（存在確認）・`LogService`・`ImageScenario` を組み合わせる。
- **Domain** (`domain/model/{log,tag,user,image,authentication}/`): 集約ごとに `XxxService`（ドメインロジック）と値オブジェクト（`Title`, `Content`, `LogDate`, `MailAddress` 等）を持つ。値オブジェクトはコンストラクタ内でバリデーションし、不正値は `DomainLogicInvalidException` を投げる。`Repository` はinterfaceのみ定義し実装はinfrastructure層に置く（依存性逆転）。
- **Infrastructure** (`infrastructure/database/{command,query}/**`): commandはMyBatis Mapper interface + XML（`resources/db/mapper/**`）+ RecordEntity（DBカラム1:1のDTO、ドメインモデルとは別物）。queryは一覧・詳細取得専用の `QueryService` 実装で、JOIN結果を1回のマッピングでレスポンスDTOに変換する。
- **ID管理**: 全IDはUUID文字列で扱い、DBでは `BINARY(16)`。相互変換は `util/MySQLTool`（`stringToBytes`/`bytesToString`）、ID値オブジェクトは共通の `domain/type/Id` を経由する。

### 認証・認可
- JWTアクセストークン（短命）+ Cookie保存のリフレッシュトークン（デバイスID単位、サインイン時にローテーション/削除）。`AuthenticationService` が発行・検証を担当。
- Spring Security は `oauth2ResourceServer().jwt()` でHMAC-SHA256鍵（`jwt.secret`）を使い `NimbusJwtDecoder` で検証（`scenario/config/SecurityConfig.java`）。
- CSRF はCookieトークン方式だが、`/api/v1/auth/**`, `/api/v1/users/me`, `/api/v1/tags/**`, `/api/v1/logs/**` は除外設定になっている。認証系以外のこれらAPIはJWT検証のみに依存している点に注意。

### 画像アップロード
ログ本文（Markdown）中の画像は、フロントが一時的なBlob URLを埋め込んだ状態でmultipartとして送信される。`LogScenario` → `ImageScenario.uploadImage` でS3へ保存後、`LogService.replaceBlobUrlToS3Url` が本文中のBlob URLをCloudFront配信URL（`cloudfront-url` + `logs-image-prefix` + userId + logId + imageId）に置換してから保存する。画像削除（旧ファイルのS3クリーンアップ）に相当する処理は現状ない。

### マイグレーション
Flyway管理。`resources/db/migration/V*.sql`。初期スキーマ（`post`/`comment`）はV5で廃止され、現行は `user`, `tags`, `logs`, `log_tags`（多対多、`ON DELETE CASCADE`）, `refresh_tokens`。MyBatis XMLマッパー（`resources/db/mapper/**`）はJavaのMapper interfaceと1:1でパッケージ構成が対応している。


---

## 1. Communication

- ユーザーへの説明、質問、設計提案、作業報告は日本語で行うこと。
- クラス名、メソッド名、変数名、API、技術用語などは必要に応じて英語を使用してよい。
- 判断理由を説明するときは、「何を変更するか」だけでなく「なぜそうするのか」を説明すること。
- 複数の設計案が考えられる場合は、勝手に1つを選択せず、主要な選択肢とメリット・デメリットを提示すること。

---

## 2. Existing Architecture

### Most Important Rule

現在のプロジェクト構成・アーキテクチャ・設計思想を尊重し、
既存構成を無断で変更しないこと。

新しい機能を追加する場合も、まず既存コードを調査し、
既存の責務分割、依存関係、命名規則、実装パターンに適合する方法を検討すること。

以下をユーザーの承認なしに変更してはならない。

- パッケージ構成の大幅な変更
- Layer間の責務変更
- 既存クラスの大規模な移動
- 使用フレームワークの変更
- 永続化方式の変更
- 認証・認可方式の変更
- 新しいライブラリ・フレームワークの導入
- ビルド方式の変更

アーキテクチャ上の問題を発見した場合は、
勝手に修正せず、問題点・影響・改善案をユーザーへ提示すること。

---

## 3. Architecture Principles

このプロジェクトでは以下を基本方針とする。

- Domain-Driven Design (DDD)
- Layered Architecture
- 関心の分離（Separation of Concerns）
- 依存方向を意識した設計

ただし、DDDのパターンを使用すること自体を目的にしない。

Entity、Value Object、Aggregate、Repository、Domain Serviceなどは、
ドメイン上の必要性がある場合に使用すること。

「DDDだから」という理由だけで不要な抽象化やクラスを追加しないこと。

業務ルールとユースケースを適切にコードで表現し、
変更理由がコードから理解できる設計を優先すること。

---

## 4. Layer Responsibilities

既存実装を最優先としたうえで、各Layerの責務を明確にすること。

### Presentation Layer

- HTTP Request / Responseを扱う。
- 入力値の受け取りとレスポンスへの変換を担当する。
- Controllerに業務ロジックを書かない。
- Domainの内部実装を外部APIへ直接漏らさない。

### Application Layer

- ユースケースを表現する。
- 処理の流れを制御する。
- Domain ObjectやRepository等を組み合わせてユースケースを実現する。
- 本来Domainに属する業務ルールをApplication Layerへ流出させない。

### Domain Layer

- 業務ルールを表現する。
- Entity、Value Object、Aggregate等によってドメインモデルを表現する。
- 可能な限りFrameworkやInfrastructureの詳細に依存しない。
- 不変条件（Invariant）を守る責務を持つ。

### Infrastructure Layer

- DB、外部API、Framework等の技術的詳細を扱う。
- Domain/Application LayerにInfrastructure固有の詳細を漏らさない。

---

## 5. Domain Modeling

新しいドメイン機能を追加するとき、
いきなりEntityやRepositoryを作成しないこと。

以下の順序で検討する。

1. ユーザー要求を確認する。
2. 要求の背景・目的を確認する。
3. ユースケースを整理する。
4. 業務ルール・制約を抽出する。
5. ドメイン上の概念を整理する。
6. Entity / Value Object / Aggregate等が必要か検討する。
7. Aggregateの境界と整合性ルールを検討する。
8. 既存ドメインモデルとの整合性を確認する。
9. 設計案をユーザーへ提示する。
10. ユーザーの確認後に実装する。

要件が曖昧な場合は、Claudeが推測して仕様を確定してはならない。
必要な質問をユーザーへ行うこと。

---

## 6. Development Workflow

機能追加・変更では、原則として以下の順序で進めること。

### Step 1: Investigate

実装前に関連する既存コードを調査する。

確認対象の例：

- 関連Domain
- Application Service / UseCase
- Controller
- Repository
- DB schema / migration
- Test
- 関連API
- 認証・認可
- 既存の類似実装

### Step 2: Analyze Impact

変更による影響範囲を整理する。

以下を明確にすること。

- 変更対象
- 変更理由
- 影響を受ける可能性のある機能
- DBへの影響
- APIへの影響
- テストへの影響

### Step 3: Propose Design

実装前に設計方針をユーザーへ説明する。

少なくとも以下を説明すること。

- 実現方法
- 変更予定ファイル
- Domain Modelへの影響
- Layerごとの変更内容
- 必要なテスト

設計上重要な選択肢が複数ある場合は比較すること。

### Step 4: Implement

ユーザーと合意した設計に従って実装する。

- 必要最小限の変更にする。
- 関係のないコードを変更しない。
- 勝手なリファクタリングを行わない。
- 既存の命名・コーディング規約を尊重する。

### Step 5: Test

変更に関連するテストを実行する。

テストが失敗した場合、
テストを削除・無効化・弱体化することで解決してはならない。

原因を調査し、実装またはテストのどちらに問題があるか判断すること。

### Step 6: Report

作業終了後、以下を日本語で報告する。

- 実装内容
- 変更ファイル
- 設計上の判断
- 実行したテスト
- テスト結果
- 残っている課題・懸念事項

---

## 7. Code Quality

以下を意識すること。

- クラス・メソッドの責務を明確にする。
- 1つのクラスに複数の責務を集中させない。
- 業務ルールをController等へ散在させない。
- 意味のない共通化を行わない。
- 将来使うかもしれないという理由だけで抽象化しない。
- 可読性を過度な短さより優先する。
- コードから設計意図を理解できる命名を行う。
- コメントで悪い設計を補うのではなく、可能な限りコード自体で意図を表現する。
- 既存コードとの一貫性を優先する。

---

## 8. Database

DB変更が必要な場合は、実装前にユーザーへ説明すること。

- 既存データへの影響を確認する。
- migrationの内容を確認する。
- 既存migrationを安易に変更しない。
- destructiveな変更は勝手に実行しない。
- データ整合性をDomain ModelとDBの両方から検討する。

---

## 9. Security

セキュリティに関係する変更は慎重に扱うこと。

特に以下を確認する。

- Authentication
- Authorization
- Input Validation
- SQL Injection
- XSS / CSRF（関連する場合）
- Secret / Credentialの取り扱い
- 個人情報・機密情報
- APIの公開範囲

API Key、Password、Secret等をソースコードへハードコードしないこと。

認証・認可方式をユーザーの許可なく変更しないこと。

---

## 10. Dependencies

新しいDependencyを追加する前に、

1. なぜ必要なのか
2. 標準機能または既存Dependencyで実現できないか
3. 導入によるメリット
4. 導入による影響

を確認し、ユーザーへ説明すること。

ユーザーの確認なしに新しいDependencyを追加しないこと。

---

## 11. Git

ClaudeはGit操作を慎重に扱うこと。

以下はユーザーの明示的な指示なしに実行してはならない。

- git commit
- git push
- branchの削除
- force push
- reset --hard
- 履歴を書き換える操作

コード変更後は、必要に応じてgit diff等を利用して変更内容を確認すること。

commit / pushの最終判断はユーザーが行う。

---

## 12. AI-Assisted Development Principle

Claudeは、このプロジェクトにおける最終的な設計判断者ではない。

Claudeの役割は、

- コードベースの調査
- 要件整理の支援
- 設計案の提示
- 選択肢の比較
- 実装支援
- テスト
- コードレビュー
- 問題点の発見

である。

重要な仕様・設計・技術選択については、
根拠を説明し、ユーザーが最終判断できるようにすること。

ユーザーの意図が不明な場合は、
推測して進めるのではなく質問すること。
