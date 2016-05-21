package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * MagicWallModel
 *
 * Represents the magic wall.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class MagicWallModel extends DisplayableElementModel {
    /**
     * Stores the frames
     * Used for the sprites
     */
	private ArrayList<BufferedImage> framesMagicWall;

	private long previousTime;
	private int currentFrame;
	private long speed;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	private static final String SPRITE_NAME = "magicwall";
	private static final boolean IS_DESTRUCTIBLE = false;
	private static final boolean CAN_MOVE = false;
	private static final boolean IMPACT_EXPLOSIVE = false;
	private static final boolean ANIMATE = false;
	private static final int PRIORITY = 3;
	private static final boolean FALLING = false;
	private static final String COLLIDE_SOUND = "touch";

    /**
     * Class constructor
     */
	public MagicWallModel() {
		super(new Properties(IS_DESTRUCTIBLE, CAN_MOVE, IMPACT_EXPLOSIVE, ANIMATE, FALLING), SPRITE_NAME, PRIORITY, COLLIDE_SOUND);
		this.currentFrame = 0;
		this.speed =  100;
		this.initSprites();
	}

	/**
	 * Function to animate the sprite
	 */
	public void update(long time) {
		if (time - previousTime >= speed) {
			// Update animation
			previousTime = time;

			try {
				currentFrame += 1;

				this.setSprite(framesMagicWall.get(this.currentFrame));
			} catch (IndexOutOfBoundsException e) {
				currentFrame = 0;
			}
		}
	}
	
	/**
	 * Init the subimages
	 */
	private void initSprites() {
		this.framesMagicWall = new ArrayList<BufferedImage>();
		/* INIT SPRITE FOR DIAMOND */
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 0, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 24, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 48, 0, 16, 16));
		framesMagicWall.add(grabSprite(loadSprite(spriteName), 72, 0, 16, 16));
	}
}
