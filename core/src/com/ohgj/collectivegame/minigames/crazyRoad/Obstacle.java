package com.ohgj.collectivegame.minigames.crazyRoad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;

import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;


import java.util.Random;

public class Obstacle extends AbstractGameObject{

    Body body;

    private Random random;

    private FileHandle[] sprites = new FileHandle[] {Gdx.files.internal("minigames/crazyRoad/tree.png"), Gdx.files.internal("minigames/crazyRoad/rock.png")};
    private FileHandle sprite;

    public Obstacle(Transform transform) {
        super(transform);

        random = new Random();


        body = new BoxBody(this, 0.5f, 0.5f, BodyDef.BodyType.DynamicBody, false);
        addComponent(body);

        addComponent(new SpriteRenderer(this, setSprite(), 0, 0, 0.5f, 0.5f));
    }

    protected void update(float delta) {
        if(body.getBody().getPosition().y <= Game.center.y - 4 || ((crazyRoadGame)Game.getCurrentScreen()).gameStart){
            ((crazyRoadGame)Game.getCurrentScreen()).RemoveObstacle(this);
        }

    }

    private FileHandle setSprite(){
        int i = random.nextInt(sprites.length);
        random = new Random();

        sprite = sprites[i];

        return sprite;
    }

}


