package de.undefinedhuman.algovis.screen.algorithms;

import com.badlogic.gdx.graphics.Color;
import de.undefinedhuman.algovis.screen.MainScreen;
import de.undefinedhuman.algovis.screen.algorithms.gui.BarChart;
import de.undefinedhuman.algovis.utils.Variables;

public abstract class Algorithm implements Runnable {

    protected BarChart barChart;
    protected AlgorithmType type;

    protected void swap(int i, int j) throws InterruptedException {
        Color colorI = barChart.get(i).getColor(), colorJ = barChart.get(j).getColor();
        updateBarColor(Variables.PURPLE, i, j);
        MainScreen.instance.updateText.setText("Swap: " + barChart.get(i).value + " with " + barChart.get(j).value);
        waitThread(MainScreen.instance.millis);
        int temp = barChart.get(i).value;
        barChart.get(i).updateValue(barChart.get(j).value);
        barChart.get(j).updateValue(temp);
        waitThread(MainScreen.instance.millis);
        updateBarColor(colorI, i);
        updateBarColor(colorJ, j);
    }

    protected boolean compareIndex(int i, int j) throws InterruptedException {
        Color colorI = barChart.get(i).getColor(), colorJ = barChart.get(j).getColor();
        boolean compare = barChart.get(i).value > barChart.get(j).value;
        updateBarColor(Variables.BLUE, i, j);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(i).value + " > " + barChart.get(j).value +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(colorI, i);
        updateBarColor(colorJ, j);
        return compare;
    }

    protected boolean compareIndexReverse(int i, int j) throws InterruptedException {
        Color colorI = barChart.get(i).getColor(), colorJ = barChart.get(j).getColor();
        boolean compare = barChart.get(j).value < barChart.get(i).value;
        updateBarColor(Variables.BLUE, i, j);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(j).value + " < " + barChart.get(i).value +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(colorI, i);
        updateBarColor(colorJ, j);
        return compare;
    }

    protected boolean compare(int index, int j) throws InterruptedException {
        boolean compare = barChart.get(index).value == j;
        updateBarColor(Variables.BLUE, index);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(index).value + " == " + j +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(compare ? Variables.GREEN : Color.WHITE, index);
        return compare;
    }

    protected boolean compareGreater(int index, int j) throws InterruptedException {
        boolean compare = barChart.get(index).value > j;
        updateBarColor(Variables.BLUE, index);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(index).value + " > " + j +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(Color.WHITE, index);
        return compare;
    }

    protected boolean compareGreaterEqual(int i, int j) throws InterruptedException {
        boolean compare = barChart.get(i).value >= barChart.get(j).value;
        updateBarColor(Variables.BLUE, i, j);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(i).value + " >= " + barChart.get(j).value +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(Color.WHITE, i, j);
        return compare;
    }

    protected boolean compareLowerEqual(int i, int j) throws InterruptedException {
        boolean compare = barChart.get(i).value <= barChart.get(j).value;
        updateBarColor(Variables.BLUE, i, j);
        MainScreen.instance.updateText.setText("Compare: " + barChart.get(i).value + " <= " + barChart.get(j).value +  " = " + (compare ? "True" : "False"));
        waitThread(MainScreen.instance.millis * 2);
        updateBarColor(Color.WHITE, i, j);
        return compare;
    }

    protected void updateBarColor(Color color, int... indicies) {
        for(int i : indicies)
            barChart.get(i).setColor(color);
    }

    protected void waitThread(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public Algorithm setBarChart(BarChart barChart) {
        this.barChart = barChart;
        return this;
    }

    public Algorithm setType(AlgorithmType type) {
        this.type = type;
        return this;
    }

}
