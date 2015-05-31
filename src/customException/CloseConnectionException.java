package customException;

/**
 * Created by lzielinski on 22/05/2015.
 */
public class CloseConnectionException extends Exception {

    private static final String message = "No se pudo efectuar la operacion, fallo en la coneccion, intentelo nuevamente.";

    public CloseConnectionException() {
        super(message);
    }
}
