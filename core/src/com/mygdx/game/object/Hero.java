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
	private int level = 0;
	private int coin = 0;
	private boolean hasStaff;

	public boolean isHasStaff() {
		return hasStaff;
	}

	public void setHasStaff(boolean hasStaff) {
		this.hasStaff = hasStaff;
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

	public void castMagic() {
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

	@Override
	public void draw(SpriteBatch batch) {
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
		for (Magic magic : magics) {
			magic.draw(batch);
		}
		if(hasStaff)
		{
			batch.draw(Assets.staff,
					this.position.x + 20, this.position.y + 16);
		}
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
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && hasStaff) {
			castMagic();
		}

		for (Magic magic : magics) {
			if (magic.isFinished()) {
				magics.removeValue(magic, true);
			}
			magic.update(deltaTime);
		}
	}
}
