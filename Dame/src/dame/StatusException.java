package dame;

/**
 * used if methods are called in wrong status
 */

public class StatusException extends Throwable{
    public StatusException() { super();}
    public StatusException(String message) {super(message);}
    public StatusException(String message, Throwable t) { super(message, t);}}
