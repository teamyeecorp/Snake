package yee.studios.snake.src;

import processing.core.PApplet;
import processing.core.PFont;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import yee.studios.snake.src.Player.EnumMovementDirection;
import yee.studios.snake.util.Input;

public class Snake extends PApplet {
	
	public Player player;
	public PFont f;
	
	public static void main(String[] args) {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new Input());
		//Insert the full class path of this
		PApplet.main("yee.studios.snake.src.Snake");
	}
	
	@Override
	public void settings() {
		size(801, 801);
	}
	
	@Override
	public void setup() {
		frameRate(60);
		player = new Player(this, 400, 500);
		f = createFont("Arial", 32, true);
	}
	
	@Override
	public void draw() {
		background(255);
		player.display();
		if (!player.isGameOver()) {
			player.move();
			if (Input.isButtonDown("w") || Input.isButtonDown(KeyEvent.VK_UP)) {
				player.updateMovement(EnumMovementDirection.UP);
			} else if (Input.isButtonDown("s") || Input.isButtonDown(KeyEvent.VK_DOWN)){
				player.updateMovement(EnumMovementDirection.DOWN);
			} else if (Input.isButtonDown("a") || Input.isButtonDown(KeyEvent.VK_LEFT)) {
				player.updateMovement(EnumMovementDirection.LEFT);
			} else if (Input.isButtonDown("d") || Input.isButtonDown(KeyEvent.VK_RIGHT)) {
				player.updateMovement(EnumMovementDirection.RIGHT);
			}
		}
		textFont(f, 64);
		fill(0);
		text(player.getScore(), 30, 100);
	}
	
}
