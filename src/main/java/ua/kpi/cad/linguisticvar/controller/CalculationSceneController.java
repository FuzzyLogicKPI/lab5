package ua.kpi.cad.linguisticvar.controller;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.kpi.cad.linguisticvar.domain.FuzzySet;
import ua.kpi.cad.linguisticvar.domain.LinguisticVariable;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolver;
import ua.kpi.cad.linguisticvar.domain.statementresolving.FuzzyStatementResolverImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationSceneController extends AbstractController implements Initializable {

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

    @Inject
    private FuzzyStatementResolver resolver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resolver = new FuzzyStatementResolverImpl();
    }

    @Subscribe
    public void setLinguisticVariable(LinguisticVariable variable) {
        this.variable = variable;
    }

    @FXML
    protected void executeStatement(ActionEvent event) {
        String statement = this.statement.getText();

        FuzzySet fuzzySet = resolver.resolveStatementForVariable(statement, variable);

        ObservableList<XYChart.Series<Number, Number>> data = FXCollections.observableArrayList();
        XYChart.Series<Number, Number> series = convertMFValuesToChartSeries(fuzzySet.getMembershipFunctionValues(),
                variable.getInterval());

        data.add(series);
        operationVisualization.setData(data);
    }

    @FXML
    protected void exit(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
