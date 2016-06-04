package edu.csula.datascience.auth;

/**
 * Simple auth to get token to access course server controlled env
 */
public class AuthenticationApp {
    public static void main(String[] args) {
        Authorization auth = new Authorization();

        System.out.println(
            String.format(
                "Token: %s"
/*<<<<<<< HEAD
                auth.getToken("CS-594")
=======
                auth.getToken("CS-454")
>>>>>>> c6fa708cd278d8643d493dd89c22158a3109450b*/
            )
        );
    }
}
