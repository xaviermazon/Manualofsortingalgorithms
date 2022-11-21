package com.example.manualofsortingalgorithms;

public class BubbleSort {

    int[] array;

    BubbleSort(int[] array) {
        this.array = array;
    }
    void BubbleSort() {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++) {
                if(array[i] < array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    int[] getArray() {
        return array;
    }
}
