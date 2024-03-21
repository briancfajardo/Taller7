package org.edu.eci.arep.allsecureweb.securesparkapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;

import static spark.Spark.*;

public class SecureFacade {

    private static JSONObject body;

    /**
     * Método principal donde se lanza la aplicación
     * @param args parámetro necesario para lanzar el método main
     */
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

    /**
     * Método que conecta este módulo con el encargado de validar las credenciales de un usuario por medio de un URLReader
     * @param username nombre de usuario de la persona a validar
     * @param passwordHash contraseña convertida en hash
     * @return Respuesta del otro servicio
     * @throws Exception Se lanza cuando hay un problema en la conexión
     */
    private static String login(String username, String passwordHash) throws Exception {
        return URLReader.readURL(String.format("https://ec2-3-84-235-5.compute-1.amazonaws.com:35000/login?user=%s&pass=%s", username, passwordHash));
    }
    /**
     * Método que busca dentro de las variables de entorno si hay un puerto definido, si no retorna
     * el puerto 37000
     * @return puerto donde correrá la aplicación
     */
    private static int getPort() {
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 37000;
    }
    /**
     * Método que busca en las variables de entorno el path de la llave de almacenamiento
     * @return path de la variable de almacenamiento
     */
    private static String getKeyStorePath() {
        if(System.getenv("KEYSTORE-PATH") != null){
            return System.getenv("KEYSTORE-PATH");
        }
        return "certificados/ecikeystore.p12";
    }

    /**
     * Método que busca dentro de las variables de entorno la llave de almacenamiento, en
     * caso de no encontrarla retorna una por defecto
     * @return clave de almacenamiento
     */
    private static String getKeyStorePassword() {
        if(System.getenv("KEYSTORE-PASSWORD") != null){
            return System.getenv("KEYSTORE-PASSWORD");
        }
        return "123456";
    }

}
