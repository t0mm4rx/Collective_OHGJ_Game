package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.collectivegame.minigames.HoldUp.HoldUpGame;
import com.ohgj.collectivegame.minigames.crazyRoad.crazyRoadGame;
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

        fadeIn(0.6f);

        camera.zoom = 0.7f;

        world.setGravity(new Vector2(0, 0));
        areLightsEnabled(true);

        ground = new Texture("ground.jpg");

        // Transform object contains location, scale and rotation. Game.center is a vector that contains screen center coordonates.
        player = new Player(new Transform(Game.center));
        // We add the player to the screen
        add(player);

        // Adding arcades
        add(new Interactable(new Vector2(5, 5), Gdx.files.internal("console.png"), () -> {
            loadMiniGame(new PongGame());
            return false;
        },new PongGame().getGameName()));
        add(new Interactable(new Vector2(6, 5), Gdx.files.internal("console.png"), () -> {
            loadMiniGame(new crazyRoadGame());
            return false;
        },new crazyRoadGame().getGameName()));
        add(new Interactable(new Vector2(7, 5), Gdx.files.internal("console.png"), () -> {
            loadMiniGame(new HoldUpGame());
            return false;
        },new HoldUpGame().getGameName()));
        add(new Interactable(new Vector2(8, 3), Gdx.files.internal("portal.png"), () -> {
            setScreen(new UtilsRoom(game));
            return false;
        },"UtilsRoom"));

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

        // If G is pressed, the debug mode will be swithed
        if (Keys.isKeyJustPressed(Input.Keys.G)) {
            Game.debugging = !Game.debugging;
        }

        if (hasQuitted) {
            camera.zoom -= 0.03f;
        } else {
            camera.zoom += (1 - camera.zoom) / 10;
        }
    }

}
