package com.mygdx.game.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Monster extends AbstractObject {
	private TextureRegion texture;
	private float width, height;
	private Rectangle rectangle;
	private boolean isAlive;
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
		width = 32;
		height = 48;
		rectangle = new Rectangle(position.x, position.y, width, height);
		this.position.set(position);
		texture = Assets.monsterDown_textureRegion;
	}
	
	public void update(float deltaTime)
	{
		rectangle = new Rectangle(position.x, position.y, width, height);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(texture, this.position.x, this.position.y);
	}
	
	public void hitMagic(Rectangle rect,Hero hero)
	{
		if(rect != null)
		{
			if(rect.overlaps(rectangle))
			{
				isAlive = false;
				hero.setLevel(hero.getLevel() + 1);
			}
		}
	}

}
