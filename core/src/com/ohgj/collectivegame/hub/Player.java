package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Game.GameObject;
import com.ohgj.engine.IO.Keys;

public class Player extends AbstractGameObject {

    Body body;

    final float maxSpeed = 10f;

    public Player(Transform transform) {
        super(transform);

        // We add a body component that will handle physics (movements + collisions)
        body = new BoxBody(this, 0.2f, 0.2f, BodyDef.BodyType.DynamicBody, false);
        body.getBody().setFixedRotation(true);
        addComponent(body);

        // We add boxrenderer that will simply show a red square, for placeholder
        addComponent(new BoxRenderer(this, 0.2f, 0.2f, Color.RED));

    }

    protected void update(float delta) {

        // Keyboard handling
        if (Keys.isKeyPressed(Input.Keys.RIGHT) || Keys.isKeyPressed(Input.Keys.D)) {
            if (body.getBody().getLinearVelocity().x < maxSpeed) {
                body.getBody().applyForceToCenter(0.2f, 0, false);
            }
        }
        if (Keys.isKeyPressed(Input.Keys.LEFT) || Keys.isKeyPressed(Input.Keys.A)) {
            if (body.getBody().getLinearVelocity().x > -maxSpeed) {
                body.getBody().applyForceToCenter(-0.2f, 0, false);
            }
        }
        if (Keys.isKeyPressed(Input.Keys.UP) || Keys.isKeyPressed(Input.Keys.W)) {
            if (body.getBody().getLinearVelocity().y < maxSpeed) {
                body.getBody().applyForceToCenter(0, 0.2f, false);
            }
        }
        if (Keys.isKeyPressed(Input.Keys.DOWN) || Keys.isKeyPressed(Input.Keys.S)) {
            if (body.getBody().getLinearVelocity().y > -maxSpeed) {
                body.getBody().applyForceToCenter(0, -0.2f, false);
            }
        }

        body.getBody().setAwake(true);

        if (getNearestArcade().body.getBody().getPosition().dst(body.getBody().getPosition()) < 1) {
            getNearestArcade().text.setColor(new Color(1, 1, 1, 1));
            if (Keys.isKeyJustPressed(Input.Keys.ENTER)) {
                getNearestArcade().doAction();
            }
        } else {
            getNearestArcade().text.setColor(new Color(1, 1, 1, 0));
        }

    }

    private Arcade getNearestArcade() {
        Arcade a = null;
        for (AbstractGameObject go : Game.getCurrentScreen().getGameObjectsByTag("Arcade")) {
            ((Arcade) go).text.setColor(new Color(1, 1, 1, 0));
            if (a == null) {
                a = ((Arcade) go);
            }
            if (a.body.getBody().getPosition().dst(body.getBody().getPosition()) > ((Arcade)go).body.getBody().getPosition().dst(body.getBody().getPosition())) {
                a = ((Arcade) go);
            }
        }
        return a;
    }
}
