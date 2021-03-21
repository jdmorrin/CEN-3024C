/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod2_003;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * 
 * @author Juandi
 * @implements Comparable
 */
public class WordCount implements Comparable <WordCount>{
    private String word;
    private int freq;
   
    public WordCount(String word){
        this.word = word;
    }
        
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
    
    
    /**
     * 
     * This method records all unique words in the text document, but does not count them
     * 
     * @param textFl
     * @return 
     * @throws FileNotFoundException 
     */
    public static List <WordCount> listWords(File textFl) throws FileNotFoundException{
        List <WordCount> list = new ArrayList<WordCount>();
        Scanner scan = new Scanner(textFl);
        
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
    }
    
    /**
     * 
     * Takes a list of words and counts how many times they appear in a text document
     * 
     * @param file A text document
     * @param list The list of words that will be counted
     * @return
     * @throws FileNotFoundException 
     */
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
    
    // Need to override the equals method so that the contains() method works
    /**
     * 
     * Used for sorting.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WordCount other = (WordCount) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }

    
    /**
     * 
     * Compares WordCount objects by frequency
     * 
     * @param o 
     * @return 
     */
    @Override
    public int compareTo(WordCount o) {
        return o.getFreq() - this.getFreq();
        
    }

    @Override
    public String toString() {
        return this.getWord() + " : " + this.getFreq();
    }
        
}

