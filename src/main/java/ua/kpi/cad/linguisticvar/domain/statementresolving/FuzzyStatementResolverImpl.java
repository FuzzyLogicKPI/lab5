package ua.kpi.cad.linguisticvar.domain.statementresolving;

import com.google.inject.Inject;
import lombok.Setter;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

public class FuzzyStatementResolverImpl implements FuzzyStatementResolver {
    @Inject
    private FuzzyStatementParser statementParser;

    public FuzzyStatementResolverImpl() {}

    @Override
    public FuzzySet resolveStatementForVariable(String statement, LinguisticVariable linguisticVariable) {
        //parse statement to get operands
        //parse statement to get function which will be applied to operands

        return null;
    }
}
