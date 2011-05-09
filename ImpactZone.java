/** 
 * ImpactZone.java
 * By Matthew McNierney for CS5 Lab #2
 * 
 * Defines an impact zone circle.
 * Implements moving bug legs, variable-speed bugs, and Raid!! extra credit.
 */

import java.awt.*;

public class ImpactZone {
	private int radius;						// Current radius of the circle
	private Point impactPoint;		// Center of the impact circle
	private int speed;						// Current speed of change of radius
	
	final private Color COLOR = Color.red; // Color of the circle
	final private int MAX_RADIUS = 200;		 // Max radius of the circle
	final private int GROW_SPEED = 35;		 // Speed at which the radius changes
	
	/**
	 * Constructor. Creates an impact zone based on an initial location.
	 * @param impactLocation - The center of the impact zone
	 */
	public ImpactZone(Point impactLocation) {
		impactPoint = impactLocation;
		radius = 1;
		speed = GROW_SPEED;
	}
	
	/**
	 * Draws an impact zone. Maintains the graphics object's color.
	 * @param g - Graphics object to draw the impact zone.
	 */
	public void draw(Graphics g) {
		Color savedColor = g.getColor();
		g.setColor(COLOR);
		g.drawOval(impactPoint.x - radius, impactPoint.y - radius, 2 * radius,
        2 * radius);
		g.setColor(savedColor);
	}
	
	/**
	 * Grows and shrinks the impact zone.
	 * @return - true if impact zone should still be drawn
	 */
	public boolean growImpact() {
		speed = (radius >= MAX_RADIUS || speed <= 0) ? -GROW_SPEED : GROW_SPEED;
		radius += speed;
		return radius > 0;
	}
}