package com.ohgj.collectivegame.minigames.HoldUp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.ohgj.engine.Collisions.CollisionsListener;
import com.ohgj.engine.Collisions.CollisionsManager;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.ParticleManager;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Util.Math;

public class HoldUpBall extends AbstractGameObject {

    Body body;
    Vector2 vel;
    ParticleManager pm;
    private float Speed;


    public HoldUpBall() {
        super(new Transform(Game.center));

        body = new BoxBody(this, 0.1f, 0.1f, BodyDef.BodyType.DynamicBody, false);
        addComponent(body);

        // First, we will lock the ball rotation
        body.getBody().setFixedRotation(true);

        addComponent(new BoxRenderer(this, 0.1f, 0.1f, new Color(1, 1, 1, 1)));
        Speed = 2.0f;
        pm = new ParticleManager(this);
        addComponent(pm);

        setTag("Ball");
        init();
        addParticle();

        new CollisionsManager(new CollisionsListener() {
            public void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact) {

                if (b.getTag().equals("Ball") && a.getTag().equals("Paddle")) {
                    vel.y = -vel.y;
                    vel.x += Math.random(-0.4f, 0.4f);
                    ((HoldUpPaddle) a).height = 1.1f;
                    vel.scl(1.1f);
                    shakeScreen(60);
                    new HoldUpGame().addScore(1);
                }
            }

            public void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact) {

            }
        });
    }


    protected void update(float delta) {


        //Collison
        if (getTransform().getPosition().y <0) {
            init();
            if( new HoldUpGame().getHighScore()<  new HoldUpGame().getScore()) {
                new HoldUpGame().setHighScore( new HoldUpGame().getScore());
            }
            new HoldUpGame().setScore(0);
        }
        if (getTransform().getPosition().x < 0 || getTransform().getPosition().x > Game.size.x) {
            vel.x = -vel.x;
        }
        if(getTransform().getPosition().y>Game.size.y){
            vel.y = - vel.y;
        }
        body.getBody().setLinearVelocity(vel);

    }



    public void shakeScreen(int intensity){
        Game.getCurrentScreen().shake(vel.len() / intensity,100f);
    }

    private void init() {
        vel = new Vector2(Speed,Speed);
        getTransform().getPosition().set(Game.center);
        body.getBody().setTransform(Game.center, 0);
    }

    private void addParticle() {
        Game.waitAndDo(100, () -> {
            pm.addParticle(new HoldUpBallParticle(new Transform(getTransform().getPosition().cpy())));
            addParticle();
            return false;
        });
    }

}