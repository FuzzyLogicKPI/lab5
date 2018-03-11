package ua.kpi.cad.linguisticvar.domain.statementresolving;

public class CorruptedOperandStackException extends RuntimeException {
    public CorruptedOperandStackException(String message) {
        super(message);
    }
}
