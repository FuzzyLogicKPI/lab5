package ua.kpi.cad.linguisticvar.diconfig;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParser;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementParserImpl;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolver;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolverImpl;

public class ApplicationClassesFactory {
    private static Injector injector = Guice.createInjector(new AppModule());

    public static LinguisticVariableCreator getLinguisticVariableCreator() {
        return injector.getInstance(LinguisticVariableCreator.class);
    }

    public static FuzzyStatementParser getFuzzyStatementParser() {
        return injector.getInstance(FuzzyStatementParserImpl.class);
    }

    public static FuzzyStatementResolver getFuzzyStatementResolver() {
        return injector.getInstance(FuzzyStatementResolverImpl.class);
    }
}
