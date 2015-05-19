package com.mygdx.game.object.equipment;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Armour extends Equipment{
	public Armour(Vector2 position) {
		super(position);
		setArmor_attribute(10);
		setTexture(Assets.armour);
		setType("armour");
		setHeight(getTexture().getRegionHeight());
		setWidth(getTexture().getRegionWidth());
		setRectangle(new Rectangle(position.x, position.y, getWidth(), getHeight()));
		// TODO Auto-generated constructor stub
	}
}
