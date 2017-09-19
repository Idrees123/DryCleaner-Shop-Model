package drycleaner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class GraphController implements Initializable {

    @FXML
    private BarChart<String, Number> graph;
    @FXML
    private BarChart<String, Number> graph2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        final BarChart.Series<String, Number> series1 = new BarChart.Series<>();

        graph.setTitle("Average Q(t)");
        graph.setCategoryGap(0);
        graph.setBarGap(0);
        series1.getData().add(new XYChart.Data<String, Number>("Spilitter", Simulation.reportObj.s1AvgQL));
        series1.getData().add(new XYChart.Data<String, Number>("Jacket_Washer", Simulation.reportObj.s2AvgQL));
        series1.getData().add(new XYChart.Data<String, Number>("Pant_Washer", Simulation.reportObj.s3AvgQL));
        series1.getData().add(new XYChart.Data<String, Number>("Reassembler", Simulation.reportObj.s4AvgQL));
        series1.getData().add(new XYChart.Data<String, Number>("Customer_Care", Simulation.reportObj.s5AvgQL));

        barChartData.add(series1);
        graph.setData(barChartData);
        
        
        
        
        
        
        
        ObservableList<XYChart.Series<String, Number>> barChartData1 = FXCollections.observableArrayList();
        final BarChart.Series<String, Number> series2 = new BarChart.Series<>();

        graph2.setCategoryGap(0);
        graph2.setBarGap(0);
        series2.getData().add(new XYChart.Data<String, Number>("Spilitter", Simulation.reportObj.s1Buzziness));
        series2.getData().add(new XYChart.Data<String, Number>("Jacket_Washer", Simulation.reportObj.s2Buzziness));
        series2.getData().add(new XYChart.Data<String, Number>("Pant_Washer", Simulation.reportObj.s3Buzziness));
        series2.getData().add(new XYChart.Data<String, Number>("Reassembler", Simulation.reportObj.s4Buzziness));
        series2.getData().add(new XYChart.Data<String, Number>("Customer_Care", Simulation.reportObj.s5Buzziness));

        barChartData1.add(series2);
        graph2.setData(barChartData1);
        graph2.setTitle(" Average B(t)");
         

    
    
    
    
    
    
    
    
    
    
    
    }

}
