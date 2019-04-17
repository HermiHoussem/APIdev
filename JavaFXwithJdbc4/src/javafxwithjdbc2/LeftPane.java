package javafxwithjdbc2;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafxwithjdbc2.controller.ReclamationControl;
import javafxwithjdbc2.model.Reclamation;
import org.controlsfx.control.Notifications;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafxwithjdbc2.RightPane.table;
import javafxwithjdbc2.controller.DBConnection;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JComboBox;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fadi
 */
public class LeftPane extends VBox {
    
 
            
    Label labelTitle = new Label("Gestion reclamation");
    
    TextField txtTitre = new TextField();
    TextField txtSujet = new TextField();
    TextField txtDescription = new TextField();
  ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Service Communication",
        "Service Mecanique",
        "Service Electronique",
            "Service Client"
    );
final ComboBox comboBox = new ComboBox(options);
    Button btnSave= new Button("Enregistrer");
  
     Button btnRating= new Button("Rating");
    
  
    
    int tempId;
    
    public LeftPane(){
        txtTitre.setFont(new Font(20));
        txtDescription.setFont(new Font(20));
        txtSujet.setFont(new Font(20));
       comboBox .setPrefWidth(230);
       comboBox .setMinHeight(50);

        txtTitre.setPromptText("Entrer Titre");
        txtSujet.setPromptText("Entrer Sujet");
        txtDescription.setPromptText("Entrer Description");
         comboBox.setPromptText("choisir un Sujet");
        txtDescription.setPrefSize(300, 300);
        
        btnSave.setPrefWidth(150);
  
        btnRating.setPrefWidth(150);
         ReclamationControl uc = new ReclamationControl();
        
        btnSave.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
          
               btnRating.setStyle("-fx-background-color:#0984e3;-fx-font-size:18;-fx-text-fill:#FFF");
        
        labelTitle.setStyle("-fx-font-size:20px;-fx-text-fill:#FFF");
        
        this.setStyle("-fx-background-color:#6c5ce7");
      this.setSpacing(20);  
   this.setPadding(new Insets(20));
   this.setAlignment(Pos.CENTER);
    this.getChildren().addAll(labelTitle, txtTitre ,  comboBox , txtDescription, btnSave );
   
      //actions
      
      btnSave.setOnMouseClicked((MouseEvent e)->{
          
       if((txtTitre.getText().trim().isEmpty())||(txtDescription.getText().trim().isEmpty())){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Attention");
        fail.setContentText("tu dois remplir tout les champs de saisie");
        fail.showAndWait();
    } else{
                try{
                    Reclamation reclamation= new Reclamation();
                    reclamation.setId(tempId);
                    reclamation.setTitre(txtTitre.getText());
                    reclamation.setSujet(comboBox.getSelectionModel().getSelectedItem().toString());
                    
                    reclamation.setDescription(txtDescription.getText());
                    reclamation.setEtat("en cours");
                    uc.insert(reclamation);
                    JavaFxwithJdbc2.rightPane.table.setItems(uc.getAllReclamations2());
                    txtTitre.setText("");
                    txtSujet.setText("");
                    txtDescription.setText("");
                    
                    
                    
                    try{
                        String host ="smtp.gmail.com" ;
                        String user = "fadi.kraiem@esprit.tn";
                        String pass = "maxo16cc";
                        String to = "nukeruj@easy-mail.top";
                        String from = "fadi.kraiem@esprit.tn";
                        String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
                        String messageText = "Your Is Test Email :";
                        boolean sessionDebug = false;
                        
                        Properties props = System.getProperties();
                        
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.required", "true");
                        
                        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                        Session mailSession = Session.getDefaultInstance(props, null);
                        mailSession.setDebug(sessionDebug);
                        Message msg = new MimeMessage(mailSession);
                        msg.setFrom(new InternetAddress(from));
                        InternetAddress[] address = {new InternetAddress(to)};
                        msg.setRecipients(Message.RecipientType.TO, address);
                        msg.setSubject(subject); msg.setSentDate(new Date());
                        msg.setText(messageText);
                        
                        Transport transport=mailSession.getTransport("smtp");
                        transport.connect(host, user, pass);
                        transport.sendMessage(msg, msg.getAllRecipients());
                        transport.close();
                        System.out.println("message send successfully");
                    }catch(Exception ex)
                    {
                        System.out.println(ex);
                    }
                    
                    
                    
                    Image img = new Image("/image1.png");
                    
                    Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("votre reclamation a ete envoye avec succes")
                            .graphic(new ImageView(img))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT);/*
                    .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    System.out.println("Clicked on Notification");
                    }
                    }); */
                    notificationBuilder.darkStyle();
                    notificationBuilder.show();
                    
                    
     
                    
                    
                    
                    
                    
                      Paragraph p2 = new Paragraph( txtDescription.getText());
                    Document document = new Document();
                    
                    PdfWriter.getInstance(document, new FileOutputStream("Reclamation.pdf"));
                  
                    document.open();
 /*              PdfPTable tb1 = new PdfPTable(4);
               
               tb1.addCell("titre");
                 tb1.addCell("sujet");
                   tb1.addCell("description");
                     tb1.addCell("rating");
                   
               for(int i = 0; i < RightPane.table.getRowCount()  ; i++) {
                String titre = RightPane.table.getValueAt(1,0).toString();
                  String sujet = tb1Employee.getValueAt(1,1).toString();
                    String description = tb1Employee.getValueAt(1,2).toString();
                      String rating = tb1Employee.getValueAt(1,3).toString();
                      
                tb1.addCell(titre);
                  tb1.addCell(sujet);
                    tb1.addCell(description);
                      tb1.addCell(rating);
                
                }*/
                    
                    Paragraph p1 = new Paragraph("Votre reclamation!");
                    
                    document.add(p1);
                    document.add(p2);
                  
             //       document.add(tb1);
                    document.close();
                }catch(FileNotFoundException ex)
        {
               Logger.getLogger(LeftPane.class.getName()).log(Level.SEVERE, null,ex);
        }  catch (DocumentException ex) {
               Logger.getLogger(LeftPane.class.getName()).log(Level.SEVERE, null, ex);
           }
    } 
    
      
      });
      
   
    
    
    
       btnRating.setOnMouseClicked(e->{
            try {
                Stage stage = new Stage();
                Parent root1 = null;
                
                
                
                root1 = FXMLLoader.load(getClass().getResource("Rating.fxml"));
                
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LeftPane.class.getName()).log(Level.SEVERE, null, ex);
            }
			
		
    });
    
   
        
       
       
    }
  
     
}
