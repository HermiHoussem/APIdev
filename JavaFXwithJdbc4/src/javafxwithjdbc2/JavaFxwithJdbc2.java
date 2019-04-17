/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 *
 * @author Fadi
 */
public class JavaFxwithJdbc2 extends Application {
    
  
  public  static LeftPane leftpane = new LeftPane();
  public  static RightPane rightPane = new RightPane();
   public static HBox main = new HBox();
    
    public static void main(String[] args) {
        // TODO code application logic here
         launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      
        
        
       
        main.getChildren().addAll(leftpane , rightPane);
        
       leftpane.prefWidthProperty().bind(main.widthProperty().divide(3));
       rightPane.prefWidthProperty().bind(main.widthProperty().subtract(main.widthProperty().divide(3)));
       
     Scene    scene = new Scene(main , 800 , 600); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
