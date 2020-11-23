package de.undefinedhuman.algovis.screen.algorithms.sorting;

import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Variables;

public class BubbleSort extends Algorithm {

    @Override
    public void run() {
        try {
            for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS; i++) {
                for(int j = 0; j < Variables.AMOUNT_OF_ELEMENTS - i - 1; j++) {
                    if(compareIndex(j, j+1))
                        swap(j, j+1);
                    updateBarColor(Color.WHITE, j, j+1);
                }
                MainScreen.instance.updateText.setText(barChart.get(barChart.getBars().length-i-1).value + " is sorted!");
                updateBarColor(Variables.GREEN, barChart.getBars().length-i-1);
                waitThread((long) (MainScreen.instance.millis * 0.5f));
            }
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

}
