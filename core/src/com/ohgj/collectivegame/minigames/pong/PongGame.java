package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.GameClass;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;

public class PongGame extends MiniGame {

    Racket racket1, racket2;
    public int scoreLeft = 0, scoreRight = 0;

    public void show() {

        //Disable the gravity :)
        world.setGravity(new Vector2(0, 0));


        //Transform object contains positon, scale and rotation

        racket1 = new Racket(new Transform(new Vector2(1, Game.center.y)));
        racket2 = new Racket(new Transform(new Vector2(Game.size.x - 1, Game.center.y)));

        // Now we add those gameobjects to this game
        add(racket1);
        add(racket2);

        add(new Ball());

    }

    public void draw() {
        Draw.rect(Game.center.x, Game.center.y, 0.01f, Game.size.y, new Color(1, 1, 1, 1));
        Draw.text("" + scoreLeft, Game.center.x / 2, Game.size.y - 1, new Color(1, 1, 1, 1), GameClass.font15);
        Draw.text("" + scoreRight, Game.center.x / 2 + 5, Game.size.y - 1, new Color(1, 1, 1, 1), GameClass.font15);
    }

    public void update() {

        // Here is the update loop, we can check here keyboard inputs
        // For left player
        if (Keys.isKeyPressed(Input.Keys.W) && racket1.body.getBody().getPosition().y < Game.size.y - racket1.height / 2) {
            racket1.body.getBody().setTransform(racket1.body.getBody().getPosition().add(0, 0.1f), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.S) && racket1.body.getBody().getPosition().y > racket1.height / 2) {
            racket1.body.getBody().setTransform(racket1.body.getBody().getPosition().add(0, -0.1f), 0);
        }
        // For right player
        if (Keys.isKeyPressed(Input.Keys.UP) && racket2.body.getBody().getPosition().y < Game.size.y - racket2.height / 2) {
            racket2.body.getBody().setTransform(racket2.body.getBody().getPosition().add(0, 0.1f), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.DOWN) && racket2.body.getBody().getPosition().y > racket2.height / 2) {
            racket2.body.getBody().setTransform(racket2.body.getBody().getPosition().add(0, -0.1f), 0);
        }

        // Is there anything you want i show you ?
    }
}
