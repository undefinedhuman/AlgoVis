package de.undefinedhuman.algovis.gui.transforms.offset;

public class PixelOffset extends Offset {

    public PixelOffset(float value) {
        super(value);
    }

    @Override
    public int getValue(float guiScale) {
        return (int) (value * guiScale);
    }

}
