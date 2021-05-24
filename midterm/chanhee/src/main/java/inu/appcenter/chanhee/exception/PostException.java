package inu.appcenter.chanhee.exception;

public class PostException extends RuntimeException{

    public PostException() {
        super();
    }

    public PostException(String message) {
        super(message);
    }

    public PostException(Throwable cause) {
        super(cause);
    }
}
