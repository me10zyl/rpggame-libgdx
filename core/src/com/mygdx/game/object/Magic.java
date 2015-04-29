package com.mygdx.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Magic extends AbstractObject{
	private boolean isFinished;
	public boolean isFinished() {
		return isFinished;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	abstract public void update(float deltaTime);
	abstract public Rectangle getRectangle();
}
