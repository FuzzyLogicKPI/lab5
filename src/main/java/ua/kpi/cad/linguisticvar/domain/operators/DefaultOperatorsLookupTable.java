package ua.kpi.cad.linguisticvar.domain.operators;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultOperatorsLookupTable implements OperatorsLookup {
    private static final String SLIGHTLY = "слегка";
    private static final String VERY = "очень";
    private static final String NOT = "не";
    private static final String OR = "или";
    private static final String AND = "и";

    private Map<String, Operator> lookupTable;

    public DefaultOperatorsLookupTable() {
        lookupTable = new HashMap<>();

        lookupTable.put(SLIGHTLY, new SlightlyOperator());
        lookupTable.put(VERY, new VeryOperator());
        lookupTable.put(NOT, new NotOperator());
        lookupTable.put(AND, new AndOperator());
        lookupTable.put(OR, new OrOperator());
    }

    public Set<String> getAllOperationNames() {
        return lookupTable.keySet();
    }

    public Operator lookup(String operatorName) {
        String lowercaseName = operatorName.toLowerCase();
        checkForExistance(lowercaseName);
        return lookupTable.get(lowercaseName);
    }

    private void checkForExistance(String operation) {
        if (!lookupTable.containsKey(operation)) {
            throw new NotExistingOperationException(operation + " does not exist.");
        }
    }
}
