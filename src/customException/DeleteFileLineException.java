package customException;

/**
 * Created by lzielinski on 22/05/2015.
 */
public class DeleteFileLineException extends Exception {

    private static final String message = "No se pudo efectuar la operacion, fallo al eliminar, intentelo nuevamente.";

    public DeleteFileLineException() {
        super(message);
    }
}
