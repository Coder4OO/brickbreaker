package com.greenmeows.brickbreaker;

import com.badlogic.gdx.graphics.Color;

public class Constants {
	//window
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH/16*9;
	public static final String TITLE = "Brickbreaker";

	//paddle
	public static final float PADDLEWIDTH = WIDTH/5;
	public static final float PADDLEHEIGHT = HEIGHT/15;
	public static final float PADDLEX = WIDTH/3;
	public static final float PADDLEY = HEIGHT/10*2;
	public static final float TIMEINSECS = 0.75F;
	public static final float PADDLESPEED = WIDTH/TIMEINSECS;
	public static final Color DEFAULTPADDLECOLOUR = Color.RED;
	
	//ball
	public static final float BALLRADIUS = WIDTH/64;
	public static final float BALLX = WIDTH/2-BALLRADIUS;
	public static final float BALLY = HEIGHT/2-BALLRADIUS;
	public static final float BALLSPEED = PADDLESPEED/3F;
	public static final Color DEFAULTBALLCOLOUR = Color.CHARTREUSE;
	public static final float BALLDEBOUNCEINSECS = 0.1F;
	
	//bricks
	public static final int BRICKROWS = 5;
	public static final int BRICKCOLOUMNS = 5;
	public static final float BRICKHEIGHT = 75F;
	
	//util
	public static final Color[] RAINBOW = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.VIOLET};

	//gamestates
	public static final int MENU = 0;
	public static final int GAME = 1;
	public static final int MESSAGE = 2;
}
