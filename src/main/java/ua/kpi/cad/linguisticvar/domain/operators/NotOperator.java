package ua.kpi.cad.linguisticvar.domain.operators;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import java.util.List;

public class NotOperator extends Operator {
    {
        this.PRIORITY = 2;
    }

    @Override
    public FuzzySet applyTo(List<FuzzySet> operands) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}