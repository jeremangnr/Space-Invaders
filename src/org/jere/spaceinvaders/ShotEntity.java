package org.jere.spaceinvaders;

public class ShotEntity extends Entity {
	/** The speed at which the shots will move **/
	private int shotSpeed = 300;	
	/** Indicates if the shot has already hit something, used to prevent double kills **/
	private boolean used = false;
	
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
	
	/**
	 * Notifies that the shot entity has collided with something
	 * 
	 * @param other The entity it has collided with
	 */
	public void collidedWith(Entity other) {
		// used to avoid one shot killing 2 aliens
		if (this.used) {
			return;
		}
		// if it's an alien, we want both the shot and the alien to disappear
		if (other instanceof AlienEntity) {
			this.game.removeEntity(this);
			this.game.removeEntity(other);
			this.used = true;
			
			// let the game world know what's going on
			this.game.notifyAlienKilled();
		}
	}
	
	public void doLogic() {
		
	}
}
