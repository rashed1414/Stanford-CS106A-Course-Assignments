/*
* File Name : HailStone
* ======================
*
* This File (Class) Used to Solve Mathematical Quiz Hailstone Sequence.
*
* */
package com.stan.assignment2;
import acm.program.*;


public class HailStone extends ConsoleProgram {

    public void run(){

        int input=readInt(" Enter Your Number : ");
        int i=0;

        while (input !=1){

            int in =input;
            if (input % 2 ==0){
                input/=2;
                println(in+" Is Even So I take the half :   "+input);
            }

            else {

                input=input*3+1;
                println(in+" Is Odd So, I Make It 3xn+1 :   "+input);
            }

            i+=1;
        }

        println(" Your Process took : "+i+" To Reach : 1");
    }
}
