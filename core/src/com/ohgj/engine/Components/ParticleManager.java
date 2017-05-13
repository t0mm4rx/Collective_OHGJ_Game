package com.ohgj.engine.Components;

import com.ohgj.engine.Util.Particle;

import java.util.ArrayList;

import com.ohgj.engine.Game.AbstractGameObject;
import com.ohgj.engine.Util.LayoutSorter;

public class ParticleManager extends Component {

    private ArrayList<Particle> particles, toDelete;

    public ParticleManager(AbstractGameObject go) {
        super(go);
        particles = new ArrayList<>();
        toDelete = new ArrayList<>();
    }

    public void addParticle(Particle p) {
        particles.add(p);
    }

    public void render() {
        for (Particle p : LayoutSorter.sortParticlesByLayout(particles)) {
            p.render();
        }
    }

    public void renderInHUD() {

    }

    public void update() {
        for (Particle p : LayoutSorter.sortParticlesByLayout(particles)) {
            p.update();
            if (p.hasRequestedDelete()) {
                toDelete.add(p);
            }
        }
        if (toDelete.size() > 0) {
            for (int i = 0; i < toDelete.size(); i++) {
                if (i < particles.size()) {
                    particles.get(i).dispose();
                    particles.remove(i);
                }
            }
            for (int i = 0; i < toDelete.size(); i++) {
                toDelete.remove(i);
            }
        }
    }

    public void dispose() {
        for (Particle p : particles) {
            p.dispose();
        }
    }
}
