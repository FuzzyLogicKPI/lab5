package ua.kpi.cad.linguisticvar.domain.term;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;

@Getter
@ToString
@AllArgsConstructor
public class Term {
    private String name;
    private FuzzySet fuzzySet;
}
