 /**
 * Swarm.java
 * By Matthew McNierney for CS5 Lab #2
 * 
 * Keeps track of all of the bugs to be drawn in the applet.
 * Implements moving bug legs, variable-speed bugs, and Raid!! extra credit.
 */

import java.awt.*;

public class Swarm {
	private Bug bugs[];				// Holds all of the bug objects currently drawn
	private int numBugs;			// The number of bugs currently being drawn
	
	/**
	 * Constructor. Creates array of Bugs and sets the initial number of bugs to
	 * 0.
	 * @param maxBugs - The maximum number of the bugs that can be drawn at any
	 * given time
	 */
	public Swarm(int maxBugs) {
		bugs = new Bug[maxBugs];
		numBugs = 0;
	}
	
	/**
	 * Getter method for the number of bugs currently being drawn.
	 * @return - numBugs
	 */
	public int getNumBugs() {
		return numBugs;
	}
	
	/**
	 * Draws all of the bugs.
	 * @param g - Graphics objects to draw the bugs
	 */
	public void drawBugs(Graphics g) {
		for (int i = 0; i < numBugs; i++)
			bugs[i].draw(g);
	}
	
	/**
	 * Adds a bug to the array if there's room.
	 * @param initPos - Initial position of the bug
	 */
	public void addBug(Point initPos) {
		if (numBugs < bugs.length)
			bugs[numBugs++] = new Bug(initPos);
	}
	
	/**
	 * Moves all of the bugs if they are still visible. Adds all of the currently
	 * visible bugs to a temporary array and then replaces the bugs array with
	 * the temporary array.
	 */
	public void moveBugs() {
		Bug[] tempSwarm = new Bug[bugs.length]; // Temp array to hold visible bugs
		int tempBugCount = 0;										// Temp var for # of visible bugs

		// Move and animate all visible bugs and add them to the visible array
		for (int i = 0; i < numBugs; i++)
			if (bugs[i].crawl())
				tempSwarm[tempBugCount++] = bugs[i];
		
		// Set bugs and numBugs to the temporary array and variable
		bugs = tempSwarm;
		numBugs = tempBugCount;
	}
	
	/**
	 * Tests whether a point is within a bug and terminates all bugs that include
	 * the point.
	 * @param hitPoint - The point to test
	 */
	public void hitTestTerminateBug(Point hitPoint) {
		Bug[] tempSwarm = new Bug[bugs.length];	// Temp array to hold bugs to keep
		int tempBugCount = 0;										// Temp var for # of bugs to keep

		// Test point for each bug and add it to the temp array if it should be
		// kept.
		for (int i = 0; i < numBugs; i++)
			if (!bugs[i].hitTest(hitPoint))
				tempSwarm[tempBugCount++] = bugs[i];
		
		// Set bugs and numBugs to the temporary array and variable
		bugs = tempSwarm;
		numBugs = tempBugCount;
	}
}