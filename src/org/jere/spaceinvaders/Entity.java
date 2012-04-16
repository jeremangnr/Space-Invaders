package org.jere.spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
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
	/** The entity needs to be aware of the game world it exists in **/
	protected Game game;
	/** Rectangle used as bounding-box for collision detection of THIS entiy **/
	protected Rectangle me = new Rectangle();
	/** Rectangle used as bounding-box for collision detection of the entiy I'm colliding with **/
	protected Rectangle him = new Rectangle();
	
	/**
	 * Creates an entity based on a sprite (a reference to it) and places it at X, Y coordinates
	 * 
	 * @param pGame The game world in which the entity is contained
	 * @param spriteRef String specifying the location of the sprite
	 * @param pX X Position
	 * @param pY Y Position
	 */
	public Entity(Game pGame, String spriteRef, double pX, double pY) {
		this.game = pGame;
		this.sprite = SpriteStore.getInstance().getSprite(spriteRef);
		this.x = pX;
		this.y = pY;
	}
	
	/**
	 * Get the entity's current X position
	 * 
	 * @return The entity's currents position on the X axis
	 */
	protected double getXPosition() {
		return x;
	}
	
	/**
	 * Set a new X position for the entity
	 * 
	 * @param x The entity's new X position
	 */
	protected void setXPosition(double x) {
		this.x = x;		
	}	
	
	/**
	 * Get the entity's current Y position
	 * 
	 * @return The entity's currents position on the Y axis
	 */
	protected double getYPosition() {
		return y;
	}
	
	/**
	 * Set a new Y position for the entity
	 * 
	 * @param y The entity's new Y position
	 */
	protected void setYPosition(double y) {
		this.y = y;		
	}
	
	/**
	 * Return the value of the entity's horizontal speed 
	 * 
	 * @return The value of the entity's horizontal speed
	 */
	protected double getXSpeed() {
		return dx;
	}

	/**
	 * Set a new value for the entity's horizontal speed
	 * 
	 * @param dx The new value of the entity's horizontal speed
	 */
	protected void setXSpeed(double dx) {
		this.dx = dx;
	}

	/**
	 * Return the value of the entity's vertical speed 
	 * 
	 * @return The value of the entity's vertical speed
	 */
	protected double getYSpeed() {
		return dy;
	}

	/**
	 * Set a new value for the entity's vertical speed
	 * 
	 * @param dy The new value of the entity's vertical speed
	 */
	protected void setYSpeed(double dy) {
		this.dy = dy;
	}

	/**
	 * Moves the entity according to it's current position, speed and time elapsed since the last move 
	 * 
	 * @param delta Time elapsed since the entity was last updated
	 */
	public void move(long delta) {
		// update location of the entity based on it's current position and vertical and horizontal speeds
		// we divide by 1000 here, because time velocity is in pixels/sec, but time is in milliseconds
		this.x += (delta * this.dx) / 1000;
		this.y += (delta * this.dy) / 1000;
	}
	
	/**
	 * Draw the entity onto the given graphics surface
	 * 
	 * @param g The graphics surface on which the entity will be drawn.
	 */
	public void draw(Graphics g) {
		this.sprite.draw(g, (int) this.x, (int) this.y);
	}
	
	/**
	 * Checks if an entity collides with another one based on whether their rectangle bounding-boxes
	 * intersect or not.
	 * 
	 * @param other The entity we're checking for collisions with.
	 * @return True if we collide with the other entity.
	 */
	public boolean collidesWith(Entity other) {
		this.me.setBounds((int) this.x, (int) this.y, this.sprite.getWidth(), this.sprite.getHeight());
		this.him.setBounds((int) other.x, (int) other.y, other.sprite.getWidth(), other.sprite.getHeight());
		
		return me.intersects(him);
	}
	
	/**
	 * Notification that the entity has collided with something. It's implementation is based on
	 * the entity (different entities will react differently to collisions)
	 * 
	 * @param other The entity against which we collided
	 */
	public abstract void collidedWith(Entity other);
	
	/**
	 * Each entity (subclass of it) is able to implement some basic logic that will be run when requested
	 * in the game loop.
	 * 
	 */
	public abstract void doLogic();
}
