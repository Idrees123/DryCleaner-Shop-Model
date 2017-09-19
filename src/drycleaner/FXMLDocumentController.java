/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Idrees
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField simulation_time;
    @FXML
    private TextField server4_serviceTimeDamage;
    @FXML
    private TextField damagePentProb;
    @FXML
    private TextField server3_serviceTime;
    @FXML
    private TextField server1_serviceTime;
    @FXML
    private TextField server2_serviceTime;
    @FXML
    private TextField system_interarrival;
    @FXML
    private TextField damageShirtProb;
    @FXML
    private TextField server4_serviceTimeUndamage;
    @FXML
    private TextField server5_serviceTime;

    boolean press=false;
    @FXML
    private AnchorPane Dt;
    @FXML
    private Label finalresult;
    @FXML
    private Button simulateBtn;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(!press){
        

        Problem p = new Problem();
        p.probabilityJ = Double.parseDouble(damageShirtProb.getText());
        p.probabilityP = Double.parseDouble(damagePentProb.getText());
        p.serviceTime1 = Double.parseDouble(server1_serviceTime.getText());
        p.serviceTime2 = Double.parseDouble(server2_serviceTime.getText());
        p.serviceTime3 = Double.parseDouble(server3_serviceTime.getText());
        p.serviceTime4d = Double.parseDouble(server4_serviceTimeDamage.getText());
        p.serviceTime4u = Double.parseDouble(server4_serviceTimeUndamage.getText());
        p.serviceTime5 = Double.parseDouble(server5_serviceTime.getText());
        p.simulationTime = Double.parseDouble(simulation_time.getText());
        p.systemInterArrivalTime = Double.parseDouble(system_interarrival.getText());

        Simulation simulator = new Simulation(p);
        simulator.Simulator();

        File file = new File("report.txt");

        try {
            PrintWriter writer = new PrintWriter(file);
            simulator.getreport();
            writer.println("Total Suits: " + simulator.totalcount);

            writer.println("No of Damage Suits: " + simulator.damagecount);
            writer.println("No of Damage Jackets: " + simulator.damagedJ);
            writer.println("No of Damage Pants: " + simulator.damagedP);
            writer.println("Average Suit Time: " + simulator.avgsuittime);
            writer.println("Maximum Suit Time: " + simulator.maxsuittime);
            writer.println("Average Damage Suit Time: " + simulator.avgDamagesuittime);
            writer.println("Average Undamage Suit Time:" + simulator.avgundamagesuittime);
            writer.println("Server 1 Average Queue Length: " + simulator.servers[0].avgQL);
            writer.println("Server 2 Average Queue Length: " + simulator.servers[1].avgQL);
            writer.println("Server 3 Average Queue Length: " + simulator.servers[2].avgQL);
            writer.println("Server 4 Average Queue Length: " + simulator.servers[3].avgQL);
            writer.println("Server 5 Average Queue Length: " + simulator.servers[4].avgQL);
            writer.println("Suggestion: Decrease the time of the server " + simulator.max);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        simulateBtn.setText("Report");
        }
        else{
            try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Report.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();      
            stage.setScene(new Scene(root1));
            stage.setTitle("Final Report");
           stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
            simulateBtn.setText("Simulate");
        }
        
press=!press;

    
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
