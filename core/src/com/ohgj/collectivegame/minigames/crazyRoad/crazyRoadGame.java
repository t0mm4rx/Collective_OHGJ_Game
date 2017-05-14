package com.ohgj.collectivegame.minigames.crazyRoad;

import java.util.Random;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;

public class crazyRoadGame extends MiniGame{

    private String name = "Crazy Road";

    Random random;

    private Player player;
    private int playerPos = 0;
    private boolean keyHeldDown = false;

    private Obstacle[] obstacles = new Obstacle[]{};
    private Vector2[] obstacleSpawns = new Vector2[] {new Vector2(Game.center.x, Game.center.y + 5)};

    public void draw() {
        Draw.rect(Game.center.x + 0.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
        Draw.rect(Game.center.x - 0.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));

        Draw.rect(Game.center.x + 1.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
        Draw.rect(Game.center.x - 1.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
    }

    public void show() {
        random = new Random();

        //camera.position = new Vector3(Game.center.x, Game.center.y, camera.position.z);
        //world.setGravity(new Vector2(0f, 0f));

        player = new Player(new Transform(new Vector2(Game.center.x, Game.center.y - 2)));

        add(player);

        createObstacle();
    }

    public void update() {
        HandlePlayerInput(player.body, Input.Keys.A, Input.Keys.D);
        HandlePlayerInput(player.body, Input.Keys.LEFT, Input.Keys.RIGHT);
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
        //Vector2 spawnPos = obstacleSpawns[random.nextInt(obstacleSpawns.length)];

        Vector2 spawnPos = new Vector2(Game.center.x, Game.center.y + 0);

        Obstacle obstacle = new Obstacle(new Transform(spawnPos));
        //obstacles[obstacles.length + 1] = obstacle;

        add(obstacle);
    }

}
