package de.undefinedhuman.algovis.gui.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import de.undefinedhuman.algovis.Main;
import de.undefinedhuman.algovis.gui.Gui;
import de.undefinedhuman.algovis.gui.GuiComponent;
import de.undefinedhuman.algovis.gui.transforms.Axis;
import de.undefinedhuman.algovis.gui.transforms.GuiTransform;
import de.undefinedhuman.algovis.gui.transforms.constraints.CenterConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.Constraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.TextConstraint;
import de.undefinedhuman.algovis.gui.transforms.offset.CenterOffset;
import de.undefinedhuman.algovis.resources.font.Font;
import de.undefinedhuman.algovis.resources.font.FontManager;

public class Text extends GuiComponent {

    private GlyphLayout layout = new GlyphLayout();
    private String text;
    private int align = Align.left;
    private Font fontType = Font.BahnSchrift;
    private BitmapFont font;
    private boolean wrap = false;
    private Constraint lineLength;
    private int fontSize = 16;

    private boolean hasBackground = false;
    private Gui backgroundGui;

    public Text(Object text) {
        super();
        this.text = String.valueOf(text);
        this.font = FontManager.instance.getFont(fontType, Main.guiScale * fontSize);
        setSize(new TextConstraint(layout), new TextConstraint(layout));
        lineLength = new PixelConstraint(0).setGui(this).setAxis(Axis.LINE_LENGTH);
        backgroundGui = (Gui) new Gui("blank.png")
                .set(new CenterConstraint(), new CenterConstraint(), new PixelConstraint(28), new PixelConstraint(28))
                .setOffset(new CenterOffset(), new CenterOffset())
                .setParent(this);
    }

    @Override
    public void resize(int width, int height) {
        font = FontManager.instance.getFont(fontType, Main.guiScale * fontSize);
        layout.setText(font, text, color, lineLength.getValue(Main.guiScale), align, wrap);
        super.resize(width, height);
        backgroundGui.resize(width, height);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        super.render(batch, camera);
        if (!visible || !parent.isVisible()) return;
        if(hasBackground) backgroundGui.render(batch, camera);
        font.draw(batch, layout, getCurrentValue(Axis.X), getCurrentValue(Axis.Y) + getCurrentValue(Axis.HEIGHT));
    }

    @Override
    public void delete() {
        super.delete();
        backgroundGui.delete();
        layout.reset();
    }

    @Override
    public GuiTransform set(Constraint x, Constraint y, Constraint width, Constraint height) {
        return super.set(x, y, new TextConstraint(layout), new TextConstraint(layout));
    }

    @Override
    public GuiTransform setSize(Constraint width, Constraint height) {
        return super.setSize(new TextConstraint(layout), new TextConstraint(layout));
    }

    public Text setText(Object text) {
        this.text = String.valueOf(text);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setFont(Font font) {
        this.fontType = font;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setLineLength(Constraint lineLength) {
        this.lineLength = lineLength.setAxis(Axis.LINE_LENGTH).setGui(this);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setWrap(boolean wrap) {
        this.wrap = wrap;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setAlign(int align) {
        this.align = align;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setFontSize(int fontSize) {
        this.fontSize = fontSize;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return this;
    }

    public Text setBackgroundColor(Color color) {
        hasBackground = true;
        backgroundGui.setColor(color);
        return this;
    }

    public BitmapFont getFont() { return this.font; }

    public Color getColor() { return color; }

    public String getText() { return this.text; }

    public GlyphLayout getLayout() { return layout; }

}