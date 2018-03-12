package ua.kpi.cad.linguisticvar.domain.statementresolving;

import com.google.inject.Inject;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.operator.Operator;

import java.util.Deque;
import java.util.LinkedList;

public class FuzzyStatementResolverImpl implements FuzzyStatementResolver {

    @Inject
    private FuzzyStatementParser statementParser;

    public FuzzyStatementResolverImpl() {
    }

    @Override
    public FuzzySet resolveStatementForVariable(String statement, LinguisticVariable linguisticVariable) {
        ParsedStatement parsedStatement = statementParser.parseForVariable(statement, linguisticVariable);
        return calculateResultForParsedStatement(parsedStatement);
    }

    private FuzzySet calculateResultForParsedStatement(ParsedStatement statement) {
        // Use this to store intermediate results
        Deque<FuzzySet> operandStack = new LinkedList<>();

        while (statement.hasNext()) {
            StatementUnit currentUnit = statement.getNextUnit();
            switch (currentUnit.getType()) {
                case OPERAND:
                    operandStack.push((FuzzySet) currentUnit);
                    break;
                case OPERATOR:
                    handleOperator((Operator) currentUnit, operandStack);
                    break;
            }
        }

        validateOperandStackBeforeReturn(operandStack);

        return operandStack.pop();
    }

    private void validateOperandStackBeforeReturn(Deque<FuzzySet> operandStack) {
        if (operandStack.size() != 1) {
            throw new CorruptedOperandStackException("Stack size != 1 after all operations are done.");
        }
    }

    private void handleOperator(Operator currentOperator, Deque<FuzzySet> operandStack) {
        validateOperandBeforeProcessingWithOperator(operandStack);
        if (currentOperator.isUnary()) {
            FuzzySet operand = operandStack.pop();
            FuzzySet resultOfOperation = currentOperator.applyTo(operand);
            operandStack.push(resultOfOperation);
        } else {
            //since it's stack the second operand is on top
            FuzzySet secondOperand = operandStack.pop();
            validateOperandBeforeProcessingWithOperator(operandStack);
            FuzzySet firstOperand = operandStack.pop();
            FuzzySet resultOfOperation = currentOperator.applyTo(firstOperand, secondOperand);
            operandStack.push(resultOfOperation);
        }
    }

    private void validateOperandBeforeProcessingWithOperator(Deque<FuzzySet> operandStack) {
        if (operandStack.isEmpty()) {
            throw new CorruptedOperandStackException("Stack is empty when processing is not finished.");
        }
    }
}
