package com.example.manualofsortingalgorithms;

import android.util.Log;

public class MergeSort {

    int[] array;

    MergeSort(int[] array) {
        this.array = array;
    }

    void merge(int min, int mid, int max) {
        int tmp[] = new int[max-min];
        int i = min, j = mid, k = 0;

        while (i < mid && j < max) {
            if (array[i] <= array[j]) {
                tmp[k] = array[i];
                i++;
            }
            else {
                tmp[k] = array[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < mid) {
            tmp[k] = array[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < max) {
            tmp[k] = array[j];
            j++;
            k++;
        }

        for (i = min; i < max; ++i)
            array[i] = tmp[i-min];
    }

    void mergeSort(int min, int max) {
        if((max - min) > 1) {
            int mid = (max + min) / 2;
            mergeSort(min, mid);
            mergeSort(mid, max);
            merge(min, mid, max);
        }
    }

    void sort() {
        mergeSort(0, array.length);
    }

    int[] getArray() {
        return array;
    }

}
