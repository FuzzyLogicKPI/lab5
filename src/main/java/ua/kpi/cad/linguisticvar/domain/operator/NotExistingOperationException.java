package ua.kpi.cad.linguisticvar.domain.operator;

public class NotExistingOperationException extends RuntimeException {
    public NotExistingOperationException(String message) {
        super(message);
    }
}
