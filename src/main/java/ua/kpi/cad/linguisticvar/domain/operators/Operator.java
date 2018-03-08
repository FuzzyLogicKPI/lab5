package ua.kpi.cad.linguisticvar.domain.operators;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import java.util.List;

public abstract class Operator {
    protected int PRIORITY = 0;

    public abstract FuzzySet applyTo(List<FuzzySet> operands);

    public int getPriority() {
        return PRIORITY;
    }
}