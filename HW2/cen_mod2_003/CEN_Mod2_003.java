

package cen_mod2_003;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main class
 * 
 * @author Juan David Morrell Rincon
 * @version 2.0
 */
public class CEN_Mod2_003 extends Application{

    public static void main(String[] args) {
        
        // Change the file path when useing a new Computer
        File rawPoem = new File("C:\\Users\\denym\\Desktop\\Generic\\CEN-3024C\\HW2\\cen_mod2_003\\poem.txt");
        try {
            File cleanedPoem = cleanPoem(rawPoem);
            List <WordCount> wordCount = WordCount.listWords(cleanedPoem);
            wordCount = WordCount.countWords(cleanedPoem, wordCount);
            
            Collections.sort(wordCount);
            cleanedPoem.delete();   
            
            Database db = new Database();
            db.updateDatabase(wordCount);
            db.printResults();
            
            MainController.setWords(wordCount);
            
//            for(WordCount word : wordCount){
//                System.out.println(word);
//            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(CEN_Mod2_003.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        launch(args);
    }   // end main
    
    /**
     * 
     * The basic GUI skeleton. I still don't understand how GUIs work, but somehow I got this to display.
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
       
//        Button showBtn = new Button("Show Word Occurrence");
//        Button exit = new Button("Quit");
//        exit.setOnAction(e -> System.exit(0));
//        
//        Label message = new Label();
//        
//        EventHandler <ActionEvent> showOccurrence = new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent t) {
//                message.setText("Hello");
//                
//                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//            
//        };
//        
//        showBtn.setOnAction(showOccurrence);
//        VBox root = new VBox();
//        root.getChildren().addAll(showBtn, exit, message);
        
        Parent root = FXMLLoader.load(getClass().getResource("/cen_mod2_003/main.fxml"));

        Scene scene = new Scene(root, 600, 222);
        
        stage.setScene(scene);
        stage.setTitle("Word Occurrences");
        stage.show();
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * This method parses the file to just include the actual poem.
     * Also eliminates HTML tags and converts everything to plain text
     * 
     * @param file
     * @return
     * @throws IOException
     * @throws FileNotFoundException 
     */
    public static File cleanPoem(File file) throws IOException, FileNotFoundException{
        Scanner scan = new Scanner(file);
        
        File parsedFile = new File("parsedFile.txt");
        
        FileWriter poemWriter = new FileWriter(parsedFile, true);       
        PrintWriter poemPrinter = new PrintWriter(poemWriter);
        
        // SPLIT THE READING OF THE FILE INTO WHILE LOOPS
        // This way we can ignore the tex at the beginning and end of the document
        // Thie first while loop will go through each line in the document until it gets to "<H1 ALIGN="center">"
        // The second while loop will go through each line until it gets to the line "End of the Project Gutenberg EBook of The Raven, by Edgar Allan Poe"
        
        while(!scan.nextLine().equals("Produced by Levent Kurnaz.  HTML version by Al Haines.")){
            // DO NOTHING HERE
        }
        
        String line = "";
        
        while(!line.equals("End of the Project Gutenberg EBook of The Raven, by Edgar Allan Poe")){
            line = scan.nextLine().replaceAll("\\<.*?\\>","");
            poemPrinter.println(line);
        }
        
        poemPrinter.close();
        poemWriter.close();
        
        return parsedFile;     
        
    }

 
    
}   // end class
