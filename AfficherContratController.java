/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import entites.Contrat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ContratServices;
import services.ProduitServices;
import services.sendMail;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class AfficherContratController implements Initializable {

    @FXML
    private Button modif;
    @FXML
    private Button sup;
    @FXML
    private TableView<Contrat> tabcnt;
    @FXML
    private TableColumn<Contrat, ?> idc;
    @FXML
    private TableColumn<Contrat, ?> idcl;
    @FXML
    private TableColumn<Contrat, ?> dated;
    @FXML
    private TableColumn<Contrat, ?> datef;
    @FXML
    private TableColumn<Contrat, ?> prix;
    @FXML
    private Button notif;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    public static AnchorPane holderPane1;
    @FXML
    private TextField recherche;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
        idc.setCellValueFactory(new PropertyValueFactory<>("id"));
        idcl.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        dated.setCellValueFactory(new PropertyValueFactory<>("Date_MEC"));
        datef.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
        prix.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ContratServices p=new ContratServices();
        tabcnt.setItems(p.afficherContrat());
        ObservableList<Contrat> data = FXCollections.observableArrayList();
        data = p.afficherContrat();
        FilteredList<Contrat> filterdData = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filterdData.setPredicate((Predicate<? super Contrat>) exp -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    if (String.valueOf(exp.getId()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(exp.getPrix()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(exp.getDate_MEC()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(exp.getDate_debut()).contains(newValue)) {
                        return true;
                    } else if (String.valueOf(exp.getDate_fin()).contains(newValue)) {
                        return true;
                    } 

//                    else if(cotisa.getPrenom().contains(newValue)){
//                        return true;
//                    }
                    return false;
                });
            }));
            SortedList<Contrat> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(tabcnt.comparatorProperty());
            tabcnt.setItems(sortedData);

        });
    }    

    @FXML
    private void md(MouseEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assurance/AjouterContrat.fxml"));
       try {
            Parent root = loader.load();
            AjouterContratController P=loader.getController();
   
            P.setDateMec(tabcnt.getSelectionModel().getSelectedItem().getDate_MEC()
            );   
           P.setUsage(tabcnt.getSelectionModel().getSelectedItem().getUsage_());
           P.setValeur(tabcnt.getSelectionModel().getSelectedItem().getValeur());
           P.setPuissance(tabcnt.getSelectionModel().getSelectedItem().getPuissance());
           P.setIdCli(tabcnt.getSelectionModel().getSelectedItem().getId_client());
           P.setDatedebut(tabcnt.getSelectionModel().getSelectedItem().getDate_debut());
           P.setDatefin(tabcnt.getSelectionModel().getSelectedItem().getDate_fin());
           P.setVol(tabcnt.getSelectionModel().getSelectedItem().getVol());
           P.setBris(tabcnt.getSelectionModel().getSelectedItem().getBris());
           P.setIncendie(tabcnt.getSelectionModel().getSelectedItem().getIncendie());
           P.setAssistance(tabcnt.getSelectionModel().getSelectedItem().getAssistance());
           P.setId(tabcnt.getSelectionModel().getSelectedItem().getId());
           P.setPrix(tabcnt.getSelectionModel().getSelectedItem().getPrix());
           
            
            P.setAjouter("Modifier");
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void sup(MouseEvent event) {
         ContratServices p=new ContratServices();
        p.supprimerContrat(tabcnt.getSelectionModel().getSelectedItem().getId());
        tabcnt.setItems(p.afficherContrat());
    }

    @FXML
    private void notif(MouseEvent event) {
        ContratServices cs=new ContratServices();
        String k=cs.getMail(tabcnt.getSelectionModel().getSelectedItem().getId_client());
        sendMail s=new sendMail();
        s.send(k);
    }
    
}
