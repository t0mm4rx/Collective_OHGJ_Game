package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.Text;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

import java.util.concurrent.Callable;

public class Arcade extends AbstractGameObject {

    Body body;
    Text text;
    Callable action;

    public Arcade(Vector2 position, Callable action) {
        super(new Transform(position));

        this.action = action;

        body = new BoxBody(this, 0.4f, 0.4f, BodyDef.BodyType.StaticBody, false);
        addComponent(body);

        addComponent(new BoxRenderer(this, 0.4f, 0.4f, Color.GREEN));

        text = new Text(this, "Press enter", Color.WHITE);
        text.getColor().a = 0;
        addComponent(text);

        // We set a tag for the player can get them from the screen
        setTag("Arcade");
    }

    public void doAction() {
        try {
            action.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void update(float delta) {

    }
}
