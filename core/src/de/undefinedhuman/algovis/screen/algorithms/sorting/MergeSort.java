package de.undefinedhuman.algovis.screen.algorithms.sorting;

import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.log.Log;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.Algorithm;
import de.undefinedhuman.algovis.screen.algorithms.gui.Bar;
import de.undefinedhuman.algovis.utils.Variables;

public class MergeSort extends Algorithm {

    @Override
    public void run() {
        try {
            for(Bar bar : barChart.getBars()) {
                bar.getTempIcon().setVisible(true);
                bar.updateTempIcon(0);
            }
            mergeSort(barChart.getBars(), 0, Variables.AMOUNT_OF_ELEMENTS-1);
            for(Bar bar : barChart.getBars())
                bar.getTempIcon().setVisible(false);
        } catch(InterruptedException ex) {
            if(type != null)
                Log.info(type.name + " interrupted!");
        }
    }

    private void mergeSort(Bar[] array, int l, int r) throws InterruptedException {

        if(l < r) {
            int m = (r+l)/2;
            mergeSort(array, l, m);
            mergeSort(array, m+1, r);
            merge(array, l, m+1, r);
        }

    }

    private int[] temp = new int[Variables.AMOUNT_OF_ELEMENTS];

    private void merge(Bar[] array, int l, int m, int r) throws InterruptedException {
        int i = l, j = m;

        for(int left = l; left < m; left++)
            barChart.get(left).updateTempIconColor(Variables.LIGHT_PINK);

        for(int right = m; right <= r; right++)
            barChart.get(right).updateTempIconColor(Variables.DARK_PINK);

        MainScreen.instance.infoText.setText("Merge sub arrays: [" + l + ", ..., " + (m-1) + "] and [" + (m) + ", ..., " + r + "]");

        for(int k = l; k <= r; k++) {

            if(i >= m || (j <= r && compareIndex(i, j))) {
                barChart.get(k).updateTempIcon(array[j].value);
                temp[k] = array[j].value;
                j++;
            } else {
                barChart.get(k).updateTempIcon(array[i].value);
                temp[k] = array[i].value;
                i++;
            }

        }

        waitThread(MainScreen.instance.millis);

        for(int k = l; k <= r; k++)
            barChart.get(k).updateValue(temp[k]);

        for(Bar bar : barChart.getBars()) {
            bar.updateTempIcon(0);
            bar.updateTempIconColor(Color.WHITE);
        }
    }

}
