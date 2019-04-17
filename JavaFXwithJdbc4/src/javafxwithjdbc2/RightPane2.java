/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc2;

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
import javafxwithjdbc2.controller.ReclamationControl;
import javafxwithjdbc2.model.Reclamation;

/**
 *
 * @author Fadi
 */
public class RightPane2 extends VBox {

    HBox searchPane = new HBox();
    TextField txtSearch = new TextField();
    Button btnSearch = new Button("Search");

    Button btnLogout = new Button("logout");
    Button btnEvaluation = new Button("Evaluation");

    TableView<Reclamation> table = new TableView<Reclamation>();

    ReclamationControl uc2 = new ReclamationControl();

    public RightPane2() {

        TableColumn<Reclamation, Integer> columnId = new TableColumn("ID");
        TableColumn<Reclamation, String> columnTitre = new TableColumn("Titre");
        TableColumn<Reclamation, String> columnSujet = new TableColumn("Sujet");
        TableColumn<Reclamation, String> columnDescription = new TableColumn("Description");
        TableColumn<Reclamation, String> columnEtat = new TableColumn("Etat");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        columnSujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        columnTitre.prefWidthProperty().bind(table.widthProperty().divide(4));
        columnSujet.prefWidthProperty().bind(table.widthProperty().divide(4));
        columnDescription.prefWidthProperty().bind(table.widthProperty().divide(4));
        columnEtat.prefWidthProperty().bind(table.widthProperty().divide(4));

        table.getColumns().addAll(columnTitre, columnSujet, columnDescription, columnEtat);
        //System.out.println(uc.getAllReclamations());
        table.setItems(uc2.getAllReclamations());

        searchPane.getChildren().addAll(txtSearch, btnSearch);
        searchPane.setSpacing(20);
        searchPane.setPadding(new Insets(20));
        this.setPadding(new Insets(20));
        searchPane.getChildren().addAll(btnEvaluation, btnLogout);
        searchPane.setAlignment(Pos.TOP_RIGHT);

        btnLogout.setPrefWidth(150);
        btnEvaluation.setPrefWidth(150);

        btnLogout.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
        btnEvaluation.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");

        this.getChildren().addAll(searchPane, table);

        table.setOnMouseClicked(e -> {
            Reclamation reclamation = table.getSelectionModel().getSelectedItem();

            JavaFxwithJdbc3.leftpane2.tempId1 = reclamation.getId();

            JavaFxwithJdbc3.leftpane2.txtEtat1.setText(reclamation.getEtat() + "");

        });

        btnSearch.setOnMouseClicked(e -> {
            this.table.setItems(uc2.search(txtSearch.getText()));

        });

        btnEvaluation.setOnMouseClicked(e -> {
            Stage stage = (Stage) javafxwithjdbc2.JavaFxwithJdbc3.main.getScene().getWindow();
            // Stage      stage = (Stage)login.LoginController.usernameField.getScene().getWindow();

            javafxwithjdbc3.JavaFxwithJdbc5.main.getChildren().addAll(javafxwithjdbc3.JavaFxwithJdbc5.leftpane5, javafxwithjdbc3.JavaFxwithJdbc5.rightPane5);
            Scene scene4 = new Scene(javafxwithjdbc3.JavaFxwithJdbc5.main, 800, 600);
            //        scene1 = javafxwithjdbc2.JavaFxwithJdbc2.main.getScene();
            stage.setScene(scene4);

        });

    }
}
