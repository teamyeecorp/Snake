package yee.studios.snake.src;

import static yee.studios.snake.src.Player.EnumMovementDirection.*;

import java.util.ArrayList;
import processing.core.PApplet;
import yee.studios.snake.src.Player.EnumMovementDirection;
import yee.studios.snake.util.PModule;

public class Extension extends PModule {

	private int posX, posY, speed = 50;
	private int distToPlayer;
	private EnumMovementDirection movementDir;
	private ArrayList<MovementTask> tasks = new ArrayList<MovementTask>();
	private boolean isUpdated = false;
	
	public Extension(PApplet p, EnumMovementDirection movementDir, int distToPlayer, int playerX, int playerY) {
		super(p);
		
		this.movementDir = movementDir;
		this.distToPlayer = distToPlayer;
		posY = playerY;
		posX = playerX;
		if (movementDir == UP) {
			this.posY += distToPlayer * speed;
		} else if (movementDir == DOWN) {
			this.posY -= distToPlayer * speed;
		} else if (movementDir == LEFT) {
			this.posX += distToPlayer * speed;
		} else if (movementDir == RIGHT) {
			this.posX -= distToPlayer * speed;
		}
		
	}
	
	public void addMovementTask(MovementTask task) {
		if (isUpdated) {
			tasks.add(task);
		}
	}
	
	private void checkForMovementUpdates() {
		if (tasks.size() >= 1) {
			if (tasks.get(0).shouldPerformMovement()) {
				this.movementDir = tasks.get(0).getDirection();
				tasks.remove(0);
				if (tasks.size() >= 1) {
					tasks.get(0).performStep();
				}
			} else {
				tasks.get(0).performStep();
			}
		}
	}
	
	public void move() {
		checkForMovementUpdates();
		if (this.movementDir == UP) {
			this.posY -= speed;
		} else if (movementDir == DOWN) {
			this.posY += speed;
		} else if (movementDir == LEFT) {
			this.posX -= speed;
		} else if (movementDir == RIGHT) {
			this.posX += speed;
		}
	}
	
	public void notifyFirstChange(EnumMovementDirection direction) {
		if (!isUpdated ) {
			isUpdated = true;
			MovementTask task = new MovementTask(direction);
			task.addRemainingSteps(this.distToPlayer);
			this.addMovementTask(task);
		}
	}
	
	public EnumMovementDirection getCurrentDirection() {
		return this.movementDir;
	}
	
	public void display() {
		parent.fill(46,173,76);
		parent.rect(posX, posY, speed, speed);
	}
	
	public int predictX() {
		if (this.movementDir == LEFT) {
			return this.getPosX() - speed;
		} else if (this.movementDir == RIGHT) {
			return this.getPosX() + speed;
		}
		return this.getPosX();
	}
	
	public int predictY() {
		if (this.movementDir == UP) {
			return this.getPosY() - speed;
		} else if (this.movementDir == DOWN) {
			return this.getPosY() + speed;
		}
		return this.getPosY();
	}
	public int getDistanceToPlayer() {
		return this.distToPlayer;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	

}
