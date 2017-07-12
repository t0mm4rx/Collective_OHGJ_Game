package com.ohgj.collectivegame.minigames.Platformer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

/**
 * Created by Constantin Ross on 12.07.2017.
 */

public class Player extends AbstractGameObject{

    public Player(Transform transform) {
        super(transform);
        addComponent(new BoxRenderer(this, 0.2f,0.2f, Color.GREEN));
        addComponent(new BoxBody(this,0.2f,0.2f, BodyDef.BodyType.DynamicBody,false));
    }

    protected void update(float delta) {

    }
}
