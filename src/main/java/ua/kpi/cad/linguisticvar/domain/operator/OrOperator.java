package ua.kpi.cad.linguisticvar.domain.operator;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;

public class OrOperator extends Operator {
    {
        this.PRIORITY = 2;
    }

    @Override
    public FuzzySet applyTo(FuzzySet... operands) {
        validate(operands);

        FuzzySet leftOperand = operands[0];
        FuzzySet rightOperand = operands[1];

        double[] mfValues = new double[leftOperand.getMembershipFunctionValues().length];
        for (int i = 0; i < leftOperand.getMembershipFunctionValues().length; i++) {
            mfValues[i] = Math.min(leftOperand.getMembershipFunctionValues()[i], rightOperand.getMembershipFunctionValues()[i]);
        }

        return new FuzzySet(mfValues);
    }

    @Override
    protected void validate(FuzzySet... operands) throws IllegalArgumentException {
        if (operands.length != 2) {
            throw new IllegalArgumentException("Not operator could be applied only for single fuzzy set.");
        }

        if (operands[0].getMembershipFunctionValues().length != operands[1].getMembershipFunctionValues().length) {
            throw new IllegalArgumentException("Wrong lengths of mf-values.");
        }
    }

    @Override
    public boolean isUnary() {
        return false;
    }

    @Override
    public String toString() {
        return "|";
    }
}