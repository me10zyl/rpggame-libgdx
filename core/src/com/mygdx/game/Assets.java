package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Animation heroDown_animation;
	public static Animation heroUp_animation;
	public static Animation heroLeft_animation;
	public static Animation heroRight_animation;
	public static TextureRegion[] herodown_textureRegion = new TextureRegion[4];
	public static TextureRegion[] heroup_textureRegion = new TextureRegion[4];
	public static TextureRegion[] heroleft_textureRegion = new TextureRegion[4];
	public static TextureRegion[] heroright_textureRegion = new TextureRegion[4];
	public static BitmapFont font;
	public static TextureRegion monsterDown_textureRegion;
	public static TextureRegion[] lightlingMagic_textureRegion = new TextureRegion[5];
	public static Animation lightlingMagic_animation;
	public static TextureRegion diamond,staff;
	public static Texture desktopthings1;
	public static TextureRegion gameover;
	public static void load() {
		final Texture texture_hero = new Texture("images/rpg/hero.png");
		for (int i = 0; i < herodown_textureRegion.length; i++)
			herodown_textureRegion[i] = new TextureRegion(texture_hero, i * 32,
					0, 32, 48);
		for (int i = 0; i < heroup_textureRegion.length; i++)
			heroup_textureRegion[i] = new TextureRegion(texture_hero, i * 32,
					3 * 48, 32, 48);
		for (int i = 0; i < heroleft_textureRegion.length; i++)
			heroleft_textureRegion[i] = new TextureRegion(texture_hero, i * 32,
					48, 32, 48);
		for (int i = 0; i < heroright_textureRegion.length; i++)
			heroright_textureRegion[i] = new TextureRegion(texture_hero,
					i * 32, 2 * 48, 32, 48);
		heroDown_animation = new Animation(0.15f, herodown_textureRegion);
		heroUp_animation = new Animation(0.15f, heroup_textureRegion);
		heroLeft_animation = new Animation(0.15f, heroleft_textureRegion);
		heroRight_animation = new Animation(0.15f, heroright_textureRegion);
		heroDown_animation.setPlayMode(Animation.PlayMode.LOOP);
		heroUp_animation.setPlayMode(Animation.PlayMode.LOOP);
		heroRight_animation.setPlayMode(Animation.PlayMode.LOOP);
		heroLeft_animation.setPlayMode(Animation.PlayMode.LOOP);
		font = new BitmapFont(Gdx.files.internal("images/rpg/ffff.fnt"));
		monsterDown_textureRegion = new TextureRegion(new Texture(
				"images/rpg/monster.png"), 32, 48);
		final Texture textureMagic = new Texture("images/rpg/magic.png");
		for (int i = 0; i < 4; i++) {
			lightlingMagic_textureRegion[i] = new TextureRegion(textureMagic, i
					* (textureMagic.getWidth() / 5), 0,
					(textureMagic.getWidth() / 5),
					(textureMagic.getHeight() / 5));
		}
		lightlingMagic_textureRegion[4] = new TextureRegion(textureMagic, 0,
				(textureMagic.getHeight() / 5), (textureMagic.getWidth() / 5),
				(textureMagic.getHeight() / 5));
		lightlingMagic_animation = new Animation(0.1f,
				lightlingMagic_textureRegion);
		desktopthings1 = new Texture("images/rpg/desktopthings.png");
		diamond = new TextureRegion(desktopthings1,32 * 3,0,32,32);
		staff = new TextureRegion(desktopthings1,32 * 2 , 0,32,32);
		gameover = new TextureRegion(new Texture("images/rpg/gameover.png"));
	}
}
