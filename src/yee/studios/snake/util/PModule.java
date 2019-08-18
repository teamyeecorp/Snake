package yee.studios.snake.util;

import processing.core.PApplet;

/**
 * @author ABloodMagician
 * Extend this when using Processing Code in an Object. Then use {@link #parent}
 */
public class PModule {

	protected PApplet parent;
	
	public PModule(PApplet p) {
		this.parent = p;
	}
}
