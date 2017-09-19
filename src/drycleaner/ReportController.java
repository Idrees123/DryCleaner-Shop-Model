/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Idrees
 */
public class ReportController implements Initializable {
    @FXML
    private Label label_avgSuitTime;
    @FXML
    private Label label_avgDamageSuitTime;
    @FXML
    private Label label_avgUndamageSuitTime;
    @FXML
    private Label label_totalSuits;
    @FXML
    private Label label_noOfDamageSuits;
    @FXML
    private Label label_maxSuitTime;
    @FXML
    private Label label_server1_avgQueueLength;
    @FXML
    private Label label_server2_avgQueueLength;
    @FXML
    private Label label_server3_avgQueueLength;
    @FXML
    private Label label_server4_avgQueueLength;
    @FXML
    private Label label_server5_avgQueueLength;
    @FXML
    private Label label_suggestion;
    @FXML
    private Label label_noOfDamageJackets;
    @FXML
    private Label label_noOfDamagePants;
    @FXML
    private Button visualizeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
label_avgDamageSuitTime.setText(label_avgDamageSuitTime.getText()+" " +Simulation.reportObj.avgDamageSuitTime);
label_avgSuitTime.setText(label_avgSuitTime.getText()+" " +Simulation.reportObj.avgSuitTime);
label_avgUndamageSuitTime.setText(label_avgUndamageSuitTime.getText()+" " +Simulation.reportObj.avgUndamageTime);
label_maxSuitTime.setText(label_maxSuitTime.getText()+" " +Simulation.reportObj.maxSuitTime);
label_noOfDamageJackets.setText(label_noOfDamageJackets.getText()+" " +Simulation.reportObj.damagedJacket);
label_noOfDamagePants.setText(label_noOfDamagePants.getText()+" " +Simulation.reportObj.damagedPant);
label_noOfDamageSuits.setText(label_noOfDamageSuits.getText()+" " +Simulation.reportObj.damageSuit);
label_server1_avgQueueLength.setText(label_server1_avgQueueLength.getText()+" " +Simulation.reportObj.s1AvgQL);
label_server2_avgQueueLength.setText(label_server2_avgQueueLength.getText()+" " +Simulation.reportObj.s2AvgQL);
label_server3_avgQueueLength.setText(label_server3_avgQueueLength.getText()+" " +Simulation.reportObj.s3AvgQL);
label_server4_avgQueueLength.setText(label_server4_avgQueueLength.getText()+" " +Simulation.reportObj.s4AvgQL);
label_server5_avgQueueLength.setText(label_server5_avgQueueLength.getText()+" " +Simulation.reportObj.s5AvgQL);
label_suggestion.setText(label_suggestion.getText()+" Decrease server time  " +Simulation.reportObj.suggestion);
       
label_totalSuits.setText(label_totalSuits.getText()+" " +Simulation.reportObj.totalSuit);

        
        
        
    }    

    @FXML
    private void showgraph(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graph.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();      
            stage.setScene(new Scene(root1));
            stage.setTitle("Server with aueueLength");
           stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
