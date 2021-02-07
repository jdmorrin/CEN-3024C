/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod2_003;

import java.util.Objects;


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

    // Need to override the equals method so that the contains() method works
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

    @Override
    public int compareTo(WordCount o) {
        return o.getFreq() - this.getFreq();
        
    }

    @Override
    public String toString() {
        return this.getWord() + " : " + this.getFreq();
    }
    
    
    
}

