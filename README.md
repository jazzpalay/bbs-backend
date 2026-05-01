# 作業ログアプリ（Backend）

作業ログをMarkdown形式で記録・管理できるアプリのバックエンドです

## 技術スタック
- Java
- Spring Boot
- Flyway
- MyBatis

## 主な機能
- ユーザー認証（サインアップ / サインイン）
- ログ作成・編集（Markdown対応）
- タグ管理機能
- ログ一覧のタイトル、日付、タグでのフィルタリング

## バックエンドの工夫
- DDD（ドメイン駆動設計）を意識し、ドメインロジックとインフラ層を分離
- JWT + リフレッシュトークンによる認証（アクセストークンの短命化）
- リフレッシュトークンのローテーションによるセキュリティ強化
- CSRFトークンを用いたCookieベース認証の保護
- FlywayによるDBスキーマのバージョン管理と自動マイグレーション

## セットアップ

### 1. 環境変数の設定

`.env` ファイルを作成し、以下のように設定してください。

```env
MYSQL_ROOT_PASSWORD=password
MYSQL_DATABASE=sample_db
MYSQL_USER=user
MYSQL_PASSWORD=password
```
### 2. データベースの起動
docker compose up -d

### 3. アプリケーションの起動
./gradlew bootRun

### 4. DB管理画面（Adminer）
http://localhost:8081

## Frontend
[https://github.com/jazzpalay/bbs-frontend](https://github.com/jazzpalay/bbs-frontend)
