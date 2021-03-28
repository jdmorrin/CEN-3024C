/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw8;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class Concurrency{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        final int SIZE = 200000000;
        int[] arr = new int[SIZE];
        
        // GFenerate a randome stream of integers between 1 and 10
        Random r = new Random();
        
        for(int i = 0; i < SIZE; i++){
            arr[i] = r.nextInt(10) + 1;
        }

       //System.out.println(singleSumThread(arr));

       int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of cores: " + cores);
       //parallelSum(arr);
        
       long start = System.currentTimeMillis();
       System.out.println("Linear sum result: "+SumThread.linearSum(arr));
        System.out.println("Linear sum time: " + (System.currentTimeMillis() - start));
        
       start = System.currentTimeMillis();
       System.out.println("Parallel sum result: " + SumThread.parallelSum(arr, cores));
        System.out.println("Parallel sum time: " + (System.currentTimeMillis() - start));
    }
        
    
}
