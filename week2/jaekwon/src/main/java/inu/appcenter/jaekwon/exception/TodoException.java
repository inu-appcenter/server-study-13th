package inu.appcenter.jaekwon.exception;

public class TodoException extends RuntimeException{
    public TodoException() {
        super();
    }

    public TodoException(String message) {
        super(message);
    }

    public TodoException(Throwable cause) {
        super(cause);
    }
}
