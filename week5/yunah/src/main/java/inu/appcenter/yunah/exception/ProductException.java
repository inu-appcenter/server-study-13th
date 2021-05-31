package inu.appcenter.yunah.exception;

public class ProductException extends RuntimeException {
    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(Throwable cause) {
        super(cause);
    }
}
