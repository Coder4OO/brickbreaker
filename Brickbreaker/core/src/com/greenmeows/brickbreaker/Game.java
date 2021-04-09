package com.greenmeows.brickbreaker;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
public class Game extends ApplicationAdapter {
	Paddle paddle;
	Ball ball;
	public static ArrayList<Brick> bricks;
	
	
	private ArrayList<Brick> generate_bricks(int rows, int coloumns) {
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		for(int y=0; y < rows; y++) {
			for(int x=0; x < coloumns; x++) {
				int yfac = y+1;
				bricks.add(new Brick(x*Constants.WIDTH/coloumns, Constants.HEIGHT-(yfac*Constants.BRICKHEIGHT), Constants.WIDTH/coloumns, Constants.BRICKHEIGHT, Constants.RAINBOW[MathUtils.random(Constants.RAINBOW.length-1)]));
			}
		}
		return bricks;
	}
	
	private void init() {
		paddle = new Paddle(Constants.PADDLEX, Constants.PADDLEY, Constants.PADDLEWIDTH, Constants.PADDLEHEIGHT, Constants.PADDLESPEED, Constants.DEFAULTPADDLECOLOUR);
		ball = new Ball(Constants.BALLX, Constants.BALLY, Constants.BALLRADIUS, Constants.BALLSPEED, Constants.DEFAULTBALLCOLOUR);
		bricks = generate_bricks(Constants.BRICKROWS, Constants.BRICKCOLOUMNS);
	}
	
	@Override
	public void create () {
		init();
	}
	
	private void shapes() {
		paddle.draw();
		ball.draw();
		for(Brick brick:bricks) {
			if(bricks.contains(brick)){
				brick.draw();
			}
		}
	}
	
	
	private void draw() {
		shapes();
	}
	
	private void logic() {
		paddle.logic();
		ball.logic(paddle);
	}
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw();
		logic();
	}
	
	@Override
	public void dispose () {

	}
}
