package com.example.manualofsortingalgorithms;

public class SelectionSort {

    int[] array;

    SelectionSort(int[] array) {
        this.array = array;
    }

    void sort() {
        for(int i = 0; i < array.length; i++) {
            int min_idx = i;
            for(int j = i+1; j < array.length; j++) {
                if(array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }
            int tmp = array[i];
            array[i] = array[min_idx];
            array[min_idx] = tmp;
        }
    }

    int[] getArray() {
        return array;
    }
}
