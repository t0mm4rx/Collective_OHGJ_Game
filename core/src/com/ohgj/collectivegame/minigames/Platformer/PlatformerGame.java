package com.ohgj.collectivegame.minigames.Platformer;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.GameObject;
import com.ohgj.engine.IO.Keys;

/**
 * Created by Constantin Ross on 12.07.2017.
 */

public class PlatformerGame extends MiniGame{

    public void show() {
        world.setGravity(new Vector2(0,-9.8f));

        add(new Player(new Transform(Game.center)));

        GameObject Flor = new GameObject(new Transform(new Vector2(Game.center.x,0)));
        Flor.addComponent(new BoxRenderer(Flor,Game.size.x,0.2f, Color.RED));
        Flor.addComponent(new BoxBody(Flor,Game.size.x,0.2f, BodyDef.BodyType.StaticBody,false));
        add(Flor);

        fadeIn(0.6f);
    }

    public void update() {
        if (Keys.isKeyJustPressed(Input.Keys.G)) {
            Game.debugging = !Game.debugging;
        }
    }

    public void draw() {

    }

    public String getGameName() {
        return "PlatformerGame";
    }

}
