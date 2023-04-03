package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Units.Unit;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasant, rouge, sniper, spearMan, bandit;
	Music music;

	public static final int GANG_SIZE = 10;
	public static ArrayList<Unit> teamWhite;
	public static ArrayList<Unit> teamBlack;
	public static int step = 0;
	private static float dx, dy;
	private boolean isGameOver = false;

	private BitmapFont font;

	// Добавим TextureRegion для каждого спрайта
	private TextureRegion crossBowManRegion;
	private TextureRegion mageRegion;
	private TextureRegion monkRegion;
	private TextureRegion peasantRegion;
	private TextureRegion rougeRegion;
	private TextureRegion sniperRegion;
	private TextureRegion spearManRegion;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fon = new Texture("fons/" + String.valueOf(new Random().nextInt(5)) + ".png");
		//font = new BitmapFont(Gdx.files.internal("fonts/Xeliard.ttf"));
		music = Gdx.audio.newMusic(
				Gdx.files.internal(
						"music/paul-romero-rob-king-combat-theme-0" + String.valueOf(new Random().nextInt(4) + 1) + ".mp3"));
		music.setVolume(.125f);
		music.setLooping(true);
		music.play();
		Init.createTeams();

		// Загрузка спрайтов
		crossBowMan = new Texture("units/CrossBowMan.png");
		mage = new Texture("units/Mage.png");
		monk = new Texture("units/Monk.png");
		peasant = new Texture("units/Peasant.png");
		rouge = new Texture("units/Rouge.png");
		sniper = new Texture("units/Sniper.png");
		spearMan = new Texture("units/SpearMan.png");

		// Определение размеров ячеек поля
		int my = 0;
		my = Math.max(my, crossBowMan.getHeight());
		my = Math.max(my, mage.getHeight());
		my = Math.max(my, peasant.getHeight());
		my = Math.max(my, monk.getHeight());
		my = Math.max(my, rouge.getHeight());
		my = Math.max(my, sniper.getHeight());
		my = Math.max(my, spearMan.getHeight());
		dx = dy = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) / 10f;

		// Создание TextureRegion для каждого спрайта
		crossBowManRegion = new TextureRegion(crossBowMan);
		mageRegion = new TextureRegion(mage);
		monkRegion = new TextureRegion(monk);
		peasantRegion = new TextureRegion(peasant);
		rougeRegion = new TextureRegion(rouge);
		sniperRegion = new TextureRegion(sniper);
		spearManRegion = new TextureRegion(spearMan);

		// Зеркальное отображение для некоторых спрайтов
		monkRegion.flip(true, false);
		peasantRegion.flip(true, false);
		spearManRegion.flip(true, false);
		crossBowManRegion.flip(true, false);
		rougeRegion.flip(true, false);
		sniperRegion.flip(true, false);
		mageRegion.flip(true, false);

	}



	@Override
	public void render() {
		if (step == 0) Gdx.graphics.setTitle("Первый ход.");
		else Gdx.graphics.setTitle("Ход №"+step);


		// Если игра окончена, то отобразить сообщение о результатах игры
		String message;
		if (!Init.teamIsAlive(teamWhite) && !Init.teamIsAlive(teamBlack)) {
			message = "Game Over! Draw";
		} else if (!Init.teamIsAlive(teamWhite)) {
			message = "Game Over! Blue team wins";
		} else {
			message = "Game Over! Green team wins";
		}

		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//font.draw(batch, message, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		//font.draw(batch, "Press Escape to exit", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 50);



		teamBlack.forEach(n -> {
			switch (n.getTYPE()) {
				case "Bandit":
					if (n.isAlive())
						batch.draw(rougeRegion, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
					break;
					case "Peasant":
						if (n.isAlive())
							batch.draw(peasantRegion, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
					case "Sniper":
						if (n.isAlive())
							batch.draw(sniperRegion, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
					case "Witch":
						if (n.isAlive())
							batch.draw(mageRegion, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
			}
		});

		teamWhite.forEach(n -> {
			switch (n.getTYPE()) {
				case "Monk":
					if (n.isAlive())
						batch.draw(monk, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
					break;
					case "Peasant":
						if (n.isAlive())
							batch.draw(peasant, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
					case "Spearman":
						if (n.isAlive())
							batch.draw(spearMan, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
					case "Crossbowman":
						if (n.isAlive())
							batch.draw(crossBowMan, n.getPosition().getX() * dx, (n.getPosition().getY() - 1) * dy);
						break;
				}
		});

		batch.end();



		// Если нажата клавиша Escape, то закрыть игру
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}



		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			step++;
			Init.makeStep();
		}

		// Проверить, победила ли одна из команд
		if (!Init.teamIsAlive(teamWhite) || !Init.teamIsAlive(teamBlack)) {
			isGameOver = true;
		}
	}

		@Override
		public void dispose () {
			batch.dispose();
			fon.dispose();
			//font.dispose();
		}
}