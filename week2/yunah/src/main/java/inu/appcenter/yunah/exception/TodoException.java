package inu.appcenter.yunah.exception;

public class TodoException extends RuntimeException{

    public TodoException() {
    }

    public TodoException(String message) {
        super(message);
    }

    public TodoException(Throwable cause) {
        super(cause);
    }
}
