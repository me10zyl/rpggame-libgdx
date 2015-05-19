package com.mygdx.game.screen;

import org.omg.PortableInterceptor.Interceptor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Direction;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.object.Hero;
import com.mygdx.game.screen.ScreenFactory.Map;

public class ScreenA extends Screen {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private MapObjects collsionObjects;
	private RectangleMapObject respawnrRectangleMapObject;
	private boolean touchB, touchC, touchD;
	private RectangleMapObject teleportA;
	private RectangleMapObject teleportB;
	private RectangleMapObject teleportC;
	private RectangleMapObject teleportD;

	public ScreenA(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		map_width = 800;
		map_height = 800;
		tiledMap = new TmxMapLoader().load("images/rpg/wood.tmx");
		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		MapLayer collisionLayer = tiledMap.getLayers().get(1);
		collsionObjects = collisionLayer.getObjects();
		MapLayer settingMapLayer = tiledMap.getLayers().get(2);
		MapObjects respawnObjects = settingMapLayer.getObjects();
		teleportA = (RectangleMapObject) respawnObjects.get("teleportA");
		teleportB = (RectangleMapObject) respawnObjects.get("teleportB");
		teleportC = (RectangleMapObject) respawnObjects.get("teleportC");
		teleportD = (RectangleMapObject) respawnObjects.get("teleportD");
		if (oldMap == ScreenFactory.Map.A) {
			respawnrRectangleMapObject = teleportA;
		}
		if (oldMap == ScreenFactory.Map.B) {
			respawnrRectangleMapObject = teleportB;
		}
		if (oldMap == ScreenFactory.Map.C) {
			respawnrRectangleMapObject = teleportC;
		}
		if (oldMap == ScreenFactory.Map.D) {
			respawnrRectangleMapObject = teleportD;
		}
		hero.position.x = respawnrRectangleMapObject.getRectangle().getX();
		hero.position.y = respawnrRectangleMapObject.getRectangle().getY();
		oldMap = ScreenFactory.Map.A;
		hero.setHealth(hero.getMax_health());
	}

	@Override
	protected void updateWorld() {
		// TODO Auto-generated method stub
		float deltaTime = Gdx.graphics.getDeltaTime();
		hero.update(deltaTime);
		dectectCollision();
		if (hero.position.x < -hero.getWidth() && touchB) {
			game.setScreen(ScreenFactory.create(Map.B, game));
		}
		if (hero.position.x > map_width && touchC) {
			game.setScreen(ScreenFactory.create(Map.C, game));
		}
		if (hero.position.x > map_width && touchD) {
			game.setScreen(ScreenFactory.create(Map.D, game));
		}
	}

	@Override
	protected void drawWorld() {
		super.drawWorld();
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		hero.draw(batch);
		batch.end();
		super.drawUI();
	}
	
	private void setAllFalse()
	{
		touchB = false;
		touchC = false;
		touchD = false;
	}
	private void dectectCollision() {
		if (teleportB.getRectangle().overlaps(hero.getRectangle())) {
			setAllFalse();
			touchB = true;
		} else if (teleportC.getRectangle().overlaps(hero.getRectangle())) {
			setAllFalse();
			touchC = true;
		} else if (teleportD.getRectangle().overlaps(hero.getRectangle())) {
			setAllFalse();
			touchD = true;
		}
		for (RectangleMapObject rectangleObject : collsionObjects
				.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			CollisionDetecter collisionDetecter = new CollisionDetecter();
			collisionDetecter.antiWalk(rectangle, hero);
		}
	}
}
