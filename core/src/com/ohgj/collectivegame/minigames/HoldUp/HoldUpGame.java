package com.ohgj.collectivegame.minigames.HoldUp;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.GameClass;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;

/**
 * Created by HellowPixl on 14.05.2017.
 */


public class HoldUpGame extends MiniGame {

    private HoldUpPaddle paddle;
    Color bg;

    private String name = "HoldUp";

    private static int score;
    private static int highScore;

    public void show() {

        //Disable the gravity
        world.setGravity(new Vector2(0, 0));


        //Objects
        paddle = new HoldUpPaddle(new Transform(new Vector2(Game.center.x,0.2f)));
        score = 0;
        add(paddle);
        add(new HoldUpBall());
        bg = new Color(1, 1, 1, 0);
        fadeIn(0.6f);

    }

    public void draw() {

        Draw.rect(Game.center.x, Game.center.y, Game.size.x, Game.size.y, bg);
        Draw.text("Your Score: " + score, Game.center.x / 2 -2.5f, Game.size.y - 0.2f, new Color(1, 1, 1, 1), GameClass.font12);
        Draw.text("HighScore: " + highScore, Game.center.x / 2 -2.5f, Game.size.y - 0.5f, new Color(1, 1, 1, 1), GameClass.font12);
    }


    public void update() {
        handleInputs();


    }


    public void handleInputs() {
        if (Keys.isKeyPressed(Input.Keys.D) && paddle.body.getBody().getPosition().x < Game.size.x - paddle.width / 2) {
            paddle.body.getBody().setTransform(paddle.body.getBody().getPosition().add(0.1f,0), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.A) && paddle.body.getBody().getPosition().x > paddle.width / 2 + 0.1f) {
            paddle.body.getBody().setTransform(paddle.body.getBody().getPosition().add(-0.1f,0), 0);
        }

        if (Keys.isKeyPressed(Input.Keys.RIGHT) && paddle.body.getBody().getPosition().x < Game.size.x - paddle.width / 2) {
            paddle.body.getBody().setTransform(paddle.body.getBody().getPosition().add(0.1f,0), 0);
        }
        if (Keys.isKeyPressed(Input.Keys.LEFT) && paddle.body.getBody().getPosition().x > paddle.width / 2 + 0.1f) {
            paddle.body.getBody().setTransform(paddle.body.getBody().getPosition().add(-0.1f,0), 0);
        }

    }
    public static void addScore(int val){
        score += val;
    }
    public static void setHighScore(int val){
        highScore = val;
    }
    public static int getHighScore(){
        return highScore;
    }
    public static int getScore(){
        return score;
    }
    public static void setScore(int val){
       score = val;
    }

    public String getGameName() {
        return name;
    }
}
