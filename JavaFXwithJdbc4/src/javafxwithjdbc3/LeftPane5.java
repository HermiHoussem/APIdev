/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc3;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafxwithjdbc2.JavaFxwithJdbc3;
import javafxwithjdbc2.LeftPane2;
import javafxwithjdbc3.controller.EvaluationControl;
import javafxwithjdbc3.model.Evaluation;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Fadi
 */
public class LeftPane5 extends VBox {
    Label labelService = new Label("Espace Admin");
    
       private static Connection con;
     private static String user ="root";
            private static String password="";
    
    
     TextField txtService1 = new TextField();
    TextField txtAvis1 = new TextField();
    TextField txtNote1 = new TextField();
    TextField txtEtat1 = new TextField();
    Button btnUpdate1= new Button("Modifier");
    
    String[] possiblewords = {"traite" ,"en cours" };
    
     AutoCompletionBinding acbLogin = TextFields.bindAutoCompletion(txtEtat1, possiblewords);
    
    
    Button btnDelete2= new Button("Supprimer");
    Button btnPdf2= new Button("PDF");
    
  
    
    int tempId1;
    
    public LeftPane5(){
      
         txtService1.setFont(new Font(20));
        txtNote1.setFont(new Font(20));
        txtAvis1.setFont(new Font(20));
         txtEtat1.setFont(new Font(20));
        
        
        
        
      
        txtEtat1.setPromptText("Modifier Etat");
        txtNote1.setPrefSize(300, 300);
        
        btnUpdate1.setPrefWidth(150);
       
        
        btnPdf2.setPrefWidth(150);
        btnDelete2.setPrefWidth(150);
        
         EvaluationControl uc3 = new EvaluationControl();
        
        
              btnDelete2.setStyle("-fx-background-color:#009688;-fx-font-size:18;-fx-text-fill:#FFF");
                   btnUpdate1.setStyle("-fx-background-color:#009688;-fx-font-size:18;-fx-text-fill:#FFF");
                        btnPdf2.setStyle("-fx-background-color:#009688;-fx-font-size:18;-fx-text-fill:#FFF");
        labelService.setStyle("-fx-font-size:20px;-fx-text-fill:#FFF");
        
        this.setStyle("-fx-background-color:#636e72");
      this.setSpacing(20);  
   this.setPadding(new Insets(20));
   this.setAlignment(Pos.CENTER);
    this.getChildren().addAll(labelService ,btnPdf2,btnDelete2);
   
      //actions
      
       btnUpdate1.setOnMouseClicked(e->{
   
              Evaluation evaluation= new Evaluation();
    evaluation.setId(tempId1);
         evaluation.setEtat(txtEtat1.getText());
      uc3.update(evaluation);
        JavaFxwithJdbc5.rightPane5.table.setItems(uc3.getAllEvaluations());
    txtEtat1.setText("");
    
    });
    
 
        btnPdf2.setOnMouseClicked(e->  {
     
      
             Image img = new Image("/image1.png");
                    
                    Notifications notificationBuilder = Notifications.create()
                            .title("Notification")
                            .text("votre fichier pdf a ete cree avec succes")
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
      
             try {
                 /* Create Connection objects */
                 
                 
                 Class.forName("com.mysql.jdbc.Driver");
                 con=DriverManager.getConnection("jdbc:mysql://localhost/ReclamationDB",user ,password);
                 
                 Statement stmt = con.createStatement();
                 /* Define the SQL query */
                 ResultSet query_set = stmt.executeQuery("SELECT service,avis,note,etat FROM evaluations");
                 /* Step-2: Initialize PDF documents - logical objects */
                 Document my_pdf_report = new Document();
                 PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java2.pdf"));
                 my_pdf_report.open();
                 //we have four columns in our table
                 PdfPTable my_report_table = new PdfPTable(4);
                 //create a cell object
                 PdfPCell table_cell;
                 
                 while (query_set.next()) {
                     String service = query_set.getString("service");
                     table_cell=new PdfPCell(new Phrase(service));
                     my_report_table.addCell(table_cell);
                     String avis=query_set.getString("avis");
                     table_cell=new PdfPCell(new Phrase(avis));
                     my_report_table.addCell(table_cell);
                     String note=query_set.getString("note");
                     table_cell=new PdfPCell(new Phrase(note));
                     my_report_table.addCell(table_cell);
                     String etat=query_set.getString("etat");
                     table_cell=new PdfPCell(new Phrase(etat));
                     my_report_table.addCell(table_cell);
                 }
                 /* Attach report table to PDF */
                 my_pdf_report.add(my_report_table);
                 my_pdf_report.close();
                 
                 /* Close all DB related objects */
                 query_set.close();
                 stmt.close();               
                 con.close();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(LeftPane2.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(LeftPane2.class.getName()).log(Level.SEVERE, null, ex);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(LeftPane2.class.getName()).log(Level.SEVERE, null, ex);
             } catch (DocumentException ex) {
                 Logger.getLogger(LeftPane2.class.getName()).log(Level.SEVERE, null, ex);
             }
                      
                    
        
    
    
    });
    
      
   
  
       
       
      
   
    btnDelete2.setOnMouseClicked(e->  {
      uc3.delete(tempId1);
      JavaFxwithJdbc5.rightPane5.table.setItems(uc3.getAllEvaluations());
      
            
        
    
    
    });
    
    
    
    
    
    
    }
  
    
}
