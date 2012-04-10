package org.jere.spaceinvaders;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
	/** Image for this sprite **/
	private Image image;
	
	/**
	 * Creates a new Sprite based on an existing Image
	 * 
	 * @param image The image that represents this sprite
	 */
	public Sprite(Image pImage) {
		this.image = pImage;
	}
	
	/**
	 * Get the width of the sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return this.image.getWidth(null);
	}
	
	/**
	 * Get the height of the sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return this.image.getHeight(null);
	}
	
	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param gSurface The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 */
	public void draw(Graphics gSurface, int x, int y) {
		gSurface.drawImage(this.image, x, y, null);
	}
}
