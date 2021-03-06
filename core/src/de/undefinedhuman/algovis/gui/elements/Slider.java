package de.undefinedhuman.algovis.gui.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.undefinedhuman.algovis.gui.Gui;
import de.undefinedhuman.algovis.gui.transforms.Axis;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.offset.CenterOffset;
import de.undefinedhuman.algovis.resources.texture.TextureManager;
import de.undefinedhuman.algovis.utils.Tools;
import de.undefinedhuman.algovis.gui.GuiComponent;
import de.undefinedhuman.algovis.gui.event.ChangeEvent;
import de.undefinedhuman.algovis.gui.texture.GuiTexture;
import de.undefinedhuman.algovis.gui.transforms.constraints.RelativeConstraint;
import de.undefinedhuman.algovis.utils.Mouse;

import java.util.ArrayList;

public class Slider extends Gui {

    private float progress = 1f, progressWidth = 0, progressHeight = 0;
    private boolean grabbed = false;
    private GuiComponent pointer;

    private Texture progressTexture;

    private ArrayList<ChangeEvent> changeListeners;
    private String progressPath, pointerPath;
    private boolean wrapProgress;

    public Slider(GuiTexture backgroundTexture, String progressTexture, String pointerTexture) {
        this(backgroundTexture, progressTexture, pointerTexture, false);
    }

    public Slider(GuiTexture backgroundTexture, String progressTexture, String pointerTexture, float progress) {
        this(backgroundTexture, progressTexture, pointerTexture, false, progress);
    }

    public Slider(GuiTexture backgroundTexture, String progressTexture, String pointerTexture, boolean wrapProgress) {
        this(backgroundTexture, progressTexture, pointerTexture, wrapProgress, 1);
    }

    public Slider(GuiTexture backgroundTexture, String progressTexture, String pointerTexture, boolean wrapProgress, float progress) {
        super(backgroundTexture);
        this.progress = progress;
        this.progressPath = progressTexture;
        this.pointerPath = pointerTexture;
        this.wrapProgress = wrapProgress;
        this.changeListeners = new ArrayList<>();
    }

    @Override
    public void init() {
        initPointer(pointerPath);
        initProgress(progressPath, wrapProgress);
        notifyChangeListener();
    }

    private void initPointer(String texture) {
        addChild(pointer = (Gui) new Gui(texture)
                .set(new RelativeConstraint(progress), new RelativeConstraint(0.5f), new PixelConstraint(24), new PixelConstraint(24))
                .setOffset(new CenterOffset(), new CenterOffset())
        );
    }

    private void initProgress(String texture, boolean wrap) {
        progressTexture = new Texture(TextureManager.instance.getTexture(texture).getTexture().getTextureData());
        if (wrap) progressTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
    }

    private void notifyChangeListener() {
        for (ChangeEvent changeListener : changeListeners)
            changeListener.notify(progress);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        resizePointer();
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {

        if ((isClicked(camera) || pointer.isClicked(camera)) && Mouse.isLeftClicked()) grabbed = true;
        else if (!Mouse.isLeftClicked()) grabbed = false;

        if (grabbed) {
            float mouseX = Mouse.getMouseCoords().x;
            this.progress = Tools.clamp((mouseX - getCurrentValue(Axis.X)) / getCurrentValue(Axis.WIDTH), 0, 1f);
            resizePointer();
            notifyChangeListener();
        }

        super.render(batch, camera);
        batch.draw(progressTexture, getCurrentValue(Axis.X) + texture.getCornerSize(), getCurrentValue(Axis.Y) + texture.getCornerSize(), 0, 0, (int) progressWidth, (int) progressHeight);
        pointer.render(batch, camera);

    }

    private void resizePointer() {
        if (pointer == null) return;
        progressWidth = progress * (getCurrentValue(Axis.WIDTH) - texture.getCornerSize() * 2);
        progressHeight = getCurrentValue(Axis.HEIGHT) - texture.getCornerSize() * 2;
        pointer.setValue(Axis.X, progressWidth / getCurrentValue(Axis.WIDTH));
        pointer.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public float getProgress() {
        return progress;
    }

    public Slider setProgress(float progress) {
        this.progress = progress;
        resizePointer();
        notifyChangeListener();
        return this;
    }

    public Slider addChangeListener(ChangeEvent changeListener) {
        this.changeListeners.add(changeListener);
        return this;
    }

}
