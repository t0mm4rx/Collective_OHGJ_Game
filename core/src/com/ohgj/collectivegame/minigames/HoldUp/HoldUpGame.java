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
import com.ohgj.engine.JSON.JSONObject;
import com.ohgj.engine.Net.HTTP;
import com.ohgj.engine.Net.HTTPListener;

/**
 * Created by HellowPixl on 14.05.2017.
 */


public class HoldUpGame extends MiniGame {

    private HoldUpPaddle paddle;
    Color bg;

    private String name = "HoldUp";

    private static int score;
    private static int highScore;
    final String URL = "https://jsonblob.com/api/ab5ead67-38c9-11e7-ae4c-977a79dcd255";

    public void show() {


        try {
            HTTP.get(URL, new HTTPListener() {
                public void onFinish(String data) {
                    JSONObject json = new JSONObject(data);
                    String content = json.get("content").toString();

                    highScore = Integer.parseInt(content);
                    System.out.println("Got Highscore: " + highScore);
                }

                public void onProgress(float percentage) {
                }

                public void onFail(String error) {
                    System.out.println(error);
                }
            });
        }catch (Exception e){
            highScore = 0;
            e.printStackTrace();
        }
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
    public void addScore(int val){
        score += val;
    }
    public void setHighScore(int val){
        highScore = val;
        try {
            HTTP.put(URL, "{\"content\":\"" + highScore + "\"}", new HTTPListener() {
                public void onFinish(String data) {
                    System.out.println("Saved Highscore: " + highScore);
                }

                public void onProgress(float percentage) {

                }

                public void onFail(String error) {
                    System.out.println(error);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getHighScore(){
        return highScore;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int val){
       score = val;
    }

    public String getGameName() {
        return name;
    }
}
