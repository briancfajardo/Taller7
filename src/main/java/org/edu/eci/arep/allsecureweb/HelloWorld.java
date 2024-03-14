package org.edu.eci.arep.allsecureweb;

import org.edu.eci.arep.allsecureweb.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import static org.edu.eci.arep.allsecureweb.service.UserService.*;
import static spark.Spark.*;


public class HelloWorld {

    private static JSONObject body;

    public static void main(String[] args) {
        initUsers();
        staticFiles.location("/public");
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());

        post("/pass", (req, res) -> {
            try {
                body = new JSONObject(req.body());
            } catch (JSONException e){
                body = null;
            }
            assert body != null;
            String name = body.get("name").toString();
            String password = body.get("password").toString();
            return UserService.verifyPassword(name, password);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}