package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.collectivegame.minigames.TestMiniGame;
import com.ohgj.collectivegame.minigames.pong.PongGame;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.Screen;
import com.ohgj.engine.IO.Keys;

public class ArcadeRoom extends Screen {

    Player player;
    boolean hasQuitted = false;
    public static Texture ground;

    public ArcadeRoom(Game game) {
        super(game);
    }

    public void show() {
        // init stuff here

        world.setGravity(new Vector2(0, 0));
        areLightsEnabled(true);

        ground = new Texture("ground.jpg");

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
            // Here is the action that is called when we press enter next to the second arcade
            // So it should load our
            loadMiniGame(new PongGame());
            return false;
        }));
        add(new Arcade(new Vector2(7, 5), () -> {
            System.out.println("3");
            return false;
        }));

    }

    public void renderBefore() {
        for (float x = 0; x < Game.size.x; x += 0.32f) {
            for (float y = 0; y < Game.size.y; y += 0.32f) {
                Draw.texture(ground, x, y, 0.32f, 0.32f);
            }
        }
        //Draw.texture(ground, Game.center.x, Game.center.y, 0.32f, 0.32f);
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
