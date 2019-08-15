package yee.studios.insert_name_here.src;

import processing.core.PApplet;
import java.awt.KeyboardFocusManager;
import yee.studios.zeldagame.util.Input;

public class MainClass extends PApplet{
	
	public static void main(String[] args) {
		//Insert the full class path of this
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new Input());
		PApplet.main("yee.studios.insert_name_here.src.MainClass");
	}

	@Override
	public void settings() {
		
	}
	
	@Override
	public void setup() {
		
	}
	
	@Override
	public void draw() {
	}
	
}
