package com.mygdx.game.object.equipment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.object.AbstractObject;
import com.mygdx.game.object.Hero;
import com.mygdx.game.object.SingletonHero;


public abstract class Equipment extends AbstractObject implements AttributeAccessible{
	private Hero hero;
	private TextureRegion texture;
	private float width, height;
	private Rectangle rectangle;
	private boolean isAlive;
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBeEquiped() {
		return isBeEquiped;
	}

	public void setBeEquiped(boolean isBeEquiped) {
		this.isBeEquiped = isBeEquiped;
	}

	private boolean isBeEquiped;
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Hero getHero() {
		return hero;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public Equipment(Vector2 position) {
		super();
		this.position = position;
		this.hero = SingletonHero.getInstance();
		isAlive = true;
		this.position.set(position);
	}

	private int health_attribute = 0;
	private int armor_attribute = 0;
	private int speed_attribute = 0; 
	
	public int getHealth_attribute() {
		return health_attribute;
	}

	public int getArmor_attribute() {
		return armor_attribute;
	}

	public int getSpeed_attribute() {
		return speed_attribute;
	}

	public void setHealth_attribute(int health_attribute) {
		this.health_attribute = health_attribute;
	}

	public void setArmor_attribute(int armor_attribute) {
		this.armor_attribute = armor_attribute;
	}

	public void setSpeed_attribute(int speed_attribute) {
		this.speed_attribute = speed_attribute;
	}
	
	public void addAttribute(){
		hero.setMax_health(hero.getMax_health() < this.health_attribute ? this.health_attribute : hero.getMax_health());
		hero.setArmor(hero.getArmor() + this.armor_attribute);
		hero.setSpeed(hero.getSpeed() + this.speed_attribute);
	}
	
	public void update(float deltaTime) {
		rectangle = new Rectangle(position.x, position.y, width, height);
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(texture, this.position.x, this.position.y);
	}

	public void hitHero(Hero hero) {
		Rectangle rect = hero.getRectangle();
		if (rect.overlaps(rectangle)) {
			Array<Equipment> equipments = hero.getEquipments();
			boolean hasEqualEquipment = false;
			for(Equipment equipment : equipments)
			{
				if(equipment.getType().equals(type))
				{
					hasEqualEquipment = true;
					break;
				}
			}
			if(!hasEqualEquipment)
			{
				isAlive = false;
				hero.putOnNewEquipment(this);
			}
		}
	}
}
