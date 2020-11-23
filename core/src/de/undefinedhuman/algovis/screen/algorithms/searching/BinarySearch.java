package de.undefinedhuman.algovis.screen.algorithms.searching;

import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.utils.Utils;
import de.undefinedhuman.algovis.utils.Variables;

public class BinarySearch extends Algorithm {

    @Override
    public void run() {

        for(int i = 0; i < Variables.AMOUNT_OF_ELEMENTS; i++)
            barChart.get(i).updateValue(i+1);

        try {
            int elementToBeSearched = Utils.random.nextInt(40) + 1;
            MainScreen.instance.infoText.setText("Searching: " + elementToBeSearched);

            waitThread(MainScreen.instance.millis/2);
            int index = binarySearch(0, Variables.AMOUNT_OF_ELEMENTS-1, elementToBeSearched);
            if(index == -1) MainScreen.instance.updateText.setText(elementToBeSearched + " not found!");
            else MainScreen.instance.updateText.setText(elementToBeSearched + " was found at index: " + index);
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }

    }

    private int binarySearch(int l, int r, int elementToBeSearched) throws InterruptedException {

        MainScreen.instance.infoText.setText("Searching: " + elementToBeSearched + " at indices [" + l + ", ..., " + r + "]");

        if(l > r) return -1;
        int m = l + (r - l) / 2;

        if(compare(m, elementToBeSearched))
            return m;

        if(compareGreater(m, elementToBeSearched))
            return binarySearch(l, m - 1, elementToBeSearched);

        return binarySearch(m + 1, r, elementToBeSearched);

    }

}
