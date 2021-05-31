package inu.appcenter.study3_1.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }
}
