package de.undefinedhuman.algovis.screen.algorithms.sorting;

import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Variables;

public class InsertionSort extends Algorithm {

    @Override
    public void run() {
        try {
            MainScreen.instance.updateText.setText(barChart.get(0).value + " is sorted!");
            updateBarColor(Variables.GREEN, 0);
            waitThread(MainScreen.instance.millis);
            for(int i = 1; i < Variables.AMOUNT_OF_ELEMENTS; i++) {
                MainScreen.instance.infoText.setText("Sort " + barChart.get(i).value);
                int j = i;
                while(j > 0 && compareIndex(j-1, j)) {
                    swap(j-1, j);
                    j--;
                }
                MainScreen.instance.updateText.setText(barChart.get(j).value + " is sorted!");
                for(int u = 0; u <= i; u++)
                    updateBarColor(Variables.GREEN, u);
                waitThread(MainScreen.instance.millis);
            }
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

}
