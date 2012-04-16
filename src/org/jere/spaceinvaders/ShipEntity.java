package org.jere.spaceinvaders;

public class ShipEntity extends Entity {
	/**
	 * Creates a new Ship Entity
	 * 
	 * @param pGame The game world in which the entity is contained
	 * @param spriteRef String specifying the location of the sprite
	 * @param pX X Position
	 * @param pY Y Position
	 */
	public ShipEntity(Game pGame, String spriteRef, double x, double y) {
		super(pGame, spriteRef, x, y);
	}
	
	@Override
	public void move(long delta) {
		// if we're moving left and we are on the left-most edge of the screen, we don't want to keep moving
		// (notice we are leaving a margin of about 10px, so the ship doesn't actually get to the edge)
		if (dx < 0 && this.x < 10) {
			return;
		}
		
		// same for the right edge
		if (dx > 0 && this.x > 750) {
			return;
		}
		
		// after checking if we've moved out of bounds, move.
		super.move(delta);
	}
	/**
	 * Notification that the player's ship has collided with something
	 * 
	 * @param other The entity with which the ship has collided with
	 */
	public void collidedWith(Entity other) {
		// if we collide with an alien, the game is over
		if (other instanceof AlienEntity) {
			game.notifyPlayerDeath();
		}
	}
	
	public void doLogic() {
		
	}
}
