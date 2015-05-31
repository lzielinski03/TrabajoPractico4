package customException;

/**
 * Created by Leonardo on 25/05/2015.
 */
public class InitializeTeamException extends Exception {

    private static final String message = "No se pudo efectuar la operacion, fallo al inisializar el equipo, intentelo nuevamente.";

    public InitializeTeamException() {
        super(message);
    }
}
