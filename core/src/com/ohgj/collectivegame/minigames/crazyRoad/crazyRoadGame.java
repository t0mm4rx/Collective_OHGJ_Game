package com.ohgj.collectivegame.minigames.crazyRoad;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.GameClass;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;

public class crazyRoadGame extends MiniGame{

    String name = "Crazy Road";

    private Random random;

    private Player player;
    private int playerPos = 0;
    private boolean keyHeldDown = false;

    private Vector2[] obstacleSpawns = new Vector2[] {new Vector2(Game.center.x, Game.center.y + 5), new Vector2(Game.center.x + 1f, Game.center.y + 5), new Vector2(Game.center.x - 1f, Game.center.y + 5)};

    private Texture road = new Texture("minigames/crazyRoad/roadLong.png");

    private float time = 0f;
    private float timer = 0.5f;

    private int score = 0;
    private int highScore = 0;
    private boolean gameOn = false;
    public boolean gameStart = true;
    private boolean gameEnd = false;

    public void draw() {
        Draw.texture(road, Game.center.x + 3, Game.center.y - 3, 3, 9);

        if(gameOn || gameStart){
            Draw.text("Score: " + score, Game.center.x - 5.25f, Game.center.y + 3.4f, new Color(1f, 1f, 1f, 1), GameClass.font15);
            Draw.text("High score: " + highScore, Game.center.x - 5.25f, Game.center.y + 3f, new Color(1f, 1f, 1f, 1), GameClass.font15);
        }
        if(gameStart){
            Draw.text("Press ENTER to start!", Game.center.x - 1.4f, Game.center.y - 1f, new Color(1f, 0.1f, 0.1f, 1f), GameClass.font20);
        }

        if(gameEnd){
            Draw.text("Game Over!", Game.center.x - 1f, Game.center.y + 3.25f, new Color(1f, 0f, 0f, 1f), GameClass.font20);
            Draw.text("      Score: " + score, Game.center.x - 1f, Game.center.y + 3f, new Color(1f, 0.25f, 0.25f, 1f), GameClass.font15);

            Draw.text("Press ENTER to restart!", Game.center.x - 1.4f, Game.center.y + 1f, new Color(1f, 0.1f, 0.1f, 1f), GameClass.font20);
        }

    }

    public void show() {
        random = new Random();

        //camera.position = new Vector3(Game.center.x, Game.center.y, camera.position.z);
        world.setGravity(new Vector2(0f, -9.81f));


        player = new Player(new Transform(new Vector2(Game.center.x, Game.center.y - 2)));

        add(player);

    }

    public void update() {
        time += Gdx.graphics.getDeltaTime();
        if(gameOn){

            if (time >= timer){
                createObstacle();
                time = 0;
            }

            HandlePlayerInput(player.body, Input.Keys.A, Input.Keys.D);
            HandlePlayerInput(player.body, Input.Keys.LEFT, Input.Keys.RIGHT);
        }


        if (gameStart && Keys.isKeyJustPressed(Input.Keys.ENTER)){
            gameOn = true;
            gameStart = false;
        }

        if (gameEnd && Keys.isKeyJustPressed(Input.Keys.ENTER)){

            if(score > highScore)
                highScore = score;

            score = 0;

            gameStart = true;
            gameEnd = false;
        }
    }

    private void HandlePlayerInput(Body body, int left, int right){


        if(Keys.isKeyJustPressed(left) && !keyHeldDown){
            //Move Left
            Vector2 newPos = body.getBody().getPosition();
            newPos.x -= 1;

            if(playerPos > - 1) {
                body.getBody().setTransform(newPos, 0);
                playerPos -= 1;
            }

            keyHeldDown = true;
        }
        else if(Keys.isKeyJustPressed(right) && !keyHeldDown){
            //Move Right
            Vector2 newPos = body.getBody().getPosition();
            newPos.x += 1;

            if(playerPos < 1) {
                body.getBody().setTransform(newPos, 0);
                playerPos += 1;
            }


            keyHeldDown = true;
        }

        else{
            keyHeldDown = false;
        }
    }

    public String getGameName() {
        return name;
    }

    private void createObstacle(){
        int rand = random.nextInt(obstacleSpawns.length);

        Vector2 spawnPos = obstacleSpawns[rand];

        Obstacle obstacle = new Obstacle(new Transform(spawnPos));

        //System.out.print(rand);
        add(obstacle);
    }

    public void RemoveObstacle(Obstacle obstacle){
        if(gameOn)
            score++;

        remove(obstacle);
    }

    public void GameOver(){
        playerPos = 0;

        gameOn = false;
        gameEnd = true;
    }

}
