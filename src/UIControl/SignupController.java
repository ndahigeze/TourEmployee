/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControl;

import UIControl.Inits.SignInit;
import UIControl.Inits.EmployeeUI;
import POJO.Privilege;
import POJO.Users;
import Services.Converter;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ndahigeze
 */
public class SignupController implements Initializable {

    @FXML
    private TextField sunTf;
    @FXML
    private PasswordField spTf;
    @FXML
    private TextField fTf;
    @FXML
    private TextField lnTf;
    @FXML
    private TextField unTf;
    @FXML
    private TextField eTf;
    @FXML
    private PasswordField pTf;
    @FXML
    private PasswordField cpTf;
     @FXML
    private JFXButton signUp;
         @FXML
    private JFXButton signInBTN;
      
    /**
     * Initializes the controller class.
     */
         UserService us ;
          UserService s;
          Converter c;
    @FXML
    private JFXComboBox<String> gComb;
    @FXML
    private Label infoLabel;
    @FXML
    private Label wrongL;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combElement();
    } 
    
    public void createAccount(){
        String responce="";
        us=new UserService();
        Privilege p=new Privilege();
        p.setCode(2);
        Converter c=new Converter();
        Users u=new Users();
      if(!checkEmpty()){
         u.setFirstName(fTf.getText());
         u.setLastName( lnTf.getText());
         u.setEmainl( eTf.getText());
         u.setUserName( unTf.getText());
         u.setPassword(c.md5(pTf.getText()));
         u.setRegDate(new Date());
         u.setGender(gComb.getValue());
         u.setPrivilege(p);
           infoLabel.setText(us.createUser(u));
            clean();
      }else{
        infoLabel.setText("All Field are Required To Make Email");  
      }
    }
   
    @FXML
    void signIn(ActionEvent event) throws Exception {
     s=new UserService();
     c=new Converter();
     if(!sunTf.getText().isEmpty()||!spTf.getText().isEmpty()){
     List<Users> l=s.login(sunTf.getText(),spTf.getText());
             if(!l.isEmpty()){
                  new EmployeeUI().start(new Stage());
                  SignInit.close();
             }else{
               wrongL.setText("Wrog Password or username");
             }
     }else{
        wrongL.setText("Wrog Password or username");
     }
    }

    @FXML
    void signUp(ActionEvent event) {
                String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);         
        Matcher matcher = pattern.matcher(eTf.getText()); 
       if(match()){
           if(matcher.matches()){
            createAccount();
           }else{
              infoLabel.setText("!Put Avalid Email");
           }
       }else{
         infoLabel.setText("!Password not Match");
       }
    }
    
     public boolean checkEmpty(){
      return 
              fTf.getText().isEmpty()||
              lnTf.getText().isEmpty()||
              unTf.getText().isEmpty()||
              eTf.getText().isEmpty()||
              pTf.getText().isEmpty()||
              cpTf.getText().isEmpty()||
              gComb.getValue().isEmpty();
    }
    void clean(){
              fTf.setText("");
              lnTf.setText("");
              unTf.setText("");
              eTf.setText("");
              pTf.setText("");
              cpTf.setText("");
              gComb.setValue("");
    }
   public boolean match(){
    c=new Converter();
    return c.md5(cpTf.getText()).equals(c.md5(pTf.getText()));
   }
       public void combElement(){
       
           gComb.getItems().addAll("Male","Female");
     }
       
       public String checkSave(String username){
         s=new UserService();
          List<Users>  l=s.findByUsername(username);
         if(l.isEmpty()){
             return "Account Created Successfull";
         }else{
             return "Username is Already Taken, try to find Different One";
         }
       }
       
       void clearField(){
            fTf.setText("");
              lnTf.setText("");
              unTf.setText("");
              eTf.setText("");
              pTf.setText("");
              cpTf.setText("");
              gComb.setValue("");
       }
}
