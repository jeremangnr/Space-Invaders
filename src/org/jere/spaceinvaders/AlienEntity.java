package org.jere.spaceinvaders;

public class AlienEntity extends Entity {
	/** The speed at which the aliens will move horizontally by default (pixels/sec) **/
	private int moveSpeed = 75;
	
	/**
	 * Creates a new Alien Entity
	 * 
     * @param pGame The game world in which the entity is contained
	 * @param spriteRef String specifying the location of the sprite
	 * @param pX X Position
	 * @param pY Y Position
	 */
	public AlienEntity(Game pGame, String spriteRef, double x, double y) {
		super(pGame, spriteRef, x, y);
		
		// we want the aliens to start moving left, like they do in the original version
		this.dx = -this.moveSpeed;
	}
	
	@Override
	public void move(long delta) {
		// if we're moving left and we are on the left-most edge of the screen, we want the aliens to move another row down,
		// but to do this we first need to update the game logic to check some things
		if (dx < 0 && this.x < 10) {
			//game.updateLogic();
		}
		
		// same for the right edge
		if (dx > 0 && this.x > 750) {
			//game.updateLogic();
		}
		
		super.move(delta);
	}
}
