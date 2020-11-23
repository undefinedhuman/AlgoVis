package de.undefinedhuman.algovis.screen.algorithms.sorting;

import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Variables;

public class SelectionSort extends Algorithm {

    @Override
    public void run() {
        try {
            for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS-1; i++) {
                int minimum = i;
                MainScreen.instance.infoText.setText("Current minimum: " + barChart.get(minimum).value + ", at index: " + minimum);
                for(int j = i+1; j < Variables.AMOUNT_OF_ELEMENTS; j++) {
                    if(compareIndex(minimum, j)) {
                        minimum = j;
                        MainScreen.instance.infoText.setText("Current minimum: " + barChart.get(minimum).value + ", at index: " + minimum);
                        waitThread(MainScreen.instance.millis);
                    }
                }
                swap(i, minimum);
                MainScreen.instance.updateText.setText(barChart.get(i).value + " is sorted!");
                updateBarColor(Variables.GREEN, i);
                waitThread(MainScreen.instance.millis);
            }
            MainScreen.instance.updateText.setText(barChart.get(Variables.AMOUNT_OF_ELEMENTS-1).value + " is sorted!");
            updateBarColor(Variables.GREEN, Variables.AMOUNT_OF_ELEMENTS-1);
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

}
