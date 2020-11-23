package de.undefinedhuman.algovis.screen.algorithms.sorting;

import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Tools;
import de.undefinedhuman.algovis.utils.Variables;

public class QuickSort extends Algorithm {

    @Override
    public void run() {
        try {
            quicksort(0, Variables.AMOUNT_OF_ELEMENTS-1);
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

    private void quicksort(int l, int r) throws InterruptedException {
        if(l < r) {
            MainScreen.instance.infoText.setText("Pivot: " + barChart.get(r).value);
            int i = l-1;

            for(int j = l; j < r; j++) {
                if(compareLowerEqual(j, r)) {
                    i++;
                    swap(i, j);
                }
            }

            i++;
            swap(i, r);
            updateBarColor(Variables.GREEN, i);
            quicksort(l, i-1);
            quicksort(i+1, r);
        } else if(Tools.isInRange(l, 0, 24)) updateBarColor(Variables.GREEN, l);
    }

}
