/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxwithjdbc3.controller.EvaluationControl;
import javafxwithjdbc3.model.Evaluation;

/**
 *
 * @author Fadi
 */
public class RightPane5 extends VBox{
    
    HBox searchPane = new HBox();
    TextField txtSearch = new TextField();
    Button btnSearch= new Button("Search");
      Button btnLogout= new Button("logout");
        Button btnReclamation= new Button("Reclamation");

     TableView<Evaluation> table = new TableView<Evaluation>();
    
    EvaluationControl uc2 = new EvaluationControl();
    
    
    
    
    public RightPane5(){
        
        TableColumn<Evaluation,Integer> columnId= new TableColumn("ID");
           TableColumn<Evaluation,String> columnTitre= new TableColumn("Service");
              TableColumn<Evaluation,String> columnSujet= new TableColumn("Avis");
                 TableColumn<Evaluation,String> columnDescription= new TableColumn("Note");
                    TableColumn<Evaluation,String> columnEtat= new TableColumn("Rating");
             
                 
                 columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                    columnTitre.setCellValueFactory(new PropertyValueFactory<>("service"));
                       columnSujet.setCellValueFactory(new PropertyValueFactory<>("avis"));
                          columnDescription.setCellValueFactory(new PropertyValueFactory<>("note"));
                              columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                          
                      
                            columnTitre.prefWidthProperty().bind(table.widthProperty().divide(4));
                              columnSujet.prefWidthProperty().bind(table.widthProperty().divide(4));
                                columnDescription.prefWidthProperty().bind(table.widthProperty().divide(4));
                                    columnEtat.prefWidthProperty().bind(table.widthProperty().divide(4));
                          
        table.getColumns().addAll(columnTitre,columnSujet,columnDescription,columnEtat);
                          //System.out.println(uc.getAllEvaluations());
        table.setItems(uc2.getAllEvaluations());
                          
    searchPane.getChildren().addAll(txtSearch,btnSearch);
    searchPane.setSpacing(20);
    searchPane.setPadding(new Insets(20));
     this.setPadding(new Insets(20));
    
     
     searchPane.getChildren().addAll(btnReclamation,btnLogout);
     searchPane.setAlignment(Pos.TOP_RIGHT);   
     btnLogout.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
         btnReclamation.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
     
           btnLogout.setPrefWidth(150);
      btnReclamation.setPrefWidth(150);
         
    
    this.getChildren().addAll(searchPane,table);
    
    table.setOnMouseClicked(e->{
    Evaluation evaluation = table.getSelectionModel().getSelectedItem();
   
    JavaFxwithJdbc5.leftpane5.tempId1=evaluation.getId();
    
    
    JavaFxwithJdbc5.leftpane5.txtEtat1.setText(evaluation.getEtat()+"");
  
    
    });
    
    btnSearch.setOnMouseClicked(e->{
    this.table.setItems(uc2.search(txtSearch.getText()));
    
    });
    
    
    btnReclamation.setOnMouseClicked(e->{
     Stage stage = (Stage) javafxwithjdbc3.JavaFxwithJdbc5.main.getScene().getWindow();
            // Stage      stage = (Stage)login.LoginController.usernameField.getScene().getWindow();

            javafxwithjdbc2.JavaFxwithJdbc3.main.getChildren().addAll(javafxwithjdbc2.JavaFxwithJdbc3.leftpane2, javafxwithjdbc2.JavaFxwithJdbc3.rightPane2);
            Scene scene5 = new Scene(javafxwithjdbc2.JavaFxwithJdbc3.main, 800, 600);
            //        scene1 = javafxwithjdbc2.JavaFxwithJdbc2.main.getScene();
            stage.setScene(scene5);
    
    });
    
    
    }
}

