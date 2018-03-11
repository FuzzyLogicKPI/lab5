package ua.kpi.cad.linguisticvar.domain.operator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;

// TODO: parameterize and add more cases.
@RunWith(JUnit4.class)
public class AndOperatorTest {

    private DefaultOperatorsLookupTable lookupTable;

    @Before
    public void init() {
        lookupTable = new DefaultOperatorsLookupTable();
    }

    @Test
    public void testAndOperator() {
        Operator operator = lookupTable.lookup("и");

        FuzzySet fuzzySet1 = new FuzzySet(new double[]{0.4, 1.0, 0.3, 0.7, 0.2, 0.0, 0.1, 1.0, 0.3, 0.2});
        FuzzySet fuzzySet2 = new FuzzySet(new double[]{0.6, 0.4, 0.1, 0.8, 0.2, 0.1, 0.7, 0.0, 0.9, 0.3});

        FuzzySet res = operator.applyTo(fuzzySet1, fuzzySet2);

        Assert.assertArrayEquals(new double[] {.6, 1.0, .3, .8, .2, .1, .7, 1.0, .9, .3}, res.getMembershipFunctionValues(), 0.01);
    }
}
