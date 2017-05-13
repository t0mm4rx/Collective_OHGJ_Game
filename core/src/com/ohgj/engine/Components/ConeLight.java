package com.ohgj.engine.Components;


import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Game.Game;

public class ConeLight extends Component{
    private int power;
    private Color color;
    private box2dLight.ConeLight light;
    private float direction;
    private float length;
    private float lastX, lastY;
    private RayHandler rayHandler;

    public ConeLight(AbstractGameObject go, int power, float length, Color color, float direction, float field) {
        super(go);
        this.power = power;
        this.length = length;
        this.color = color;
        this.direction = direction;
        this.rayHandler = Game.getCurrentScreen().getRayHandler();
        light = new box2dLight.ConeLight(rayHandler, power, color, length, go.getTransform().getPosition().x + offsetX, go.getTransform().getPosition().y + offsetY, direction, field);
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;
    }

    public box2dLight.ConeLight getLight() {
        return light;
    }

    public void setField(float field) {
        light.setConeDegree(field);
    }

    public void setDirection(float angle) {
        light.setDirection(angle);
        this.direction = angle;
    }

    public float getDirection() { return direction; }

    public float getAngle() {
        return light.getConeDegree();
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        if (getGameObject().getTransform().getPosition().x + offsetX != lastX || getGameObject().getTransform().getPosition().y + offsetY != lastY) {
            light.setPosition(getGameObject().getTransform().getPosition().x + offsetX, getGameObject().getTransform().getPosition().y + offsetY);
            lastX = getGameObject().getTransform().getPosition().x + offsetX;
            lastY = getGameObject().getTransform().getPosition().y + offsetY;
        }
    }

    public void dispose() {
        light.setDistance(0);
        light.dispose();
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
        light.setDistance(length);
    }

}
