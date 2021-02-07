// Making a text anylyzer that counts and sorts words based on frequency

// I. Create two arrays or lists
//      a. one that stores all unique words in the file /words/
//      b. one that stores word frequencies /freq/
// II. Start a loop to count the words
//      1. Begin to read file
//      2. read a word and store it in a string /currentWord/
//          a. if the word being read is in the /words/ list read the next word in the file
//      3. Go trhough file until all instances of /currentWord/ have been recorded into an int /count/
//      4. When all instances of /currentWord/ have been recorded
//          a. store value of /currentWord/ in the /words/ list
//          b. store value of /cunt/ in the /freq/ list
//      5. Go back to beginning of file until every unique word in the file can be found in the /words/ list
//  III. Sorting

// Solution: Instead of generating two "while" loops, one nested inside the other, make two separate while loops.
// One will store uniqie words into an array list
// Use that array list as the basis for the second while loop 
// Maybe

// Make function that will convert file from HTML code to just a text file
// AKA function will remove html tags, punctuation, and capitalization
// Write output into a new file, and use the new file as the source for the rest of the code


package cen_mod2_003;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Juan David Morrell Rincon
 */
public class CEN_Mod2_003 {

    public static void main(String[] args) {
        
        File rawPoem = new File("text3.txt");
        try {
            File cleanedPoem = cleanPoem(rawPoem);
            List <WordCount> wordCount = listWords(cleanedPoem);
            wordCount = countWords(cleanedPoem, wordCount);
            
            Collections.sort(wordCount);
            
            for(WordCount word : wordCount){
                System.out.println(word);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CEN_Mod2_003.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }   // end main
    
    // This method parses the file to just include the actual oem
    // Also eliminates HTML tags and converts everything to plain texxt
    public static File cleanPoem(File file) throws IOException, FileNotFoundException{
        Scanner scan = new Scanner(file);
        
        File parsedFile = new File("parsedFile");
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
    
    // This method records all unique words in the poem, but does not count them
    public static List <WordCount> listWords(File textFile) throws FileNotFoundException {
        Scanner scan = new Scanner(textFile);
        List <WordCount> list = new ArrayList();
        
        // Loop goes through every String in the poem
        while(scan.hasNext()){
            
            // replaceAll() makes it so that punctuatio surrounding the string is ignored
            // toLowerCase() ignores capitalization
            String currentWord = scan.next().replaceAll("[\\W]","").toLowerCase(); 
            WordCount current = new WordCount(currentWord);
            
            // If the list does not already contain a WordCount with the currentWord in it
            // Then add a new Word Count to the list                                 
            if(!list.contains(new WordCount(currentWord)))
                list.add(new WordCount(currentWord));            
            
        }
        
        return list;
    }   // end listWords
    
    public static List <WordCount> countWords(File file, List <WordCount> list) throws FileNotFoundException{
        
        int count = 0;
        
        for(WordCount wCount : list){
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                String str = scan.next().replaceAll("[\\W]","").toLowerCase(); 
                if(wCount.getWord().equals(str))
                    count++;
            }
           
            wCount.setFreq(count);
            count = 0;
            scan.close();
        }
        return list;
    } // end countWords
    
}   // end class
