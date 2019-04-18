/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import entites.ClientA;
import entites.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<Produit> tabProduit;
    @FXML
    private TableColumn<Produit, ?> idproduit;
    @FXML
    private TableColumn<Produit, ?> nomproduit;
    @FXML
    private TableColumn<Produit, ?> descProduit;
    @FXML
    private TableColumn<Produit, ?> catProduit;
    @FXML
    private TableColumn<Produit, ?> imageProduit;
    @FXML
    private ImageView img;
    @FXML
    private Button supp;
    @FXML
    private Button modif;
    @FXML
    private AnchorPane anchorp;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idproduit.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomproduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
        catProduit.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        imageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
        ProduitServices p=new ProduitServices();
        tabProduit.setItems(p.afficherProduit());
       ObservableList<Produit> data = FXCollections.observableArrayList();
        data = p.afficherProduit();
        FilteredList<Produit> filterdData = new FilteredList<>(data, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filterdData.setPredicate((Predicate<? super Produit>) exp -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    if (String.valueOf(exp.getId()).contains(newValue)) {
                        return true;
                    } else if (exp.getNom().contains(newValue)) {
                        return true;
                    } else if (exp.getCategorie().contains(newValue)) {
                        return true;
                    } else if (exp.getDescription().contains(newValue)) {
                        return true;
                    } 

//                    else if(cotisa.getPrenom().contains(newValue)){
//                        return true;
//                    }
                    return false;
                });
            }));
            SortedList<Produit> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(tabProduit.comparatorProperty());
            tabProduit.setItems(sortedData);

        });
       

    }    

    @FXML
    private void tabProduit(MouseEvent event) {
                try {
            Image imagee = new Image(new FileInputStream(tabProduit.getSelectionModel().getSelectedItem().getImage()));
            img.setImage(imagee);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supp(MouseEvent event) {
        ProduitServices p=new ProduitServices();
        p.supprimerProduit(tabProduit.getSelectionModel().getSelectedItem().getId());
        tabProduit.setItems(p.afficherProduit());
    }

    @FXML
    private void modif(MouseEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assurance/AjouterProduit.fxml"));
        try {
            Parent root = loader.load();
            AjouterProduitController apc=new AjouterProduitController();
            apc.setId(tabProduit.getSelectionModel().getSelectedItem().getId());
            apc.setTitre("Formulaire Modif Produit");
            apc.setNom(tabProduit.getSelectionModel().getSelectedItem().getNom());
            apc.setDesc(tabProduit.getSelectionModel().getSelectedItem().getDescription());
            apc.setUploadpath(tabProduit.getSelectionModel().getSelectedItem().getImage());
            apc.setAjouter("Modifier");
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
