/*
* File Name : FindRange
* ======================
*
* THis File used to find (Min - Max - Range) of some Numbers Until User Enter 0 .
*
* There is additional conditions in the code have Fun.
*
* */
package com.stan.assignment2;
import acm.program.*;


public class FindRange extends ConsoleProgram {

    private  static final int SENTINAL=0;

    public void run(){

        int in= readInt(" ? ");

        if (in != SENTINAL) {

            int min=in,max=in;

            while (true) {

                int input = readInt(" ? ");

                if (input == SENTINAL) break;
                if (min>input) min=input;
                if (max<input) max=input;

            }

            println(" The Smallest Num is : "+min);
            println(" The Largest Num is : "+max);
            println(" Your Num Range is : "+(max-min));

        }

        else {

            println(("Bad News First number is 0"));
        }
    }

}
