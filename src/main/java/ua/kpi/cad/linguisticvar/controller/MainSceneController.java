package ua.kpi.cad.linguisticvar.controller;

import com.google.common.eventbus.EventBus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lombok.Getter;
import ua.kpi.cad.linguisticvar.diconfig.ApplicationClassesFactory;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariableCreator;
import ua.kpi.cad.linguisticvar.domain.term.Term;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainSceneController extends AbstractController implements Initializable {
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

    @Getter
    private LinguisticVariable variable;

    // TODO: should be injected.
    private EventBus eventBus = new EventBus();

    private LinguisticVariableCreator factory = ApplicationClassesFactory.getLinguisticVariableCreator();

    // TODO: next btn should be hidden while var is not calculated.
    public void initialize(URL location, ResourceBundle resources) {
        yAxis.setUpperBound(1);
        yAxis.setLowerBound(0);
        yAxis.setTickUnit(0.2);
        yAxis.setAutoRanging(false);
    }

    // TODO: set styles for a line chart.
    @FXML
    protected void calculateMembershipFunc(ActionEvent event) {
        LinguisticVariable var = parseLinguisticVariable();
        this.variable = var;

        xAxis.setLowerBound(var.getInterval().getLeftBoundary());
        xAxis.setUpperBound(var.getInterval().getRightBoundary());
        xAxis.setAutoRanging(false);

        List<Term> terms = var.getTerms();
        List<XYChart.Series<Number, Number>> chartsData = terms.stream()
                .map(term -> convertMFValuesToChartSeries(term.getFuzzySet().getMembershipFunctionValues(), var.getInterval()))
                .collect(Collectors.toList());


        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();
        data.addAll(chartsData);

        memberShipFuncVisualization.setData(data);
    }

    @FXML
    protected void goToCalculationScene(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();

        try {
            Parent parent = loader.load(getClass().getResourceAsStream(CALCULATION_SCENE_FXML));
            eventBus.register(loader.getController());
            eventBus.post(variable);

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

            String[] termNames = terms.getText().split("\n");

            return factory.create(name, Arrays.asList(termNames), leftBoundary, rightBoundary);

        } catch (NumberFormatException e) {
            Alert alert = getWarningAlert("Wrong number format.", "Please, check boundaries number format. " + e.getMessage());
            alert.showAndWait();
            return null;
        }
    }

    private Alert getWarningAlert(String headerMsg, String contentMsg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(headerMsg);
        alert.setHeaderText(headerMsg);
        alert.setContentText(contentMsg);

        return alert;
    }
}
