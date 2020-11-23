package de.undefinedhuman.algovis.screen.algorithms;

import de.undefinedhuman.algovis.screen.algorithms.gui.BarChart;
import de.undefinedhuman.algovis.screen.algorithms.searching.BinarySearch;
import de.undefinedhuman.algovis.screen.algorithms.searching.LinearSearch;
import de.undefinedhuman.algovis.screen.algorithms.sorting.*;

public enum AlgorithmType {

    BUBBLESORT("Bubble Sort", BubbleSort.class),
    INSERTIONSORT("Insertion Sort", InsertionSort.class),
    SELECTIONSORT("Selection Sort", SelectionSort.class),
    MERGESORT("Merge Sort", MergeSort.class),
    QUICKSORT("Quick Sort", QuickSort.class),
    LINEARSEARCH("Linear Search", LinearSearch.class),
    BINARYSEARCH("Binary Search", BinarySearch.class);

    public String name;
    private Class<? extends Algorithm> algorithm;

    AlgorithmType(String name, Class<? extends Algorithm> algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }

    public Runnable createInstance(BarChart barChart) {
        try {
            return algorithm.newInstance().setBarChart(barChart).setType(this);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
