package com.ryoga.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// パスワードをハッシュ化するためのユーティリティ(テスト用)
public class CreateHashPassword {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("guitar1234");
        System.out.println(hash);
    }
}
