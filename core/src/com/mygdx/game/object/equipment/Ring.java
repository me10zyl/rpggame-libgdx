package com.mygdx.game.object.equipment;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.object.Hero;

public class Ring extends Equipment{

	public Ring(Vector2 position) {
		super(position);
		setHealth_attribute(3000);
		setTexture(Assets.ring);
		setType("ring");
		setHeight(getTexture().getRegionHeight());
		setWidth(getTexture().getRegionWidth());
		setRectangle(new Rectangle(position.x, position.y, getWidth(), getHeight()));
		// TODO Auto-generated constructor stub
	}
}
