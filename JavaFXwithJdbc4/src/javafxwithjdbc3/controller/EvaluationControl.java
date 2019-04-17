/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxwithjdbc3.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafxwithjdbc3.model.Evaluation;

/**
 *
 * @author Fadi
 */
public class EvaluationControl {
    
     Statement st;
    private String service;
    public void insert(Evaluation evaluation)  {
    
      try{
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("insert into evaluations(service,avis,note,etat) values('"+evaluation.getService()+
                   "','"+evaluation.getAvis()+"','"+evaluation.getNote()+"','"+evaluation.getEtat()+"')");
      }  catch (SQLException ex) {
             Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     public void update(Evaluation evaluation) {
    
        try {
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("update evaluations set etat = '"+evaluation.getEtat()+"' where id ="+evaluation.getId()+"");
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     
     public void delete (int id ) {
         
          try {
            st=DBConnection.getConnection().createStatement();
            st.executeUpdate("delete from evaluations where id ="+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
     public  ObservableList<Evaluation> getAllEvaluations(){
         ObservableList<Evaluation> evaluations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from evaluations");
            resultSet.beforeFirst();
            
            while(resultSet.next()){
                Evaluation evaluation=new Evaluation();
                evaluation.setId(resultSet.getInt(1));
                evaluation.setService(resultSet.getString(2));
                evaluation.setAvis(resultSet.getString(3));
                evaluation.setNote(resultSet.getString(4));
                evaluation.setEtat(resultSet.getString(5));
                evaluations.add(evaluation);
                System.out.println(evaluation);
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return evaluations;
     }
      public  ObservableList<Evaluation> getAllEvaluations2(){
       ObservableList<Evaluation> evaluations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from evaluations order by id DESC LIMIT 1 ");
            
            resultSet.beforeFirst();
            
            while(resultSet.next()){
                Evaluation evaluation=new Evaluation();
                evaluation.setId(resultSet.getInt(1));
                evaluation.setService(resultSet.getString(2));
                evaluation.setAvis(resultSet.getString(3));
                evaluation.setNote(resultSet.getString(4));
                evaluation.setEtat(resultSet.getString(5));
                evaluations.add(evaluation);
                System.out.println(evaluation);
            }
            
          
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return evaluations;
      
      }
     
     
      public  ObservableList<Evaluation> search(String service){
         ObservableList<Evaluation> evaluations= FXCollections.observableArrayList();
        try {
            st=DBConnection.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery("select * from evaluations where service like'%"+service+"%'");
            resultSet.beforeFirst();
            
            while(resultSet.next()){
            Evaluation evaluation=new Evaluation();
                evaluation.setId(resultSet.getInt(1));
                evaluation.setService(resultSet.getString(2));
                evaluation.setAvis(resultSet.getString(3));
                evaluation.setNote(resultSet.getString(4));
                 evaluation.setEtat(resultSet.getString(5));
                evaluations.add(evaluation);
                System.out.println(evaluation);

            }
            
        } catch (SQLException ex) { 
             Logger.getLogger(EvaluationControl.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return evaluations;
     }
     
    
    public static void main(String[] args) {
    Evaluation us = new Evaluation();
    us.setService("n");

   
     us.setNote("n");
    
    new EvaluationControl().insert(us);
        
    }
    
    
}
