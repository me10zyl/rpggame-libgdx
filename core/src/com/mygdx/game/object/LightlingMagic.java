package com.mygdx.game.object;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class LightlingMagic extends Magic {
	private Animation animation;
	private float animationStateTime;
	private float width,height;
	private Rectangle rectangle;
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public LightlingMagic(Vector2 postion) {
		// TODO Auto-generated constructor stub
		width = Assets.lightlingMagic_textureRegion[0].getRegionWidth();
		height = Assets.lightlingMagic_textureRegion[0].getRegionHeight();
		this.position.set(postion);
		animation = Assets.lightlingMagic_animation;
	}

	public void update(float deltaTime) {
		animationStateTime += deltaTime;
		rectangle = new Rectangle(this.position.x, this.position.y , width/2,height/2);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		final TextureRegion keyFrame = animation.getKeyFrame(animationStateTime);
		batch.draw(keyFrame, position.x,
				position.y,0,0,keyFrame.getRegionWidth(),keyFrame.getRegionHeight(),0.5f,0.5f,0); 
		if(animation.isAnimationFinished(animationStateTime))
		{
			setFinished(true);
		}
	}

}
