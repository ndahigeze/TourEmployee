/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControl;

import POJO.Application;
import POJO.Guide;
import POJO.Location;
import Services.ApplicationService;
import Services.Converter;
import Services.GuideService;
import Services.LocationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Ndahigeze
 */
public class HandleRequestController implements Initializable {
   LocationService ls;
    @FXML
    private JFXButton searchBTN;
    @FXML
    private JFXComboBox<Location> locationTf;
    @FXML
    private JFXDatePicker dateTf;
    @FXML
    private JFXComboBox<Location> slTf;
    @FXML
    private JFXDatePicker sdTf;
    @FXML
    private JFXButton markBTN;
    @FXML
    private JFXButton refreshTripBTN;
    @FXML
    private TableView<Application> requestView;
    @FXML
    private TableColumn<Application, String> clientCol;
    @FXML
    private TableColumn<Application, Date> dateRequested;
    @FXML
    private TableColumn<Application, String> locationCol;
    
    @FXML
    private TableView<Guide> guideTable;
    @FXML
    private TableColumn<Guide, String> nameCol;
     @FXML
    private TableColumn<Guide, String> lnameCol;
    @FXML
    private TableColumn<Guide, Date> dateCol;
     @FXML
    private TableColumn<Guide, Integer> guideCodeCol;
      @FXML
    private TableColumn<Guide, String> AlocationCol;
    @FXML
    private JFXButton refreshDriverBTN;
    @FXML
    private TableView<Application> tripMadeList;
    @FXML
    private TableColumn<Application, String> clientNameCol;
    @FXML
    private TableColumn<Application, String> locationDoneCCol;
    @FXML
    private TableColumn<Application, Date> dateTripCol;
   
    @FXML
    private JFXButton asingnBTN;
    @FXML
    private Label searchedTripCodelabel;
    @FXML
    private JFXDatePicker adTf;
    @FXML
    private JFXComboBox<Location> atTf;
    @FXML
    private JFXTextField gcTf;
    @FXML
    private JFXDatePicker ddatePicker;
    @FXML
    private Label confMSG;
     Converter c;
    @FXML
    private Label confLabel;
    @FXML
    private Label labelNofound;
    @FXML
    private JFXButton serchAsiggnedBTN;
    @FXML
    private JFXDatePicker dtAsigned;
    @FXML
    private JFXButton searchGuider;
    @FXML
    private Label nFound;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        viewRequest();
         populateComb();
         viewTripDone();
         viewGuide();
    }    


    @FXML
    private void refreshTrip(ActionEvent event) {
         viewRequest();
    }

    @FXML
    private void refreshDriver(ActionEvent event) {
       viewGuide();
    }

    @FXML
    private void asignDriver(ActionEvent event) {
         c=new Converter();  
         GuideService gs=new GuideService();
        if(checkDEmpty()){
          confLabel.setText("All Field Are Required");
        }else{
            Guide g=new Guide();
            g.setCode(c.toInt(gcTf.getText()));
            g.setLocation(atTf.getValue());
            g.setWorkDate(c.convertToDate(adTf.getValue()));
            confLabel.setText(gs.updateGuider(g));
        }
         viewGuide();
    }
    Boolean checkDEmpty(){
      
       return adTf.getValue()==null||atTf.getValue()==null||gcTf.getText().isEmpty();
    }

 
    void viewRequest(){
         ApplicationService ps=new ApplicationService();
         ObservableList list=FXCollections.observableArrayList(ps.requestCheck());
         clientCol.setCellValueFactory(new PropertyValueFactory<>("Client"));
         dateRequested.setCellValueFactory(new PropertyValueFactory<>("BookDate"));
         locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
         requestView.setItems(list);
    }
    void viewTripDone(){
          ApplicationService ps=new ApplicationService();
         ObservableList list=FXCollections.observableArrayList(ps.requestDone());
         clientNameCol.setCellValueFactory(new PropertyValueFactory<>("Client"));
         locationDoneCCol.setCellValueFactory(new PropertyValueFactory<>("BookDate"));
         dateTripCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
         tripMadeList.setItems(list);
    }
     public void populateComb(){
        ls=new LocationService();
        List<Location> list=ls.viewLocation();
        list.forEach((l) -> {
            slTf.getItems().add(l);
        });
        
        //popurate onthe comb
        list.forEach((l) -> {
            atTf.getItems().add(l);
        });
        list.forEach((l) -> {
            locationTf.getItems().add(l);
        });
    }

    @FXML
    private void markTheDone(ActionEvent event) {
        c=new Converter();
       if(checkEmpty()){
           confMSG.setText("All field Are Required");
       } else{
         Application ap=new Application();
         ap.setBookDate(c.convertToDate(sdTf.getValue()));
         ap.setLocation(slTf.getValue());
         ApplicationService ps=new ApplicationService();
        confMSG.setText(ps.updateRequest(ap));
       }
       viewTripDone();
    }
   
    boolean checkEmpty(){
      return slTf.getValue()==null||sdTf.getValue()==null;
    }
    void viewGuide(){
          GuideService gs=new GuideService();
          ObservableList list=FXCollections.observableArrayList(gs.viewGuiders());
          guideCodeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
          lnameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
          nameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
          dateCol.setCellValueFactory(new PropertyValueFactory<>("workDate"));
          AlocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
          guideTable.setItems(list);
    }

    @FXML
    private void searchTrip(ActionEvent event) {
              c=new Converter();
            ApplicationService ps=new ApplicationService();
            if(locationTf.getValue()==null||dateTf.getValue()==null){
                 labelNofound.setText("Not Record Found");
            }else{
                ObservableList list=FXCollections.observableArrayList(ps.searchByLocation(locationTf.getValue().getCode(),c.convertToDate(dateTf.getValue())));
                if(list.isEmpty()){
                     labelNofound.setText("Not Record Found");
                }else{
                clientNameCol.setCellValueFactory(new PropertyValueFactory<>("Client"));
                locationDoneCCol.setCellValueFactory(new PropertyValueFactory<>("BookDate"));
                dateTripCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
                requestView.setItems(list);
                }
            
            }
                 
     }

    @FXML
    private void serchAsiggned(ActionEvent event) {
        c=new Converter();
        GuideService gs=new GuideService();
        if(dtAsigned.getValue()==null){
             nFound.setText("Not Found");
        }else{
          ObservableList list=FXCollections.observableArrayList(gs.viewAsigned(c.convertToDate(dtAsigned.getValue())));
          if(list.isEmpty()){
             nFound.setText("Not Found");
          }else{
            guideCodeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
            lnameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("workDate"));
            AlocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
            guideTable.setItems(list);
          
          }
        }
    }

    @FXML
    private void searchGuider(ActionEvent event) {
         c=new Converter();
        GuideService gs=new GuideService();
        if(ddatePicker.getValue()==null){
           nFound.setText("Not Found"); 
        }else{
          ObservableList list=FXCollections.observableArrayList(gs.viewNotAsigned(c.convertToDate(ddatePicker.getValue())));
          if(list.isEmpty()){
             nFound.setText("Not Found");
          }else{
            guideCodeCol.setCellValueFactory(new PropertyValueFactory<>("Code"));
            lnameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("workDate"));
            AlocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
            guideTable.setItems(list);
          
          }
        }
    }
}
