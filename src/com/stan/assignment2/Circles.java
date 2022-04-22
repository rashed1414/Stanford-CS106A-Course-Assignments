/*
* File Name : Circles
*
* This File (Class) used to draw logo contain 3 Circles 2 Red 1 White.
* */
package com.stan.assignment2;
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Circles extends GraphicsProgram{
    public void run(){
        int starterX=getWidth()/2,starterY=getHeight()/2;
        int r=72;

        drawCircle(starterX,starterY,r,Color.RED);
        drawCircle(starterX+(r/5),starterY+(r/5),r*0.63,Color.WHITE);
        drawCircle((starterX+(r/10)*4)-2,(starterY+(r/10)*4)-3,r*0.3,Color.RED);
    }

    private void drawCircle(int X ,int Y,double r,Color color) {

        GOval oval=new GOval(X,Y,r,r);
        oval.setFilled(true);
        oval.setFillColor(color);
        add(oval);
    }
}
