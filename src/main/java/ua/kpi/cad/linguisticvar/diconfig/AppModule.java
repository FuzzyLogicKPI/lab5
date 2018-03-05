package ua.kpi.cad.linguisticvar.diconfig;

import com.google.inject.AbstractModule;
import ua.kpi.cad.linguisticvar.domain.term.TermBuilder;
import ua.kpi.cad.linguisticvar.domain.term.TriangularMembershipFunctionTermBuilder;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TermBuilder.class).to(TriangularMembershipFunctionTermBuilder.class);
    }
}
