package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

public class Racket extends AbstractGameObject {

    //This components will handle collisions
    Body body;
    public float width, height;

    public Racket(Transform transform) {
        super(transform);

        width = 0.1f;
        height = 1f;

        body = new BoxBody(this, width, height, BodyDef.BodyType.KinematicBody, false);
        // Then we add it to the gameobject
        addComponent(body);

        addComponent(new SpriteRenderer(this, Gdx.files.internal("texture.jpg"), 0, 0, width, height));

        setTag("Racket");

    }

    protected void update(float delta) {

    }
}
