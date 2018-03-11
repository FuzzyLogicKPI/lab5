package ua.kpi.cad.linguisticvar.domain.operator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;

// TODO: parameterize and add more cases.
@RunWith(JUnit4.class)
public class NotOperatorTest {

    private DefaultOperatorsLookupTable lookupTable;

    @Before
    public void init() {
        lookupTable = new DefaultOperatorsLookupTable();
    }

    @Test
    public void testNotOperator() {
        Operator operator = lookupTable.lookup("не");

        FuzzySet fuzzySet = new FuzzySet(new double[]{0.4, 1.0, 0.3, 0.7, 0.2, 0.0, 0.1, 1.0, 0.9, 0.0});

        FuzzySet res = operator.applyTo(fuzzySet);

        Assert.assertArrayEquals(new double[]{0.6, 0.0, 0.7, 0.3, 0.8, 1.0, 0.9, 0.0, 0.1, 1.0}, res.getMembershipFunctionValues(), 0.01);
    }
}
