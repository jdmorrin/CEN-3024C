/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SumThread extends Thread{
    
    int[] arr;
    int min;
    int max;
    int chunk;
    
    public SumThread(int[] arr, int min, int max){
        this.arr = arr;
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void run(){
        chunk = rangedSum(arr, min, max);
    }

    public int getChunk() {
        return chunk;
    }    
    
    /**
     * 
     * @param arr
     * @return the sum of all elements in an array
     */
    public static int linearSum(int[] arr){
        int sum = 0;
        
         for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        
        return sum;
    }
    
    /**
     * 
     * @param arr
     * @param min
     * @param max
     * @return the sum of elements in an array within a specific range
     * Used to make partial sums when running the parallel sum method
     */
    public int rangedSum(int[] arr, int min, int max){
        int sum = 0;
        
        for(int i = min; i < max; i++){
            sum += arr[i];
            
        }
        
        return sum;
    }
    
    /**
     * 
     * @param arr
     * @param threads
     * @return sum of the elements in an array
     */
    public static int parallelSum(int[] arr, int threads){
        int sum = 0;
        int size = (int) Math.floor(arr.length/threads);
        
        SumThread[] sumParts = new SumThread[threads];
        
        // Split the big array into smaller parts
        // The parts take the whole array, but only read the number from the min (i * size) to the max (i+1 * size) indeces
        for(int i = 0; i < threads; i++){
            sumParts[i] = new SumThread(arr, i * size, (i+1) * size);
            sumParts[i].start();
        }
        
        // Joins all the sum parts together so that the main thread does not end befor ethey do
        for(SumThread part : sumParts){
            try {
                part.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SumThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(SumThread part : sumParts){
            sum += part.getChunk();
        }
        
        return sum;
    }
}
