/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kraiemfadi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class log extends Application{

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
    stage.setScene(new Scene(root));
    stage.setTitle("login");
    stage.show();
    
    }
    public static void main(String[] args) {
        launch(args);
    }
}
