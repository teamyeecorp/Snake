package yee.studios.snake.src;

import java.util.Random;

import processing.core.PApplet;
import yee.studios.snake.util.PModule;

public class Apple extends PModule {

	private int posX, posY;
	private int suggestedX, suggestedY;
	private Random random = new Random();
	private boolean	isVerified = false;
	
	public Apple(PApplet p) {
		super(p);
	}
	
	public void suggestResummonPos() {
		this.suggestedX = random.nextInt(750 / 50) * 50;
		this.suggestedY = random.nextInt(750 / 50) * 50;
	}
	
	public void setNotVerified() {
		this.isVerified = false;
	}
	
	public boolean isVerified() {
		return isVerified;
	}
	
	public void verifyPosition() {
		this.isVerified = true;
		this.posX = suggestedX;
		this.posY = suggestedY;
	}
	
	public int getSuggestedX() {
		return this.suggestedX;
	}
	
	public int getSuggestedY() {
		return suggestedY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void display() {
		parent.fill(255, 25, 25);
		parent.rect(posX, posY, 50, 50);
	}
	
}
