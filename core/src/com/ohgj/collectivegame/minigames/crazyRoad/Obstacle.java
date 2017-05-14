package com.ohgj.collectivegame.minigames.crazyRoad;

import com.badlogic.gdx.graphics.Color;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

public class Obstacle extends AbstractGameObject{

    Body body;

    public Obstacle(Transform transform) {
        super(transform);



        //TODO: Add sprites
        addComponent(new BoxRenderer(this, 0.5f, 0.5f, new Color(1f, 0.5f, 1f, 1f)));
    }

    protected void update(float delta) {

    }
}
