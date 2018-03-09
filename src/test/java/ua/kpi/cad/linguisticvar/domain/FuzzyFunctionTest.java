package ua.kpi.cad.linguisticvar.domain;

import fuzzy4j.sets.TriangularFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FuzzyFunctionTest {
    @Test
    public void howTriangularFunctionWorks() {
        TriangularFunction tf = new TriangularFunction(-3, 0, 3);
        System.out.println(tf.membership(0));
        System.out.println(tf.membership(1.5));
        System.out.println(tf.membership(3));
    }
}
