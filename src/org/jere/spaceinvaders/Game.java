package org.jere.spaceinvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends Canvas {

	/** The game's X resolution **/
	public static final int RES_X = 800;
	/** The game's Y resolution **/
	public static final int RES_Y = 600;
	
	/** Buffer strategy used to render accelerated graphics **/
	private BufferStrategy bufferStrategy;
	/** Indicates whether the game is running or not (defaults to true) **/
	public boolean gameRunning = true;
	/** Time elapsed since the last run of the game loop **/
	private long lastLoopTime;
	
	public Game() {
		//create frame to contain game
		JFrame container = new JFrame("Space Invaders - By Jere");
		
		//get the container panel of the frame to setup the game's resolution
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(RES_X, RES_Y));
		panel.setLayout(null);
		
		//setup the canvas size and put it in the content pane
		this.setBounds(0, 0, RES_X, RES_Y);
		panel.add(this);
		
		//since the canvas we're working with is going to be actively redrawn,
		//we need to prevent AWT from attempting to redraw our surface (we will do it ourselves)
		this.setIgnoreRepaint(true);
		
		//and let there be light
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		//create buffer strategy to use accelerated graphics method, and we are all set
		this.createBufferStrategy(2);
		this.bufferStrategy = this.getBufferStrategy();
	}
	
	public void gameLoop() {
		while (this.gameRunning) {
			//calculate how long has it been since the last run of the game loop
			long delta = System.currentTimeMillis() - this.lastLoopTime;
			this.lastLoopTime = System.currentTimeMillis();
			
			//get the current graphics surface and blank it out (black it out, actually)
			Graphics2D graphSurface = (Graphics2D) this.bufferStrategy.getDrawGraphics();
			graphSurface.setColor(Color.BLACK);
			graphSurface.fillRect(0, 0, RES_X, RES_Y);
		}
	}
}
