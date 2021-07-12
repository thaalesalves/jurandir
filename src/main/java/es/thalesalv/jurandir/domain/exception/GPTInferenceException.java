package es.thalesalv.jurandir.domain.exception;

public class GPTInferenceException extends RuntimeException {

    public GPTInferenceException(String message, Throwable error) {
        super(message, error);
    }

    public GPTInferenceException(String message) {
        super(message);
    }

    public GPTInferenceException() {
        super();
    }
}
