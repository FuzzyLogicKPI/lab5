package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

public class AndOperator extends Operator {
    {
        this.PRIORITY = 1;
    }

    @Override
    public FuzzySet applyTo(FuzzySet... operands) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}