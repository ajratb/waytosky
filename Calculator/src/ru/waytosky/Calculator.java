/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

/**
 *
 * @author Ayrat
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a=2;
        int b=2;
        System.out.println("let's add a to b: ");
        System.out.println( "a="+a+" b="+b);
        Adder adder=new Adder();
        System.out.println("a+b="+adder.add(a, b));
        System.out.println("And now let's divide them");
        Divider divider=new Divider();
        System.out.println("a/b=" +divider.divide(a, b));
    }
    
}
