package com.stan.assignment3;/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICKS_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Speed of the ball bouncing --> Hint : make it larger make the ball slower */

	private static final int BALL_SPEED=30;

/** Offset of the first brick row from the left */

	private static final int BRICK_X_OFFSET= (BRICK_SEP / 2);

/** Number of turns */
	private static final int NTURNS = 3;

/** Bad Object (GRect) */
	private static GRect pad;

/** Ball Object (GOval)*/
	private static GOval ball;

/** Ball Velocity */
	private double vx,vy;
/** gameStatus Variables */
	private int turn;
	private boolean endGame=false;

/** Random generator var*/
	private final RandomGenerator rgen=RandomGenerator.getInstance();

/** int var to represent player score */
	private int scorePoints=0;
	GLabel score;

	/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		addMouseListeners(this);
		setup();
		while(!endGame) {
			printScore();

			moveBall();
			pause(BALL_SPEED);
			checkCollision();
			gameStatus();
			remove(score);
		}
		add(score);
	}

	private void printScore() {
		score = new GLabel(" YOUR SCORE : " + scorePoints, 15, 30);
		score.setFont("Railway-Bold-30");
		score.setColor(Color.black);
		add(score);
	}

	/** Method: gameStatus()
*  Usage: this Method used to check whether the player win or lose
*  Affection: this Method effect on boolean endGame variable
* 	Used by: run()*/
	private void gameStatus() {
		if (turn==0){
			remove(ball);
			endGame=true;
			GLabel label=new GLabel("Game Over !! ");
			label.setFont("RailWay-Bold-22");
			label.setColor(Color.RED);
			add(label,(WIDTH/2.0)-label.getWidth()/2,(HEIGHT/2.0)-label.getAscent());
		}
		if (ball.getY() >= (getHeight()-(BALL_RADIUS*2))){
			turn-=1;
			ball.setLocation(WIDTH/2.0,HEIGHT/2.0);
		}
		if (getElementCount()==2){
			endGame=true;
			GLabel label=new GLabel("Congratulation: You Won !! ");
			label.setFont("RailWay-Italic-28");
			label.setColor(Color.CYAN);
			add(label,(WIDTH/2.0)-label.getWidth()/2,(HEIGHT/2.0)-label.getAscent());
		}
	}

/**  Method: setup()
 *  Usage: this Method used to Create and prepare the required objects and variables
 *  Affection: this Method effect on whole game frame
 * 	Used by:run() */
	private void setup() {
		turn=NTURNS;
		createBricks();
		createBall();
		createPad();
		initDisplacements();
	}

/**  Method: createBricks()
 *  Usage: this Method used to Create bricks in the game (GRect) objects
 *  Affection: this Method effect on  top half of the game frame
 *  used by: this Method used by setup() Method*/
	private void createBricks() {
		int newY=BRICK_Y_OFFSET;
		for (int i = 0; i< NBRICKS_ROWS; i++){
			int newX=BRICK_X_OFFSET;
			for(int j=0;j<NBRICKS_PER_ROW;j++){
				GRect brick = new GRect(newX, newY, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				paintBricks(i, brick);
				add(brick);
				newX+=(BRICK_SEP+BRICK_WIDTH);
			}
			newY+=(BRICK_HEIGHT+BRICK_SEP);
		}

	}

/**  Method: paintBricks()
 *  Usage: this Method used to make bricks colored in the game (GRect) objects
 *  Affection: this Method effect on each brick (GRect) object of the game frame
 *  used by: this Method used by createBricks Method
 * 	Parameters: this Method take 2 parameters an integer that represent row number
  	and GRect object that represent the brick meant to be colored*/
	private void paintBricks(int i, GRect rect) {

		if(i<2){
			rect.setColor(Color.RED);
		}else if (i<4){
			rect.setColor(Color.ORANGE);
		}else if (i<6){
			rect.setColor(Color.YELLOW);
		}else if (i<8){
			rect.setColor(Color.GREEN);
		} else if (i<10) {
			rect.setColor(Color.CYAN);
		}
	}

/**  Method: createBall()
 *  Usage: this Method used to Create ball in the game (GOval) objects
 *  Affection: this Method effect on whole game frame
 *  used by: this Method used by setup() Method*/
	private void createBall() {
		ball=new GOval(WIDTH/2.0,HEIGHT/2.0,BALL_RADIUS*1.5,BALL_RADIUS*1.5);
		ball.setFilled(true);
		add(ball);
	}

/**  Method: createPad()
 *  Usage: this Method used to Create Paddle in the game (GRect) objects
 *  Affection: this Method effect on  last quarter of the game frame
 *  used by: this Method used by setup() Method*/
	private void createPad() {
		pad=new GRect(WIDTH/2.0,HEIGHT - PADDLE_Y_OFFSET,PADDLE_WIDTH,PADDLE_HEIGHT);
		pad.setFilled(true);
		add(pad);
	}

/**  Method: initDisplacement()
 *  Usage: this Method used to initialize the displacement on X and Y axis
 *  Affection: this Method effect on  ball (GOval) object movement
 *  used by: this Method used by setup() Method*/

	private void initDisplacements() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
/**  Method: moveBall()
 *  Usage: this Method used to moving the ball
 *  Affection: this Method effect on  ball (GOval) object movement
 *  used by: this Method used by run() Method*/
	private void moveBall() {
		ball.move(vx, vy);
	}

/**  Method: checkCollision()
 *  Usage: this Method used to check the collision on edges,bricks and paddle
 *  Affection: this Method effect on  ball (GOval) object movement,bricks (GRect) objects existence
 *  used by: this Method used by run() Method*/

	private void checkCollision() {
		checkForEdgeCollision();
		checkForBricksCollision();
		checkPadCollision();
	}

/**  Method: checkEdgeCollision()
 *  Usage: this Method used to check the collision on edges
 *  Affection: this Method effect on  ball (GOval) object movement
 *  used by: this Method used by checkCollision() Method*/

	private void checkForEdgeCollision() {
		if (ball.getX() >= 0 && ball.getY() >= 0) {
			if (ball.getX() >= (WIDTH) - (BALL_RADIUS*2)) vx = -vx;
			if (ball.getY() > HEIGHT - (BALL_RADIUS*2)) vy = -vy;
			ball.move(vx, vy);
		}else {
			if (ball.getX() <= 0) vx = -vx;
			if (ball.getY() <= 0) vy = -vy;
			ball.move(vx, vy);


		}
	}

/**  Method: checkBricksCollision()
 *  Usage: this Method used to check the collision on bricks
 *  Affection: this Method effect on  ball (GOval) object movement and bricks (GRect) object existence
 *  used by: this Method used by checkCollision() Method*/
	private void checkForBricksCollision() {

		if (getElementAt(ball.getX(),ball.getY()) != null &&
			getElementAt(ball.getX(),ball.getY()) !=pad ){

			brickStatus(getElementAt(ball.getX(),ball.getY()));
			vy =-vy;
			ball.move(vx,vy);

		} else if (getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()) != null &&
				   getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()) !=pad ) {

			brickStatus(getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()));
			vy =-vy;
			ball.move(vx,vy);

		} else if (getElementAt(ball.getX(),ball.getY()+BALL_RADIUS) != null &&
				   getElementAt(ball.getX(),ball.getY()+BALL_RADIUS)!=pad) {

			brickStatus(getElementAt(ball.getX(),ball.getY()+BALL_RADIUS));
			vy =-vy;
			ball.move(vx,vy);

		} else if (getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+BALL_RADIUS) != null &&
				   getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+BALL_RADIUS)!=pad) {

			brickStatus(getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+BALL_RADIUS));
			vy =-vy;
			ball.move(vx,vy);

		} else if (getElementAt(ball.getX(),ball.getY()+(BALL_RADIUS/2.0)) != null &&
				   getElementAt(ball.getX(),ball.getY()+(BALL_RADIUS/2.0)) !=pad ) {

			brickStatus(getElementAt(ball.getX(),ball.getY()+(BALL_RADIUS/2.0)));
			vy =-vy;
			ball.move(vx,vy);

		} else if (getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+(BALL_RADIUS/2.0)) != null &&
				   getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+(BALL_RADIUS/2.0)) !=pad ) {

			brickStatus(getElementAt(ball.getX()+(BALL_RADIUS*2),ball.getY()+(BALL_RADIUS/2.0)));
			vy =-vy;
			ball.move(vx,vy);
		}
	}



/**  Method: brickStatus()
 *  Usage: this Method used to change brick's color to add more fun to user
 *  Affection: this Method effect on  bricks (GRect) object color
 *  used by: this Method used by checkForBrickCollision() Method*/
	private void brickStatus(GObject element) {
		if(element.getColor()==Color.CYAN){
			remove(element);
			scorePoints+=1;
		}else if (element.getColor()==Color.GREEN){
			element.setColor(Color.CYAN);
		}else if (element.getColor()==Color.YELLOW){
			element.setColor(Color.GREEN);
		}else if (element.getColor()== Color.ORANGE){
			element.setColor(Color.YELLOW);
		} else if (element.getColor()==Color.RED) {
			element.setColor(Color.ORANGE);
		}
	}



	/**  Method: checkPadCollision()
 *  Usage: this Method used to check the collision on paddle
 *  Affection: this Method effect on  ball (GOval) object movement
 *  used by: this Method used by checkCollision() Method*/
	private void checkPadCollision(){

		if (getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+BALL_RADIUS)==pad){
			vy =-vy;
		}
		ball.move(vx,vy);

	}

/**  Method: mouseMoved()
 *  Usage: this Method used to check mouse movement
 *  Affection: this Method effect on paddle (GRect) object movement
 *  used by: this Method used by addMouseListener() Method*/
	public void mouseMoved(MouseEvent e){
		if (e.getX()<(400-PADDLE_WIDTH))
			pad.setLocation(e.getX(),(HEIGHT-PADDLE_Y_OFFSET*2));

	}

}
