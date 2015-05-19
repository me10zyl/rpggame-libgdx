package com.mygdx.game.object.monster;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.object.Coin;
import com.mygdx.game.object.SingletonHero;
import com.mygdx.game.object.equipment.Ring;

public class LizardmanMonster extends Monster {
	private final int coin_amount = 2;
	private final float haveEquipmentRate = 0.2f;

	public LizardmanMonster(Vector2 position) {
		// TODO Auto-generated constructor stub
		super(position);
		setTexture(Assets.xiyirenDown_textureRegion);
		setWidth(getTexture().getRegionWidth());
		setHeight(getTexture().getRegionHeight());
		setRectangle(new Rectangle(position.x, position.y, getWidth(),
				getHeight()));
		setHealth(150);
		setAttack(20);
		setLevelValue(2);
	
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
			getDropEquipments().add(new Ring(position2));
			coin = null;
		}
	}
}
