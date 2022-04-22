/*
* File Name : SubClassDiagram
=============================

* This File (Class) used to represent hierarchical Structure of Program class
============================================================================

* This File (Class) contain 3 method for printing Rectangles,Lines and Texts;

 */

package com.stan.assignment2;
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class SubClassDiagram extends GraphicsProgram{

    private static final int HEIGHT=100, WIDTH=200;


    public void run(){

        int starterX=getWidth()/2,starterY=getWidth()/2;
        int padY=starterY+(HEIGHT*2), padX=WIDTH/2;

        drawRectangels(starterX,starterY);
        drawRectangels(starterX-(padX*3),padY);
        drawRectangels(starterX,padY);
        drawRectangels(starterX+(padX*3),padY);

        drawLines(starterX,starterY, starterX-(padX*2), padY);
        drawLines(starterX,starterY,(starterX+padX),padY);
        drawLines(starterX,starterY,starterX+(padX*4),padY);

        //TODO Modify insetText Method to be Dynamic
        insertText("Program",(starterX+HEIGHT/5),(starterY+WIDTH/2),"RailWay-Bold-20");
        insertText("GraphicsProgram",(int)(starterX-HEIGHT*2.8),(padY+WIDTH/3),"Railway-Italic-20");
        insertText("ConsoleProgram",(starterX+HEIGHT/6),(padY+WIDTH/3),"Railway-Italic-20");
        insertText("DialogProgram",(int)(starterX+HEIGHT*3.3),(padY+WIDTH/3),"Railway-Italic-20");



    }

/*
* This Method used to draw Text on the screen.
*
* It's Required 3 Parameters 1 string , 2 int.
* */
    private void insertText(String text,int x,int y,String font) {

        GLabel label=new GLabel(text,x,y);
        label.setFont(font);
        label.setColor(Color.RED);
        add(label);

    }
/*
* This Method used to draw Lines on Screen.
*
* It is Required 4 int Parameters.
*/

    private void drawLines(int firstX,int firstY,int Lastx, int Lasty) {

        int StarterXLine=firstX+(WIDTH/2),StarterYLine=firstY+(HEIGHT);

        GLine line=new GLine(StarterXLine,StarterYLine,Lastx,Lasty);
        add(line);


    }
/*
* This Method Used To Draw Rectangles On Screen
*
* It is Required 2 Int Parameter
* */
    private void drawRectangels(int x, int y){

        GRect rect=new GRect(x,y,WIDTH,HEIGHT);
        add(rect);


    }
}
