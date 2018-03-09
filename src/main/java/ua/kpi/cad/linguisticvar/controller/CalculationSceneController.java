package ua.kpi.cad.linguisticvar.controller;

import com.google.common.eventbus.Subscribe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationSceneController implements Initializable {

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

    private LinguisticVariable variable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Subscribe
    public void setLinguisticVariable(LinguisticVariable variable) {
        this.variable = variable;
    }

    @FXML
    protected void executeOperator(ActionEvent event) {
        System.out.println(variable);
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
