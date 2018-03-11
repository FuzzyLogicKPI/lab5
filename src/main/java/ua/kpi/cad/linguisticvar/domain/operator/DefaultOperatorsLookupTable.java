package ua.kpi.cad.linguisticvar.domain.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultOperatorsLookupTable implements OperatorsLookup {
    private static final String SLIGHTLY = "слегка";
    private static final String VERY = "очень";
    private static final String NOT = "не";
    private static final String OR = "или";
    private static final String AND = "и";

    private Map<String, Operator> lookupTable = new HashMap<>();

    public DefaultOperatorsLookupTable() {
        lookupTable = new HashMap<>();

        lookupTable.put(SLIGHTLY, new SlightlyOperator());
        lookupTable.put(VERY, new VeryOperator());
        lookupTable.put(NOT, new NotOperator());
        lookupTable.put(AND, new AndOperator());
        lookupTable.put(OR, new OrOperator());
    }

    @Override
    public Set<String> getAllOperationNames() {
        return lookupTable.keySet();
    }

    public Operator lookup(String stringRepresentationOfOperator) {
        String lowercaseName = stringRepresentationOfOperator.toLowerCase();
        checkForExistence(lowercaseName);

        return lookupTable.get(lowercaseName);
    }

    private void checkForExistence(String operation) {
        if (!lookupTable.containsKey(operation)) {
            throw new NotExistingOperationException(operation + " does not exist.");
        }
    }
}
