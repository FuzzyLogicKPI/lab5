package ua.kpi.cad.linguisticvar.domain.operators;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnit;
import ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType;

import java.util.List;

import static ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType.OPERATOR;

public abstract class Operator implements StatementUnit {
    protected int PRIORITY = 0;

    public abstract FuzzySet applyTo(List<FuzzySet> operands);

    public abstract boolean isUnary();

    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public final StatementUnitType getType() {
        return OPERATOR;
    }
}