/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import entites.ClientA;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ClientAServices;
import services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class AfficherClientController implements Initializable {

    @FXML
    private TableView<ClientA> tabCli;
    @FXML
    private Button cnt;
    @FXML
    private TableColumn<ClientA, ?> id;
    @FXML
    private TableColumn<ClientA, ?> nom;
    @FXML
    private TableColumn<ClientA, ?> prenom;
    @FXML
    private TableColumn<ClientA, ?> tel;
    @FXML
    private TableColumn<ClientA, ?> email;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        ClientAServices p=new ClientAServices();
        tabCli.setItems(p.afficherCli());
        ObservableList<ClientA> data = FXCollections.observableArrayList();
        data = p.afficherCli();
        FilteredList<ClientA> filterdData = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filterdData.setPredicate((Predicate<? super ClientA>) exp -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    if (String.valueOf(exp.getId()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(exp.getCin()).contains(newValue)) {
                        return true;
                    } else if (exp.getNom().contains(newValue)) {
                        return true;
                    } else if (exp.getPrenom().contains(newValue)) {
                        return true;
                    } else if (exp.getTel().contains(newValue)) {
                        return true;
                    } 

//                    else if(cotisa.getPrenom().contains(newValue)){
//                        return true;
//                    }
                    return false;
                });
            }));
            SortedList<ClientA> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(tabCli.comparatorProperty());
            tabCli.setItems(sortedData);

        });
    }    

    @FXML
    private void cnt(MouseEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assurance/AjouterContrat.fxml"));
        try {
            Parent root = loader.load();
            AjouterContratController apc=loader.getController();
            apc.setIdCli(tabCli.getSelectionModel().getSelectedItem().getId());
          
            apc.setNomCli(tabCli.getSelectionModel().getSelectedItem().getNom());
            apc.setPrenomCli(tabCli.getSelectionModel().getSelectedItem().getPrenom());
            apc.setCinCli(tabCli.getSelectionModel().getSelectedItem().getCin());
            
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tabCli(MouseEvent event) {
    }
    
}
