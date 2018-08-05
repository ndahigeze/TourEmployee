/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControl;

import POJO.Guide;
import POJO.Location;
import POJO.LocationServicePackage;
import Services.Converter;
import Services.GuideService;
import Services.LocationService;
import Services.ServicePackage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ndahigeze
 */
public class CreateTripController implements Initializable {

    @FXML
    private JFXTextField lTf;
    @FXML
    private JFXTextField ldTf;
    @FXML
    private JFXButton recordBTN;
    @FXML
    private JFXTextField tripNameTf;
    @FXML
    private JFXButton SearchTripBTN;
    @FXML
    private TableView<Location> tripView;
    @FXML
    private TableColumn<Location, String> tripNameCol;
    @FXML
    private TableColumn<Location, String> tripDesCol;
    @FXML
    private JFXButton refreshTripBTN;
    @FXML
    private TableView<LocationServicePackage> serviceView;
    @FXML
    private TableColumn<LocationServicePackage, String> stripNameCol;
    @FXML
    private TableColumn<LocationServicePackage, String> serviceNameCol;
    @FXML
    private TableColumn<LocationServicePackage, Number> amountCol;
    @FXML
    private JFXComboBox<Location> slTf;
    @FXML
    private JFXTextField nTf;
    @FXML
    private JFXTextField saTf;
    @FXML
    private JFXButton saveServiceBTN;
    LocationService ls;
    @FXML
    private Label lConfLabel;
    @FXML
    private Label confMesage;
    Converter c;
    @FXML
    private Label searchLabel;
    ServicePackage lp;
    @FXML
    private JFXButton updateBTN;
    /**
     * Initializes the controller class.
     */
    private int sCode;
    private int code;
    @FXML
    private TableColumn<Location,Integer> locationCode;
    @FXML
    private TableColumn<LocationServicePackage, Integer> serviceCode;
    @FXML
    private JFXButton updateServiceBTN;
    @FXML
    private JFXTextField gfnTf;
    @FXML
    private JFXTextField glnTf;
    @FXML
    private JFXButton recordGuideBTN;
    @FXML
    private Label labelG;
    @FXML
    private JFXTextField dlTf;
    @FXML
    private JFXButton deleteLocationBTN;
    @FXML
    private JFXButton deleteServiceBTN;
    @FXML
    private JFXTextField dSTf;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateComb();
        viewLocation();
        viewService();
    }    

    @FXML
    private void searchTrip(ActionEvent event) {
          ls=new LocationService();
          List<Location> li=ls.viewByName(tripNameTf.getText());
         ObservableList list=FXCollections.observableArrayList(li);
      if(list.isEmpty()){
       searchLabel.setText("No record Found !");
      }else{
      tripNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
      tripDesCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
      locationCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
       tripView.setItems(list);
       int id=0;
       for(Location l: li){
         id=l.getCode();
       }
       ServiceviewByLocation(id);
      }
    }

    @FXML
    private void tripClick(MouseEvent event) {
      lTf.setText(tripView.getSelectionModel().getSelectedItem().getName());
      ldTf.setText(tripView.getSelectionModel().getSelectedItem().getDescription());
      code=tripView.getSelectionModel().getSelectedItem().getCode();
       ServiceviewByLocation(code);
    }

    @FXML
    private void refreshTripTableBTN(ActionEvent event) {
       viewLocation();
        viewService();
    }

    @FXML
    private void serviceClick(MouseEvent event) {
     nTf.setText(serviceView.getSelectionModel().getSelectedItem().getServiceName());
     saTf.setText(serviceView.getSelectionModel().getSelectedItem().getAmount()+"");
     sCode=serviceView.getSelectionModel().getSelectedItem().getCode();
     
    }

    void ServiceviewByLocation(int id){
     lp=new ServicePackage();
       ObservableList list=FXCollections.observableArrayList(lp.findByLocation(id));
        serviceCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        stripNameCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("ServiceName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        amountCol.setCellFactory(col -> 
                new TableCell<LocationServicePackage, Number>() {
               @Override
               public void updateItem(Number Amount, boolean empty) {
                   super.updateItem(Amount, empty);
                   if (empty) {
                       setText(null);
                   } else {
                      setText("RWF "+NumberFormat.getInstance(Locale.US).format(Amount));
                   }
               }
           });
        serviceView.setItems(list);
    } 
    void viewLocation(){
       ls=new LocationService();
      ObservableList list=FXCollections.observableArrayList(ls.viewLocation());
       locationCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
      tripNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
      tripDesCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
       tripView.setItems(list);
    }
    void viewService(){
         lp=new ServicePackage();
       ObservableList list=FXCollections.observableArrayList(lp.viewTripService());
        serviceCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        stripNameCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("ServiceName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("Amount"));
         amountCol.setCellFactory(col -> 
                new TableCell<LocationServicePackage, Number>() { 
               @Override
               public void updateItem(Number Amount, boolean empty) {
                   super.updateItem(Amount, empty);
                   if (empty) {
                       setText(null);
                   } else {
                      setText("RWF "+NumberFormat.getInstance(Locale.US).format(Amount));
                   }
               }
           });
        serviceView.setItems(list);
    }
    
    @FXML
    private void saveService(ActionEvent event) {
        c=new Converter();
        ServicePackage tc=new ServicePackage();
       if(!checServiceEmpty()){
           LocationServicePackage sp=new LocationServicePackage();
           sp.setServiceName(nTf.getText());
           sp.setLocation(slTf.getValue());
           sp.setAmount(c.toDouble(saTf.getText()));
           tc.createTripService(sp);
           confMesage.setText("Service Created");
           cleanService();
       }else{
         confMesage.setText("Some Field Are Blank");
       }
       viewService();
    }

    public void populateComb(){
        slTf.valueProperty().setValue(null);
        ls=new LocationService();
        List<Location> list=ls.viewLocation();
        list.forEach((l) -> {
            slTf.getItems().add(l);
        });
    }
    @FXML
    private void recordLocation(ActionEvent event) {
       ls=new LocationService();
        if(!checkEmpty()){
           Location l=new Location();
           l.setName(lTf.getText());
           l.setDescription(ldTf.getText());
            lConfLabel.setText(ls.createLocation(l));
             cleanField();
       }else{
         lConfLabel.setText("All above Field Are Required");
        }  
        slTf.valueProperty().setValue(null);
        populateComb();
         viewLocation();
        
    }
    
    public boolean checkEmpty(){
     return lTf.getText().isEmpty()||
            ldTf.getText().isEmpty();
    }
    void cleanField(){
       lTf.setText("");
            ldTf.setText("");
    }
    public boolean checServiceEmpty(){
       return  nTf.getText().isEmpty()||
               saTf.getText().isEmpty();
    }
    void cleanService(){
               nTf.setText("");
               saTf.setText("");
    }
    @FXML
    private void updatelocation(ActionEvent event) {
       ls=new LocationService();
        if(lTf.getText().isEmpty()||ldTf.getText().isEmpty()){
          lConfLabel.setText("Please fill All fiel To update");  
        }else{
             Location l=new Location();
            l.setCode(code);
            l.setName(lTf.getText());
            l.setDescription(ldTf.getText());
            lConfLabel.setText(ls.updateList(l));
             cleanField();
        }
    }

    @FXML
    private void updateService(ActionEvent event) {
        c=new Converter();
        lp=new ServicePackage();
        if(nTf.getText().isEmpty()||saTf.getText().isEmpty()||slTf.getValue()==null){
            confMesage.setText("All field are required to update");
        }else{
            LocationServicePackage sl=new LocationServicePackage();
            sl.setAmount(c.toDouble(saTf.getText()));
            sl.setCode(sCode);
            sl.setLocation(slTf.getValue());
            sl.setServiceName(nTf.getText());
            confMesage.setText(lp.serviceUpdater(sl));
             cleanService();
        }
    }

    @FXML
    private void recordGuide(ActionEvent event) {
        
        if(check()){
            labelG.setText("All fIELd ARE Required");
        }else{
           Guide g=new Guide();
           g.setFirstName(gfnTf.getText());
           g.setLastName(glnTf.getText());
           GuideService gs=new GuideService();
           labelG.setText(gs.createLocation(g));
           clearField();
        }
    }
    boolean check(){
     return gfnTf.getText().isEmpty()||glnTf.getText().isEmpty();
    }
    void clearField(){
       gfnTf.setText("");
               glnTf.setText("");
    }

    @FXML
    private void deleteLocation(ActionEvent event) {
        Location l=new Location();
        l.setCode(Integer.parseInt(dlTf.getText()));
        LocationService ls=new LocationService();
        ls.delete(l);
    }

    @FXML
    private void deleteService(ActionEvent event) {
        ServicePackage s=new ServicePackage();
        LocationServicePackage p=new LocationServicePackage();
        p.setCode(Integer.parseInt(dSTf.getText()));
        dSTf.setText(s.serviceDelete(p));
    }

}
