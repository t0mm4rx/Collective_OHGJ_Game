package com.ohgj.engine.Game;

import com.badlogic.gdx.Gdx;
import com.ohgj.engine.Components.Component;
import com.ohgj.engine.Components.SpriteRenderer;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Util.LayoutSorter;

import java.util.ArrayList;
import java.util.UUID;

public abstract class AbstractGameObject extends Drawable {

    private String id, tag;
    private ArrayList<Component> components;

    public AbstractGameObject(Transform transform) {

        isGameObject = true;

        //Generate UUID
        id = UUID.randomUUID().toString();

        tag = "";
        components = new ArrayList<Component>();

        //Adding a transform component
        this.addComponent(transform);

    }

    public String getUUID() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void addComponent(Component comp) {
        this.components.add(comp);
    }

    public Component getComponentByClass(String className) {
        for (Component comp : components) {
            if (comp.getClass().getSimpleName().equals(className)) {
                return comp;
            }
        }
        //System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public ArrayList<Component> getComponentsByClass(String className) {
        ArrayList<Component> comps = new ArrayList<Component>();
        for (Component comp : components) {
            if (comp.getClass().getSimpleName().equals(className)) {
                comps.add(comp);
            }
        }
        return comps;
    }

    public Transform getTransform() {
        return (Transform)getComponentByClass("Transform");
    }

    public SpriteRenderer getSpriteRenderer() {
        return (SpriteRenderer)getComponentByClass("SpriteRenderer");
    }

    public void render() {
        Game.batch.setProjectionMatrix(Game.getCurrentScreen().camera.combined);
        Game.getCurrentScreen().shapeRenderer.setProjectionMatrix(Game.batch.getProjectionMatrix());
        for (Component comp : LayoutSorter.sortComponentsByLayout(components)) {
            comp.render();
        }
    }

    public void renderInHUD() {
        for (Component comp : LayoutSorter.sortComponentsByLayout(components)) {
            comp.renderInHUD();
        }
    }

    public void update() {
        for (Component comp : components) {
            comp.update();
        }
        update(Gdx.graphics.getDeltaTime());
    }

    protected abstract void update(float delta);

    public void dispose() {
        for (Component comp : components) {
            comp.dispose();
        }
    }

}
