/*
* File Name : RectPyramids
* ========================
*
* This File (Class) used to draw Pyramids of Rectangels.
*
* */
package com.stan.assignment2;

import acm.program.*;
import acm.graphics.*;
import java.awt.*;




public class RectPyramids extends GraphicsProgram{

    public  void run() {

        int Brick_Width=30 ,Brick_Height=12 ,BRICKS_IN_BASE=15;
        int x=(((getWidth()/Brick_Width)-Brick_Width)+(getWidth()-(BRICKS_IN_BASE*Brick_Width))/2);
        int y=getHeight();

        for (int i =0 ;i<BRICKS_IN_BASE;i++) {

            for(int j=0;j<BRICKS_IN_BASE-i;j++){

                GRect rect=new GRect(x+(j*Brick_Width),y,Brick_Width,Brick_Height);
                rect.setFilled(false);
                rect.setColor(Color.RED);
                add(rect);
            }
            x=x+(Brick_Width/2);
            y=y-Brick_Height;
        }

    }
}
