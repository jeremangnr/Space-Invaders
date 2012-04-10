package org.jere.spaceinvaders;

import java.awt.Graphics;

public class Entity {
	/** X position of this entity **/
	protected double x;
	/** Y position of this entity **/
	protected double y;
	/** Value of the entity's velocity in the X axis (pixels/sec) **/
	protected double dx;
	/** Value of the entity's velocity in the Y axis (pixels/sec) **/
	protected double dy;
	/** Visual representation of the entity (Sprite) **/
	protected Sprite sprite;
	
	public Entity(String spriteRef, double pX, double pY) {
		this.sprite = SpriteStore.getInstance().getSprite(spriteRef);
		this.x = pX;
		this.y = pY;
	}
	
	public void move(long delta) {
		// update location of the entity based on it's current position and vertical and horizontal speeds
		// we divide by 1000 here, because time velocity is in pixels/sec, but time is in milliseconds
		this.x += (delta * this.dx) / 1000;
		this.y += (delta * this.dy) / 1000;
	}
	
	public void draw(Graphics g) {
		this.sprite.draw(g, (int) this.x, (int) this.y);
	}
}
