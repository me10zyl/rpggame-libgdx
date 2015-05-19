package com.mygdx.game.object.monster;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.object.Coin;
import com.mygdx.game.object.equipment.Armour;
import com.mygdx.game.object.equipment.Ring;

public class ManeaterMonster extends Monster{
	private final int coin_amount = 3;
	private final float haveEquipmentRate = 0.2f;
	public ManeaterMonster(Vector2 position) {
		super(position);
		// TODO Auto-generated constructor stub
		setTexture(Assets.shirenzuDown_textureRegion);
		setWidth(getTexture().getRegionWidth());
		setHeight(getTexture().getRegionHeight());
		setRectangle(new Rectangle(position.x, position.y, getWidth(), getHeight()));
		setHealth(250);
		setAttack(2);
		setLevelValue(1);
		
		for (int i = 0; i < coin_amount; i++) {
			Coin coin = new Coin(position);
			final Vector2 position2 = new Vector2(position);
			position2.x = position.x + i * coin.getWidth() / 2;
			coin.position = position2;
			getCoins().add(coin);
		}
		if (MathUtils.randomBoolean(haveEquipmentRate)) {
			Coin coin = new Coin(position);
			final Vector2 position2 = new Vector2(position);
			position2.x = position.x - coin.getWidth() / 2;
			getDropEquipments().add(new Armour(position2));
			coin = null;
		}
	}

}
