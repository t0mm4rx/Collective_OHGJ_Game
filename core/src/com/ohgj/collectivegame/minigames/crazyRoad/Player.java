    package com.ohgj.collectivegame.minigames.crazyRoad;


    import com.badlogic.gdx.Gdx;

    import com.badlogic.gdx.physics.box2d.BodyDef;
    import com.badlogic.gdx.physics.box2d.Contact;
    import com.ohgj.engine.Collisions.CollisionsListener;
    import com.ohgj.engine.Collisions.CollisionsManager;
    import com.ohgj.engine.Components.Body;
    import com.ohgj.engine.Components.BoxBody;

    import com.ohgj.engine.Components.SpriteRenderer;
    import com.ohgj.engine.Components.Transform;
    import com.ohgj.engine.Game.AbstractGameObject;
    import com.ohgj.engine.Game.Game;


    public class Player extends AbstractGameObject{

        Body body;



        public Player(Transform transform){
            super(transform);

            body = new BoxBody(this, 0.5f, 0.8f, BodyDef.BodyType.KinematicBody, false);
            addComponent(body);

            //TODO: Add a sprite
            addComponent(new SpriteRenderer(this, Gdx.files.internal("minigames/crazyRoad/car.png"), 0, 0, 1f, 1f));

            new CollisionsManager(
                    new CollisionsListener() {
                        public void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact) {
                            Game.getCurrentScreen().shake(0.25f, 200f);
                            ((crazyRoadGame) Game.getCurrentScreen()).GameOver();
                        }

                        public void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact) {

                        }
                    }
            );
        }

        protected void update(float delta) {
            if(((crazyRoadGame)Game.getCurrentScreen()).gameStart){
                body.getBody().setTransform(Game.center.x, Game.center.y - 2, 0f);
            }
        }


    }
