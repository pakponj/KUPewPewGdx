package com.kupewpew;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Timer;
import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Factories.ApproachEnemyFactory;
import com.kupewpew.Factories.SpiralEnemyFactory;
import com.kupewpew.Factories.StraightEnemyFactory;
import com.kupewpew.Models.Bullet;
import com.kupewpew.Models.Player;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter implements InputProcessor{

//	private final static int MAX_BULLET_AMOUNT = 100;
	private final static int MAX_ENEMIES = 50;
	private final static float BULLET_SPEED = 12f;
	private final static float ENEMY_SPEED = 15f;
	private static Rectangle screenRect;

	private	SpriteBatch batch;
//	private Sprite jetSprite;
	private Player player;
	private Texture enemyTexture;
//	private Texture bulletTexture;
//	private static Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
//	private static Texture jetTexture = new Texture(Gdx.files.internal("jet64x64.png"));
//	private Texture enemyTexture;
//	private static Texture enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));

//	private List<Enemy> enemiesPool;
//	private List<Sprite> enemySpritesPool;
//	private List<Bullet> bulletsPool;
//	private List<Sprite> bulletSpritesPool;
//
//	private final List<Bullet> bulletsOnScreenList = new ArrayList<Bullet>();
//	private List<Sprite> bulletSpritesOnScreenList;
//
//	private List<Enemy> enemiesOnScreenList;
//	private List<Sprite> enemySpritesOnScreenList;

	private List<Bullet> bulletsOnScreenList;// = new ArrayList<Bullet>();
	private Pool<Bullet> bulletsPool;/* = new Pool<Bullet>() {
		@Override
		protected Bullet newObject() {
			return new Bullet(BULLET_SPEED, bulletTexture);
		}
	};*/

	private List<Enemy> enemiesOnScreenList;// = new ArrayList<Enemy>();
	private Pool<Enemy> enemiesPool;/* = new Pool<Enemy>() {
		@Override
		protected Enemy newObject() {
			return createEnemy();
		}
	};*/

	private static ApproachEnemyFactory approachEnemyFactory = new ApproachEnemyFactory();
	private static StraightEnemyFactory straightEnemyFactory = new StraightEnemyFactory();
	private static SpiralEnemyFactory spiralEnemyFactory = new SpiralEnemyFactory();

	private Game instance;
//	private int usedBullets = 0;
//	private int enemiesOnScreen = 0;

//	private static Rectangle screenRect = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	@Override
	public void create () {

		player = Player.getInstance();
//		Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));

		final Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
//		enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));
//		bulletsPool = new ArrayList<Bullet>(MAX_BULLET_AMOUNT);
//		enemiesPool = new ArrayList<Enemy>(MAX_ENEMIES);

//		bulletsOnScreenList = new ArrayList<Bullet>();
//		bulletSpritesOnScreenList = new ArrayList<Sprite>();
//		enemiesOnScreenList = new ArrayList<Enemy>();
//		enemySpritesOnScreenList = new ArrayList<Sprite>();
		bulletsOnScreenList = new ArrayList<Bullet>();
		enemiesOnScreenList = new ArrayList<Enemy>();

		bulletsPool = new Pool<Bullet>() {
			@Override
			protected Bullet newObject() {
				return new Bullet(BULLET_SPEED, bulletTexture);
			}
		};
		enemiesPool = new Pool<Enemy>() {
			@Override
			protected Enemy newObject() {
				return createEnemy();
			}
		};

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		Gdx.app.log("Screen's width", "" + Gdx.app.getGraphics().getWidth());
		Gdx.app.log("Screen's height", "" + Gdx.app.getGraphics().getHeight());
		screenRect = new Rectangle(0, 0, w, h);

		batch = new SpriteBatch();
//		Texture jet = new Texture(Gdx.files.internal("jet64x64.png"));
//		Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
//		Texture enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));

//		jetSprite = new Sprite(jetTexture);
//		bulletSpritesPool = new ArrayList<Sprite>(MAX_BULLET_AMOUNT);
//		enemySpritesPool = new ArrayList<Sprite>(MAX_ENEMIES);

//		for(int i = 0; i < MAX_BULLET_AMOUNT; i++) {
//			bulletsPool.add(new Bullet(BULLET_SPEED, bulletTexture));
//			bulletSpritesPool.add(new Sprite(bulletTexture));
//		}
//		for(int i = 0; i < MAX_ENEMIES; i++) {
//			enemiesPool.add(createEnemy());
//			Sprite enemySprite = new Sprite(enemyTexture);
//			enemySpritesPool.add(enemySprite);
//		}

		float posX = w/2 - player.getSprite().getWidth()/2;
		float posY = h/2 - player.getSprite().getHeight()/2;
		player.setpX(posX);
		player.setpY(posY);

		player.getSprite().setPosition(player.getpX(), player.getpY());

		new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if (enemiesOnScreenList.size() < MAX_ENEMIES) {
//					Enemy enemy = enemiesPool.remove(enemiesOnScreenList.size());
//					Sprite enemySprite = enemySpritesPool.remove(enemySpritesOnScreenList.size());
//					float spawnX = (float) Math.floor(Math.random() * Gdx.graphics.getWidth());
//
//					enemy.setpX(spawnX);
//					enemy.setpY(h - h / 5);
//
//					enemySprite.setPosition(enemy.getpX(), enemy.getpY());
//
//					enemiesOnScreenList.add(enemy);
//					enemySpritesOnScreenList.add(enemySprite);
					Enemy enemy = enemiesPool.obtain();
					float spawnX = (float) Math.floor( Gdx.graphics.getHeight() - (Math.random() * Gdx.graphics.getHeight()));
					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getWidth());
					enemy.init(spawnX, spawnY);
					enemiesOnScreenList.add(enemy);

					Gdx.app.log("Enemies #", "On screen: " + enemiesOnScreenList.size());
				}
			}
		}, 3, 0.5f);

//				if(enemiesOnScreen < 20) {
//					Enemy enemy = enemiesPool.get(enemiesOnScreen);
//
////					float spawnX = (float) Math.floor(Math.random() * (Gdx.graphics.getWidth() / 2) );
//					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getHeight());
//					enemy.setpX(0);
//					enemy.setpY(spawnY);
//
////					Sprite enemySprite = enemySprites.get(enemiesOnScreen++);
//
//					Gdx.app.log("Enemies", "Enemies on screen: " + enemiesOnScreen);
//				}
//	}
//},1, 2.5f);

		new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {

				Bullet bullet = bulletsPool.obtain();
				bullet.init(player.getpX(), player.getpY());
				bulletsOnScreenList.add(bullet);
//				Bullet bullet = bulletsPool.remove(bulletsOnScreenList.size());
//				Sprite bulletSprite = bulletSpritesPool.remove(bulletSpritesOnScreenList.size());
//
//				bullet.setPosX(player.getpX());
//				bullet.setPosY(player.getpY());
//
//				bulletSprite.setPosition(bullet.getPosX(), bullet.getPosY());
//
//				bulletsOnScreenList.add(bullet);

//				Gdx.app.log("Bullets #", "" + bulletsOnScreenList.size());
//				bulletSpritesOnScreenList.add(bulletSprite);
				Gdx.app.log("BulletSprites #",""+bulletsOnScreenList.size());

			}
		}, 1, 0.75f);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

//		Gdx.app.log("Player", "Player's Live: "+player.getLive() + ", Player's Health: "+player.getHP());

		if( player.isDead() ) {

//			Gdx.app.log("Player'Status", "You are Dead");
		} else {
			//separate to drawPlayer
			player.getSprite().setPosition(player.getpX(), player.getpY());

			//Pooling bullets
			if(bulletsOnScreenList.size() > 0 ) drawBullets();

			//drawEnemies
			if (enemiesOnScreenList.size() > 0) {
				drawEnemies();
				//bullet collision
//				enemyCollision();
			}
			//Player collsion
//			playerCollision();
			player.getSprite().draw(batch);
		}

		checkCollision();

		batch.end();
	}

	public void drawBullets() {
//		if (bulletsOnScreenList.size() > 0) {
//
//			for (int i = 0; i < bulletsOnScreenList.size(); i++) {
//				Bullet checkingBullet = bulletsOnScreenList.get(i);
//				Sprite checkingBulletSprite = bulletSpritesOnScreenList.get(i);
//
//				if (isBulletOutOfScreen(checkingBullet, checkingBulletSprite)) {
//					checkingBulletSprite.setAlpha(0);
//					bulletsOnScreenList.remove(i);
//					bulletSpritesOnScreenList.remove(i);
//					bulletsPool.add(checkingBullet);
//					bulletSpritesPool.add(checkingBulletSprite);
//				} else {
//					checkingBulletSprite.setAlpha(1);
//					checkingBulletSprite.translateY(BULLET_SPEED);
//				}
//
////				Gdx.app.log("# of bullets on screen", ""+bulletsOnScreenList.size());
//				checkingBulletSprite.draw(batch);
//			}
//
//		}

		for( Bullet bullet : bulletsOnScreenList ) {

			bullet.update();

			if( !bullet.isAlive() ) {
				bulletsOnScreenList.remove(bullet);
				bulletsPool.free(bullet);
			}
			bullet.getSprite().draw(batch);
		}
	}

	public static Rectangle getScreenRect() { return screenRect; }

	public boolean isBulletOutOfScreen(Bullet checkingBullet, Sprite checkingBulletSprite) {
//		float posX = checkingBullet.getPosX();
//		float posY = checkingBullet.getPosY();
		Rectangle bulletRect = checkingBulletSprite.getBoundingRectangle();
//		Gdx.app.log("Bullet's area", bulletRect.getWidth()+"x"+bulletRect.getHeight());
//		Gdx.app.log("Screen's area", screenRect.getWidth()+"x"+screenRect.getHeight());

		return !bulletRect.overlaps(screenRect);

//		if( posX < 0 || posX > Gdx.graphics.getWidth()) return true;
//		if( posY < 0 || posY > Gdx.graphics.getHeight()) return true;
//		return false;
	}

//	public boolean isEnemyOutOfScreen(Enemy checkingEnemy, Sprite checkingEnemySprite) {
//		Rectangle enemyRect = checkingEnemySprite.getBoundingRectangle();
//
//		return !enemyRect.overlaps(screenRect);
//	}

	public void drawEnemies() {
		for( Enemy enemy : enemiesOnScreenList ) {

			enemy.update();

			if( !enemy.isAlive() ) {
				enemiesOnScreenList.remove(enemy);
				enemiesPool.free(enemy);
			}
			enemy.getSprite().draw(batch);
		}

//		for (int i = 0; i < enemiesOnScreenList.size(); i++) {
//			Enemy enemy = enemiesOnScreenList.get(i);
//			Sprite enemySprite = enemySpritesOnScreenList.get(i);
//
//			if (isEnemyOutOfScreen(enemy, enemySprite)) {
//				enemySprite.setAlpha(0);
//				enemiesOnScreenList.remove(i);
//				enemySpritesOnScreenList.remove(i);
//				enemiesPool.add(enemy);
//				enemySpritesPool.add(enemySprite);
//			} else {
//				enemySprite.setAlpha(1);
//				enemy.move();
//				enemySprite.setPosition(enemy.getpX(), enemy.getpY());
//			}
//			Gdx.app.log("# of enemies on screen", "" + enemiesOnScreenList.size());
//			enemySprite.draw(batch);
//		}
	}

	public void checkCollision() {
		playerCollision();
		enemiesCollision();
	}

	public void playerCollision() {
		Rectangle playerRect = player.getSprite().getBoundingRectangle();
		for( Enemy enemy : enemiesOnScreenList ) {
			Rectangle enemyRect = enemy.getSprite().getBoundingRectangle();
			if( playerRect.overlaps(enemyRect) ) {
				Gdx.app.log("", "Player got hit");
				player.setInvulnerable(true);
				player.getHurt();
				new Timer().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						Gdx.app.log("Player's Status", "Out of Invulnerability, Health: "+player.getHP());
						player.setInvulnerable(false);
					}
				}, 3, 3, 1);
				break;
			}
//			if( Math.abs(enemy.getpX() - player.getpX()) < 32 && Math.abs())
		}

//		for (int i = 0; i < enemiesOnScreenList.size(); i++) {
//			Enemy enemy = enemiesPool.get(i);
//			if( (Math.abs(enemy.getpX() -  player.getpX()) < 32) && (Math.abs(enemy.getpY() - player.getpY()) < 32) && !player.isInvulnerable()) {
//				player.setInvulnerable(true);
//				player.getHurt();
//				new Timer().scheduleTask(new Timer.Task() {
//					@Override
//					public void run() {
//						Gdx.app.log("Player's Status", "Out of Invulnerability");
//						Gdx.app.log("Player's Health", "Health: " + player.getHP());
//						player.setInvulnerable(false);
//					}
//				}, 3, 3, 1 );
//				break;
//			}
//		}
	}

	public void enemiesCollision() {
//		if (enemiesOnScreenList.size() <= 0) return;
		for( Enemy enemy : enemiesOnScreenList ) {
			Rectangle enemyRect = enemy.getSprite().getBoundingRectangle();
			for( Bullet  bullet : bulletsOnScreenList ) {
				Rectangle bulletRect = bullet.getSprite().getBoundingRectangle();
				if(bulletRect.overlaps(enemyRect)) {
					Gdx.app.log("", enemy.getClass()+" got hit");
					bulletsOnScreenList.remove( bullet );
					enemiesOnScreenList.remove( enemy );
					bulletsPool.free( bullet );
					enemiesPool.free( enemy );
				}
			}
		}
//		for (int i = 0; i < bulletsOnScreenList.size(); i++) {
//			Bullet checkingBullet = bulletsOnScreenList.get(i);
//			Sprite checkingBulletSprite = bulletSpritesOnScreenList.get(i);
//
//			for (int j = 0; j < enemiesOnScreenList.size(); j++) {
//				Enemy checkingEnemy = enemiesOnScreenList.get(j);
//				Sprite checkingEnemySprite = enemySpritesOnScreenList.get(j);
//				//Check collision
//				Rectangle bulletRect = checkingBulletSprite.getBoundingRectangle();
//				Rectangle enemyRect = checkingEnemySprite.getBoundingRectangle();
//				if (bulletRect.overlaps(enemyRect)) {
//					checkingBulletSprite.setAlpha(0);
//					checkingEnemySprite.setAlpha(0);
//					bulletsOnScreenList.remove(i);
//					bulletSpritesOnScreenList.remove(i);
//					enemiesOnScreenList.remove(j);
//					enemySpritesOnScreenList.remove(j);
//
//					bulletsPool.add(checkingBullet);
//					bulletSpritesPool.add(checkingBulletSprite);
//
//					enemiesPool.add(checkingEnemy);
//					enemySpritesPool.add(checkingEnemySprite);
//					break;
//				}
//
//
//			}
//		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int sec = (Gdx.graphics.getHeight() - screenY);
		player.setpX(screenX);
		player.setpY(sec);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private Enemy createEnemy() {
		if( enemyTexture == null ) enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));
		int random = (int) Math.floor(Math.random() * 2 );
		if(random == 0) {
			return approachEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		} else if(random == 1) {
			return straightEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		} else {
			return spiralEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		}
	}
}
