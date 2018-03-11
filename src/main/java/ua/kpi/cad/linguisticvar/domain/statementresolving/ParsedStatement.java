package ua.kpi.cad.linguisticvar.domain.statementresolving;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParsedStatement {
    private List<StatementUnit> statement;
    private Iterator<StatementUnit> iterator;
    private boolean isFirstCall;

    public ParsedStatement() {
        statement = new LinkedList<>();
        isFirstCall = true;
    }

    public void add(StatementUnit unit) {
        statement.add(unit);
    }

    public boolean hasNext() {
        initializeIteratorIfFirstCall();
        return iterator.hasNext();
    }

    /**
     * @throws EmptyStatementException if there are no more elements left
     * @return next StatementUnit object of parsed statement
     */
    public StatementUnit getNextUnit() {
        initializeIteratorIfFirstCall();
        return nextOrThrow();
    }

    private StatementUnit nextOrThrow() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            throw new EmptyStatementException("No more elements");
        }
    }

    private void initializeIteratorIfFirstCall() {
        if (isFirstCall) {
            iterator = statement.iterator();
            isFirstCall = false;
        }
    }
}
