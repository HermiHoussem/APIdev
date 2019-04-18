/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import entites.Demande_annulation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Demande_annulationService;

/**
 *
 * @author dell user
 */
public class FXMLDocumentController implements Initializable {
    
   
   
     
      @FXML
    private TableView tttttt;
       @FXML
    private TableColumn <Demande_annulation, Integer> id ;
     
    @FXML
    private TableColumn <Demande_annulation, String> nom;
     @FXML
    private TableColumn <Demande_annulation, String> prenom;
      @FXML
    private TableColumn <Demande_annulation, Integer> valeur;
    @FXML
    private Button tnbf;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Demande_annulationService ser=new Demande_annulationService();
        
        ArrayList<Demande_annulation> lissst=new ArrayList<Demande_annulation>();
        
        
        lissst=ser.afficherDemande_annul();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_cli"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        valeur.setCellValueFactory(new PropertyValueFactory<>("id_cnt"));
           System.out.println(lissst.get(0).toString());
      //  tttttt.setItems(demande);
      
      ObservableList<Demande_annulation> data = FXCollections.<Demande_annulation>observableArrayList();
      
      data.addAll(lissst);
      
       tttttt.setItems(data);
        
        
        
    }    
    
}
