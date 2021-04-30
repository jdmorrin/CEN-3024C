/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.cen_mod2_test;

import cen_mod2_003.WordCount;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Admin
 */
public class WordCountTest {
    
    // set up
   
    File testFile = new File("testFile.txt");
    File countFile = new File("countFile.txt");
        
    List <WordCount> inputList = new ArrayList<WordCount>();

    
    
    /**
     * Makes sure that the listWords method collects only UNIQUE instances of words
     */
    @Test
    public void testListWords(){
        try {
            List <WordCount> testList = WordCount.listWords(testFile);
            boolean noRepetitionFound = true;
            
            for(WordCount word1 : testList){
                for(WordCount word2 : testList){
                    if(word2.equals(word1))
                        noRepetitionFound = false;
                }
                    
            }
            
            assertTrue(noRepetitionFound);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordCountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Tests for count accuracy
     */
    @Test
    public void testCountWords(){
       inputList.add(new WordCount("test"));
        try {
            List <WordCount> testList = WordCount.countWords(countFile, inputList);         
            
            assertEquals(18, testList.get(0).getFreq());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordCountTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
