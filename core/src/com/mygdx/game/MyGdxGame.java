package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screen.ScreenFactory;

public class MyGdxGame extends Game {
	public static int scr_width = 800;
	public static int scr_height = 600;
	
	@Override
	public void create() {
		Assets.load();
		this.setScreen(ScreenFactory.create(ScreenFactory.Map.A, this));
	}
	
	@Override
	public void render() {
		super.render();
	}
}
