package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.mygdx.game.Assets;
import com.mygdx.game.object.Hero;
import com.mygdx.game.object.SingletonHero;
import com.mygdx.game.object.Teleport;

public class Screen extends ScreenAdapter {
	static Hero hero;
	OrthographicCamera camera,uiCamera;
	SpriteBatch batch;
	Game game;
	BitmapFont font;
	static ScreenFactory.Map oldMap = ScreenFactory.Map.A;
	static int map_width = 800;
	static int map_height = 800;
	public Screen(Game game) {
		// TODO Auto-generated constructor stub
		font = Assets.font;
		this.game = game;
		batch = new SpriteBatch();
		hero = SingletonHero.getInstance();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		camera.update();
		uiCamera = new OrthographicCamera();
		uiCamera.setToOrtho(false, 800, 600);
		uiCamera.update();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		updateWorld();
		drawWorld();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	protected void updateWorld() {
		// TODO Auto-generated method stub
		
	}

	protected void drawWorld() {
		// TODO Auto-generated method stub
		if (hero.position.x + camera.viewportWidth / 2 < map_width) {
			if (hero.position.x - camera.viewportWidth / 2 > 0) {
				camera.position.x = hero.position.x;
			} else {
				camera.position.x = camera.viewportWidth / 2;
			}
		} else
			camera.position.x = map_width - camera.viewportWidth / 2;
		if (hero.position.y - camera.viewportHeight / 2 > 0) {
			if (hero.position.y + camera.viewportHeight / 2 < map_height) {
				camera.position.y = hero.position.y;
			} else {
				camera.position.y = map_height - camera.viewportHeight / 2;
			}
		} else {
			camera.position.y = camera.viewportHeight / 2;
		}
		camera.update();
		uiCamera.update();
	}
	
	protected void drawUI() {
		batch.setProjectionMatrix(uiCamera.combined);
		batch.begin();
		font.draw(batch, "µÈ¼¶:"+hero.getLevel(),0,32);
		final String str = "½ð±Ò:"+hero.getCoin();
		font.draw(batch, str,uiCamera.viewportWidth - str.length() * 32,32);
		batch.end();
	}

}
