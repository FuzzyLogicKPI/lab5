package ua.kpi.cad.linguisticvar.domain.statementresolving;

import org.junit.Before;
import org.junit.Test;
import ua.kpi.cad.linguisticvar.diconfig.ApplicationClassesFactory;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;

import static org.junit.Assert.*;

public class FuzzyStatementResolverImplTest {

    public FuzzyStatementResolver resolver;
    public LinguisticVariableCreator variableCreator;

    @Before
    public void init() {
        resolver = ApplicationClassesFactory.getFuzzyStatementResolver();
        variableCreator = ApplicationClassesFactory.getLinguisticVariableCreator();
    }
}