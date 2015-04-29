package com.mygdx.game.screen;

import com.badlogic.gdx.Game;

public class ScreenFactory {
	public static enum Map{
		A, B, C, D
	}
	public static Screen create(ScreenFactory.Map map,Game game)
	{
		Screen screen = null;
		switch (map) {
		case A:
			screen = new ScreenA(game);
			break;
		case B:
			screen = new ScreenB(game);
			break;
		case C:
			screen = new ScreenC(game);
			break;
		case D:
			screen = new ScreenD(game);
			break;

		default:
			break;
		}
		return screen;
	}
}
