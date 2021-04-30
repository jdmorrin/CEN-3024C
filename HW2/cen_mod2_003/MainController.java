/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod2_003;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Main FXML controller for Scene Builder GUI
 * 
 * @author Juan David Morrell Rincon
 * @version 2.0
 */
public class MainController {
    
    private int index = 0;
    private static List <WordCount> words;
    @FXML
    private Label message = new Label();
    
    

    public List<WordCount> getWords() {
        return words;
    }

    public static void setWords(List<WordCount> list) {
        words = list;
    }
    
/**
 * Goes through a list of WordCount objects, and displays each object.
 * 
 * @param e 
 * 
 */    
    public void display(ActionEvent e){
        
        message.setText(words.get(index).toString());
        // System.out.println(words.get(index).toString());
        
        index++;
        if(index == words.size())
            index = 0;
    }
    
    
}
