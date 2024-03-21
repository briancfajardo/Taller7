package org.edu.eci.arep.allsecureweb.login;


import static org.edu.eci.arep.allsecureweb.login.UserService.*;
import static spark.Spark.*;


public class SecureLogin {

    /**
     * Método main necesario para que la app funcione
     * @param args parámetros requeridos
     */
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

    /**
     * Método que busca dentro de las variables de entorno si hay un puerto definido, si no retorna
     * el puerto 35000
     * @return puerto donde correrá la aplicación
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
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
}