package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

public class OrOperator extends Operator {
    {
        this.PRIORITY = 2;
    }

    @Override
    public FuzzySet applyTo(FuzzySet... operands) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}