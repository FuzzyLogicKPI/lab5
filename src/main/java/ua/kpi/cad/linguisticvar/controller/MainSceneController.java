package ua.kpi.cad.linguisticvar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

        // get values from var.mf ??
        // put it to axis..

        // ...
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

        Integer leftBoundary = Integer.valueOf(this.leftBoundary.getText());
        Integer rightBoundary = Integer.valueOf(this.rightBoundary.getText());

        String[] termNames = terms.getText().split(";");

        // create linguistic var..
        return null;
    }
}
