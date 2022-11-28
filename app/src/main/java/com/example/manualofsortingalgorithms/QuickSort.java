package com.example.manualofsortingalgorithms;

public class QuickSort {
    int[] array;

    QuickSort(int[] array) {
        this.array = array;
    }

    int partition(int min, int max) {
        int pivote = array[min];
        int i = min+1, j = max;

        while (i < j + 1) {
            while(i < j + 1 && array[i] <= pivote) i++;
            while(i < j + 1 && array[j] >= pivote) j--;
            if(i<j+1) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        int tmp = array[min];
        array[min] = array[j];
        array[j] = tmp;
        return j;
    }

    void quickSort(int min, int max) {
        if(min < max) {
            int mid = partition(min, max);
            quickSort(min, mid-1);
            quickSort(mid+1, max);
        }
    }

    void sort() {
        quickSort(0, array.length-1);
    }

    int[] getArray() {
        return array;
    }}
