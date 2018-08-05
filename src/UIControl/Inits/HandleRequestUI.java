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
public class HandleRequestUI extends Application{
       public static Stage handleRequest;
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/UIFxml/handleRequest.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        handleRequest=stage;
    }
    public static void close(){
       handleRequest.close();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
