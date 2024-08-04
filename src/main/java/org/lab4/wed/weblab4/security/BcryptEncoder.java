package org.lab4.wed.weblab4.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoder {
    public static String encode(String pass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.encode(pass);
    }
    public static boolean checkPass(String pass, String hashPass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.matches(pass, hashPass);
    }
}
