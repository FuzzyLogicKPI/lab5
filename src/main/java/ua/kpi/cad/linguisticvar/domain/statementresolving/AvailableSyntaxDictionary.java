package ua.kpi.cad.linguisticvar.domain.statementresolving;

import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.operator.OperatorsLookup;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType.OPERAND;
import static ua.kpi.cad.linguisticvar.domain.statementresolving.StatementUnitType.OPERATOR;

public class AvailableSyntaxDictionary {
    private Map<String, StatementUnitType> allAvailableWords = new HashMap<>();

    public void makeUpFor(LinguisticVariable variable, OperatorsLookup operators) {
        variable.getTermsRegistry().forEach(put(OPERAND));

        operators.getAllOperationNames().forEach(put(OPERATOR));
    }

    private Consumer<? super String> put(StatementUnitType type) {
        return s -> allAvailableWords.put(s.toLowerCase(), type);
    }

    /**
     *
     * @param word from string representation of statement
     * @return type of statement unit which corresponds to given word
     * @throws IllegalArgumentException if word is not present in dictionary
     */
    public StatementUnitType getTypeIfExists(String word) {
        word = word.toLowerCase();
        if (!allAvailableWords.containsKey(word)) {
            throw new IllegalArgumentException("No such word = " + word);
        } else {
            return allAvailableWords.get(word);
        }
    }
}
