package ua.kpi.cad.linguisticvar.domain.operators;

public class NotExistingOperationException extends RuntimeException {
    public NotExistingOperationException(String message) {
        super(message);
    }
}
