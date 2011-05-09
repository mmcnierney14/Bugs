/**
 * Bug.java
 * By Matthew McNierney for CS5 Lab #2
 * 
 * Represents a single bug.
 * Implements moving bug legs, variable-speed bugs, and Raid!! extra credit.
 */

import java.awt.*;
import java.applet.Applet;
import java.util.Random;

public class Bug {
	private Point position;			// The bug's current position
	private int speed;					// The bug's speed
	private int imgIndex;				// The index of the current bug image to draw
	
	final private static int WIDTH = 33;		// Width of the bug image
	final private static int HEIGHT = 50;		// Height of the bug image
	
	private static Applet applet;			// Reference to the applet
	private static Image[] bugImg;		// Array of bug animation frames
	
	/**
	 * Initializes the information needed to allow the bug to draw itself. Called
	 * in init() method in main applet.
	 * @param bugImgArray - The array of images to provide bug animation
	 * @param app - Reference to the applet
	 */
	public static void initializeBugs(Image[] bugImgArray, Applet app) {
		applet = app;
		bugImg = bugImgArray;
	}
	
	/**
	 * Constructor. Sets the initial position of the bug, randomizes the bug's
	 * speed, and initializes animation.
	 * @param bugPos - The initial position of the bug.
	 */
	public Bug(Point bugPos) {
		position = bugPos;
		
		// Make the bug's speed random
		Random generator = new Random();
		speed = generator.nextInt(8) + 1;
		
		// Set imgIndex for bug animation
		imgIndex = 0;
	}
	
	/**
	 * Updates imgIndex to represent the next animation frame and moves the bug.
	 * @return - true if bug is still visible, false otherwise
	 */
	public boolean crawl() {
		imgIndex = (imgIndex < bugImg.length-1) ? imgIndex+1 : 0;
		position.y -= speed;
		return position.y > -HEIGHT;
	}
	
	/**
	 * Tests whether a point is inside the bug.
	 * @param hitPoint - Point to test
	 * @return - true if the point is inside the bug, false otherwise
	 */
	public boolean hitTest(Point hitPoint) {
		return
			hitPoint.x >= position.x &&
			hitPoint.x <= position.x + WIDTH &&
			hitPoint.y >= position.y &&
			hitPoint.y <= position.y + HEIGHT;
	}
	
	/**
	 * Allows the bug to draw itself based on its position and current animation
	 * frame.
	 * @param g - Graphics object to draw the bug
	 */
	public void draw(Graphics g) {
		g.drawImage(bugImg[imgIndex], position.x, position.y, applet); 
	}
}