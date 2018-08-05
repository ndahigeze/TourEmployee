/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControl;

import POJO.Application;
import Services.ApplicationService;
import UIControl.Inits.EmployeeUI;
import UIControl.Inits.SignInit;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ndahigeze
 */
public class EmployeeMainUIController implements Initializable {

    @FXML
    private JFXButton logoutBTN;
    @FXML
    private Label username;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton createTripBTN;
    @FXML
    private JFXButton handleRequestBTN;
    @FXML
    private JFXButton reportBTN;
    @FXML
    private Label requestAlert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkRequest();
        try {
            startP() ;
        } catch (IOException ex) {
            Logger.getLogger(EmployeeMainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @FXML
    private void logout(ActionEvent event) throws Exception {
        
      new SignInit().start(new Stage());
      EmployeeUI.close();
     
    }
     void startP() throws IOException{
        AnchorPane load=FXMLLoader.load(getClass().getResource("/UIFxml/CreateTrip.fxml"));
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setTopAnchor(load, 0.0);
       rootPane.getChildren().setAll(load);
     }
    
    @FXML
    private void createTripAndService(ActionEvent event) throws IOException {
        AnchorPane load=FXMLLoader.load(getClass().getResource("/UIFxml/CreateTrip.fxml"));
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setTopAnchor(load, 0.0);
       rootPane.getChildren().setAll(load);
    }

    @FXML
    private void handleRequest(ActionEvent event) throws IOException {
          AnchorPane load=FXMLLoader.load(getClass().getResource("/UIFxml/handleRequest.fxml"));
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setTopAnchor(load, 0.0);
       rootPane.getChildren().setAll(load);
    }

    @FXML
    private void report(ActionEvent event) throws IOException {
          AnchorPane load=FXMLLoader.load(getClass().getResource("/UIFxml/reportAndTripMade.fxml"));
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setTopAnchor(load, 0.0);
       rootPane.getChildren().setAll(load);
    }
    
    void checkRequest(){
       ApplicationService p=new ApplicationService ();
       List<Application> ap=p.requestCheck();
       if(!ap.isEmpty()){
           requestAlert.setText(" There Some Trip Request Click on Request Handle To Answer Them ");
       }
    }
    
}
