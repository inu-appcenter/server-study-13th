package inu.appcenter.jaekwon.exception;

public class CommentException extends RuntimeException{
    public CommentException() {
        super();
    }

    public CommentException(String message) {
        super(message);
    }

    public CommentException(Throwable cause) {
        super(cause);
    }
}
