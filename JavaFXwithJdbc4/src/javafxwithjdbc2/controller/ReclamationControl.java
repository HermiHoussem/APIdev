/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc2.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxwithjdbc2.model.Reclamation;

/**
 *
 * @author Fadi
 */
public class ReclamationControl {
    
     Statement st;
    private String titre;
    public void insert(Reclamation reclamation)  {
    
      try{
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("insert into reclamations(titre,sujet,description,etat) values('"+reclamation.getTitre()+
                   "','"+reclamation.getSujet()+"','"+reclamation.getDescription()+"','"+reclamation.getEtat()+"')");
      }  catch (SQLException ex) {
             Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     public void update(Reclamation reclamation) {
    
        try {
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("update reclamations set etat = '"+reclamation.getEtat()+"' where id ="+reclamation.getId()+"");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     
     public void delete (int id ) {
         
          try {
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("delete from reclamations where id ="+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
     public  ObservableList<Reclamation> getAllReclamations(){
         ObservableList<Reclamation> reclamations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from reclamations");
            resultSet.beforeFirst();
            
            while(resultSet.next()){
                Reclamation reclamation=new Reclamation();
                reclamation.setId(resultSet.getInt(1));
                reclamation.setTitre(resultSet.getString(2));
                reclamation.setSujet(resultSet.getString(3));
                reclamation.setDescription(resultSet.getString(4));
                reclamation.setEtat(resultSet.getString(5));
                reclamations.add(reclamation);
                System.out.println(reclamation);
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return reclamations;
     }
      public  ObservableList<Reclamation> getAllReclamations2(){
       ObservableList<Reclamation> reclamations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from reclamations order by id DESC LIMIT 1 ");
            
            resultSet.beforeFirst();
            
            while(resultSet.next()){
                Reclamation reclamation=new Reclamation();
                reclamation.setId(resultSet.getInt(1));
                reclamation.setTitre(resultSet.getString(2));
                reclamation.setSujet(resultSet.getString(3));
                reclamation.setDescription(resultSet.getString(4));
                
                reclamations.add(reclamation);
                System.out.println(reclamation);
            }
            
          
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return reclamations;
      
      }
     
     
      public  ObservableList<Reclamation> search(String titre){
         ObservableList<Reclamation> reclamations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from reclamations where titre like'%"+titre+"%'");
            resultSet.beforeFirst();
            
            while(resultSet.next()){
            Reclamation reclamation=new Reclamation();
                reclamation.setId(resultSet.getInt(1));
                reclamation.setTitre(resultSet.getString(2));
                reclamation.setSujet(resultSet.getString(3));
                reclamation.setDescription(resultSet.getString(4));
                reclamations.add(reclamation);
                System.out.println(reclamation);

            }
            
        } catch (SQLException ex) { 
             Logger.getLogger(ReclamationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return reclamations;
     }
     
    
    public static void main(String[] args) {
    Reclamation us = new Reclamation();
    us.setTitre("n1");

   us.setId(11);
     us.setDescription("n3");
    
    new ReclamationControl().getAllReclamations();
        
    }
    
    
}
