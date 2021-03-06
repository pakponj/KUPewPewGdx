package com.kupewpew.GameUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Timer;
import com.kupewpew.Enemies.Enemy;
import com.kupewpew.Factories.ApproachEnemyFactory;
import com.kupewpew.Factories.SpiralEnemyFactory;
import com.kupewpew.Factories.StraightEnemyFactory;
import com.kupewpew.Models.Bomb;
import com.kupewpew.Models.Bullet;
import com.kupewpew.Models.Player;
import com.kupewpew.Models.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Game extends ApplicationAdapter implements InputProcessor,Screen ,Observer {

	public static Game instance;
	public static boolean startGame;

	public final static float BULLET_SPEED = 12f;
	public final static float ENEMY_SPEED = 15f;
	public final static float BOMB_SPEED = 20f;

	private static Rectangle screenRect;
	private final Timer.Task enemyTimer, bulletTimer;

	private	SpriteBatch batch;
	private Player player;
	private Texture enemyTexture;

	private List<Bullet> bulletsOnScreenList;
	private Pool<Bullet> bulletsPool;

	private List<Enemy> enemiesOnScreenList;
	private Pool<Enemy> enemiesPool;

	private List<Bomb> bombsOnScreenList;
	private Pool<Bomb> bombsPool;

	private static ApproachEnemyFactory approachEnemyFactory = new ApproachEnemyFactory();
	private static StraightEnemyFactory straightEnemyFactory = new StraightEnemyFactory();
	private static SpiralEnemyFactory spiralEnemyFactory = new SpiralEnemyFactory();

	String score;
	StringBuilder hpBuilder;
	private BitmapFont font;

	private Score observable;
	private static int maxScore = 0;

	public Game() {

		//Score Thing
		score = "Score : 0";
		hpBuilder = new StringBuilder("HP : ");
		font = new BitmapFont();
		font.getData().setScale(5,5);
		font.setColor(Color.BLACK);

		observable = new Score();
		observable.addObserver(this);

		player = Player.getInstance();

		final Texture bulletTexture = new Texture(Gdx.files.internal("bullet32x32.png"));
		bulletsOnScreenList = new ArrayList<Bullet>();
		enemiesOnScreenList = new ArrayList<Enemy>();
		bombsOnScreenList = new ArrayList<Bomb>();

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
		bombsPool = new Pool<Bomb>() {
			@Override
			protected Bomb newObject() {
				return new Bomb(Game.BOMB_SPEED);
			}
		};

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		screenRect = new Rectangle(0, 0, w, h);

		batch = new SpriteBatch();

		float posX = w/2 - player.getSprite().getWidth()/2;
		float posY = h/2 - player.getSprite().getHeight()/2;
		player.setpX(posX);
		player.setpY(posY);

		player.getSprite().setPosition(player.getpX(), player.getpY());

		enemyTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				Enemy enemy = enemiesPool.obtain();
				float spawnX = (float) ( Math.floor(Math.random() * Gdx.graphics.getWidth()) );
				float spawnY =  (Gdx.graphics.getHeight() * 2 / 3) + (float) ( Math.floor(Math.random() * (Gdx.graphics.getHeight() / 3) ) );
				enemy.init(spawnX, spawnY);
				enemiesOnScreenList.add(enemy);
			}
		}, 1, 0.75f);

		bulletTimer = new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {

				Bullet bullet = bulletsPool.obtain();
				bullet.init(player.getpX(), player.getpY());
				bulletsOnScreenList.add(bullet);
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
		bulletTimer.cancel();
		enemyTimer.cancel();
		Gdx.app.log("Player'Status", "You are Dead");
	}

	@Override
	public void render (SpriteBatch sb) {
		this.batch = sb;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//draw font
		batch.begin();
		font.draw(sb, score, 0 , Gdx.graphics.getHeight() * 0.95f );
		font.draw(sb, hpBuilder.toString() + player.getHP(), 0, Gdx.graphics.getHeight() * 0.9f );
		batch.end();

		batch.begin();

		if( player.isDead() ) {
			maxScore = observable.getScore();
			resetGame();
		} else {
			//separate to drawPlayer
			player.getSprite().setPosition(player.getpX(), player.getpY());

			//Pooling bullets
			if(bulletsOnScreenList.size() > 0 ) drawBullets();

			//drawEnemies
			if (enemiesOnScreenList.size() > 0) drawEnemies();

			if(bombsOnScreenList.size() > 0) drawBombs();

			player.getSprite().draw(batch);

			checkCollision();
		}
		batch.end();
	}


	public static Rectangle getScreenRect() { return screenRect; }

	public void drawBullets() {
		for(int i = 0; i < bulletsOnScreenList.size(); i++) {
			Bullet bullet = bulletsOnScreenList.get(i);
			bullet.update();

			if( !bullet.isAlive() ) {
				bulletsOnScreenList.remove(bullet);
				bulletsPool.free(bullet);
			}
			bullet.getSprite().draw(batch);
		}
	}

	public void drawEnemies() {
		for(int i = 0; i < enemiesOnScreenList.size(); i++) {
			Enemy enemy = enemiesOnScreenList.get(i);
			enemy.move();

			if( !enemy.isAlive() ) {
				enemiesOnScreenList.remove(enemy);
				enemiesPool.free(enemy);
			}
			enemy.getSprite().draw(batch);
		}
	}

	public void drawBombs() {
		for(int i = 0; i < bombsOnScreenList.size(); i++) {
			Bomb bomb = bombsOnScreenList.get(i);
			bomb.update();

			if( !bomb.isAlive() ) {
				bombsOnScreenList.remove(bomb);
				bombsPool.free(bomb);
			}
			bomb.getSprite().draw(batch);
		}
	}

	public void checkCollision() {
		playerCollision();
		enemiesCollision();
		explosionCollision();
	}

	public void playerCollision() {
		Rectangle playerRect = player.getSprite().getBoundingRectangle();
		for( Enemy enemy : enemiesOnScreenList ) {
			Rectangle enemyRect = enemy.getSprite().getBoundingRectangle();
			if( playerRect.overlaps(enemyRect) && !player.isInvulnerable()) {
				player.getHurt();
				player.setInvulnerable(true);
				new Timer().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						player.setInvulnerable(false);
					}
				}, 3, 3, 1);
				break;
			}
		}
	}

	public void enemiesCollision() {

		//Collide with enemies
		for(int i = 0; i < enemiesOnScreenList.size(); i++ ) {
			Enemy enemy = enemiesOnScreenList.get(i);
			Rectangle enemyRect = enemy.getSprite().getBoundingRectangle();
			for(int j = 0; j < bulletsOnScreenList.size(); j++ ) {
				Bullet bullet = bulletsOnScreenList.get(j);
				Rectangle bulletRect = bullet.getSprite().getBoundingRectangle();
				if(bulletRect.overlaps(enemyRect)) {
					observable.ready();
					observable.updateScore( enemy.getScore() );

					bulletsOnScreenList.remove( bullet );
					enemiesOnScreenList.remove( enemy );
					dropBomb( enemy );
					bulletsPool.free( bullet );
					enemiesPool.free( enemy );
				}
			}
		}

		//Collide with bombs;
		for(int i = 0; i < bombsOnScreenList.size(); i++) {
			final Bomb bomb = bombsOnScreenList.get(i);
			Rectangle bombRect= bomb.getSprite().getBoundingRectangle();
			Rectangle playerRect = player.getSprite().getBoundingRectangle();
			if(bombRect.overlaps(playerRect) && !bomb.isExploding()) {
				startExplosion(bomb);
			}
		}
	}

	private void startExplosion(final Bomb bomb) {
		bomb.startExplode();
		new Timer().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				bombsOnScreenList.remove(bomb);
				bombsPool.free(bomb);
			}
		}, 3, 0, 1);
	}

	private void explosionCollision() {
		for(int i = 0; i < bombsOnScreenList.size(); i++) {
			Bomb explodingBomb = bombsOnScreenList.get(i);
			if(explodingBomb.isExploding()) {
				Rectangle explosionRanges = explodingBomb.getSprite().getBoundingRectangle();
				for(int j = 0; j < enemiesOnScreenList.size(); j++) {
					Enemy enemy = enemiesOnScreenList.get(j);
					Rectangle enemyRect = enemy.getSprite().getBoundingRectangle();
					if( explosionRanges.overlaps( enemyRect )) {

						observable.ready();
						observable.updateScore( enemy.getScore() );

						enemiesOnScreenList.remove( enemy );
						enemiesPool.free( enemy );
					}
				}
			}
		}
	}

	private void dropBomb(Enemy enemy) {
		int chance = (int) Math.floor( Math.random() * 100);
		if( chance < 15 ) {
			Bomb bomb = bombsPool.obtain();
			if(bomb.getSprite() == bomb.bombSprite) {
				bomb.init(enemy.getpX(), enemy.getpY());
				bombsOnScreenList.add(bomb);
				Gdx.app.log("","Bomb has been dropped");
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
		if( enemyTexture == null ) enemyTexture = new Texture(Gdx.files.internal("invader1_64x64.png"));
		int random = (int) Math.floor(Math.random() * 7);
		if(random == 0) {
			return approachEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		} else if(random == 1) {
			return straightEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		} else {
			return spiralEnemyFactory.createEnemy(enemyTexture, ENEMY_SPEED);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		score = "Score : " + observable.getScore();
	}

	public static int getMaxScore()
	{
		return maxScore;
	}
}