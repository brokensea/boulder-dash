package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.models.DisplayableElementModel;


/**
 * DirtModel
 *
 * Represents the dirt in the game.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class DirtModel extends DisplayableElementModel {
    /**
     * Static dataset
     * Specifies the physical parameters of the object
     */
	private static final String SPRITE_NAME = "dirt";
	private static final boolean IS_DESTRUCTIBLE = true;
	private static final boolean CAN_MOVE = false;
	private static final boolean IMPACT_EXPLOSIVE = false;
	private static final boolean ANIMATE = false;
	private static final int PRIORITY = 0;
	private static final boolean FALLING = false;
	private static final String COLLIDE_SOUND = null;

    /**
     * Class constructor
     */
	public DirtModel() {
		super(new Properties(IS_DESTRUCTIBLE, CAN_MOVE, IMPACT_EXPLOSIVE, ANIMATE, FALLING), SPRITE_NAME, PRIORITY, COLLIDE_SOUND);

        this.loadSprite(spriteName);
	}
}