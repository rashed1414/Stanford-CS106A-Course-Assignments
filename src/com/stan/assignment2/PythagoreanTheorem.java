/*
* File Name : PythagoreanTheorem
* ==============================
*
* THis File (Class) used to solve the mathematical problem (PythagoreanTheorem)
*
* */
package com.stan.assignment2;
import acm.program.*;


public class PythagoreanTheorem extends ConsoleProgram {
    public void run(){

        int FIRST_INPUT=readInt("a = ");
        int SECOND_INPUT=readInt("b =  ");

        println("c = "+Math.sqrt(Math.pow(FIRST_INPUT,2)+Math.pow(SECOND_INPUT,2)));
    }
}
