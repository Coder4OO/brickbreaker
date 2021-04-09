package com.greenmeows.brickbreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
public class Paddle {
	
	private float x, y, width, height, speed, dx;
	private Color color;
	public Paddle(float x, float y, float width, float height, float speed, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.color = color;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public float getDirection() {
		return this.dx;
	}
	
	public void setX(float x) {
		this.x = x; 
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	
	
	public void draw() {
		 ShapeRenderer render = new ShapeRenderer();
		 render.begin(ShapeType.Filled);
		 render.setColor(color);
		 render.rect(x, y, width, height);
		 render.end();
	}
	
	public void logic() {
		if(Gdx.input.isKeyPressed(Keys.D) && this.getX() < Constants.WIDTH-this.getWidth()) {
			dx = 1;
			this.setX(this.getX()+this.speed*Gdx.graphics.getDeltaTime()*this.dx);
		}
		else if(Gdx.input.isKeyPressed(Keys.A) && this.getX() > 0) {
			dx = -1;
			this.setX(this.getX()+this.speed*Gdx.graphics.getDeltaTime()*this.dx);
		}
		else {
			dx = 0;
		}
	}
	
}
