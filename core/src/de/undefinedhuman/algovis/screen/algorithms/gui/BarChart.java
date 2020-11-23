package de.undefinedhuman.algovis.screen.algorithms.gui;

import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.gui.Gui;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.RelativeConstraint;
import de.undefinedhuman.algovis.utils.Utils;

import java.util.ArrayList;

public class BarChart extends Gui {

    private Bar[] bars;
    private ArrayList<Integer> possibleElements = new ArrayList<>();

    public BarChart() {
        super("blank.png");
        setColor(Variables.DARK_GREY);
        set(new PixelConstraint(0), new RelativeConstraint(0.3f), new RelativeConstraint(0.75f), new RelativeConstraint(0.6f));

        bars = new Bar[Variables.AMOUNT_OF_ELEMENTS];
        for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS; i++)
            this.addChild(bars[i] = new Bar(i));
    }

    public BarChart randomize() {
        possibleElements.clear();
        for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS; i++)
            possibleElements.add(i + 1);
        for(Bar bar : bars)
            bar.updateValue(possibleElements.remove(Utils.random.nextInt(possibleElements.size())));
        return this;
    }

    public void reset() {
        resetColors();
        randomize();
        for(Bar bar : bars)
            bar.reset();
    }

    public void resetColors() {
        for(Bar bar : bars)
            bar.setColor(Color.WHITE);
    }

    public Bar[] getBars() {
        return bars;
    }

    public Bar get(int index) {
        return bars[index];
    }

}
