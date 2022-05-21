package Web.EnglishCenter.api.handel;

public class InUseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public InUseException(String message) {
        super(message);
    }

    public InUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InUseException(Throwable cause) {
        super(cause);
    }
}
