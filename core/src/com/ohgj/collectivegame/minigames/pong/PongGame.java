package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;

public class PongGame extends MiniGame {

    Racket racket1, racket2;

    public void show() {

        //Disable the gravity :)
        world.setGravity(new Vector2(0, 0));


        //Transform object contains positon, scale and rotation

        racket1 = new Racket(new Transform(new Vector2(1, Game.center.y)));
        racket2 = new Racket(new Transform(new Vector2(9, Game.center.y)));

        // Now we add those gameobjects to this game
        add(racket1);
        add(racket2);

        add(new Ball(new Transform(Game.center)));

    }

    public void draw() {
        //Don't hesiitate to ask questions
    }

    public void update() {

        // Here is the update loop, we can check here keyboard inputs
        // For left player
        if (Keys.isKeyPressed(Input.Keys.UP) && racket1.body.getBody().getPosition().y < Game.size.y - racket1.height / 2) {
            racket1.body.getBody().setTransform(racket1.body.getBody().getPosition().add(0, 0.2f), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.DOWN) && racket1.body.getBody().getPosition().y > racket1.height / 2) {
            racket1.body.getBody().setTransform(racket1.body.getBody().getPosition().add(0, -0.2f), 0);
        }
        // For right player
        if (Keys.isKeyPressed(Input.Keys.W) && racket2.body.getBody().getPosition().y < Game.size.y - racket2.height / 2) {
            racket2.body.getBody().setTransform(racket2.body.getBody().getPosition().add(0, 0.2f), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.S) && racket2.body.getBody().getPosition().y > racket2.height / 2) {
            racket2.body.getBody().setTransform(racket2.body.getBody().getPosition().add(0, -0.2f), 0);
        }

        // Is there anything you want i show you ?
    }
}
