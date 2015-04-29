package com.mygdx.game.object;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Teleport extends AbstractObject{
	 Texture tapeta1;
	 public Teleport() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(tapeta1, position.x,position.y);
	}
	
	public void update(float deltaTime,Hero hero)
	{
		Rectangle rect1 = new Rectangle();
		Rectangle rect2 = new Rectangle();
		rect1.setPosition(hero.position);
		rect1.setSize(hero.getWidth(), hero.getHeight());
		rect2.setPosition(position);
		rect2.setSize(tapeta1.getWidth(),tapeta1.getHeight());
		if(rect1.overlaps(rect2))
		{
			hero.position.set(200, 0);
		}
	}

}
