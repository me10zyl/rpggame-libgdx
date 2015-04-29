package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.screen.ScreenFactory.Map;

public class ScreenD extends Screen {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private MapObjects collsionObjects;
	private RectangleMapObject respawnrRectangleMapObject;
	private MapLayer dialogMapLayer;
	private RectangleMapObject guideCollisionObject;
	private RectangleMapObject rectangleDialogObject;
	private boolean isDialogShow;
	private int levelAtLeaset = 5;
	private boolean isGameOver;
	public ScreenD(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		map_width = 1280;
		map_height = 800;
		tiledMap = new TmxMapLoader().load("images/rpg/mountain.tmx");
		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		MapLayer collisionLayer = tiledMap.getLayers().get("block");
		collsionObjects = collisionLayer.getObjects();
		MapLayer settingMapLayer = tiledMap.getLayers().get("respawn");
		MapObjects respawnObjects = settingMapLayer.getObjects();
		MapObject respawnObject = respawnObjects.get("teleportA");
		respawnrRectangleMapObject = (RectangleMapObject) respawnObject;
		hero.position.x = respawnrRectangleMapObject.getRectangle().getX();
		hero.position.y = respawnrRectangleMapObject.getRectangle().getY();
		dialogMapLayer = tiledMap.getLayers().get("dialog");
		MapLayer guideObjectLayer =  tiledMap.getLayers().get("guideObject");
		guideCollisionObject = (RectangleMapObject) guideObjectLayer.getObjects().get("guide");
		MapLayer dialogOjectLayer =  tiledMap.getLayers().get("dialogObject");
		rectangleDialogObject = (RectangleMapObject) dialogOjectLayer.getObjects().get("dialog");
		oldMap = ScreenFactory.Map.D;
	}
	
	@Override
	protected void updateWorld() {
		// TODO Auto-generated method stub
		float deltaTime = Gdx.graphics.getDeltaTime();
		hero.update(deltaTime);
		if(hero.position.x < -hero.getWidth())
		{
			game.setScreen(ScreenFactory.create(Map.A, game));
		}
		detectCollision();
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				// TODO Auto-generated method stub
				Rectangle rectangle = rectangleDialogObject.getRectangle();
				if (rectangle.contains(camera.position.x - camera.viewportWidth/2 + screenX, map_height - screenY)) {
					if (hero.getLevel() >= levelAtLeaset) {
						System.out.println("hah");
						isGameOver = true;
						isDialogShow = false;
					}
				}
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		if(isGameOver)
		{
			hero.velocity.set(0,0);
		}
	}

	private void detectCollision() {
		if(guideCollisionObject.getRectangle().overlaps(hero.getRectangle()))
		{
			isDialogShow = true;
			dialogMapLayer.setVisible(true);
		}else
		{
			isDialogShow = false;
			dialogMapLayer.setVisible(false);
		}
		for (RectangleMapObject rectangleObject : collsionObjects
				.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			CollisionDectecter.antiWalk(rectangle, hero);
		}
	}
	
	@Override
	protected void drawWorld() {
		// TODO Auto-generated method stub
		super.drawWorld();
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		hero.draw(batch);
		if(isDialogShow)
		{
			if(hero.getLevel() >= levelAtLeaset)
			{
				font.draw(batch, "加入本门派", rectangleDialogObject.getRectangle().getX(), rectangleDialogObject.getRectangle().getY() + rectangleDialogObject.getRectangle().getHeight());
			}else
			{
				font.draw(batch, "您的等级不够,至少5", rectangleDialogObject.getRectangle().getX(), rectangleDialogObject.getRectangle().getY() + rectangleDialogObject.getRectangle().getHeight());
			}
		}
		if(isGameOver)
		{
//			font.draw(batch, "Game Over", camera.position.x - 32 * 9, camera.position.y - 32);
			batch.draw(new TextureRegion(Assets.gameover), camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
		}
		batch.end();
		super.drawUI();
	}

}
