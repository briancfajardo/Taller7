package org.edu.eci.arep.allsecureweb.securesparkapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;

import static spark.Spark.*;

public class SecureFacade {

    private static JSONObject body;

    public static void main(String[] args) {
        staticFiles.location("/public");
        SSLContext.setContext();
        port(getPort());
        secure(getKeyStorePath(), getKeyStorePassword(), null, null);
        post("/pass", (req, res) -> {
            try {
                body = new JSONObject(req.body());
            } catch (JSONException e){
                body = null;
            }
            assert body != null;
            String user = body.get("user").toString();
            String password = body.get("pass").toString();
            return login(user, password);
        });
        get("/happy.jpeg", (req, res) -> Files.readAllBytes(Path.of("src/main/resources/public/happy.jpeg")));
    }

    private static String login(String username, String passwordHash) throws Exception {
        return URLReader.readURL(String.format("https://ec2-3-84-235-5.compute-1.amazonaws.com:35000/login?user=%s&pass=%s", username, passwordHash));
    }

    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 37000;
    }

    private static String getKeyStorePath() {
        if(System.getenv("KEYSTORE-PATH") != null){
            return System.getenv("KEYSTORE-PATH");
        }
        return "certificados/ecikeystore.p12";
    }


    private static String getKeyStorePassword() {
        if(System.getenv("KEYSTORE-PASSWORD") != null){
            return System.getenv("KEYSTORE-PASSWORD");
        }
        return "123456";
    }

}
