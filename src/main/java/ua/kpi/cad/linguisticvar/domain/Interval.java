package ua.kpi.cad.linguisticvar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Interval {
    private double leftBoundary;
    private double rightBoundary;
}
