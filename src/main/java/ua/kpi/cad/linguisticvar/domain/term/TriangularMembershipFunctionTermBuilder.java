package ua.kpi.cad.linguisticvar.domain.term;

import fuzzy4j.sets.TriangularFunction;
import lombok.Data;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;

import static java.lang.Math.abs;

public class TriangularMembershipFunctionTermBuilder implements TermBuilder {
    private static final int NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES = 1000;

    @Override
    public Term buildTerm(TermInfo info) {
        TermBoundaries termBoundaries = calculateTermBoundaries(info);
        double[] membershipFunctionValues = calculateMembershipFunctionValues(termBoundaries);

        return new Term(info.getTermName(), new FuzzySet(membershipFunctionValues));
    }

    TermBoundaries calculateTermBoundaries(TermInfo info) {
        int middle;
        int right;
        int left;
        int numberOfValuesForOneTerm = NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES / info.getTotalNumOfTerms();

        if (info.isFirstTerm()) {
            middle = 0;
            right = numberOfValuesForOneTerm;
            left = -right;
        } else if (info.isLastTerm()) {
            middle = NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES;
            left = NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES - numberOfValuesForOneTerm;
            right = NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES + numberOfValuesForOneTerm;
        } else {
            int termIndex = info.getTermIndex();
            left = termIndex * numberOfValuesForOneTerm;
            right = (termIndex + 1) * numberOfValuesForOneTerm;
            middle = (left + right) / 2;
        }

        return new TermBoundaries(left, middle, right);
    }

    double[] calculateMembershipFunctionValues(TermBoundaries termBoundaries) {
        TriangularFunction membershipFunction =
                new TriangularFunction(termBoundaries.left, termBoundaries.middle, termBoundaries.right);

        double[] membershipFunctionValues = new double[NUMBER_OF_MEMBERSHIP_FUNCTION_VALUES];

        for (int i = 0; i < membershipFunctionValues.length; i++) {
            membershipFunctionValues[i] = membershipFunction.membership(i);
        }

        return membershipFunctionValues;
    }

    @Data
    static class TermBoundaries {
        private static final double OVERLAPPING_RATIO = 15./100;

        private int left;
        private int right;
        private int middle;

        public TermBoundaries(int left, int middle, int right) {
            this.left = left;
            this.right = right;
            this.middle = middle;
            correctValuesDueToOverlapping();
        }

        private void correctValuesDueToOverlapping() {
            left = left - (int)(abs(left) * OVERLAPPING_RATIO);
            right = right + (int)(abs(right) * OVERLAPPING_RATIO);
        }
    }
}
