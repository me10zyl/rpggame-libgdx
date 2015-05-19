package com.mygdx.game.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.Direction;
import com.mygdx.game.object.equipment.Equipment;

public class Hero extends AbstractObject {
	public static Animation heroDown_animation;
	public static Animation heroUp_animation;
	public static Animation heroLeft_animation;
	public static Animation heroRight_animation;
	private float width;
	private float height;
	private Direction direction;
	private float animationStateTime;
	private int speed = 150;
	private Array<Magic> magics = new Array<Magic>();
	private Array<Equipment> equipments = new Array<Equipment>();
	private int level = 0;
	private int coin = 0;
	private boolean hasStaff;
	private boolean hasToken;
	public boolean isHasToken() {
		return hasToken;
	}

	public void setHasToken(boolean hasToken) {
		this.hasToken = hasToken;
	}

	private int max_health = 1000;
	public void setMax_health(int max_health) {
		this.max_health = max_health;
	}

	public int getMax_health() {
		return max_health;
	}

	private int health = max_health;
	private float armor = 1.2f;
	private int attack;
	public int getHealth() {
		return health;
	}

	public float getArmor() {
		return armor;
	}

	public int getAttack() {
		if(hasStaff)
		{
			return attack + 2;
		}
		return attack;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setArmor(float f) {
		this.armor = f;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Array<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(Array<Equipment> equipments) {
		this.equipments = equipments;
	}

	public boolean isHasStaff() {
		return hasStaff;
	}

	public void setHasStaff(boolean hasStaff) {
		this.hasStaff = hasStaff;
	}
	
	public void subHealth(int value)
	{
		int realDamage = (int) ((value - this.armor * 0.2) < 0 ? 0 :  value - this.armor * 0.2);
		this.health = (int) (this.health -  realDamage);
	}
	public Hero() {
		width = 32;
		height = 48;
		heroDown_animation = Assets.heroDown_animation;
		heroUp_animation = Assets.heroUp_animation;
		heroLeft_animation = Assets.heroLeft_animation;
		heroRight_animation = Assets.heroRight_animation;
		init();
	}

	public void castLightlingMagic() {
		final LightlingMagic lightlingMagic = new LightlingMagic(position);
		Vector2 castPostion = new Vector2();
		castPostion.x = position.x - lightlingMagic.getWidth() / 2 + width * 2;
		castPostion.y = position.y - height / 2;
		switch (direction) {
		case UP:
			castPostion.y += 100;
			break;
		case DOWN:
			castPostion.y -= 100;
			break;
		case LEFT:
			castPostion.x -= 100;
			break;
		case RIGHT:
			castPostion.x += 100;
			break;

		default:
			break;
		}
		lightlingMagic.position.set(castPostion);
		magics.add(lightlingMagic);
	}
	
	public void castBuddhaLightMagic() {
		final BuddhaLightMagic buddhaLightMagic = new BuddhaLightMagic(position);
		Vector2 castPostion = new Vector2();
		castPostion.x = position.x - buddhaLightMagic.getWidth() / 2 + width * 2;
		castPostion.y = position.y - height / 2;
		buddhaLightMagic.position.set(castPostion);
		magics.add(buddhaLightMagic);
		int addHealth = (int)Math.abs( (this.max_health * 0.1f));
		if(addHealth + this.getHealth() < max_health)
		{
			this.health += addHealth;
		}else
		{
			this.health = max_health;
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (Magic magic : magics) {
			magic.draw(batch);
		}
		switch (direction) {
		case UP:
			batch.draw(heroUp_animation.getKeyFrame(animationStateTime),
					this.position.x, this.position.y);
			break;
		case DOWN:
			batch.draw(heroDown_animation.getKeyFrame(animationStateTime),
					this.position.x, this.position.y);
			break;
		case LEFT:
			batch.draw(heroLeft_animation.getKeyFrame(animationStateTime),
					this.position.x, this.position.y);
			break;
		case RIGHT:
			batch.draw(heroRight_animation.getKeyFrame(animationStateTime),
					this.position.x, this.position.y);
			break;
		default:
			break;
		}
		if(hasStaff)
		{
			batch.draw(Assets.staff,
					this.position.x + 20, this.position.y + 16);
		}
		Assets.font.draw(batch, this.health + "", position.x - width / 2 - 7, position.y + height + 30);
	}

	public int getCoin() {
		return coin;
	}

	public Direction getDirection() {
		return direction;
	}

	public float getHeight() {
		return height;
	}

	public int getLevel() {
		return level;
	}

	public Array<Magic> getMagics() {
		return magics;
	}

	public Rectangle getRectangle() {
		return new Rectangle(position.x, position.y, width, height);
	}

	public int getSpeed() {
		return speed;
	}

	public float getWidth() {
		return width;
	}

	public void init() {
		direction = Direction.DOWN;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMagics(Array<Magic> magics) {
		this.magics = magics;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void update(float deltaTime) {
		this.position.mulAdd(this.velocity, deltaTime);
		this.velocity.set(0, 0);
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			this.velocity.x = -speed;
			this.animationStateTime += deltaTime;
			direction = Direction.LEFT;
		} else if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			this.velocity.x = speed;
			this.animationStateTime += deltaTime;
			direction = Direction.RIGHT;
		} else if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			this.velocity.y = speed;
			this.animationStateTime += deltaTime;
			direction = Direction.UP;
		} else if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			this.velocity.y = -speed;
			this.animationStateTime += deltaTime;
			direction = Direction.DOWN;
		} else {
			this.animationStateTime = 0;
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			castLightlingMagic();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ALT_LEFT)) {
			if(hasToken)
			{
				castBuddhaLightMagic();
			}
		}

		for (Magic magic : magics) {
			if (magic.isFinished()) {
				magics.removeValue(magic, true);
			}
			magic.update(deltaTime);
		}
	}
	
	public void putOnNewEquipment(Equipment e)
	{
		equipments.add(e);
		for(Equipment equipment : equipments)
		{
			if(!equipment.isBeEquiped())
			{
				equipment.addAttribute();
				equipment.setBeEquiped(true);
			}
		}
	}
}
