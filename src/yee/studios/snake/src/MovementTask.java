package yee.studios.snake.src;

import yee.studios.snake.src.Player.EnumMovementDirection;

public class MovementTask {
	
	private EnumMovementDirection direction;
	private int steps = 0;
	
	public MovementTask(EnumMovementDirection direction) {
		this.direction = direction;
	}
	
	public void performStep() {
		this.steps--;
	}
	
	public boolean shouldPerformMovement() {
		return this.steps == 0;
	}
	
	public EnumMovementDirection getDirection() {
		return this.direction;
	}
	
	public void addRemainingSteps(int steps) {
		this.steps = steps;
	}
	
	public int getRemainingSteps() {
		return this.steps;
	}

}
