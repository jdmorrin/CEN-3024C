/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod5_001;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * This app executes two fibonacci methods, records their execution time, and plots a chart based on the data.
 * @author Juan
 * 
 */
public class CEN_Mod5_001 extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
        
       
    }
    
    
    /**
     * Iterative fibonacci
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
    
    
    /**
     * Recursive fibonacci
     * @param num number of times the method will run
     * @return fibonacci number recursively
     */
    public static int reFib(int num){
        if(num <= 1)
            return 1;
        else
            return reFib(num - 1) + reFib(num -2);
    }

    @Override
    /**
     * @param stage the primary stage of the JavaFX
     */
    public void start(Stage stage) throws Exception {
        NumberAxis xAxis = new NumberAxis(0,11,1); // The X-Axis goes from 0 to 50 and increments of 10
        xAxis.setLabel("Input");
        
        NumberAxis yAxis = new NumberAxis(0,30000,5000);
        yAxis.setLabel("Time");
        
        LineChart chart = new LineChart(xAxis,yAxis);
        
        // Gets data
        // When adding data, data must be added in (X,Y) format
        XYChart.Series series = new XYChart.Series();
        series.setName("Iterative Fibonacci");
        
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Rcursive Fibonacci");
        
        for(int i = 1; i <= 10; i++){
            
            long startTime = System.nanoTime();
            fib(i);
            long endTime = System.nanoTime();
            
            long duration = endTime - startTime;
            series.getData().add(new XYChart.Data(i, duration));
            
            
            startTime = System.nanoTime();
            reFib(i);
            endTime = System.nanoTime();
            
            long duration2 =  endTime - startTime;
            series2.getData().add(new XYChart.Data(i, duration2));
            
            System.out.println(duration + "     " + duration2);
           
        }
        
        // Now that we have the data, we need to put the data in the chart
        chart.getData().addAll(series, series2);
        
        Group root = new Group(chart);
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Iterative vs Recursive Fibonacci");
        stage.setScene(scene);
        
        stage.show();
        
        
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
