package com.mygdx.game.object;

public class SingletonHero {
	private static Hero instance = null;

	public static Hero getInstance() {
		if (instance == null) {
			synchronized (Hero.class) {
				if (instance == null) {
					instance = new Hero();
				}
			}
		}
		return instance;
	}
}
