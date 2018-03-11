package ua.kpi.cad.linguisticvar.domain.operators;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import java.util.List;

public class AndOperator extends Operator {
    {
        this.PRIORITY = 1;
    }

    @Override
    public FuzzySet applyTo(List<FuzzySet> operands) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean isUnary() {
        return false;
    }

    @Override
    public String toString() {
        return "&";
    }
}