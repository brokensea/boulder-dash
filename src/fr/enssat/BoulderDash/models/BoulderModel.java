package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * BoulderModel
 *
 * Represents the boulders.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class BoulderModel extends DisplayableElementModel {
    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	private static final String SPRITE_NAME = "boulder";
	private static final boolean IS_DESTRUCTIBLE = false;
	private static final boolean CAN_MOVE = true;
	private static final boolean IMPACT_EXPLOSIVE = false;
	private static final boolean ANIMATE = true;
	private static final int PRIORITY = 2;
	private static final String COLLIDE_SOUND = "die";
	private static final boolean FALLING = false;

    /**
     * Class constructor
     */
	public BoulderModel(boolean convertible) {
		super(new Properties(IS_DESTRUCTIBLE, CAN_MOVE, IMPACT_EXPLOSIVE, ANIMATE, FALLING), SPRITE_NAME, PRIORITY, COLLIDE_SOUND, convertible);
        this.loadSprite(spriteName);
	}

    public BoulderModel() {
        this(false);
    }
}