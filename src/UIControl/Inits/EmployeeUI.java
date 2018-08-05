/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControl.Inits;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Ndahigeze
 */
public class EmployeeUI extends Application {
     public static Stage employeeUi;
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/UIFxml/EmployeeMainUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        employeeUi=stage;
    }
    public static void close(){
       employeeUi.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
