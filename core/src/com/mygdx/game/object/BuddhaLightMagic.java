package com.mygdx.game.object;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class BuddhaLightMagic extends Magic {
	private Animation animation;
	private float animationStateTime;

	public BuddhaLightMagic(Vector2 postion) {
		// TODO Auto-generated constructor stub
		setWidth(Assets.buddhaLight_textureRegion[0].getRegionWidth());
		setHeight(Assets.buddhaLight_textureRegion[0].getRegionHeight());
		setAttack(0);
		this.position.set(postion);
		animation = Assets.buddhaLight_animation;
	}

	public void update(float deltaTime) {
		animationStateTime += deltaTime;
		setRectangle(new Rectangle(this.position.x, this.position.y , getWidth()/2,getHeight()/2));
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
