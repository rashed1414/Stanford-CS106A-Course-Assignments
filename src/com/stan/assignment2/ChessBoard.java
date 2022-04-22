/*
* File Name : ChessBoard
*
* this File (Class) used to draw chess board
* */
package com.stan.assignment2;
import acm.graphics.GRect;
import acm.program.*;

public class ChessBoard extends GraphicsProgram {

    private static final int ROWS=8;
    private static final int COLUMNS=8;

    public void run(){

        int squareEdge=getWidth()/COLUMNS;
        int Y=0;

        for(int i=0;i<ROWS;i++){
            int X=0;
            for (int j=0;j<COLUMNS;j++){
                GRect square=new GRect(X,Y,squareEdge,squareEdge);
                square.setFilled((i+j)%2 != 0);
                add(square);
                X+=squareEdge;
            }
            Y+=squareEdge;
        }


    }
}
