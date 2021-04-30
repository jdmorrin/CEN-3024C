/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod2_003;

/**
 *
 * @author Admin
 */

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    
    private final Connection conn;
    private Statement stmt;
    private PreparedStatement insert;
    private ResultSet results;
    
    public Database(){
        this.conn = connect();
        createDatabase();
    }
    
    // Attempts to connect to the SQL Server
    public final Connection connect(){
        try {
            final String DRIVER = "com.mysql.cj.jdbc.Driver";
            final String URL = "jdbc:mysql://localhost:3306/?allowMultiQueries=true";
            
            final String USER = "root";
            final String PASS = "St4yedup2Late";
            
            Connection c;
            
            System.out.println("Establishing connection...");
            Class.forName(DRIVER);
            c = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Success!");
            
            return c;
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Failed to connect.");
        return null;
    }
    
    public void createDatabase() {
        try {
            stmt = conn.createStatement();
            int create = stmt.executeUpdate("CREATE DATABASE wordOccurrences; "
                    + "USE wordOccurrences;"
                    + "CREATE TABLE word"
                    + "("
                    + "id int not null auto_increment, "
                    + "word VARCHAR(255) NOT NULL, "
                    + "frequency INT NOT NULL, "
                    + "PRIMARY KEY (id)"
                    + ");");
            System.out.println("Database created.\n"
                    + "Table created.");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Uodate the database
    public void updateDatabase(List <WordCount> occurrences){
        try {
            insert = conn.prepareStatement("INSERT INTO wordOccurrences.word values(default, ?, ?)");
            
            for(WordCount count : occurrences){
                insert.setString(1, count.getWord());
                insert.setInt(2, count.getFreq());
                insert.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printResults(){
        try {
            results = stmt.executeQuery("select * from wordOccurrences.word");
            while(results.next()){
                int id = results.getInt("id");
                String word = results.getString("word");
                int frequency = results.getInt("frequency");
                System.out.println(id + ". " + word + "-" + frequency);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
