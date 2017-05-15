package com.ohgj.collectivegame.hub;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ohgj.collectivegame.GameClass;
import com.ohgj.collectivegame.game.MiniGame;
import com.ohgj.engine.Components.Body;
import com.ohgj.engine.Components.BoxBody;
import com.ohgj.engine.Components.BoxRenderer;
import com.ohgj.engine.Components.ConeLight;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Text;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.AbstractGameObject;

import java.util.concurrent.Callable;

public class Interactable extends AbstractGameObject {

    Body body;
    Text text;
    Callable action;



    public Interactable(Vector2 position, FileHandle texture, Callable action, String name) {
        super(new Transform(position));

        this.action = action;

        Texture t = new Texture(texture);
        float width = new Float(t.getWidth()) / 100;
        float height = new Float(t.getHeight()) / 100;
        t.dispose();

        body = new BoxBody(this, width, height, BodyDef.BodyType.StaticBody, false);
        addComponent(body);

        addComponent(new SpriteRenderer(this, texture, 0, 0, width, height));

        text = new Text(this, GameClass.font12, "Press enter to Join: \n"+name, Color.WHITE);
        text.getColor().a = 0;
        text.setOffset(-0.5f, 0.7f);
        addComponent(text);

        // We set a tag for the player can get them from the screen
        setTag("Interactable");
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
