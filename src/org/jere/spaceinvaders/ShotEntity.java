package org.jere.spaceinvaders;

public class ShotEntity extends Entity {
	private int shotSpeed = 300;	
	
	/**
	 * Creates a new Shot Entity
	 * 
	 * @param pGame The game world in which the entity is contained
	 * @param spriteRef String specifying the location of the sprite
	 * @param pX X Position
	 * @param pY Y Position
	 */
	public ShotEntity(Game pGame, String spriteRef, double x, double y) {
		super(pGame, spriteRef, x, y);
		
		// the shot will be moving up by default
		this.dy = -shotSpeed;
	}
	
	@Override
	public void move(long delta) {
		// move normally
		super.move(delta);
		
		// if shot goes outside the screen, remove it.
		if (y < -100) {
			this.game.removeEntity(this);
		}
	}
}
