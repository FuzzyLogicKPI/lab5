package ua.kpi.cad.linguisticvar.domain.statementresolving;

import org.junit.Before;
import org.junit.Test;
import ua.kpi.cad.linguisticvar.diconfig.ApplicationClassesFactory;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FuzzyStatementResolverImplTest {

    public FuzzyStatementResolver resolver;
    public LinguisticVariableCreator variableCreator;

    @Before
    public void init() {
        resolver = ApplicationClassesFactory.getFuzzyStatementResolver();
        variableCreator = ApplicationClassesFactory.getLinguisticVariableCreator();
    }

    @Test
    public void testResolving() {
        String statement = "толщина изделия слегка а или не очень б";
        LinguisticVariable variable =
                variableCreator.create("толщина изделия", Arrays.asList("а","б", "в"),100, 200);

        FuzzySet fuzzySet = resolver.resolveStatementForVariable(statement, variable);
        System.out.println(fuzzySet);
    }
}