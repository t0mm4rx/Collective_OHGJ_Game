package com.ohgj.collectivegame.minigames;

import com.badlogic.gdx.graphics.Color;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Game.Draw;

public class TestMiniGame extends MiniGame {

    public void draw() {
        Draw.rect(1, 1, 1, 1, Color.BLUE);
    }

    @Override
    public void renderBefore() {

    }

    public void update() {

    }

    public void show() {

    }
}
