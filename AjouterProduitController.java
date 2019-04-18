/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import services.ProduitServices;
import utils.ConnexionDB;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField uploadpath;
    @FXML
    private Button upload;
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private Label nominv;
    @FXML
    private Label descriinv;
    @FXML
    private Label imageinv;

    public void setUploadpath(String uploadpath) {
        this.uploadpath.setText(uploadpath);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

   

    public void setAjouter(String ajouter) {
        this.ajouter.setText(ajouter);
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }
    @FXML
    private ComboBox<String> cat;
    @FXML
    private Button ajouter;
   
    @FXML
    private Text titre;
    @FXML
    private TextField id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String>categorie=FXCollections.observableArrayList("Assurance vie","Assurance Voiture","Assurance voyage");
        cat.setValue("Assurance voiture");
        cat.setItems(categorie);
        nominv.setVisible(false);
        descriinv.setVisible(false);
        imageinv.setVisible(false);
    }  
        

    @FXML
    private void upload(javafx.scene.input.MouseEvent event) {
        ConnexionDB d= new ConnexionDB();
        d.filen();
        String path=d.getpath();
        if (path==null) {
            
        }else{
            uploadpath.setText(path);
        }
    }

    @FXML
    private void ajouter(javafx.scene.input.MouseEvent event) {
         ProduitServices CP=new ProduitServices();
         Produit P=new Produit();
         
         if (nom.getText().equals("")) {
            
            nominv.setVisible(true);
        }
         if (desc.getText().equals("")) {
            descriinv.setVisible(true);
        }
         if (uploadpath.getText().equals("")) {
            imageinv.setVisible(true);
        }
         else{
             nominv.setVisible(false); 
             descriinv.setVisible(false); 
             imageinv.setVisible(false); 
        
        if (ajouter.getText().equals("Modifier")) {
             P.setNom(nom.getText());   
           P.setDescription(desc.getText());
         P.setCategorie(cat.getValue());
                    P.setImage(uploadpath.getText());
                    int a=Integer.parseInt(id.getText());
                    CP.modifierProduit(a, P);
                  
                    ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assurance/MainBack.fxml"));
             try {
                 Parent root = loader.load();
                 Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
           
          P.setNom(nom.getText());   
           P.setDescription(desc.getText());
         P.setCategorie(cat.getValue());
                    P.setImage(uploadpath.getText());
                    CP.ajouterProduit(P);
                    ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assurance/MainBack.fxml"));
             try {
                 Parent root = loader.load();
                 Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
       
    }
    
}
