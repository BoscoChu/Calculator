
package calculator;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class calculator extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
            
        System.out.println(calculator.class.getClass());
       
       
        Parent root = FXMLLoader.load(getClass().getResource("/calculator/FXML2.fxml")); 


       
  
     
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



 
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
