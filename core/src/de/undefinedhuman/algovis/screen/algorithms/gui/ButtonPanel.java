package de.undefinedhuman.algovis.screen.algorithms.gui;

import de.undefinedhuman.algovis.resources.font.Font;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.gui.Gui;
import de.undefinedhuman.algovis.gui.event.ClickEvent;
import de.undefinedhuman.algovis.gui.text.Text;
import de.undefinedhuman.algovis.gui.transforms.constraints.CenterConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.PixelConstraint;
import de.undefinedhuman.algovis.gui.transforms.constraints.RelativeConstraint;
import de.undefinedhuman.algovis.gui.transforms.offset.CenterOffset;
import de.undefinedhuman.algovis.gui.transforms.offset.RelativeOffset;
import de.undefinedhuman.algovis.screen.algorithms.AlgorithmType;

public class ButtonPanel extends Gui {

    public ButtonPanel() {
        super("blank.png");
        setColor(Variables.LIGHT_GREY);
        set(new RelativeConstraint(1), new PixelConstraint(0), new RelativeConstraint(0.25f), new RelativeConstraint(1));
        setOffsetX(new RelativeOffset(-1f));

        for(int i = 0; i < AlgorithmType.values().length; i++) {
            AlgorithmType type = AlgorithmType.values()[i];
            addButton(type.name, i, new ClickEvent() {
                @Override
                public void onClick() {
                    MainScreen.instance.updateThread(type);
                }
            });
        }

        addButton("Reset", AlgorithmType.values().length, new ClickEvent() {
            @Override
            public void onClick() {
                MainScreen.instance.reset();
            }
        });
    }

    private void addButton(String name, int index, ClickEvent event) {
        Gui button = (Gui) new Gui("blank.png")
                .addChild(
                        new Text(name)
                                .setFont(Font.BahnSchrift)
                                .setFontSize(32)
                                .setColor(Variables.DARK_GREY)
                                .setPosition(new CenterConstraint(), new CenterConstraint()).setOffset(new CenterOffset(), new CenterOffset())
                )
                .addEvent(event)
                .set(new RelativeConstraint(0.5f), new RelativeConstraint(0.9725f), new RelativeConstraint(0.9f), new RelativeConstraint(0.075f))
                .setOffset(new CenterOffset(), new RelativeOffset(-1.2f * (index + 1)));
        addChild(button);
    }

}
