package ua.kpi.cad.linguisticvar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CalculationSceneController {

    @FXML
    private TextField statement;

    @FXML
    private Button closeBtn;

    @FXML
    private LineChart<Number, Number> operationVisualization;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    protected void executeOperator(ActionEvent event) {
        // parse statement
        // calculate new fuzzy set
        // display fuzzy set
    }

    @FXML
    protected void exit(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
