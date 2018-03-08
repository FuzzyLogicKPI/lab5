package ua.kpi.cad.linguisticvar.domain.operators;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import java.util.List;

public class VeryOperator extends Operator {
    {
        this.PRIORITY = 3;
    }

    @Override
    public FuzzySet applyTo(List<FuzzySet> operands) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
