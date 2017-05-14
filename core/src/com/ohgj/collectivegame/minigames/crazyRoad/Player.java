    package com.ohgj.collectivegame.minigames.crazyRoad;

    import java.util.Random;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.files.FileHandle;
    import com.badlogic.gdx.graphics.Color;
    import com.badlogic.gdx.physics.box2d.BodyDef;
    import com.ohgj.engine.Components.Body;
    import com.ohgj.engine.Components.BoxBody;
    import com.ohgj.engine.Components.BoxRenderer;
    import com.ohgj.engine.Components.SpriteRenderer;
    import com.ohgj.engine.Components.Transform;
    import com.ohgj.engine.Game.AbstractGameObject;

    public class Player extends AbstractGameObject{

        Body body;



        public Player(Transform transform){
            super(transform);

            body = new BoxBody(this, 1f, 1f, BodyDef.BodyType.KinematicBody, false);
            addComponent(body);

            //TODO: Add a sprite
            addComponent(new SpriteRenderer(this, Gdx.files.internal("console.png"), 0, 0, 1f, 1f));
        }

        protected void update(float delta) {

        }


    }
