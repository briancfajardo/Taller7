package org.edu.eci.arep.allsecureweb.service;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

public class UserService {
    private static final Map<String, String> USERS = new HashMap<>();

    public static boolean verifyPassword(String name, String pass){
        String passSha256 = stringToSha256(pass);
        return USERS.containsKey(name) && USERS.get(name).equals(passSha256);
    }

    public static String stringToSha256(String text) {
        MessageDigest md;
        String passSha256 = "";
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(text.getBytes(StandardCharsets.UTF_8));
            passSha256 = Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return passSha256;
    }

    public static void initUsers(){
        USERS.put("Camilo", stringToSha256("123456"));
        USERS.put("Jaider", stringToSha256("123456"));
    }

}
