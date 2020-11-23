package de.undefinedhuman.algovis.gui.event;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ChangeEvent extends Event {

    public abstract void notify(float progress);

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {

    }

}
