package ua.kpi.cad.linguisticvar.domain.statementresolving;

import org.junit.Before;
import org.junit.Test;
import ua.kpi.cad.linguisticvar.diconfig.ApplicationClassesFactory;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FuzzyStatementParserImplTest {

    public LinguisticVariableCreator creator;
    public FuzzyStatementParser parser;

    @Before
    public void init() {
        creator = ApplicationClassesFactory.getLinguisticVariableCreator();
        parser = ApplicationClassesFactory.getFuzzyStatementParser();
    }

    @Test
    public void testParsingForStatement1() {
        String statement = "толщина изделия слегка а или не очень б";
        LinguisticVariable variable =
                creator.create("толщина изделия", Arrays.asList("а","б"),100, 200);

        ParsedStatement parsedStatement = parser.parseForVariable(statement, variable);

        StringBuilder sb = new StringBuilder();
        while (parsedStatement.hasNext()) {
            sb.append(parsedStatement.getNextUnit().toString());
        }

        assertEquals("FuzzySet~FuzzySet^!|", sb.toString());
    }

}
