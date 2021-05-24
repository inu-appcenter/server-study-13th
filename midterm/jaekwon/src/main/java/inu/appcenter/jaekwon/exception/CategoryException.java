package inu.appcenter.jaekwon.exception;

public class CategoryException extends RuntimeException{
    public CategoryException() {
        super();
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }
}
