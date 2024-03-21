package org.edu.eci.arep.allsecureweb.login;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

/**
 * Clase que maneja los servicios los usuarios
 */
public class UserService {
    private static final Map<String, String> USERS = new HashMap<>();

    /**
     * Método que verifica si un usuario se encuentra registrado
     * @param name Nombre del usuario
     * @param pass Contraseña del usuario
     * @return Booleano con la confirmación de la existencia o no existencia del usuario
     */
    public static boolean verifyPassword(String name, String pass){
        String passSha256 = stringToSha256(pass);
        return USERS.containsKey(name) && USERS.get(name).equals(passSha256);
    }

    /**
     * Método que convierte una cadena en SHA-256
     * @param text texto que se desea convertir
     * @return Nueva cadena convertida a SHA-256
     */
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

    /**
     * Usuarios de prueba
     */
    public static void initUsers(){
        USERS.put("Camilo", stringToSha256("123456"));
        USERS.put("Jaider", stringToSha256("123456"));
    }

}
