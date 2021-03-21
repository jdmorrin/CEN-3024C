/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cen_mod2_test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Admin
 */
public class TestRunner {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(WordCountTest.class);
        
        for(Failure fail : result.getFailures()){
            System.out.println(fail);
        }
        
        System.out.println(result.wasSuccessful());
    }
}
