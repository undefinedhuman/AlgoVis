package de.undefinedhuman.algovis.gui.transforms.constraints;

import de.undefinedhuman.algovis.gui.transforms.Axis;

public class AspectConstraint extends Constraint {

    public AspectConstraint() {
        super(1);
    }

    @Override
    public int getValue(float scale) {
        return currentTransform.getCurrentValue(Axis.WIDTH);
    }


}
