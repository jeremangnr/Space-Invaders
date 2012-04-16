package org.jere.spaceinvaders;

public class AlienEntity extends Entity {
	/** The speed at which the aliens will move horizontally by default (pixels/sec) **/
	private double moveSpeed = 75;
	/** Percentage at which the alien's horizontal movement will speed when another alien is shot (2%) **/
	private double speedUpPercentage = 1.02;
	
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
	
	/**
	 * Speeds the alien's horizontal movement by a certain percentage
	 * 
	 */
	public void speedUp() {
		this.setXSpeed(this.dx * this.speedUpPercentage);
	}
	
	@Override
	public void move(long delta) {
		// if we're moving left and we are on the left-most edge of the screen, we want the aliens to move another row down,
		// but to do this we first need to update the game logic to check some things
		if (dx < 0 && this.x < 10) {
			game.updateLogic();
		}
		
		// same for the right edge
		if (dx > 0 && this.x > 750) {
			game.updateLogic();
		}
		
		super.move(delta);
	}
	
	public void collidedWith(Entity other) {
		// aliens don't need to notify anyone when they collide, until now at least
	}
	
	public void doLogic() {
		// swap over horizontal movement and move down the screen a bit
		this.dx = -this.dx;
		this.y += 10;
		
		// if we've reached the bottom of the screen then the player dies
		if (y > 570) {
			game.notifyPlayerDeath();
		}
	}
}
