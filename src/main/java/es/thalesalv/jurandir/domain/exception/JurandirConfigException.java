package es.thalesalv.jurandir.domain.exception;

public class JurandirConfigException extends RuntimeException {
    
    public JurandirConfigException(String message, Throwable error) {
        super(message, error);
    }
}
