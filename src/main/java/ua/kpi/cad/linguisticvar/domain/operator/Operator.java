package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

public abstract class Operator {
    protected int PRIORITY = 0;

    public abstract FuzzySet applyTo(FuzzySet... operands);

    protected abstract void validate(FuzzySet... operands) throws IllegalArgumentException;

    public int getPriority() {
        return PRIORITY;
    }
}