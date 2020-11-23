package de.undefinedhuman.algovis.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.undefinedhuman.algovis.gui.event.Event;
import de.undefinedhuman.algovis.gui.transforms.GuiTransform;

import java.util.ArrayList;

public class GuiComponent extends GuiTransform {

    protected float alpha = 1;
    protected Color color = new Color(Color.WHITE);

    private ArrayList<Event> events = new ArrayList<>();

    public GuiComponent() {
        super();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        for (Event event : events) event.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        super.render(batch, camera);
        for (Event event : events) event.render(batch, camera);
    }

    @Override
    public void delete() {
        events.clear();
    }

    public GuiComponent setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public GuiComponent setColor(Color color) {
        this.color = color;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public GuiComponent addEvent(Event event) {
        this.events.add(event);
        event.setGuiComponent(this);
        return this;
    }

}
