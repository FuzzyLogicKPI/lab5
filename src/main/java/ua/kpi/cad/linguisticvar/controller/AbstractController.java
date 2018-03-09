package ua.kpi.cad.linguisticvar.controller;

import javafx.scene.chart.XYChart;
import ua.kpi.cad.linguisticvar.domain.Interval;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController {

    protected XYChart.Series<Number, Number> convertMFValuesToChartSeries(double[] mfValues, Interval interval) {
        double step = (interval.getRightBoundary() - interval.getLeftBoundary()) / mfValues.length;

        List<XYChart.Data<Number, Number>> chartData = new ArrayList<>();
        int counter = 0;
        for (double i = interval.getLeftBoundary(); i < interval.getRightBoundary() - step; i += step) {
            chartData.add(new XYChart.Data<>(i, mfValues[counter++]));
        }

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().addAll(chartData);

        return series;
    }
}
