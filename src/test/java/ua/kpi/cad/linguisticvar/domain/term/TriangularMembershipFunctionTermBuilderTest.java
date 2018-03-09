package ua.kpi.cad.linguisticvar.domain.term;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TriangularMembershipFunctionTermBuilderTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testBoundariesCalculationForFirstTerm() {
        TermInfo info = new TermInfo("name", 0, 3);
        TriangularMembershipFunctionTermBuilder.TermBoundaries tb =
                new TriangularMembershipFunctionTermBuilder().calculateTermBoundaries(info);

        int expectedLeft = -333 - (int)(333 * 0.05);
        int expectedMiddle = 0;
        int expectedRight = 333 + (int)(333 * 0.05);

        collector.checkThat(tb.getLeft(), CoreMatchers.is(expectedLeft));
        collector.checkThat(tb.getRight(), CoreMatchers.is(expectedRight));
        collector.checkThat(tb.getMiddle(), CoreMatchers.is(expectedMiddle));
    }

    @Test
    public void testBoundariesCalculationForMiddleTerm() {
        TermInfo info = new TermInfo("name", 1, 3);
        TriangularMembershipFunctionTermBuilder.TermBoundaries tb =
                new TriangularMembershipFunctionTermBuilder().calculateTermBoundaries(info);

        int expectedLeft = 333 - (int)(333 * 0.05);
        int expectedRight = 666 + (int)(666 * 0.05);
        int expectedMiddle = (333 + 666) / 2;

        collector.checkThat(tb.getLeft(), CoreMatchers.is(expectedLeft));
        collector.checkThat(tb.getRight(), CoreMatchers.is(expectedRight));
        collector.checkThat(tb.getMiddle(), CoreMatchers.is(expectedMiddle));
    }

    @Test
    public void testBoundariesCalculationForRightTerm() {
        TermInfo info = new TermInfo("name", 2, 3);
        TriangularMembershipFunctionTermBuilder.TermBoundaries tb =
                new TriangularMembershipFunctionTermBuilder().calculateTermBoundaries(info);

        int expectedLeft = 667 - (int)(667 * 0.05);
        int expectedRight = 1333 + (int)(1333 * 0.05);
        int expectedMiddle = 1000;

        collector.checkThat(tb.getLeft(), CoreMatchers.is(expectedLeft));
        collector.checkThat(tb.getRight(), CoreMatchers.is(expectedRight));
        collector.checkThat(tb.getMiddle(), CoreMatchers.is(expectedMiddle));
    }

    @Test
    public void testMembershipValueCalculationForMiddleTerm() {
        TermInfo info = new TermInfo("name", 1, 3);
        TriangularMembershipFunctionTermBuilder.TermBoundaries tb =
                new TriangularMembershipFunctionTermBuilder().calculateTermBoundaries(info);

        double[] calculatedValues =
                new TriangularMembershipFunctionTermBuilder().calculateMembershipFunctionValues(tb);

        assertEquals(1.0, calculatedValues[(333 + 666) / 2], 0.000_000_001);
    }
}
