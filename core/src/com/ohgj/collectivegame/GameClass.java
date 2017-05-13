package com.ohgj.collectivegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ohgj.collectivegame.hub.ArcadeRoom;
import com.ohgj.collectivegame.minigames.pong.PongGame;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Util.Util;

public class GameClass extends Game {

	// Some static assets that will be used everywhere
	public static BitmapFont font12, font15, font20;

	public void init() {

		// Init assets
		font12 = Util.ttfToBitmap(Gdx.files.internal("GUI/font.ttf"), 12);
		font15 = Util.ttfToBitmap(Gdx.files.internal("GUI/font.ttf"), 15);
		font20 = Util.ttfToBitmap(Gdx.files.internal("GUI/font.ttf"), 20);

		setScreen(new ArcadeRoom(this));
	}

}
