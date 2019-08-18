package yee.studios.snake.src;

import java.util.ArrayList;

import processing.core.PApplet;
import yee.studios.snake.util.PModule;
import static yee.studios.snake.src.Player.EnumMovementDirection.*;

public class Player extends PModule {
	
	public int posX, posY, speed = 50, timeToMove = 0;

	private ArrayList<Extension> extensions = new ArrayList<Extension>();
	private EnumMovementDirection movementDir = UP;
	private boolean movementAlreadyUpdated = false;
	private int stepsSinceLastChange = 0;
	private int score = 0;
	private boolean isGameOver = false;
	private Apple apple;
	
	public Player(PApplet p, int posX, int posY) {
		super(p);
		
		this.posX = posX;
		this.posY = posY;
		this.apple = new Apple(p);
		spawnApple();
	}
	
	public void increaseLenght() {
		extensions.add(new Extension(parent, movementDir, extensions.size() + 1, posX, posY));
	}
	
	public void display() {
		parent.fill(46,173,76);
		parent.rect(posX, posY, speed, speed);
		for (Extension extension : extensions) {
			extension.display();
		}
		apple.display();
	}
	
	public void move() {
		if (timeToMove == 23) {
			stepsSinceLastChange++;
			this.movementAlreadyUpdated = false;
			predictMovement();
			if (!isGameOver()) {
				if (this.movementDir == UP) {
					this.posY -= speed;
				} else if (movementDir == DOWN) {
					this.posY += speed;
				} else if (movementDir == LEFT) {
					this.posX -= speed;
				} else if (movementDir == RIGHT) {
					this.posX += speed;
				}
				
				for (Extension extension : extensions) {
					extension.move();
				}
				
				timeToMove = 0;
			}
		} else {
			timeToMove++;
		}
	}
	
	public void updateMovement(EnumMovementDirection movementDir) {
		if (movementDir != this.movementDir && !movementDir.isOpposite(this.movementDir) && !movementAlreadyUpdated) {
			for (Extension extension : extensions) {
				MovementTask task = new MovementTask(movementDir);
				if (!isAlignedWithPlayer(extension)) {
					task.addRemainingSteps(stepsSinceLastChange);
				} else {
					task.addRemainingSteps(extension.getDistanceToPlayer());
				}
				extension.addMovementTask(task);
				extension.notifyFirstChange(movementDir);
			}
			stepsSinceLastChange = 0;
			this.movementDir = movementDir;
			this.movementAlreadyUpdated = true;
		}
	}
	
	private boolean isAlignedWithPlayer(Extension e) {
		int arrayPos = e.getDistanceToPlayer() - 1;
		for (int i = 0; i <= arrayPos; i++) {
			if (extensions.get(i).getCurrentDirection() != this.movementDir) {
				return false;
			}
		}
		return true;
	}
	
	private void onCollideWithApple() {
		this.score++;
		this.increaseLenght();
		System.out.println(score);
		this.spawnApple();
	}
	
	private void spawnApple() {
		this.apple.setNotVerified();
		boolean flag = true;
		int safetyFirst = 0;
		while (!this.apple.isVerified()) {
			this.apple.suggestResummonPos();
			if (safetyFirst >= 150) {
				System.out.println("Be glad. I safed your life");
				break;
			}
			if (this.posX != this.apple.getSuggestedX() && this.posY != this.apple.getSuggestedY()) {
				for (Extension e : extensions) {
					if (e.getPosX() == this.apple.getSuggestedX() || e.getPosY() == this.apple.getSuggestedY()) {
						flag = false;
						System.out.print("Extension");
						break;
					}
				}
				if (flag) {
					apple.verifyPosition();
					return;
				}
			} else {
				System.out.println("Player");
			}
			safetyFirst++;
		}
		this.apple.suggestResummonPos();
		this.apple.verifyPosition();
	}
	
	private void setGameOver() {
		this.isGameOver = true;
		System.out.println("gameover");
	}
	
	private void predictMovement() {
		//Predict Movement
		int predictedX = this.posX;
		int predictedY = this.posY;
		if (this.movementDir == UP) {
			predictedY -= speed;
		} else if (this.movementDir == DOWN) {
			predictedY += speed;
		} else if (this.movementDir == LEFT) {
			predictedX -= speed;
		} else if (this.movementDir == RIGHT) {
			predictedX += speed;
		}
		//Score
		if (predictedX == this.apple.getPosX() && predictedY == this.apple.getPosY()) {
			this.onCollideWithApple();
		}
		//Game Over
		if (predictedX < 0 || predictedX >= 751 || predictedY < 0 || predictedY >= 751) {
			this.setGameOver();
		}
		for (Extension e : extensions) {
			if (predictedX == e.predictX() && predictedY == e.predictY()) {
				this.setGameOver();
			}
		}
 	}
	
	public boolean isGameOver() {
		return isGameOver;
	}
	
	public int getScore() {
		return score;
	}
	
	public static enum EnumMovementDirection {
		UP,
		DOWN,
		RIGHT,
		LEFT;
		
		public boolean isOpposite(EnumMovementDirection movementDir) {
			if (this == UP) return movementDir == DOWN;
			if (this == DOWN) return movementDir == UP;
			if (this == RIGHT) return movementDir == LEFT;
			if (this == LEFT) return movementDir == RIGHT;
			return false;
		}
	}
	
}
