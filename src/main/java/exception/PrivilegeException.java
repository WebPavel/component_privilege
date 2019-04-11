package exception;

public class PrivilegeException extends Exception {
    public PrivilegeException() {
        super();
    }

    public PrivilegeException(String message) {
        super(message);
    }

    public PrivilegeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrivilegeException(Throwable cause) {
        super(cause);
    }

    protected PrivilegeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
