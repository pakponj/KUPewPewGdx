package com.kupewpew.GameUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
	private final static float BULLETSPEED = 5f;

	private SpriteBatch batch;
	private Texture jet;
	private Texture bulletTexture;
	private Texture enemyTexture;
	private Sprite jetSprite;
	private Sprite bulletSprite;
	private Player player;
	private List<Enemy> enemiesPool;
	private List<Sprite> enemySprites;
	private List<Bullet> bulletList;
	private List<Sprite> bulletSprites;

	private static ApproachEnemyFactory approachEnemyFactory = new ApproachEnemyFactory();
	private static StraightEnemyFactory straightEnemyFactory = new StraightEnemyFactory();
	private static SpiralEnemyFactory spiralEnemyFactory = new SpiralEnemyFactory();

	private Timer.Task bulletTimer;
	private Timer.Task enemyTimer;


	private int usedBullets = 0;
	private int enemiesOnScreen = 0;


	public Game()
	{

		player = Player.getInstance();
		bulletList = new ArrayList<Bullet>(MAX_BULLET_AMOUNT);
		enemiesPool = new ArrayList<Enemy>(MAX_ENEMIES);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		jet = new Texture(Gdx.files.internal("jet64x64.png"));

		bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
		enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));

		jetSprite = new Sprite(jet);
		bulletSprites = new ArrayList<Sprite>(MAX_BULLET_AMOUNT);
		enemySprites = new ArrayList<Sprite>(MAX_ENEMIES);
		for (int i = 0; i < MAX_BULLET_AMOUNT; i++) {
			bulletList.add(new Bullet());
			bulletSprites.add(new Sprite(bulletTexture));
		}
		for (int i = 0; i < MAX_ENEMIES; i++) {
			enemiesPool.add(createEnemy());
			Sprite enemySprite = new Sprite(enemyTexture);
			enemySprite.rotate90(false);
			enemySprites.add(enemySprite);
		}

		jetSprite.rotate90(false);
		float posX = w / 2 - jetSprite.getWidth() / 2;
		float posY = h / 2 - jetSprite.getHeight() / 2;
		player.setpX(posX);
		player.setpY(posY);

		jetSprite.setPosition(player.getpX(), player.getpY());

		enemyTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if (enemiesOnScreen < 20) {
					Enemy enemy = enemiesPool.get(enemiesOnScreen);

//					float spawnX = (float) Math.floor(Math.random() * (Gdx.graphics.getWidth() / 2) );
					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getHeight());
					enemy.setpX(0);
					enemy.setpY(spawnY);

					Sprite enemySprite = enemySprites.get(enemiesOnScreen++);

					Gdx.app.log("Enemies", "Enemies on screen: " + enemiesOnScreen);
				}
			}
		}, 1, 1);

		bulletTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				Bullet bullet = bulletList.get(usedBullets);
				bullet.setPosX(player.getpX());
				bullet.setPosY(player.getpY());
				Sprite bulletSprite = bulletSprites.get(usedBullets++);

				Gdx.app.log("BulletCount", "Bullet count; " + usedBullets);
			}
		}, 1, 2);

		Gdx.input.setInputProcessor(this);
	}
	public void reset()
	{

	}


	public static Game getInstance()
	{
		return new Game();
	}


	@Override
	public void create() {

	}

	@Override
	public void update() {

	}

	@Override
	public void dispose()
	{

	}

	public void resetGame()
	{
		ScreenManager.setScreen(new GameOverScreen());
		startGame = false;
		bulletTimer.cancel();;
		enemyTimer.cancel();
		Gdx.app.log("Player'Status", "You are Dead");
	}

	@Override
	public void render(SpriteBatch sb) {
		batch=sb;
		if(startGame == true) {
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();

//		Gdx.app.log("Player", "Player's Live: "+player.getLive() + ", Player's Health: "+player.getHP());

			if (player.isDead()) {
				resetGame();
			} else {
				//separate to drawPlayer
				jetSprite.setPosition(player.getpX(), player.getpY());
				if (usedBullets > 0) {
					for (int i = 0; i < usedBullets; i++) {
						Bullet bullet = bulletList.get(i);
						Sprite bulletSprite = bulletSprites.get(i);
						bulletSprite.setAlpha(1);
						bullet.setPosX(bullet.getPosX() - BULLETSPEED);

						bulletSprite.setPosition(bullet.getPosX(), bullet.getPosY());
						bulletSprite.draw(batch);

					}
				}

				//drawEnemies
				if (enemiesOnScreen > 0) {
					drawEnemy();
					//bullet collision
					enemyCollision();
				}
				//Player collsion
				playerCollision();
				jetSprite.draw(batch);
			}
		}
			else{}
		batch.end();

	}

	public void drawEnemy() {
		for(int i = 0; i < enemiesOnScreen; i++) {
			Enemy enemy = enemiesPool.get(i);
			Sprite enemySprite = enemySprites.get(i);
			//enemy.setpX(enemy.getpX() + enemy.getSPEED());
			enemy.move();
			enemySprite.setPosition(enemy.getpX(), enemy.getpY());
			enemySprite.draw(batch);
		}
	}

	public void playerCollision() {
		for(int i = 0; i < enemiesOnScreen; i++) {
			Enemy enemy = enemiesPool.get(i);
			if( (Math.abs(enemy.getpX() -  player.getpX()) < 32) && (Math.abs(enemy.getpY() - player.getpY()) < 32) && !player.isInvulnerable()) {
				player.setInvulnerable(true);

				new Timer().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						Gdx.app.log("Player's Status", "Invulnerable");
						player.setInvulnerable(false);
					}
				}, 3, 3, 1 );
				player.getHurt();
				break;
			}
		}
	}

	public void enemyCollision() {
		for(int i = 0; i < usedBullets; i++) {

			Bullet bullet = bulletList.get(i);
			Sprite checkingBullet = bulletSprites.get(i);
			for(int j = 0; j < enemiesOnScreen; j++) {
				Enemy enemy = enemiesPool.get(j);
				Sprite checkingEnemy = enemySprites.get(j);
				//Check collision
				if( (Math.abs(enemy.getpX() -  bullet.getPosX()) < 32) && (Math.abs(enemy.getpY() - bullet.getPosY()) < 32)) {
					bulletSprites.get(j).setAlpha(0);
					bulletList.remove(bullet);
					bulletList.add(bullet);
					bulletSprites.remove(checkingBullet);
					bulletSprites.add(checkingBullet);
					usedBullets--;
					enemiesPool.remove(enemy);
					enemiesPool.add(enemy);
					enemySprites.remove(checkingEnemy);
					enemySprites.add(checkingEnemy);
					enemiesOnScreen--;
					float spawnY = (float) Math.floor(Math.random() * Gdx.graphics.getHeight());

					bullet.setPosX(player.getpX());
					bullet.setPosY(player.getpY());
					checkingBullet.setPosition(bullet.getPosX(), bullet.getPosY());
					enemy.setpX(0);
					enemy.setpY(spawnY);
					checkingEnemy.setPosition(enemy.getpX(), enemy.getpY());
					Gdx.app.log("Enemies", "Enemies on screen after destroyed: " + enemiesOnScreen);
					break;
				}

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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

    private Vector2 lastTouch = new Vector2();

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int sec = (int) (Gdx.graphics.getHeight() - screenY);
        player.setpX(screenX);
        player.setpY(sec);
        return  true;
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
		int random = (int)Math.floor(Math.random() * 4);
		if(random == 0) {
			return approachEnemyFactory.createEnemy();
		} else if(random == 1) {
			return straightEnemyFactory.createEnemy();
		} else {
			return spiralEnemyFactory.createEnemy();
		}
	}
}
