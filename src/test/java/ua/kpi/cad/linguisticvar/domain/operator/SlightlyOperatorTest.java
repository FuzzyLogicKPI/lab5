package ua.kpi.cad.linguisticvar.domain.operator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;

// TODO: parameterize and add more cases.
@RunWith(JUnit4.class)
public class SlightlyOperatorTest {

    private DefaultOperatorsLookupTable lookupTable;

    @Before
    public void init() {
        lookupTable = new DefaultOperatorsLookupTable();
    }

    @Test
    public void testSlightlyOperator() {
        Operator operator = lookupTable.lookup("слегка");

        FuzzySet fuzzySet = new FuzzySet(new double[]{0.4, 1.0, 0.3, 0.7, 0.2, 0.0, 0.1, 1.0, 0.3, 0.2});

        FuzzySet res = operator.applyTo(fuzzySet);

        Assert.assertArrayEquals(new double[] {.632, 1.0, .5477, .836, .447, .0, .3162, 1.0, .5477, .4472}, res.getMembershipFunctionValues(), 0.01);
    }
}
