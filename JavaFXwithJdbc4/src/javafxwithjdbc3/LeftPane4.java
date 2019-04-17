package javafxwithjdbc3;


import java.io.IOException;
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
import javafxwithjdbc3.controller.EvaluationControl;
import javafxwithjdbc3.model.Evaluation;
import org.controlsfx.control.Notifications;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JComboBox;

/**
 *
 * @author Fadi
 */
public class LeftPane4 extends VBox {
    Label labelTitle = new Label("Gestion evaluation");
    
    TextField txtService = new TextField();
  
    TextField txtNote = new TextField();
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
    ToggleGroup group = new ToggleGroup();
    RadioButton button1 = new RadioButton("j\'aime");
      RadioButton button2 = new RadioButton("je n\'aime pas");
   FlowPane flowPane = new FlowPane(button1,button2);
  
    private String var1 ;
    int tempId;
    
    public LeftPane4(){
        txtService.setFont(new Font(20));
        txtNote.setFont(new Font(20));
       
       comboBox .setPrefWidth(230);
       comboBox .setMinHeight(50);

        txtService.setPromptText("Entrer Service");
         
        txtNote.setPromptText("Saisir Commentaire");
         comboBox.setPromptText("choisir un Service");
        txtNote.setPrefSize(300, 300);
        button1.setSelected(true);
        btnSave.setPrefWidth(150);
  
        btnRating.setPrefWidth(150);
         EvaluationControl uc = new EvaluationControl();
        
        btnSave.setStyle("-fx-background-color:#00cec9;-fx-font-size:18;-fx-text-fill:#FFF");
          
               btnRating.setStyle("-fx-background-color:#0984e3;-fx-font-size:18;-fx-text-fill:#FFF");
        
        labelTitle.setStyle("-fx-font-size:20px;-fx-text-fill:#FFF");
        
        this.setStyle("-fx-background-color:#55efc4");
      this.setSpacing(20);  
   this.setPadding(new Insets(20));
   this.setAlignment(Pos.CENTER);
     GridPane.setHalignment(button1, HPos.CENTER);
     GridPane.setHalignment(button2, HPos.CENTER);
     
            VBox container = new VBox(flowPane);
    this.getChildren().addAll(labelTitle,  comboBox,flowPane, txtNote,btnRating, btnSave );
   
      //actions
      
      btnSave.setOnMouseClicked((MouseEvent e)->{
          
         if((txtNote.getText().trim().isEmpty())){
        Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("Attention");
        fail.setContentText("tu dois remplir tout les champs de saisie");
        fail.showAndWait();
    } else{
           Evaluation  evaluation= new Evaluation();
    evaluation.setId(tempId);
    
    
    evaluation.setService( comboBox.getSelectionModel().getSelectedItem().toString()    );
    
    /*
    
      button1.setToggleGroup(group);
        button2.setToggleGroup(group);
   group.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
            RadioButton tempRadioButton = (RadioButton) newValue;
            if (tempRadioButton == button1) {
                evaluation.setAvis("jaime");
            }
            else if (tempRadioButton == button2) {
               evaluation.setAvis("je naime pas");
            }
        });
   
    */
    if( button1.isSelected()){evaluation.setAvis("jaime");}
       if( button2.isSelected()){evaluation.setAvis("je naime pas");}
    //  evaluation.setAvis("jaime");
      evaluation.setNote(txtNote.getText());
      evaluation.setEtat("Rating : 3.0");
       evaluation.setEtat(javafxwithjdbc3.HelloRating.avg);
      uc.insert(evaluation);
        JavaFxwithJdbc4.rightPane4.table.setItems(uc.getAllEvaluations2());
        txtService.setText("");
         
          txtNote.setText("");
          
    
        
                try{
            String host ="smtp.gmail.com" ;
            String user = "fadi.kraiem@esprit.tn";
            String pass = "maxo16cc";
            String to = "zugerog@mailing.one";
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
               .text("votre evaluation a ete envoye avec succes")
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
                Logger.getLogger(LeftPane4.class.getName()).log(Level.SEVERE, null, ex);
            }
			
		
    });  
  
       
       
    }
  
     
}
