package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * SteelWallModel
 *
 * Represents the steelWall
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class SteelWallModel extends DisplayableElementModel {
    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	private static final String SPRITE_NAME = "steelwall";
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
	public SteelWallModel() {
		super(new Properties(IS_DESTRUCTIBLE, CAN_MOVE, IMPACT_EXPLOSIVE, ANIMATE, FALLING), SPRITE_NAME, PRIORITY, COLLIDE_SOUND);
	}
}
