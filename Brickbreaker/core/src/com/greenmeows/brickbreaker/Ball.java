package com.greenmeows.brickbreaker;

import java.time.Instant;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Ball {
	private float x,y,dx,dy,radius,speed;
	private Color color;
	private float debounce = Constants.BALLDEBOUNCEINSECS;
	private long lasttime = Instant.now().toEpochMilli();
	
	private void determine_start_velocity() {
		//x
		switch(MathUtils.random(1)) {
		case 0:
			this.dx = -1;
			break;
		case 1:
			this.dx = 1;
			break;
		}
		//y
		switch(MathUtils.random(1)) {
		case 0:
			this.dy = -1;
			break;
		case 1:
			this.dy = 1;
			break;
		}
	}
	
	public Ball(float x, float y, float radius, float speed, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speed = speed;
		this.color = color;
		determine_start_velocity();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void draw() {
		ShapeRenderer render = new ShapeRenderer();
		render.begin(ShapeType.Filled);
		render.setColor(getColor());
		render.circle(getX(), getY(), getRadius());
		render.end();
	}
	
	private void bounce(String object, Paddle p) {
		switch(object) {
		case "wallx":
			this.dx *= -1;
			break;
		case "wally":
			this.dy *= -1;
			break;
		case "paddle":
			this.dy *= -1;
			this.dx = p.getDirection();
			break;
		case "brick":
			this.dy *= -1;
			this.dx *= -1;
			break;
		}
	}
	
	public void logic(Paddle paddle) {
		setX(getX()+this.speed*this.dx*Gdx.graphics.getDeltaTime());
		setY(getY()+this.speed*this.dy*Gdx.graphics.getDeltaTime());
		if(Instant.now().toEpochMilli() - lasttime > debounce*1000) {
			// screen detection
			if(getX() < 0 || getX() > Constants.WIDTH) {
				bounce("wallx", paddle);
				lasttime = Instant.now().toEpochMilli();
			}
			if(getY() < 0 || getY() > Constants.HEIGHT) {
				bounce("wally", paddle);
				lasttime = Instant.now().toEpochMilli();
			}
			// paddle detection
			if(getX() >= paddle.getX() && getX() <= paddle.getX()+paddle.getWidth() && getY() >= paddle.getY()+paddle.getHeight()-getSpeed() && getY() <= paddle.getY()+paddle.getHeight()) {
				bounce("paddle", paddle);
				lasttime = Instant.now().toEpochMilli();
			}
			
			// brick detection
			for(Iterator<Brick> it = Game.bricks.iterator(); it.hasNext();) {
				Brick brick = it.next();
				if(getX() >= brick.getX() && getX() <= brick.getX()+brick.getWidth() && getY() >= brick.getY() && getY() <= brick.getY()+getSpeed()) {
					setColor(brick.getColor());
					bounce("brick", paddle);
					it.remove();
				}
			}
		}
	}
}
