package javafxwithjdbc3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafxwithjdbc3.controller.EvaluationControl;
import javafxwithjdbc3.model.Evaluation;
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
public class RightPane4 extends VBox{
    
    HBox searchPane = new HBox();
     TextField txtSearch = new TextField();
    Button btnSearch= new Button("Search");
      Button btnLogout= new Button("logout");
        Button btnReclamation= new Button("Reclamation");
     String[] possiblewords = {"fadi" ,"fadi1" ,"fadi2" };
    
     AutoCompletionBinding acbLogin = TextFields.bindAutoCompletion(txtSearch, possiblewords);
    
     TableView<Evaluation> table = new TableView<>();
    
    EvaluationControl uc = new EvaluationControl();
    
     Image image = new Image("ratingim.png",450,350,false,false);
   ImageView iv = new ImageView();

    
    
    public RightPane4(){
        
     
           TableColumn<Evaluation,String> columnService= new TableColumn("Service");
              TableColumn<Evaluation,String> columnAvis= new TableColumn("Avis");
                 TableColumn<Evaluation,String> columnNote= new TableColumn("Commentaire");
                      TableColumn<Evaluation,String> columnEtat= new TableColumn("Rating");
             
                 
                 
                    columnService.setCellValueFactory(new PropertyValueFactory<>("service"));
                       columnAvis.setCellValueFactory(new PropertyValueFactory<>("avis"));
                          columnNote.setCellValueFactory(new PropertyValueFactory<>("note"));
                               columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                        
                            columnService.prefWidthProperty().bind(table.widthProperty().divide(4));
                              columnAvis.prefWidthProperty().bind(table.widthProperty().divide(4));
                                columnNote.prefWidthProperty().bind(table.widthProperty().divide(4));
                          columnEtat.prefWidthProperty().bind(table.widthProperty().divide(4));
                     table.setPrefHeight(100);
                     table.setPrefWidth(200);
        table.getColumns().addAll(columnService,columnAvis,columnNote,columnEtat);
                          //System.out.println(uc.getAllEvaluations());
        table.setItems(uc.getAllEvaluations2());
                          
   // searchPane.getChildren().addAll(txtSearch,btnSearch);
    searchPane.setSpacing(20);
    searchPane.setPadding(new Insets(20));
     this.setPadding(new Insets(20));
      searchPane.getChildren().addAll(btnReclamation,btnLogout);
     searchPane.setAlignment(Pos.TOP_RIGHT);   
     btnLogout.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
         btnReclamation.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
     
           btnLogout.setPrefWidth(150);
      btnReclamation.setPrefWidth(150);
         
      iv.setImage(image);
    this.getChildren().addAll(searchPane,table,iv);
    
    table.setOnMouseClicked(e->{
    Evaluation evaluation = table.getSelectionModel().getSelectedItem();
    JavaFxwithJdbc4.leftpane4.txtService.setText(evaluation.getService());
   
 //   JavaFxwithJdbc4.leftpane4.txtNote.setText(evaluation.getNote());

    
    
    });
    
    btnSearch.setOnMouseClicked(e->{
    this.table.setItems(uc.search(txtSearch.getText()));
    
    });
    
    
    btnLogout.setOnMouseClicked(e->{
  Stage stage = (Stage)javafxwithjdbc3.JavaFxwithJdbc4.main.getScene().getWindow();
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
    
    
    
     btnReclamation.setOnMouseClicked(e->{
   
           Stage stage = (Stage)javafxwithjdbc3.JavaFxwithJdbc4.main.getScene().getWindow();
               // Stage      stage = (Stage)login.LoginController.usernameField.getScene().getWindow();
           javafxwithjdbc3.JavaFxwithJdbc4.main.getChildren().addAll(javafxwithjdbc2.JavaFxwithJdbc2.leftpane , javafxwithjdbc2.JavaFxwithJdbc2.rightPane);
           Scene scene3 = new Scene(javafxwithjdbc2.JavaFxwithJdbc2.main ,800,600);
 //        scene1 = javafxwithjdbc2.JavaFxwithJdbc2.main.getScene();
            stage.setScene (scene3 );  
           
        
         
    
    });
    
    
    
    }
}

