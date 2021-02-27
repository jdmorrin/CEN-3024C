/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod5_001;

import java.util.Scanner;

/**
 *
 * @author Juan
 */
public class CEN_Mod5_001 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scan = new Scanner(System.in);
        int times;
        
       
        // Infinite loop for testing multiple inputs
        while(true){
            System.out.println("How many runs will the sequence be executed?");
            times = scan.nextInt();
            
            System.out.println("Iterative ------ Recursive");
            System.out.print(fib(times) + "------");
            System.out.print(reFib(times));
            System.out.println();
        }
        
       
    }
    
    // Iterative fibonacci
    /**
     * 
     * @param num number of times the method will run
     * @return second fibonacci number
     */
    public static int fib(int num){
        int first = 0, second = 1, temp;
        
        for(int i = 1; i < num; i++){
            temp = first + second;
            first = second;
            second = temp;
        }
        
        return second;
    }
    
    // Recusrsive fib
    /**
     * 
     * @param num number of times the method will run
     * @return fibonacci number recursively
     */
    public static int reFib(int num){
        if(num <= 1)
            return 1;
        else
            return reFib(num - 1) + reFib(num -2);
    }
}
