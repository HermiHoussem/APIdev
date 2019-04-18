/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assurance;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author dell user
 */
public class MainBackController implements Initializable {

    @FXML
    private Button but1;
    @FXML
    private Button but2;
    @FXML
    private Button but3;
    @FXML
    private BorderPane MainBack;
    @FXML
    private Button but31;

    public  void setMainBack(Parent root) {
        this.MainBack.setCenter(root);
    }

    

    @FXML
    private void but1(javafx.scene.input.MouseEvent event) {
        loadUi("AjouterProduit");
    }
     @FXML
    private void but2(javafx.scene.input.MouseEvent event) {
         loadUi("AfficherProduit");
    }
    
  
public void loadUi(String UI)
{
  Parent root=null;
  
      try {
          root=FXMLLoader.load(getClass().getResource(UI+".fxml"));
      } catch (IOException ex) {
          Logger.getLogger(MainBackController.class.getName()).log(Level.SEVERE, null, ex);
      }
      MainBack.setCenter(root);
  }
  

        
        
    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void but3(javafx.scene.input.MouseEvent event) {
        loadUi("AfficherClient");
    }

    @FXML
    private void but31(javafx.scene.input.MouseEvent event) {
         loadUi("AfficherContrat");
    }

   
    
}
