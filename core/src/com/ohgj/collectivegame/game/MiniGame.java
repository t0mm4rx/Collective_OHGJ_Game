package com.ohgj.collectivegame.game;

import com.badlogic.gdx.Input;
import com.ohgj.collectivegame.hub.ArcadeRoom;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.Screen;
import com.ohgj.engine.IO.Keys;

public abstract class MiniGame extends Screen{

    boolean hasQuitted = false;

    public MiniGame() {
        super(Game.getCurrentScreen().game);
    }

    public void renderBefore() {
        this.draw();
    }

    public void renderAfter() {

        if (Keys.isKeyJustPressed(Input.Keys.ESCAPE)) {
            quit();
        }

    }

    public void quit() {
        if (!hasQuitted) {
            fadeOut(0.4f);
            Game.waitAndDo(400, () -> {
                setScreen(new ArcadeRoom(this.game));
                return false;
            });
            hasQuitted = true;
        }
    }

    public abstract void draw();
    public abstract String getGameName();
}
