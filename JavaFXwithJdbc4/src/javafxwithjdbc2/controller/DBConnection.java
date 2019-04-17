/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fadi
 */
public class DBConnection {
    private static Connection con;
     private static String user ="root";
            private static String password="";
    private DBConnection () {
        
        
    }
    
    public static Connection getConnection() {
      if ( con == null ) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/ReclamationDB",user ,password);
            
        } catch (ClassNotFoundException ex) { 
              Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
          } 
        
      }
    return con;
    }

    public  static void disconnect() {
    if(con !=null) {
    
    con = null;
    
    
    
    }
    
    
    
    }
}
