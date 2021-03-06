package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

public class Racket extends AbstractGameObject {


    Body body;
    public float width, height;

    public Racket(Transform transform) {
        super(transform);

        width = 0.1f;
        height = 1f;

        body = new BoxBody(this, width, height, BodyDef.BodyType.KinematicBody, false);
        addComponent(body);

        addComponent(new BoxRenderer(this, width, height, new Color(1, 1, 1, 1)));

        setTag("Racket");

    }

    protected void update(float delta) {
        height += (1-height) / 10;
        ((BoxRenderer)getComponentByClass("BoxRenderer")).setHeight(height);
    }
}
