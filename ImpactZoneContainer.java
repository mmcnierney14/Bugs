 /**
 * ImpactZoneContainer.java
 * By Matthew McNierney for CS5 Lab #2
 * 
 * Keeps track of all of the impact zones to be drawn in the applet.
 * Implements moving bug legs, variable-speed bugs, and Raid!! extra credit.
 */

import java.awt.*;

public class ImpactZoneContainer {
	private ImpactZone impacts[];// Holds all of the impact objects currently drawn
	private int numImpacts;			 // The number of impacts currently being drawn
	
	/**
	 * Constructor. Creates array of impact zones and sets the initial number of
	 * impacts to 0.
	 * @param maxImpacts - The maximum number of the impacts that can be drawn at
	 * any given time
	 */
	public ImpactZoneContainer(int maxImpacts) {
		impacts = new ImpactZone[maxImpacts];
		numImpacts = 0;
	}
	
	/**
	 * Draws all of the impacts.
	 * @param g - Graphics objects to draw the impacts
	 */
	public void drawImpacts(Graphics g) {
		for (int i = 0; i < numImpacts; i++)
			impacts[i].draw(g);
	}
	
	/**
	 * Adds an impact to the array if there's room.
	 * @param initPos - Initial position of the impact
	 */
	public void addImpact(Point initPos) {
		if (numImpacts < impacts.length)
			impacts[numImpacts++] = new ImpactZone(initPos);
	}
	
	/**
	 * Updates the impacts if they are still visible. Adds all of the currently
	 * visible impacts to a temporary array and then replaces the impacts array
	 * with the temporary array.
	 */
	public void growImpacts() {
		// Temp array to hold visible impacts
		ImpactZone[] tempImpacts = new ImpactZone[impacts.length];
		// Temp var for # of visible impacts
		int tempImpactCount = 0;										

		// Move and animate all visible impacts and add them to the visible array
		for (int i = 0; i < numImpacts; i++)
			if (impacts[i].growImpact())
				tempImpacts[tempImpactCount++] = impacts[i];
		
		// Set impacts and numImpacts to the temporary array and variable
		impacts = tempImpacts;
		numImpacts = tempImpactCount;
	}
}