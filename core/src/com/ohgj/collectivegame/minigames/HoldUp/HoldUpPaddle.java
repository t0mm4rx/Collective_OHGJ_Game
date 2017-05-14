package com.ohgj.collectivegame.minigames.HoldUp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

/**
 * Created by HellowPixl on 14.05.2017.
 */

public class HoldUpPaddle extends AbstractGameObject {


    Body body;
    public float width, height;

    public HoldUpPaddle(Transform transform) {
        super(transform);

        width = 1f;
        height = 0.1f;

        body = new BoxBody(this, width, height, BodyDef.BodyType.KinematicBody, false);
        addComponent(body);

        addComponent(new BoxRenderer(this, width, height, new Color(1, 1, 1, 1)));

        setTag("Paddle");

    }

    protected void update(float delta) {
        handleInputs();

    }

    public void handleInputs(){

    }
}
