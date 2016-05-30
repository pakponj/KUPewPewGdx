package com.kupewpew.GameUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Factories.ApproachEnemyFactory;
import com.kupewpew.Factories.SpiralEnemyFactory;
import com.kupewpew.Factories.StraightEnemyFactory;
import com.kupewpew.Models.Bullet;
import com.kupewpew.Models.Player;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter implements InputProcessor,Screen {

	public static Game instance;
	public static boolean startGame;
	private final static int MAX_BULLET_AMOUNT = 100;
	private final static int MAX_ENEMIES = 50;
	private final static float BULLETSPEED = 12f;
	private static Rectangle screenRect;
	private final Timer.Task enemyTimer;
	private final Timer.Task bulletTimer;

	private	SpriteBatch batch;
	private Sprite jetSprite;
	private Player player;

	private List<Enemy> enemiesPool;
	private List<Sprite> enemySpritesPool;
	private List<Bullet> bulletsPool;
	private List<Sprite> bulletSpritesPool;

	private List<Bullet> bulletsOnScreenList;
	private List<Sprite> bulletSpritesOnScreenList;

	private List<Enemy> enemiesOnScreenList;
	private List<Sprite> enemySpritesOnScreenList;

	private static ApproachEnemyFactory approachEnemyFactory = new ApproachEnemyFactory();
	private static StraightEnemyFactory straightEnemyFactory = new StraightEnemyFactory();
	private static SpiralEnemyFactory spiralEnemyFactory = new SpiralEnemyFactory();

//	private int usedBullets = 0;
//	private int enemiesOnScreen = 0;

	public Game() {

		player = Player.getInstance();
		bulletsPool = new ArrayList<Bullet>(MAX_BULLET_AMOUNT);
		enemiesPool = new ArrayList<Enemy>(MAX_ENEMIES);

		bulletsOnScreenList = new ArrayList<Bullet>();
		bulletSpritesOnScreenList = new ArrayList<Sprite>();
		enemiesOnScreenList = new ArrayList<Enemy>();
		enemySpritesOnScreenList = new ArrayList<Sprite>();

		float w = Gdx.graphics.getWidth();
		final float h = Gdx.graphics.getHeight();

		Gdx.app.log("Screen's width", "" + Gdx.app.getGraphics().getWidth());
		Gdx.app.log("Screen's height", "" + Gdx.app.getGraphics().getHeight());
		screenRect = new Rectangle(0, 0, w, h);

		batch = new SpriteBatch();

		Texture jet = new Texture(Gdx.files.internal("jet64x64.png"));
		Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
		Texture enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));

		jetSprite = new Sprite(jet);
		bulletSpritesPool = new ArrayList<Sprite>(MAX_BULLET_AMOUNT);
		enemySpritesPool = new ArrayList<Sprite>(MAX_ENEMIES);

		for(int i = 0; i < MAX_BULLET_AMOUNT; i++) {
			bulletsPool.add(new Bullet(BULLETSPEED));
			bulletSpritesPool.add(new Sprite(bulletTexture));
		}
		for(int i = 0; i < MAX_ENEMIES; i++) {
			enemiesPool.add(createEnemy());
			Sprite enemySprite = new Sprite(enemyTexture);
			enemySpritesPool.add(enemySprite);
		}

		float posX = w/2 - jetSprite.getWidth()/2;
		float posY = h/2 - jetSprite.getHeight()/2;
		player.setpX(posX);
		player.setpY(posY);

		jetSprite.setPosition(player.getpX(), player.getpY());

		enemyTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if (enemiesOnScreenList.size() < MAX_ENEMIES) {
					Enemy enemy = enemiesPool.remove(enemiesOnScreenList.size());
					Sprite enemySprite = enemySpritesPool.remove(enemySpritesOnScreenList.size());
//					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getHeight());
					float spawnX = (float) Math.floor(Math.random() * Gdx.graphics.getWidth());

					enemy.setpX(spawnX);
					enemy.setpY(h - h / 5);

					enemySprite.setPosition(enemy.getpX(), enemy.getpY());

					enemiesOnScreenList.add(enemy);
					enemySpritesOnScreenList.add(enemySprite);

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

		bulletTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {

				Bullet bullet = bulletsPool.remove(bulletsOnScreenList.size());
				Sprite bulletSprite = bulletSpritesPool.remove(bulletSpritesOnScreenList.size());

				bullet.setPosX(player.getpX());
				bullet.setPosY(player.getpY());

				bulletSprite.setPosition(bullet.getPosX(), bullet.getPosY());

				bulletsOnScreenList.add(bullet);
				Gdx.app.log("Bullets #", "" + bulletsOnScreenList.size());

				bulletSpritesOnScreenList.add(bulletSprite);
//				Gdx.app.log("BulletSprites #",""+bulletSpritesOnScreenList.size());

			}
		}, 1, 0.75f);

		Gdx.input.setInputProcessor(this);
	}

	public static Game getInstance() {
		return new Game();
	}

	@Override
	public void create() {

	}

	@Override
	public void update() {

	}

	@Override
	public void dispose() {

	}

	public void resetGame() {
		ScreenManager.setScreen(new GameOverScreen());
		startGame = false;
		bulletTimer.cancel();;
		enemyTimer.cancel();
		Gdx.app.log("Player'Status", "You are Dead");
	}

	@Override
	public void render (SpriteBatch sb) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

//		Gdx.app.log("Player", "Player's Live: "+player.getLive() + ", Player's Health: "+player.getHP());

		if( player.isDead() ) {
			resetGame();
//			Gdx.app.log("Player'Status", "You are Dead");
		} else {
			//separate to drawPlayer
			jetSprite.setPosition(player.getpX(), player.getpY());

			//Pooling bullets
			drawBullets();

			//drawEnemies
			if (enemiesOnScreenList.size() > 0) {
				drawEnemies();
				//bullet collision
				enemyCollision();
			}
			//Player collsion
			playerCollision();
			jetSprite.draw(batch);
		}
		batch.end();
	}

	public void drawBullets() {
		if (bulletsOnScreenList.size() > 0) {

			for (int i = 0; i < bulletsOnScreenList.size(); i++) {
				Bullet checkingBullet = bulletsOnScreenList.get(i);
				Sprite checkingBulletSprite = bulletSpritesOnScreenList.get(i);

				if (isBulletOutOfScreen(checkingBullet, checkingBulletSprite)) {
					checkingBulletSprite.setAlpha(0);
					bulletsOnScreenList.remove(i);
					bulletSpritesOnScreenList.remove(i);
					bulletsPool.add(checkingBullet);
					bulletSpritesPool.add(checkingBulletSprite);
				} else {
					checkingBulletSprite.setAlpha(1);
					checkingBulletSprite.translateY(BULLETSPEED);
				}

//				Gdx.app.log("# of bullets on screen", ""+bulletsOnScreenList.size());
				checkingBulletSprite.draw(batch);
			}

		}
	}

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

	public boolean isEnemyOutOfScreen(Enemy checkingEnemy, Sprite checkingEnemySprite) {
		Rectangle enemyRect = checkingEnemySprite.getBoundingRectangle();

		return !enemyRect.overlaps(screenRect);
	}

	public void drawEnemies() {
		for (int i = 0; i < enemiesOnScreenList.size(); i++) {
			Enemy enemy = enemiesOnScreenList.get(i);
			Sprite enemySprite = enemySpritesOnScreenList.get(i);

			if (isEnemyOutOfScreen(enemy, enemySprite)) {
				enemySprite.setAlpha(0);
				enemiesOnScreenList.remove(i);
				enemySpritesOnScreenList.remove(i);
				enemiesPool.add(enemy);
				enemySpritesPool.add(enemySprite);
			} else {
				enemySprite.setAlpha(1);
				enemy.move();
				enemySprite.setPosition(enemy.getpX(), enemy.getpY());
			}
			//Gdx.app.log("# of enemies on screen", "" + enemiesOnScreenList.size());
			enemySprite.draw(batch);
		}
	}

	public void playerCollision() {
		//Gdx.app.log("Collide","Player pX" + player.getpX() + "Pla");
		for (int i = 0; i < enemiesOnScreenList.size(); i++) {
			Enemy enemy = enemiesPool.get(i);
			if( (Math.abs(enemy.getpX() -  player.getpX()) < 3) && (Math.abs(enemy.getpY() - player.getpY()) < 3) && !player.isInvulnerable()) {
				player.setInvulnerable(true);
				player.getHurt();
				new Timer().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						Gdx.app.log("Player's Status", "Out of Invulnerability");
						Gdx.app.log("Player's Health", "Health: " + player.getHP());
						player.setInvulnerable(false);
					}
				}, 3, 3, 1 );
				break;
			}
		}
	}

	public void enemyCollision() {
		if (enemiesOnScreenList.size() <= 0) return;

		for (int i = 0; i < bulletsOnScreenList.size(); i++) {
			Bullet checkingBullet = bulletsOnScreenList.get(i);
			Sprite checkingBulletSprite = bulletSpritesOnScreenList.get(i);

			for (int j = 0; j < enemiesOnScreenList.size(); j++) {
				Enemy checkingEnemy = enemiesOnScreenList.get(j);
				Sprite checkingEnemySprite = enemySpritesOnScreenList.get(j);
				//Check collision
				Rectangle bulletRect = checkingBulletSprite.getBoundingRectangle();
				Rectangle enemyRect = checkingEnemySprite.getBoundingRectangle();
				if (bulletRect.overlaps(enemyRect)) {
					checkingBulletSprite.setAlpha(0);
					checkingEnemySprite.setAlpha(0);
					bulletsOnScreenList.remove(i);
					bulletSpritesOnScreenList.remove(i);
					enemiesOnScreenList.remove(j);
					enemySpritesOnScreenList.remove(j);

					bulletsPool.add(checkingBullet);
					bulletSpritesPool.add(checkingBulletSprite);

					enemiesPool.add(checkingEnemy);
					enemySpritesPool.add(checkingEnemySprite);
				}

//				if( (Math.abs(checkingEnemy.getpX() -  checkingBullet.getPosX()) < 32) && (Math.abs(checkingEnemy.getpY() - checkingBullet.getPosY()) < 32)) {
//				if( (Math.abs(checkingEnemy.getpY() - checkingBullet.getPos().x) < 32)) {
//					checkingBulletSprite.setAlpha(0);
//					checkingEnemySprite.setAlpha(0);
//					bulletsOnScreenList.remove(checkingBullet);
//					bulletSpritesOnScreenList.remove(checkingBulletSprite);
//					enemiesOnScreenList.remove(checkingEnemy);
//					enemySpritesOnScreenList.remove(checkingEnemySprite);

//					bulletSprites.get(j).setAlpha(0);
//					bulletList.remove(bullet);
//					bulletList.add(bullet);
//					bulletSprites.remove(checkingBullet);
//					bulletSprites.add(checkingBullet);
//					usedBullets--;
//					enemiesPool.remove(enemy);
//					enemiesPool.add(enemy);
//					enemySprites.remove(checkingEnemy);
//					enemySprites.add(checkingEnemy);
//					enemiesOnScreen--;
//					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getHeight());
//
//					bullet.setPosX(player.getpX());
//					bullet.setPosY(player.getpY());
//					checkingBullet.setPosition(bullet.getPosX(), bullet.getPosY());
//					enemy.setpX(0);
//					enemy.setpY(spawnY);
//					checkingEnemy.setPosition(enemy.getpX(), enemy.getpY());
//				Gdx.app.log("Enemies", "Enemies on screen after destroyed: " + enemiesOnScreenList.size());
//					Gdx.app.log("Enemies", "Enemies on screen after destroyed: " + enemiesOnScreen);
//					break;
//				}

			}
		}
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
		int random = (int) Math.floor(Math.random() * 3);
		if(random == 0) {
			return approachEnemyFactory.createEnemy();
		} else if(random == 1) {
			return straightEnemyFactory.createEnemy();
		} else {
			return spiralEnemyFactory.createEnemy();
		}
	}
}