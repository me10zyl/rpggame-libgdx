package com.mygdx.game.object.equipment;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.object.Hero;

public class Shoes extends Equipment{

	public Shoes(Vector2 position) {
		super(position);
		setSpeed_attribute(100);
		setTexture(Assets.shoes);
		setType("shoes");
		setHeight(getTexture().getRegionHeight());
		setWidth(getTexture().getRegionWidth());
		setRectangle(new Rectangle(position.x, position.y, getWidth(), getHeight()));
	}
	
}
