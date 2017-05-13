package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.collectivegame.minigames.TestMiniGame;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.Screen;
import com.ohgj.engine.IO.Keys;

public class ArcadeRoom extends Screen {

    Player player;
    boolean hasQuitted = false;

    public ArcadeRoom(Game game) {
        super(game);
    }

    public void show() {
        // init stuff here

        world.setGravity(new Vector2(0, 0));
        areLightsEnabled(true);

        // Transform object contains location, scale and rotation. Game.center is a vector that contains screen center coordonates.
        player = new Player(new Transform(Game.center));
        // We add the player to the screen
        add(player);

        // Adding arcades
        add(new Arcade(new Vector2(5, 5), () -> {
            loadMiniGame(new TestMiniGame());
            return false;
        }));
        add(new Arcade(new Vector2(6, 5), () -> {
            System.out.println("2");
            return false;
        }));
        add(new Arcade(new Vector2(7, 5), () -> {
            System.out.println("3");
            return false;
        }));

    }

    public void renderBefore() {

    }

    public void renderAfter() {

    }

    private void loadMiniGame(MiniGame game) {
        if (!hasQuitted) {
            fadeOut(0.4f);
            Game.waitAndDo(400, () -> {
                setScreen(game);
                return false;
            });
            hasQuitted = true;
        }
    }

    public void update() {

        // If escape is pressed, the debug mode will be swithed
        if (Keys.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Game.debugging = !Game.debugging;
        }
    }

}
