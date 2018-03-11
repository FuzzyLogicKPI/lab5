package ua.kpi.cad.linguisticvar.domain.statementresolving;

import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

public interface FuzzyStatementParser {
    ParsedStatement parseForVariable(String statement, LinguisticVariable variable);
}
