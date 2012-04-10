package org.jere.spaceinvaders;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SpriteStore {
	/** Instance for the singleton **/
	private static SpriteStore instance;
	
	/** Stores already cached sprites **/
	private Map<String, Sprite> sprites;

	private SpriteStore() {
		this.sprites = new HashMap<String, Sprite>();
	}

	/** Return an instance of the SpriteStore
	 * 
	 * @return The single instance of SpriteStore
	 */
	public static SpriteStore getInstance() {
		if (instance == null) {
			instance = new SpriteStore();
		}

		return instance;
	}

	/**
	 * Retrieve a sprite from the store
	 * 
	 * @param path Path to the image to be used for the sprite
	 * @return A sprite instance containing an accelerated image
	 */
	public Sprite getSprite(String path) {
		// check if we already have that sprite in the cache
		if (this.sprites.get(path) != null) {
			return this.sprites.get(path);			
		}
		
		BufferedImage sourceImage = null;
		// the chain of functions used here to get the sprite is only there to
		// support specialized class loaders
		try {
			URL url = this.getClass().getClassLoader().getResource(path);
			// check if resource was found
			if (url == null) {
				JOptionPane.showMessageDialog(null,
						"The specified resource '" + path + "' was not found",
						"Sorry!",
						JOptionPane.ERROR_MESSAGE);
				
				System.exit(0);
			}

			// here we actually load the image
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"There was an error while loading the sprite",
					"Sorry!",
					JOptionPane.ERROR_MESSAGE);
			
			if (Game.DEBUG_ENABLED) {
				e.printStackTrace();				
			}
			
			System.exit(0);
		}
		
		// this will allow us to ONLY use the GPU to draw the image, taking the load of the CPU.
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
													  .getDefaultScreenDevice()
													  .getDefaultConfiguration();
		
		// create the "accelerated" image (this is kind of like the "frame" in which we'll draw the actual image)
		Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
		// draw the source image inside the "accelerated" one
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		
		// create the sprite, add it to cache, and return it
		Sprite sprite = new Sprite(image);
		this.sprites.put(path, sprite);
		
		return sprite;
	}
}