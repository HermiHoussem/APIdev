/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entites.Contrat;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ContratServices;
import services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class AjouterContratController implements Initializable {
public int k=0;
    @FXML
    private DatePicker dateMec;
    @FXML
    private ComboBox<String> usage;
    @FXML
    private TextField valeur;
    @FXML
    private ComboBox<String> puissance;
    @FXML
    private TextField idCli;
    @FXML
    private TextField NomCli;
    @FXML
    private TextField PrenomCli;
    @FXML
    private TextField cinCli;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private CheckBox vol;
    @FXML
    private TextField prix;
    @FXML
    private CheckBox Assistance;
    @FXML
    private CheckBox incendie;
    @FXML
    private CheckBox tout;
    @FXML
    private CheckBox Bris;
    @FXML
    private TextField id;

    public void setK(int k) {
        this.k = k;
    }

    public void setDateMec(LocalDate d) {
        this.dateMec.setValue(d);
    }

    public void setUsage(String d) {
        this.usage.setValue(d);
    }

    public void setValeur(String valeur) {
        this.valeur.setText(valeur);
    }

    public void setPuissance(String puissance) {
        this.puissance.setValue(puissance);
    }

   

   

   
    public void setDatedebut(LocalDate datedebut) {
        this.datedebut.setValue(datedebut);
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin.setValue(datefin);
    }

    public void setVol(int vol) {
        if (vol==1) {
            this.vol.setSelected(true);
        }
        
    }

    public void setPrix(double prix) {
        this.prix.setText(String.valueOf(prix));
    }

    public void setAssistance(int Assistance) {
        if (Assistance==1) {
            this.Assistance.setSelected(true);
        }
    }

    public void setIncendie(int incendie) {
        if (incendie==1) {
            this.incendie.setSelected(true);
        }
    }

    

    public void setBris(int Bris) {
       if (Bris==1) {
            this.Bris.setSelected(true);
        }
    }

    public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    public void setAjouter(String ajouter) {
        this.ajouter.setText(ajouter);
    }

   

    public void setIdCli(int idCli) {
        this.idCli.setText(String.valueOf(idCli));;
    }

    public void setNomCli(String NomCli) {
        this.NomCli.setText(NomCli); 
    }

    public void setPrenomCli(String PrenomCli) {
        this.PrenomCli.setText(PrenomCli);
    }

    public void setCinCli(int cinCli) {
        this.cinCli.setText(String.valueOf(cinCli));
    }
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String>e=FXCollections.observableArrayList("Privé","Professionnel");
        usage.setValue("Privé");
        usage.setItems(e);
        ObservableList<String>p=FXCollections.observableArrayList("3CV","4CV","5CV","9CV");
        puissance.setValue("3CV");
        puissance.setItems(p);
        prix.setText("500");
        
    }    

    @FXML
    private void tt(ActionEvent event) {
        if (k==0) {
        vol.setSelected(true);
        incendie.setSelected(true);
        Assistance.setSelected(true);
        Bris.setSelected(true);
        k=1;
        }else{
            vol.setSelected(false);
        incendie.setSelected(false);
        Assistance.setSelected(false);
        Bris.setSelected(false);
        k=0;
        }
    }

    @FXML
    private void ajout(MouseEvent event) {
        ContratServices  CP=new ContratServices();
         Contrat P=new Contrat();
         int idc=Integer.parseInt(idCli.getText());
         
         
         double p=Double.parseDouble(prix.getText());
         
        if (ajouter.getText().equals("Modifier")) {
           P.setDate_MEC(dateMec.getValue());   
           P.setUsage_(usage.getValue());
           P.setValeur(valeur.getText());
           P.setPuissance(puissance.getValue());
           P.setId_client(idc);
           P.setDate_debut(datedebut.getValue());
           P.setDate_fin(datefin.getValue());
            if (vol.isSelected()) {
                P.setVol(1);
            }
            if (Bris.isSelected()) {
                P.setBris(1);
            }
            if (Assistance.isSelected()) {
                P.setAssistance(1);
            }
            if (incendie.isSelected()) {
                P.setIncendie(1);
            }
            P.setPrix(p);
          int idd=Integer.parseInt(id.getText());
                    CP.modifierContrat(idd, P);
                  
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
           
           P.setDate_MEC(dateMec.getValue());   
           P.setUsage_(usage.getValue());
           P.setValeur(valeur.getText());
           P.setPuissance(puissance.getValue());
           P.setId_client(idc);
           P.setDate_debut(datedebut.getValue());
           P.setDate_fin(datefin.getValue());
            if (vol.isSelected()) {
                P.setVol(1);
            }
            if (Bris.isSelected()) {
                P.setBris(1);
            }
            if (Assistance.isSelected()) {
                P.setAssistance(1);
            }
            if (incendie.isSelected()) {
                P.setIncendie(1);
            }
            P.setPrix(p);
            CP.ajouterContrat(P);
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
