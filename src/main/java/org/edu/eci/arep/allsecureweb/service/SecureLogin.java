package org.edu.eci.arep.allsecureweb.service;

import org.json.JSONObject;

import static org.edu.eci.arep.allsecureweb.service.UserService.*;
import static spark.Spark.*;


public class SecureLogin {

    private static JSONObject body;

    public static void main(String[] args) {
        initUsers();
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure(getKeyStorePath(), getKeyStorePassword(), null, null);
        port(getPort());

        get("/login", (req, res) -> {
            String user = req.queryParams("user");
            String password = req.queryParams("pass");
            return UserService.verifyPassword(user, password);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    private static String getKeyStorePassword() {
        if(System.getenv("KEYSTORE-PASSWORD") != null){
            return System.getenv("KEYSTORE-PASSWORD");
        }
        return "123456";
    }

    private static String getKeyStorePath() {
        if(System.getenv("KEYSTORE-PATH") != null){
            return System.getenv("KEYSTORE-PATH");
        }
        return "certificados/ecikeystore.p12";
    }
}