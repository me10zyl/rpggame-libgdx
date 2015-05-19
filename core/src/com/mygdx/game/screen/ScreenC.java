package com.mygdx.game.screen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mygdx.game.Assets;
import com.mygdx.game.object.Coin;
import com.mygdx.game.object.Magic;
import com.mygdx.game.object.equipment.Equipment;
import com.mygdx.game.object.monster.HedgehogmanMonster;
import com.mygdx.game.object.monster.LizardmanMonster;
import com.mygdx.game.object.monster.ManeaterMonster;
import com.mygdx.game.object.monster.Monster;
import com.mygdx.game.screen.ScreenFactory.Map;

public class ScreenC extends Screen {
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private static MapObjects collsionObjects;
	private RectangleMapObject respawnrRectangleMapObject;
	private static Array<Monster> monsters = new Array<Monster>();
	private Array<Coin> coins = new Array<Coin>();
	private Array<Equipment> equipments = new Array<Equipment>();
	private static boolean isFirstTimeEnter = true;
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
		// initCoins();
		if(isFirstTimeEnter)
		{
			initMonsters();
		}
	}

	private static void  initMonsters() {
		isFirstTimeEnter = false;
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			generateHedgehogmanMonster();
		}
		for (int i = 0; i < 10; i++) {
			generateLizardmanMonster();
		}
		for (int i = 0; i < 10; i++) {
			generateManeaterMonster();
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int hedgehomanMonsterCount = 0;
				int lizardmanMonsterCount = 0;
				int maneaterMonsterCount = 0;
				for (Monster monster : monsters) {
					if (monster instanceof HedgehogmanMonster) {
						hedgehomanMonsterCount++;
					} else if (monster instanceof LizardmanMonster) {
						lizardmanMonsterCount++;
					} else if (monster instanceof ManeaterMonster) {
						maneaterMonsterCount++;
					}
				}
				if(hedgehomanMonsterCount < 10)
				{
					generateHedgehogmanMonster();
				}else if(lizardmanMonsterCount < 10)
				{
					generateLizardmanMonster();
				}else if(maneaterMonsterCount < 10)
				{
					generateManeaterMonster();
				}
			}
		}, 0, 5000);
	}

	private static void generateManeaterMonster() {
		ManeaterMonster maneaterMonster = null;
		boolean isCorrect = true;
		do {
			isCorrect = true;
			int ranX = MathUtils.random(map_width);
			int ranY = MathUtils.random(map_height);
			maneaterMonster = new ManeaterMonster(new Vector2(ranX, ranY));
			if (ranX - maneaterMonster.getWidth() > map_width
					|| ranY - maneaterMonster.getHeight() > map_height) {
				continue;
			}
			if(maneaterMonster.getRectangle().overlaps(hero.getRectangle()))
			{
				continue;
			}
			for (RectangleMapObject rectangleObject : collsionObjects
					.getByType(RectangleMapObject.class)) {
				Rectangle rectangle = rectangleObject.getRectangle();
				if (rectangle.overlaps(maneaterMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
			for (Monster monster : monsters) {
				Rectangle rectangle = monster.getRectangle();
				if (rectangle.overlaps(maneaterMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
		} while (!isCorrect);
		monsters.add(maneaterMonster);
	}

	private static void generateLizardmanMonster() {
		boolean isCorrect = true;
		LizardmanMonster lizardmanMonster = null;
		do {
			isCorrect = true;
			int ranX = MathUtils.random(map_width);
			int ranY = MathUtils.random(map_height);
			lizardmanMonster = new LizardmanMonster(new Vector2(ranX, ranY));
			if (ranX > map_width - lizardmanMonster.getWidth()
					|| ranY > map_height - lizardmanMonster.getHealth()) {
				continue;
			}
			if(lizardmanMonster.getRectangle().overlaps(hero.getRectangle()))
			{
				continue;
			}
			for (RectangleMapObject rectangleObject : collsionObjects
					.getByType(RectangleMapObject.class)) {
				Rectangle rectangle = rectangleObject.getRectangle();
				if (rectangle.overlaps(lizardmanMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
			for (Monster monster : monsters) {
				Rectangle rectangle = monster.getRectangle();
				if (rectangle.overlaps(lizardmanMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
		} while (!isCorrect);
		monsters.add(lizardmanMonster);
	}

	private static void generateHedgehogmanMonster() {
		boolean isCorrect = true;
		HedgehogmanMonster hedgehogmanMonster = null;
		do {
			isCorrect = true;
			int ranX = MathUtils.random(map_width);
			int ranY = MathUtils.random(map_height);
			hedgehogmanMonster = new HedgehogmanMonster(new Vector2(ranX, ranY));
			if (ranX - hedgehogmanMonster.getWidth() > map_width
					|| ranY - hedgehogmanMonster.getHeight() > map_height) {
				continue;
			}
			if(hedgehogmanMonster.getRectangle().overlaps(hero.getRectangle()))
			{
				continue;
			}
			for (RectangleMapObject rectangleObject : collsionObjects
					.getByType(RectangleMapObject.class)) {
				Rectangle rectangle = rectangleObject.getRectangle();
				if (rectangle.overlaps(hedgehogmanMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
			for (Monster monster : monsters) {
				Rectangle rectangle = monster.getRectangle();
				if (rectangle.overlaps(hedgehogmanMonster.getRectangle())) {
					isCorrect = false;
					break;
				}
			}
		} while (!isCorrect);
		monsters.add(hedgehogmanMonster);
	}

	// private void initCoins() {
	// // TODO Auto-generated method stub
	// for (int i = 0; i < 10; i++) {
	// int ranX = MathUtils.random(map_width);
	// int ranY = MathUtils.random(map_height);
	// coins.add(new Coin(new Vector2(ranX, ranY)));
	// }
	// }

	@Override
	protected void updateWorld() {
		if(hero.getHealth() <= 0)
		{
			return;
		}
		// TODO Auto-generated method stub
		float deltaTime = Gdx.graphics.getDeltaTime();
		hero.update(deltaTime);
		if (hero.position.x < -hero.getWidth()) {
			hero.position.set(0, 0);
			game.setScreen(ScreenFactory.create(Map.A, game));
		}
		detectCollision();
		for (int i = 0;i < monsters.size;i ++) {
			Monster monster = monsters.get(i);
			monster.update(deltaTime);
			Array<Magic> magics = hero.getMagics();
			for (Magic magic : magics) {
				monster.hitMagic(magic, hero);
			}
			monster.hitHero(hero);
			if (!monster.isAlive()) {
				monsters.removeValue(monster, true);
				Array<Coin> coins2 = monster.getCoins();
				coins.addAll(coins2);
				Array<Equipment> dropEquipments = monster.getDropEquipments();
				equipments.addAll(dropEquipments);
			}
		}
		for (Coin coin : coins) {
			coin.update(deltaTime);
			coin.hitHero(hero);
			if (!coin.isAlive()) {
				coins.removeValue(coin, true);
			}
		}

		for (Equipment equipment : equipments) {
			equipment.update(deltaTime);
			equipment.hitHero(hero);
			if (!equipment.isAlive()) {
				equipments.removeValue(equipment, true);
			}
		}
	}

	private void detectCollision() {
		for (RectangleMapObject rectangleObject : collsionObjects
				.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			CollisionDetecter collisionDetecter = new CollisionDetecter();
			collisionDetecter.antiWalk(rectangle, hero);
			;
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
		for (int i = 0;i < monsters.size;i++) {
			Monster monster = monsters.get(i);
			monster.draw(batch);
		}
		for (Coin coin : coins) {
			coin.draw(batch);
		}
		for (Equipment equipment : equipments) {
			equipment.draw(batch);
		}
		hero.draw(batch);
		if(hero.getHealth() <= 0)
		{
//			font.draw(batch, "Game Over", camera.position.x - 32 * 9, camera.position.y - 32);
			batch.draw(new TextureRegion(Assets.gameover), camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
		}
		batch.end();
		super.drawUI();
	}

}
