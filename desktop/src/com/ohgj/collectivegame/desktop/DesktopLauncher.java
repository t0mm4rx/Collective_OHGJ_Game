package com.ohgj.collectivegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ohgj.collectivegame.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Collective Game !";
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new GameClass(), config);
	}
}
