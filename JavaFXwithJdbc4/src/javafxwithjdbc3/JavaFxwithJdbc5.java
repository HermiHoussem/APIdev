/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Fadi
 */
public class JavaFxwithJdbc5 extends Application {public static LeftPane5 leftpane5 = new LeftPane5();
 public  static RightPane5 rightPane5 = new RightPane5();
    public static  HBox main = new HBox();
    
    public static void main(String[] args) {
        // TODO code application logic here
         launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      
        
        
      
        main.getChildren().addAll(leftpane5 , rightPane5);
        
       leftpane5.prefWidthProperty().bind(main.widthProperty().divide(3));
       rightPane5.prefWidthProperty().bind(main.widthProperty().subtract(main.widthProperty().divide(3)));
       
        Scene scene = new Scene(main , 800 , 600); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
