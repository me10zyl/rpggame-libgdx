package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Direction;
import com.mygdx.game.object.Hero;

public class CollisionDectecter {
	public static void antiWalk(Rectangle rectangle, Hero hero) {
		Hero preHero = new Hero();
		preHero.position.set(hero.position);
		preHero.velocity.set(hero.velocity);
		if (hero.getDirection() == Direction.UP) {
			preHero.velocity.y += hero.getSpeed();
		} else if (hero.getDirection() == Direction.LEFT) {
			preHero.velocity.x -= hero.getSpeed();
		} else if (hero.getDirection() == Direction.RIGHT) {
			preHero.velocity.x += hero.getSpeed();
		} else if (hero.getDirection() == Direction.DOWN) {
			preHero.velocity.y -= hero.getSpeed();
		}
		preHero.position.mulAdd(preHero.velocity, Gdx.graphics.getDeltaTime());
		if (rectangle.overlaps(preHero.getRectangle())) {
			hero.velocity.set(0, 0);
			if (hero.getDirection() == Direction.UP) {
				final float newY = rectangle.getY() - hero.getHeight() - 2;
				hero.position.y = newY;
			} else if (hero.getDirection() == Direction.LEFT) {
				final float newX = rectangle.getX() + rectangle.getWidth();
				hero.position.x = newX;
			} else if (hero.getDirection() == Direction.RIGHT) {
				final float newX = rectangle.getX() - hero.getWidth();
				hero.position.x = newX;
			} else if (hero.getDirection() == Direction.DOWN) {
				final float newY = rectangle.getY() + rectangle.getHeight();
				hero.position.y = newY;
			}
		}
	}
}
