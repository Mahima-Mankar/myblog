package com.myblog71.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainClass {

    public static void main(String[] args) {
        PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

        System.out.println(PasswordEncoder.encode("test"));
    }
}
