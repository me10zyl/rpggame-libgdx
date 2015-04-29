package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.object.Coin;
import com.mygdx.game.object.Magic;
import com.mygdx.game.object.Monster;
import com.mygdx.game.screen.ScreenFactory.Map;

public class ScreenC extends Screen {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private MapObjects collsionObjects;
	private RectangleMapObject respawnrRectangleMapObject;
	private Array<Monster> monsters = new Array<Monster>();
	private Array<Coin> coins = new Array<Coin>();

	public ScreenC(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		map_width = 992;
		map_height = 960;
		tiledMap = new TmxMapLoader().load("images/rpg/snow.tmx");
		tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
		MapLayer collisionLayer = tiledMap.getLayers().get("block");
		collsionObjects = collisionLayer.getObjects();
		MapLayer settingMapLayer = tiledMap.getLayers().get("respawn");
		MapObjects respawnObjects = settingMapLayer.getObjects();
		MapObject respawnObject = respawnObjects.get("teleportA");
		respawnrRectangleMapObject = (RectangleMapObject) respawnObject;
		hero.position.x = respawnrRectangleMapObject.getRectangle().getX();
		hero.position.y = respawnrRectangleMapObject.getRectangle().getY();
		oldMap = ScreenFactory.Map.C;
		initMonsters();
		initCoins();
	}

	private void initMonsters() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			int ranX = MathUtils.random(map_width);
			int ranY = MathUtils.random(map_height);
			monsters.add(new Monster(new Vector2(ranX, ranY)));
		}
	}
	
	private void initCoins() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			int ranX = MathUtils.random(map_width);
			int ranY = MathUtils.random(map_height);
			coins.add(new Coin(new Vector2(ranX, ranY)));
		}
	}

	@Override
	protected void updateWorld() {
		// TODO Auto-generated method stub
		float deltaTime = Gdx.graphics.getDeltaTime();
		hero.update(deltaTime);
		if (hero.position.x < -hero.getWidth()) {
			hero.position.set(0, 0);
			game.setScreen(ScreenFactory.create(Map.A, game));
		}
		detectCollision();
		for (Monster monster : monsters) {
			monster.update(deltaTime);
			Array<Magic> magics = hero.getMagics();
			for (Magic magic : magics) {
				monster.hitMagic(magic.getRectangle(), hero);
			}
			if (!monster.isAlive()) {
				monsters.removeValue(monster, true);
			}
		}
		for (Coin coin : coins) {
			coin.update(deltaTime);
			coin.hitHero(hero);
			if (!coin.isAlive()) {
				coins.removeValue(coin, true);
			}
		}
	}

	private void detectCollision() {
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
		for (Monster monster : monsters) {
			monster.draw(batch);
		}
		for (Coin coin: coins) {
			coin.draw(batch);
		}
		hero.draw(batch);
		batch.end();
		super.drawUI();
	}

}
