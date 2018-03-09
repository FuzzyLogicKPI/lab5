package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

public abstract class Operator {
    protected int PRIORITY = 0;

    public abstract FuzzySet applyTo(FuzzySet... operands);

    public int getPriority() {
        return PRIORITY;
    }
}