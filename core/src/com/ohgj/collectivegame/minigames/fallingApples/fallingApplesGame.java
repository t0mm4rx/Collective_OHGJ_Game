package com.ohgj.collectivegame.minigames.fallingApples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Collisions.CollisionsListener;
import com.ohgj.engine.Collisions.CollisionsManager;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.CircleBody;
import com.ohgj.engine.Components.ParticleManager;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.IO.Keys;
import com.ohgj.engine.Util.Math;

public class fallingApplesGame extends MiniGame{

    private Player player;
    private boolean keyHeldDown = false;


    public void draw() {
        Draw.rect(Game.center.x + 0.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
        Draw.rect(Game.center.x - 0.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));

        Draw.rect(Game.center.x + 1.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
        Draw.rect(Game.center.x - 1.5f, Game.center.y, 0.01f, 10f, new Color(1f, 1f, 1f, 1f));
    }

    public void show() {
        //camera.position = new Vector3(Game.center.x, Game.center.y, camera.position.z);
        world.setGravity(new Vector2(0, 0));

        player = new Player(new Transform(new Vector2(Game.center.x, Game.center.y - 2)));

        add(player);

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
            body.getBody().setTransform(newPos, 0);


            keyHeldDown = true;
        }
        else if(Keys.isKeyJustPressed(right) && !keyHeldDown){
            //Move Right
            Vector2 newPos = body.getBody().getPosition();
            newPos.x += 1;
            body.getBody().setTransform(newPos, 0);


            keyHeldDown = true;
        }

        else{
            keyHeldDown = false;
        }
    }

}
