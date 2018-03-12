package ua.kpi.cad.linguisticvar.diconfig;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import ua.kpi.cad.linguisticvar.domain.operator.DefaultOperatorsLookupTable;
import ua.kpi.cad.linguisticvar.domain.operator.OperatorsLookup;
import ua.kpi.cad.linguisticvar.domain.statementresolving.*;
import ua.kpi.cad.linguisticvar.domain.term.TermBuilder;
import ua.kpi.cad.linguisticvar.domain.term.TriangularMembershipFunctionTermBuilder;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TermBuilder.class).to(TriangularMembershipFunctionTermBuilder.class);
        bind(OperatorsLookup.class).to(DefaultOperatorsLookupTable.class);
        bind(FuzzyStatementParser.class).to(FuzzyStatementParserImpl.class);
        bind(FuzzyStatementResolver.class).to(FuzzyStatementResolverImpl.class);
        bind(EventBus.class).toInstance(new EventBus());
        bind(AvailableSyntaxDictionary.class).toInstance(new AvailableSyntaxDictionary());
    }
}
