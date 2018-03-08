package ua.kpi.cad.linguisticvar.domain.statementresolving;

import com.google.inject.Inject;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

public class FuzzyStatementResolverImpl implements FuzzyStatementResolver {
    @Inject
    private FuzzyStatementParser statementParser;

    public FuzzyStatementResolverImpl() {}

    @Override
    public FuzzySet resolveStatementForVariable(String statement, LinguisticVariable linguisticVariable) {
        ParsedStatement parsedStatement = statementParser.parseString(statement);
        return calculateResultForParsedStatement(parsedStatement);
    }

    private FuzzySet calculateResultForParsedStatement(ParsedStatement statement) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
