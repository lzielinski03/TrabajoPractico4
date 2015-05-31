package customException;

/**
 * Created by lzielinski on 22/05/2015.
 */
public class PersistFileException extends Exception {

    private static final String message = "No se pudo efectuar la operacion, fallo en la persistencia, intentelo nuevamente.";

    public PersistFileException() {
        super(message);
    }
}
