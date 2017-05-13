package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.ohgj.engine.Collisions.CollisionsListener;
import com.ohgj.engine.Collisions.CollisionsManager;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.CircleBody;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Util.Math;

public class Ball extends AbstractGameObject {

    Body body;
    Vector2 vel;

    public Ball() {
        super(new Transform(Game.center));

        body = new BoxBody(this, 0.1f, 0.1f, BodyDef.BodyType.DynamicBody, false);
        addComponent(body);

        // First, we will lock the ball rotation
        body.getBody().setFixedRotation(true);

        addComponent(new BoxRenderer(this, 0.1f, 0.1f, new Color(1, 1, 1, 1)));

        setTag("Ball");
        init();

        new CollisionsManager(new CollisionsListener() {
            public void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact) {
                if (a.getTag().equals("Ball") && b.getTag().equals("Racket")) {
                    vel.x = -vel.x;
                    vel.y += Math.random(-0.4f, 0.4f);
                }
                if (b.getTag().equals("Ball") && a.getTag().equals("Racket")) {
                    vel.x = -vel.x;
                    vel.y += Math.random(-0.4f, 0.4f);
                }
            }

            public void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact) {

            }
        });
    }


    protected void update(float delta) {
        // You're welcome :) Here we will do the screen bounds collisions
        // Do you understand that part ?
        if (getTransform().getPosition().x < 0) {
            init();
        }
        if (getTransform().getPosition().x > Game.size.x) {
            init();
        }
        if (getTransform().getPosition().y < 0 || getTransform().getPosition().y > Game.size.y) {
            vel.y = -vel.y;
        }
        body.getBody().setLinearVelocity(vel);

    }

    private void init() {
        vel = Math.randomVector2(3);
        getTransform().getPosition().set(Game.center);
        body.getBody().setTransform(Game.center, 0);
    }

}
