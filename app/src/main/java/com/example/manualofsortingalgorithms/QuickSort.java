package com.example.manualofsortingalgorithms;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuickSort {
    int[] array;

    QuickSort() {}

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
    }

    LinearLayout explainAlgorithm(Lesson lessonTutorial) {

        LinearLayout llPanelLesson = new LinearLayout(lessonTutorial);
        llPanelLesson.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelLesson.setOrientation(LinearLayout.VERTICAL);
        llPanelLesson.setGravity(Gravity.FILL_VERTICAL);

        TextView txtTitle = new TextView(lessonTutorial);
        txtTitle.setTextSize(36f);
        txtTitle.setText("Quick Sort");
        txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        llPanelLesson.addView(txtTitle);

        TextView txtPropierties = new TextView(lessonTutorial);
        txtPropierties.setTextSize(16f);
        txtPropierties.setText("\nPropierties:\n - Space cost: Θ(1)\n - Temporary cost AVG: Θ(n·log(n)) Worst case: Θ(n²)\n - Method: Merging\n - Stable: Yes");
        txtPropierties.setPadding(20,0,0,20);
        llPanelLesson.addView(txtPropierties);

        TextView txtIntroduccion = new TextView(lessonTutorial);
        txtIntroduccion.setText("Es uno de los algoritmos más usado/populares, su eficiencia se equipara al merge sort, su mecanismo se centra en un pivote.\n");
        txtIntroduccion.setPadding(20,0,0,20);
        llPanelLesson.addView(txtIntroduccion);

        TextView txtParrafo1 = new TextView(lessonTutorial);
        txtParrafo1.setText("Empezamos con particionar el conjunto en 2 subconjuntos, el momento de particionar 2 cursores estaran ubicados a los extremos, el cursor que estarà en las primeras posiciones ocuparà la segunda posición y el pivote estarà en la primera posicion.\n");
        txtParrafo1.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo1);

        TextView txtParrafo2 = new TextView(lessonTutorial);
        txtParrafo2.setText("El cursor que esta ubicado a la izquierda se irá desplzando a la derecha del subconjunto sea menor del cursor ubicado a la derecha con una posición de más y menor o igual del pivote, este proceso se repite de forma invertida con el cursor ubicado a la derecha del subconjunto. Después se comprueba que ambos cursores no se haya juntado o cruzado, si no se de el caso se intercambia los elementos.\n");
        txtParrafo2.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo2);

        TextView txtParrafo3 = new TextView(lessonTutorial);
        txtParrafo3.setText("Cuando ambos cursores se cruzen, se hace un intercambios de elementos entre el cursor de la derecha con el pivote i la posición que esté el cursor de la derecha serà el nuevo pivote para el nuevo\n");
        txtParrafo3.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo3);

        return llPanelLesson;
    }
}
