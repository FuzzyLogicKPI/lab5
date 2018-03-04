package ua.kpi.cad.linguisticvar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Term {
    private String name;
    private FuzzySet fuzzySet;
}
