package ua.kpi.cad.linguisticvar.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.kpi.cad.linguisticvar.diconfig.ApplicationClassesFactory;
import ua.kpi.cad.linguisticvar.domain.term.TermBuilder;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LinguisticVariableCreatorTest {

    private LinguisticVariableCreator factory;

    @Before
    public void init() {
        factory = ApplicationClassesFactory.getLinguisticVariableCreator();
    }

    @Test
    public void testInjectionOfTermBuilder() throws NoSuchFieldException, IllegalAccessException {
        assertNotNull(factory);

        Field termBuilderField = factory.getClass().getDeclaredField("termBuilder");
        termBuilderField.setAccessible(true);
        TermBuilder tb = (TermBuilder) termBuilderField.get(factory);
        termBuilderField.setAccessible(false);

        assertNotNull(tb);
    }
}