package com.ohgj.collectivegame.minigames.HoldUp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ohgj.engine.Components.Transform;
import com.ohgj.engine.Game.Draw;
import com.ohgj.engine.Util.Particle;

public class HoldUpBallParticle extends Particle{

    float size = 0.1f;
    Vector2 pos;

    public HoldUpBallParticle(Transform transform) {
        super(transform.cpy(), 2000);
        pos = new Vector2(transform.getPosition().cpy());
    }

    public void render() {
        if (size > 0) {
            size -= 0.005f;
        } else {
            size = 0;
        }
        Draw.rect(pos.x, pos.y, size, size, new Color(1, 1, 1, 1));
    }
}
