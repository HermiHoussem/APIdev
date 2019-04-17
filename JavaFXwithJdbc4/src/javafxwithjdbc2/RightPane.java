package javafxwithjdbc2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxwithjdbc2.controller.ReclamationControl;
import javafxwithjdbc2.model.Reclamation;
import static jdk.nashorn.tools.ShellFunctions.input;
import login.LoginController;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fadi
 */
public class RightPane extends VBox{
    
    HBox searchPane = new HBox();
     TextField txtSearch = new TextField();
    Button btnSearch= new Button("Search");
    Button btnLogout= new Button("logout");
        Button btnEvaluation= new Button("Evaluation");
     String[] possiblewords = {"fadi" ,"fadi1" ,"fadi2" };
    
     AutoCompletionBinding acbLogin = TextFields.bindAutoCompletion(txtSearch, possiblewords);
    
    public static TableView<Reclamation> table = new TableView<>();
    
    ReclamationControl uc = new ReclamationControl();
    
      Image image = new Image("rec1.png",450,350,false,false);
   ImageView iv = new ImageView();
    
    
    public RightPane(){
        
     
           TableColumn<Reclamation,String> columnTitre= new TableColumn("Titre");
              TableColumn<Reclamation,String> columnSujet= new TableColumn("Sujet");
                 TableColumn<Reclamation,String> columnDescription= new TableColumn("Description");
                 
             
                 
                 
                    columnTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
                       columnSujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
                          columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        
                        
                            columnTitre.prefWidthProperty().bind(table.widthProperty().divide(3));
                              columnSujet.prefWidthProperty().bind(table.widthProperty().divide(3));
                                columnDescription.prefWidthProperty().bind(table.widthProperty().divide(3));
                          
                     table.setPrefHeight(100);
                     table.setPrefWidth(200);
        table.getColumns().addAll(columnTitre,columnSujet,columnDescription);
                       //   System.out.println(uc.getAllReclamations2());
        table.setItems(uc.getAllReclamations2());
                          
    //searchPane.getChildren().addAll(txtSearch,btnSearch);
    searchPane.setSpacing(20);
    searchPane.setPadding(new Insets(20));
     this.setPadding(new Insets(20));
     searchPane.getChildren().addAll(btnEvaluation,btnLogout);
     searchPane.setAlignment(Pos.TOP_RIGHT);   
      iv.setImage(image);
      btnLogout.setPrefWidth(150);
      btnEvaluation.setPrefWidth(150);
  
      
        
        btnLogout.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
         btnEvaluation.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
  
     
    this.getChildren().addAll(searchPane,table,iv);
    
    table.setOnMouseClicked(e->{
    Reclamation reclamation = table.getSelectionModel().getSelectedItem();
    JavaFxwithJdbc2.leftpane.txtTitre.setText(reclamation.getTitre());
    JavaFxwithJdbc2.leftpane.txtSujet.setText(reclamation.getSujet());
    JavaFxwithJdbc2.leftpane.txtDescription.setText(reclamation.getDescription());

    
    
    });
    
    btnSearch.setOnMouseClicked(e->{
    this.table.setItems(uc.search(txtSearch.getText()));
    
    });
      
    btnLogout.setOnMouseClicked(e->{
  Stage stage = (Stage)javafxwithjdbc2.JavaFxwithJdbc2.main.getScene().getWindow();
               // Stage      stage = (Stage)login.LoginController.usernameField.getScene().getWindow();
               Object root = null;
         try {
             root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
               stage.setScene (new Scene ((Parent) root));  
        }
        
catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        });
      btnEvaluation.setOnMouseClicked(e->{
        Stage stage = (Stage)javafxwithjdbc2.JavaFxwithJdbc2.main.getScene().getWindow();
               // Stage      stage = (Stage)login.LoginController.usernameField.getScene().getWindow();
              
         javafxwithjdbc3.JavaFxwithJdbc4.main.getChildren().addAll(javafxwithjdbc3.JavaFxwithJdbc4.leftpane4 , javafxwithjdbc3.JavaFxwithJdbc4.rightPane4);
         Scene scene2 = new Scene(javafxwithjdbc3.JavaFxwithJdbc4.main ,800,600);
 //        scene1 = javafxwithjdbc2.JavaFxwithJdbc2.main.getScene();
         stage.setScene (scene2 );  
        
 
       
        });
    
    
    }
}

