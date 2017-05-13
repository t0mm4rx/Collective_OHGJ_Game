package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.Screen;
import com.ohgj.engine.IO.Keys;

public class ArcadeRoom extends Screen {

    Player player;

    public ArcadeRoom(Game game) {
        super(game);
    }

    public void show() {
        // init stuff here

        world.setGravity(new Vector2(0, 0));

        // Transform object contains location, scale and rotation. Game.center is a vector that contains screen center coordonates.
        player = new Player(new Transform(Game.center));
        // We add the player to the screen
        add(player);

    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    public void update() {

        // If escape is pressed, the debug mode will be swithed
        if (Keys.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Game.debugging = !Game.debugging;
        }
    }

}
