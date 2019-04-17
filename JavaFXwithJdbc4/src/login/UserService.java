/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxwithjdbc3.controller.DBConnection;

/**
 *
 * @author Khaled
 */
public class UserService {

    private Connection cnx;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<User> Users = new ArrayList<User>();

    public UserService() {
        cnx = DBConnection.getConnection();
    }
    
    public User getUserByUser(String username) throws SQLException {
        String req = "select id, username, password, etat, role from users where username='" + username + "'";
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery(req);
        User u = new User();
        if (rs.next()) {
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setEtat(rs.getString("etat"));
            u.setRole(rs.getString("role"));
        }
        return u;
    }
}