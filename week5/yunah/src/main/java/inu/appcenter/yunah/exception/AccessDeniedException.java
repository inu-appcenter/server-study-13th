package inu.appcenter.yunah.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }
}
