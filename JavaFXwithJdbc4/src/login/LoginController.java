/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.sun.javaws.Main;
import dao.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafxwithjdbc2.JavaFxwithJdbc2;
import static javafxwithjdbc2.JavaFxwithJdbc2.main;
import javafxwithjdbc2.LeftPane;
import javafxwithjdbc2.RightPane;
import javax.swing.JOptionPane;



public class LoginController {
@FXML
public TextField usernameField;

@FXML 
public PasswordField passwordField;
@FXML
/*
 public User getUserByEmail(String mail) throws SQLException {
        String req = "select * from user where email='" + mail + "'";
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery(req);
        User u = new User();
        if (rs.next()) {
            u.setUserId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setBirthDate(rs.getString("ddn"));
            u.setPassword(rs.getString("pwd"));
            u.setEmail(rs.getString("email"));
            u.setType(TypeUser.valueOf(rs.getString("type")));
            u.setNumero(rs.getString("tel"));
            u.setCin(rs.getInt("ci"));
        }
        return u;
    }
*/

private void onLogin() throws SQLException{
    if(!usernameField.getText().matches("[a-zA-Z0-9_]{4,}")) {
        return;
    }
    
    if(passwordField.getText().isEmpty()) {
    
    return;
}
    
   int status = DBConnection.checkLogin(usernameField.getText().trim().toLowerCase(), passwordField.getText());
    
   switch (status) {
       case 0 :  {
           Stage stage = (Stage)usernameField.getScene().getWindow();
        Parent root = null;
        
         try {
             root = FXMLLoader.load(getClass().getResource("/system/System.fxml"));
        }
   catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         UserService us = new UserService();
         User Current = new User();
         Current = us.getUserByUser(usernameField.getText());
    
        if (Current.getRole().equals("client")) {
         
            javafxwithjdbc2.JavaFxwithJdbc2.main.getChildren().addAll(javafxwithjdbc2.JavaFxwithJdbc2.leftpane , javafxwithjdbc2.JavaFxwithJdbc2.rightPane);
            Scene scene1 = new Scene(javafxwithjdbc2.JavaFxwithJdbc2.main ,800,600);
            stage.setScene (scene1 ); 
    
         }
         else if (Current.getRole().equals("admin")) 
         { 
         javafxwithjdbc2.JavaFxwithJdbc3.main.getChildren().addAll(javafxwithjdbc2.JavaFxwithJdbc3.leftpane2 , javafxwithjdbc2.JavaFxwithJdbc3.rightPane2);
         Scene scene2 = new Scene(javafxwithjdbc2.JavaFxwithJdbc3.main ,800,600);
         stage.setScene (scene2 );   }
         }
      
       break;
       case -1 : JOptionPane.showMessageDialog(null, "Connection Failed");break;
       case 1 : JOptionPane.showMessageDialog(null, "Username or password wrong");

   
   
}
    
}


}

