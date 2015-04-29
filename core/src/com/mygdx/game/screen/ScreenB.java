package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

public class ScreenB extends Screen {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	public static int map_width = 800;
	public static int map_height = 800;
	private MapObject dialogCollsionObject;
	private MapLayer dialogLayer;
	private MapObjects collsionObjects;
	private RectangleMapObject respawnrRectangleMapObject;
	private MapObjects dialogObjects;
	private RectangleMapObject rectDialogObject1;
	private boolean isDialogShow;
	private int coinAtLeast = 2;

	public ScreenB(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		tiledMap = new TmxMapLoader().load("images/rpg/stone.tmx");
		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		MapLayer collisionLayer = tiledMap.getLayers().get("block");
		collsionObjects = collisionLayer.getObjects();
		MapLayer settingMapLayer = tiledMap.getLayers().get("respawn");
		dialogLayer = tiledMap.getLayers().get("dialog");
		MapLayer dialogObjectLayer = tiledMap.getLayers().get("dialogObject");
		MapLayer dialogCollsionObjectLayer = tiledMap.getLayers().get("xiaoer");
		MapObjects respawnObjects = settingMapLayer.getObjects();
		MapObject respawnObject = respawnObjects.get("respawn");
		dialogObjects = dialogObjectLayer.getObjects();
		dialogCollsionObject = dialogCollsionObjectLayer.getObjects().get(
				"xiaoer");
		respawnrRectangleMapObject = (RectangleMapObject) respawnObject;
		hero.position.x = respawnrRectangleMapObject.getRectangle().getX();
		hero.position.y = respawnrRectangleMapObject.getRectangle().getY();
		rectDialogObject1 = (RectangleMapObject) dialogObjects.get("1");
		oldMap = ScreenFactory.Map.B;
	}

	@Override
	protected void updateWorld() {
		// TODO Auto-generated method stub
		float deltaTime = Gdx.graphics.getDeltaTime();
		hero.update(deltaTime);
		if (hero.position.x > map_width) {
			game.setScreen(ScreenFactory.create(Map.A, game));
		}
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
				Rectangle rectangle = rectDialogObject1.getRectangle();
				if (rectangle.contains(screenX, map_height - screenY)) {
					if (hero.getCoin() >= coinAtLeast) {
						System.out.println("您购买了装备！");
						hero.setCoin(hero.getCoin() - coinAtLeast);
						hero.setHasStaff(true);
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
		detectCollision();
	}

	private void detectCollision() {
		Rectangle dialogRect = ((RectangleMapObject) dialogCollsionObject)
				.getRectangle();
		if (dialogRect.overlaps(hero.getRectangle())) {
			isDialogShow = true;
			dialogLayer.setVisible(true);
		} else {
			isDialogShow = false;
			dialogLayer.setVisible(false);
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
		if (isDialogShow) {
			if (hero.isHasStaff()) {
					font.draw(batch, "您已购买装备", rectDialogObject1.getRectangle()
							.getX(), rectDialogObject1.getRectangle().getY()
							+ rectDialogObject1.getRectangle().getHeight());
			} else {
				if (hero.getCoin() >= coinAtLeast)
					font.draw(batch, "购买装备", rectDialogObject1.getRectangle()
							.getX(), rectDialogObject1.getRectangle().getY()
							+ rectDialogObject1.getRectangle().getHeight());
				else
					font.draw(batch, "您的钱不够,至少" + coinAtLeast,
							rectDialogObject1.getRectangle().getX(),
							rectDialogObject1.getRectangle().getY()
									+ rectDialogObject1.getRectangle()
											.getHeight());
			}
		}
		batch.end();
		super.drawUI();
	}

}
