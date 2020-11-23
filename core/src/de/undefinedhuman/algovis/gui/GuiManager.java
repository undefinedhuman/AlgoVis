package de.undefinedhuman.algovis.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.undefinedhuman.algovis.gui.transforms.GuiTransform;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.ScreenConstraint;
import de.undefinedhuman.algovis.utils.Manager;
import de.undefinedhuman.algovis.gui.texture.GuiTemplate;

import java.util.ArrayList;

public class GuiManager extends Manager {

    public static GuiManager instance;
    public GuiTransform screen = null;

    private ArrayList<GuiTransform> guiTransforms = new ArrayList<>();

    public GuiManager() {
        if (instance == null) instance = this;
    }

    @Override
    public void init() {
        screen = new GuiComponent().set(new PixelConstraint(0), new PixelConstraint(0), new ScreenConstraint(), new ScreenConstraint()).initScreen();
    }

    @Override
    public void resize(int width, int height) {
        screen.resize(width, height);
        for (GuiTransform gui : guiTransforms) gui.resize(width, height);
    }

    @Override
    public void update(float delta) {
        for (GuiTransform gui : guiTransforms) gui.update(delta);
    }

    @Override
    public void renderGui(SpriteBatch batch, OrthographicCamera camera) {
        for (GuiTransform gui : guiTransforms)
            gui.render(batch, camera);
    }

    @Override
    public void delete() {
        for (GuiTemplate template : GuiTemplate.values()) template.delete();
        screen.delete();
        guiTransforms.clear();
    }

    public boolean hasGui(GuiTransform gui) { return guiTransforms.contains(gui); }

    public void addGui(GuiTransform... guiTransforms) {
        for (GuiTransform guiTransform : guiTransforms) {
            if (hasGui(guiTransform)) continue;
            this.guiTransforms.add(guiTransform);
            guiTransform.init();
            guiTransform.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public void removeGui(GuiTransform gui) { if (hasGui(gui)) this.guiTransforms.remove(gui); }

}
