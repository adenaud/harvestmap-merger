package fr.tpdo.teso;

/**
 * Created by Anthony on 20/06/2015.
 */
public class ReadException extends RuntimeException {

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
