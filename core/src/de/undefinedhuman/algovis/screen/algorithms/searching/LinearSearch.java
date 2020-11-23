package de.undefinedhuman.algovis.screen.algorithms.searching;

import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Variables;
import de.undefinedhuman.algovis.utils.Utils;

public class LinearSearch extends Algorithm {

    @Override
    public void run() {

        int elementToBeSearched = Utils.random.nextInt(40) + 1;
        MainScreen.instance.infoText.setText("Searching: " + elementToBeSearched);

        try {
            for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS; i++) {
                if(compare(i, elementToBeSearched)) {
                    updateBarColor(Variables.GREEN, i);
                    MainScreen.instance.updateText.setText(elementToBeSearched + " was found at index: " + i);
                    break;
                }
                updateBarColor(Color.WHITE, i);
                if(i == Variables.AMOUNT_OF_ELEMENTS - 1) MainScreen.instance.updateText.setText(elementToBeSearched + " not found!");
            }
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

}
