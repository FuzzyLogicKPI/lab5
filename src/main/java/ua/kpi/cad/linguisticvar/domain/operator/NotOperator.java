package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import java.util.Arrays;

public class NotOperator extends Operator {
    {
        this.PRIORITY = 2;
    }

    @Override
    public FuzzySet applyTo(FuzzySet... operands) {
        if (operands.length != 1) {
            throw new IllegalArgumentException("Not operator could be applied only for single fuzzy set.");
        }

        FuzzySet fuzzySet = operands[0];

        double[] values = Arrays.stream(fuzzySet.getMembershipFunctionValues())
                .map(val -> 1.0 - val)
                .toArray();

        return new FuzzySet(values);
    }
}