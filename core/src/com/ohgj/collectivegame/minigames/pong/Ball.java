package com.ohgj.collectivegame.minigames.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.ohgj.engine.Collisions.CollisionsListener;
import com.ohgj.engine.Collisions.CollisionsManager;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.CircleBody;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;

public class Ball extends AbstractGameObject {

    Body body;
    Vector2 vel;

    public Ball(Transform transform) {
        super(transform);

        // bye, thx :)
        body = new CircleBody(this, 0.1f, BodyDef.BodyType.DynamicBody, false);
        addComponent(body);

        // First, we will lock the ball rotation
        body.getBody().setFixedRotation(true);

        addComponent(new SpriteRenderer(this, Gdx.files.internal("texture.jpg"), 0, 0, 0.1f, 0.1f));

        vel = new Vector2(3, 3);

        setTag("Ball");

        new CollisionsManager(new CollisionsListener() {
            public void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact) {
                if (a.getTag().equals("Ball") && b.getTag().equals("Racket")) {
                    vel.x = -vel.x;
                }
                if (b.getTag().equals("Ball") && a.getTag().equals("Racket")) {
                    vel.x = -vel.x;
                }
            }

            public void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact) {

            }
        });
    }


    protected void update(float delta) {
        // You're welcome :) Here we will do the screen bounds collisions
        // Do you understand that part ?
        if (getTransform().getPosition().x < 0 || getTransform().getPosition().x > Game.size.x) {
            vel.x = -vel.x;
        }
        if (getTransform().getPosition().y < 0 || getTransform().getPosition().y > Game.size.y) {
            vel.y = -vel.y;
        }
        body.getBody().setLinearVelocity(vel);

    }
}
