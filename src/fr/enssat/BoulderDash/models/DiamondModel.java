package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * DiamondModel
 *
 * Represents a diamond in the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class DiamondModel extends DisplayableElementModel {
	private long previousTime;
	private int currentFrame;

	private final int SIZ_X_OF_SPRITE = 16;
	private final int SIZ_Y_OF_SPRITE = 16;
	private long speed;

	private ArrayList<BufferedImage> framesDiamond;

    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	private static final String SPRITE_NAME = "diamond";
	private static final boolean IS_DESTRUCTIBLE = true;
	private static final boolean CAN_MOVE = true;
	private static final boolean IMPACT_EXPLOSIVE = false;
	private static final boolean ANIMATE = true;
	private static final int PRIORITY = 0;
	private static final boolean FALLING = false;
	private static final String COLLIDE_SOUND = "coin";

    /**
     * Class constructor
     */
	public DiamondModel() {
		super(new Properties(IS_DESTRUCTIBLE, CAN_MOVE, IMPACT_EXPLOSIVE, ANIMATE, FALLING), SPRITE_NAME, PRIORITY, COLLIDE_SOUND, false);

		this.initSprites();
	}

    /**
     * Updates the sprite (animation loop)
     *
     * @param  time  Current time
     */
	public void update(long time) {
		if (time - previousTime >= speed) {
			// Update the animation
			previousTime = time;

            try {
				this.currentFrame += 1;
                this.setSprite(framesDiamond.get(this.currentFrame));
			} catch (IndexOutOfBoundsException e) {
				this.currentFrame = 0;
			}
		}
	}

    /**
     * Initialize the sprites
     * This is an animated element, hence this method
     */
	private void initSprites() {
		/* Initialize object sprites */
		this.framesDiamond = new ArrayList<BufferedImage>();

		for (int i = 0; i < 8; i++) {
            this.framesDiamond.add(
                    this.grabSprite(loadSprite(spriteName), i * 24, 0, SIZ_X_OF_SPRITE, SIZ_Y_OF_SPRITE)
            );
		}
	}
}