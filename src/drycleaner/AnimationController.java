/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drycleaner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Furqan
 */

public class AnimationController implements Initializable {

    @FXML
    private ImageView imageView;

public static boolean isAnimating = false;
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img = new Image("file:dry-cleaner.gif");
        imageView.setImage(img);
         
        
        
//    new java.util.Timer().schedule( 
//        new java.util.TimerTask() {
//            @Override
//            public void run() {
//                // your code here
//              
//                isAnimating=true;
//                System.out.println("is animating");
//                
//                
//            }
//        }, 
//        5000 
//);
            
            
            
        }
        
        
        
        
    }    
    

