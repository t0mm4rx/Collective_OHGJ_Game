package com.ohgj.collectivegame;

import com.ohgj.collectivegame.hub.ArcadeRoom;
import com.ohgj.engine.Game.Game;

public class GameClass extends Game {

	public void init() {
		setScreen(new ArcadeRoom(this));
	}

}
