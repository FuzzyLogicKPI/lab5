package ua.kpi.cad.linguisticvar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.Interval;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.Term;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainSceneController implements Initializable {
    private static final String CALCULATION_SCENE_FXML = "/templates/calculation-scene.fxml";

    @FXML
    private TextField name;

    @FXML
    private TextField leftBoundary;

    @FXML
    private TextField rightBoundary;

    @FXML
    private TextArea terms;

    @FXML
    private LineChart<Number, Number> memberShipFuncVisualization;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void initialize(URL location, ResourceBundle resources) {
        yAxis.setUpperBound(1);
        yAxis.setLowerBound(0);
        yAxis.setTickUnit(0.2);
        yAxis.setAutoRanging(false);
    }

    @FXML
    protected void calculateMembershipFunc(ActionEvent event) {
        LinguisticVariable var = parseLinguisticVariable();

        xAxis.setLowerBound(var.getInterval().getLeftBoundary());
        xAxis.setUpperBound(var.getInterval().getRightBoundary());
        xAxis.setAutoRanging(false);

        List<Term> terms = var.getTerms();
        List<XYChart.Series<Number, Number>> chartsData = terms.stream()
                .map(term -> convertMFValuesToChartSeries(term.getFuzzySet().getMembershipFunctionValues(), var.getInterval()))
                .collect(Collectors.toList());

        for (XYChart.Series<Number, Number> series : chartsData) {
            memberShipFuncVisualization.getData().add(series);
        }
    }

    @FXML
    protected void goToCalculationScene(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent parent = loader.load(getClass().getResourceAsStream(CALCULATION_SCENE_FXML));

            Scene scene = new Scene(parent);
            Stage stage = ((Stage) ((Button) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }

    private LinguisticVariable parseLinguisticVariable() {
        String name = this.name.getText() != null ? this.name.getText() : "default";

        try {
            Integer leftBoundary = Integer.valueOf(this.leftBoundary.getText());
            Integer rightBoundary = Integer.valueOf(this.rightBoundary.getText());

            if (leftBoundary > rightBoundary) {
                Alert alert = getWarningAlert("Left boundary couldn't be greater than right boundary.", "Please, check your inputs.");
                alert.showAndWait();

                throw new IllegalArgumentException("Left boundary couldn't be greater than right boundary.");
            }


            Interval interval = new Interval(leftBoundary, rightBoundary);
            String[] termNames = terms.getText().split("\n");

            // create linguistic var..
            return new LinguisticVariable(name, mockTerms(termNames), interval);

        } catch (NumberFormatException e) {
            Alert alert = getWarningAlert("Wrong number format.", "Please, check boundaries number format. " + e.getMessage());
            alert.showAndWait();
            return null;
        }
    }

    private XYChart.Series<Number, Number> convertMFValuesToChartSeries(double[] mfValues, Interval interval) {
        double step = (interval.getRightBoundary() - interval.getLeftBoundary()) / mfValues.length;

        List<XYChart.Data<Number, Number>> chartData = new ArrayList<>();
        int counter = 0;
        for (double i = interval.getLeftBoundary(); i < interval.getRightBoundary(); i+=step) {
            chartData.add(new XYChart.Data<>(i, mfValues[counter++]));
        }

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().addAll(chartData);

        return series;
    }

    private List<Term> mockTerms(String[] names) {
        return Arrays.stream(names)
                .map(name -> {
                    double[] random = new Random().doubles(10, 10, 80).toArray();
                    return new Term(name, new FuzzySet(random));
                })
                .collect(Collectors.toList());
    }

    private Alert getWarningAlert(String headerMsg, String contentMsg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(headerMsg);
        alert.setHeaderText(headerMsg);
        alert.setContentText(contentMsg);

        return alert;
    }
}
