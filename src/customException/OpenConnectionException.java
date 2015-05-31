package customException;

/**
 * Created by lzielinski on 22/05/2015.
 */
public class OpenConnectionException extends Exception{

    private static final String message = "No se pudo efectuar la operacion, fallo en la coneccion, intentelo nuevamente.";

    public OpenConnectionException() {
        super(message);
    }
}
