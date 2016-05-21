package fr.enssat.BoulderDash.models;

public class Properties {
	private boolean isDestructible;
	private boolean canMove;
	private boolean impactExplosive;
	private boolean animate;
	private boolean falling;

	public Properties(boolean isDestructible, boolean canMove, boolean impactExplosive, boolean animate,
			boolean falling) {
		this.isDestructible = isDestructible;
		this.canMove = canMove;
		this.impactExplosive = impactExplosive;
		this.animate = animate;
		this.falling = falling;
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public void setDestructible(boolean isDestructible) {
		this.isDestructible = isDestructible;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public boolean isImpactExplosive() {
		return impactExplosive;
	}

	public void setImpactExplosive(boolean impactExplosive) {
		this.impactExplosive = impactExplosive;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
}