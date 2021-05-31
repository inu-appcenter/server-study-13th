package inu.appcenter.yunah.exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
