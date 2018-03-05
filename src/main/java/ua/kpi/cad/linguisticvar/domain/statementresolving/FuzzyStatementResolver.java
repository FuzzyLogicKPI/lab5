package ua.kpi.cad.linguisticvar.domain.statementresolving;

import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

public interface FuzzyStatementResolver {
    FuzzySet resolveStatementForVariable(String fuzzyStatement,
                                         LinguisticVariable linguisticVariable);
}
