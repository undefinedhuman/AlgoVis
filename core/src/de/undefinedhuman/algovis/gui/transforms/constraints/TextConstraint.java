package de.undefinedhuman.algovis.gui.transforms.constraints;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.undefinedhuman.algovis.gui.transforms.Axis;

public class TextConstraint extends Constraint {

    private GlyphLayout layout;

    public TextConstraint(GlyphLayout layout) {
        super(0);
        this.layout = layout;
    }

    @Override
    public int getValue(float guiScale) {
        return (int) (axis == Axis.WIDTH ? layout.width : axis == Axis.HEIGHT ? layout.height : 0);
    }

}
