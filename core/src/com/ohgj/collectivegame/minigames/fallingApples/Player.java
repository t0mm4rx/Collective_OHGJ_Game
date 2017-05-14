package com.ohgj.collectivegame.minigames.fallingApples;

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
import com.ohgj.engine.Components.ParticleManager;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;
import com.ohgj.engine.Util.Math;

public class Player extends AbstractGameObject{

    Body body;

    public Player(Transform transform){
        super(transform);

        body = new BoxBody(this, 1f, 1f, BodyDef.BodyType.KinematicBody, false);
        addComponent(body);

        addComponent(new BoxRenderer(this, 1f, 1f, new Color(1f, 1f, 1f, 1f)));
    }

    protected void update(float delta) {

    }
}
