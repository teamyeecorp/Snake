package yee.studios.insert_name_here.util;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author ABloodMagician
 * Use {@link #isButtonDown(char)} or {@link #isButtonDown(String)} to test if the given Button is pressed
 * When checking for a KeyCode use {@link #isButtonDown(int)}
 */
public class Input implements KeyEventDispatcher{

	private static ArrayList<Character> pressedKeys = new ArrayList<Character>();
	private static ArrayList<Integer> pressedKeyCodes = new ArrayList<Integer>();
	private static boolean isShiftKeyDown = false;
	private static boolean isControllKeyDown = false;
	
	/**
	 * Method to recognize KeyChanges
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		synchronized(Input.class) {
			switch(event.getID()) {
				case KeyEvent.KEY_PRESSED:
					if (!pressedKeys.contains(event.getKeyChar())) {
						pressedKeys.add(event.getKeyChar());
					} 
					
					if (!pressedKeyCodes.contains(event.getKeyCode())) {
						pressedKeyCodes.add(event.getKeyCode());
					}
					
					if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
						isShiftKeyDown = true;
					}
					
					if (event.getKeyCode() == KeyEvent.VK_CONTROL) {
						isControllKeyDown = true;
					}
					break;
				case KeyEvent.KEY_RELEASED:
					if (pressedKeys.contains(event.getKeyChar())) {
						pressedKeys.remove((Object)event.getKeyChar());
					}
					
					if (pressedKeyCodes.contains(event.getKeyCode())) {
						pressedKeyCodes.remove((Object) event.getKeyCode());
					}
					
					if (event.getKeyCode() == KeyEvent.VK_SHIFT) {
						isShiftKeyDown = false;
					}
					
					if (event.getKeyCode() == KeyEvent.VK_CONTROL) {
						isControllKeyDown = false;
					}
					break;
			}
			return false;
		}
	}
	
	/**
	 * @return Is the shift Key pressed?
	 */
	public static boolean isShiftKeyDown() {
		synchronized (Input.class) {
			return isShiftKeyDown;
		}
	}
	
	/**
	 * @return Is the controll Key pressed?
	 */
	public static boolean isCtrlKeyDown() {
		synchronized (Input.class) {
			return isControllKeyDown;
		}
	}
	
	/**
	 * @param c The Button
	 * @return Is the button pressed?
	 */
	public static boolean isButtonDown(char c) {
		synchronized (Input.class) {
			return pressedKeys.contains(c);
		}
	}
	
	/**
	 * @param s The utton
	 * @return Is the utton pressed?
	 */
	public static boolean isButtonDown(String s) {
		return isButtonDown(s.charAt(0));
	}
	
	/**
	 * @param keyCode The uttons KeyCode
	 * @return Is the utton down?
	 */
	public static boolean isButtonDown(int keyCode) {
		synchronized (Input.class) {
			return pressedKeyCodes.contains(keyCode);
		}
	}

	
}
