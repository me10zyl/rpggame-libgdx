package com.mygdx.game.object.monster;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.object.AbstractObject;
import com.mygdx.game.object.Coin;
import com.mygdx.game.object.Hero;
import com.mygdx.game.object.Magic;
import com.mygdx.game.object.equipment.Equipment;
import com.mygdx.game.screen.CollisionDetecter;
import com.mygdx.game.screen.CollisionDetecter.OnCollisionListener;

public abstract class Monster extends AbstractObject {
	private TextureRegion texture;
	private float width, height;
	private Rectangle rectangle;
	private boolean isAlive;
	private int health = 100;
	private int attack;
	private int levelValue;
	private Array<Equipment> dropEquipments = new Array<Equipment>();
	private Array<Coin> coins = new Array<Coin>();

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

	public Array<Equipment> getDropEquipments() {
		return dropEquipments;
	}

	public Array<Coin> getCoins() {
		return coins;
	}

	public void setDropEquipments(Array<Equipment> dropEquipments) {
		this.dropEquipments = dropEquipments;
	}

	public void setCoins(Array<Coin> coins) {
		this.coins = coins;
	}

	public int getLevelValue() {
		return levelValue;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setLevelValue(int levelValue) {
		this.levelValue = levelValue;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Monster(Vector2 position) {
		// TODO Auto-generated constructor stub
		isAlive = true;
		this.position.set(position);
	}

	public void update(float deltaTime) {
		rectangle = new Rectangle(position.x, position.y, width, height);
	}


	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		Assets.font.draw(batch, health + "", this.position.x - this.width / 2,
				this.position.y + this.height + 23);
		batch.draw(texture, this.position.x, this.position.y);
	}

	public void hitMagic(Magic magic, Hero hero) {
		Rectangle rect = magic.getRectangle();
		if (rect != null) {
			if (rect.overlaps(rectangle)) {
				this.health = this.health - magic.getAttack()
						- hero.getAttack();
				if (this.health <= 0 && isAlive) {
					isAlive = false;
					hero.setLevel(hero.getLevel() + levelValue);
				}
			}
		}
	}

	public void hitHero(final Hero hero) {
		CollisionDetecter collisionDetecter = new CollisionDetecter();
		collisionDetecter.setOnCollisionListener(new OnCollisionListener() {
			@Override
			public void onCollision() {
				// TODO Auto-generated method stub
				hero.subHealth(Monster.this.attack);

			}
		});
		collisionDetecter.antiWalk(rectangle, hero);
	}

}
