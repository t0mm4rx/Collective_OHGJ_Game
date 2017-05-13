package com.ohgj.engine.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Util.Math;

public class Body extends Component {


    com.badlogic.gdx.physics.box2d.Body body;

    public Body(AbstractGameObject go) {
        super(go);
    }

    protected void initBody(BodyDef.BodyType type, FixtureDef def) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set((getGameObject().getTransform().getPosition().x + offsetX), (getGameObject().getTransform().getPosition().y + offsetY));
        bodyDef.angle = Math.DegreeToRadian(getGameObject().getTransform().getRotation());
        bodyDef.linearDamping = 3f;
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        body.createFixture(def);
    }

    public void render() {}

    public void renderInHUD() {}

    public void update() {
        getGameObject().getTransform().setPosition(new Vector2(body.getPosition().x + offsetX, body.getPosition().y + offsetY));
        getGameObject().getTransform().setRotation(Math.RadianToDegree(body.getAngle()));
    }

    public void dispose() {
        body.getWorld().destroyBody(body);
        body.setUserData(null);
        body = null;
    }

    public com.badlogic.gdx.physics.box2d.Body getBody() {
        return body;
    }
}
