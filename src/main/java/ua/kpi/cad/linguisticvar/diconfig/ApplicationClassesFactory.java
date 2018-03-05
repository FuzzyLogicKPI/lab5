package ua.kpi.cad.linguisticvar.diconfig;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;

public class ApplicationClassesFactory {
    private static Injector injector = Guice.createInjector(new AppModule());

    public static LinguisticVariableCreator getLinguisticVariableCreator() {
        return injector.getInstance(LinguisticVariableCreator.class);
    }
}
