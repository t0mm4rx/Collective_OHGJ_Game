package com.ohgj.engine.Collisions;

import com.badlogic.gdx.physics.box2d.Contact;

import com.ohgj.engine.Game.AbstractGameObject;

public interface CollisionsListener {

    void collisionEnter(AbstractGameObject a, AbstractGameObject b, Contact contact);
    void collisionEnd(AbstractGameObject a, AbstractGameObject b, Contact contact);

}
