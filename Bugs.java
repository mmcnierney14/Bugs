/**
 * Bugs.java
 * By Matthew McNierney for CS5 Lab #2
 * 
 * Main applet.
 * Implements moving bug legs, variable-speed bugs, and Raid!! extra credit.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Bugs extends Applet implements ActionListener,MouseListener {
	Image[] bugImg;			// Array of animation frames for bugs
	Timer timer;				// Timer to control animation
	Swarm swarm;				// Object that holds the bugs to be drawn
	
	ImpactZoneContainer impacts; // Holds the impact zones to be drawn
	
	final int APPLET_HEIGHT = 400; 		// Height of the applet
	final int APPLET_WIDTH = 350;			// Width of the applet
	final int BUG_FRAMES = 8;					// Number of animation frames for the bug
	
	/**
	 * Init method. Sets up bugs, timer, and listeners.
	 */
	public void init() {
		// Create array of bug animation frames
		bugImg = new Image[BUG_FRAMES];
		for (int i = 0; i <= BUG_FRAMES-1; i++)
			bugImg[i] = getImage(getCodeBase(),"bug"+i+".gif");
		
		// Pass bug frames array and reference to applet to the Bug class
		Bug.initializeBugs(bugImg,this);
		
		// Create swarm with 500 bug capacity
		swarm = new Swarm(500);
		
		// Create impact zone container with 100 impact capacity
		impacts = new ImpactZoneContainer(100);
		
		// Set up listeners, timers
		addMouseListener(this);
		timer = new Timer(50,this);
		
		// Set up applet
		setBackground(Color.pink);
		setSize(APPLET_WIDTH,APPLET_HEIGHT);
		
		repaint();
	}
	
	/**
	 * Paint method. Draws bugs and prints the number of visible bugs at the
	 * bottom left corner of the applet window.
	 */
	public void paint(Graphics g) {
		swarm.drawBugs(g);
		impacts.drawImpacts(g);
		g.drawString("Bugs: " + swarm.getNumBugs(), 10, APPLET_HEIGHT-10);
	}
	
	
	// EVENTS
	/**
	 * Timer event. Moves bugs and repaints the applet window.
	 */
	public void actionPerformed(ActionEvent event) {
		swarm.moveBugs();
		impacts.growImpacts();
		repaint();
	}
	
	/**
	 * mouseClicked. On double click, create a new bug. On single click, terminate
	 * any bugs under the mouse.
	 */
	public void mouseClicked(MouseEvent event) {
		Point clickPoint = event.getPoint(); // Point where the mouse clicked
		
		// On single click
		if (event.getClickCount() == 1) {
			swarm.hitTestTerminateBug(clickPoint);
			impacts.addImpact(clickPoint);
		}
		
		// On double click
		else if (event.getClickCount() == 2)
			swarm.addBug(clickPoint);
		
		
	}
	
	/**
	 * mouseEntered. Start timer, i.e. start the animation.
	 */
	public void mouseEntered(MouseEvent event) {
		timer.start();
	}
	
	/**
	 * mouseExited. Stop timer, i.e. stop the animation.
	 */
	public void mouseExited(MouseEvent event) {
		timer.stop();
	}
	
	// Empty definitions for other mouse events
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}