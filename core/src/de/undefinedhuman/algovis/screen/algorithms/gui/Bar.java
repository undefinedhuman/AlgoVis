package de.undefinedhuman.algovis.screen.algorithms.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.gui.Gui;
import de.undefinedhuman.algovis.gui.transforms.Axis;
import de.undefinedhuman.algovis.gui.transforms.constraints.CenterConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.offset.CenterOffset;
import de.undefinedhuman.algovis.gui.transforms.offset.RelativeOffset;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.gui.GuiComponent;
import de.undefinedhuman.algovis.gui.text.Text;
import de.undefinedhuman.algovis.gui.transforms.constraints.AspectConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.RelativeConstraint;

public class Bar extends Gui {

    public int value = 0;
    private Gui icon, tempIcon;
    private Text iconText, tempIconText;

    public Bar(int index) {
        super("blank.png");
        float width = ((1 - Variables.SPACE) / Variables.AMOUNT_OF_ELEMENTS) - Variables.SPACE;
        set(
                new RelativeConstraint(Variables.SPACE + index * (width + Variables.SPACE)),
                new PixelConstraint(0),
                new RelativeConstraint(width),
                new RelativeConstraint((float) value / Variables.AMOUNT_OF_ELEMENTS)
        );

        addChild(icon = (Gui) new Gui("blank.png")
                        .addChild(
                                iconText = (Text) new Text(value)
                                        .setFontSize(24)
                                        .setColor(Variables.DARK_GREY)
                                        .setPosition(new CenterConstraint(), new CenterConstraint())
                                        .setOffset(new CenterOffset(), new CenterOffset())
                        )
                        .set(new PixelConstraint(0), new PixelConstraint(0), new RelativeConstraint(1), new AspectConstraint())
                        .setOffsetY(new RelativeOffset(-1.5f)),
                tempIcon = (Gui) new Gui("blank.png")
                        .addChild(
                                tempIconText = (Text) new Text(0)
                                        .setFontSize(24)
                                        .setColor(Variables.DARK_GREY)
                                        .setPosition(new CenterConstraint(), new CenterConstraint())
                                        .setOffset(new CenterOffset(), new CenterOffset())
                        )
                        .set(new PixelConstraint(0), new PixelConstraint(0), new RelativeConstraint(1), new AspectConstraint())
                        .setOffsetY(new RelativeOffset(-3f))
                        .setVisible(false)
        );
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void updateValue(int value) {
        this.value = value;
        setValue(Axis.HEIGHT, (float) value / Variables.AMOUNT_OF_ELEMENTS);
        iconText.setText(value);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void updateTempIcon(int value) {
        this.tempIconText.setText(value);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void reset() {
        tempIconText.setText("");
        tempIcon.setVisible(false);
    }

    @Override
    public GuiComponent setColor(Color color) {
        icon.setColor(color);
        return super.setColor(color);
    }

    public void updateTempIconColor(Color color) {
        this.tempIcon.setColor(color);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Gui getTempIcon() {
        return tempIcon;
    }

}
