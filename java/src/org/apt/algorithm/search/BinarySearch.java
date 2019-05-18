package org.apt.algorithm.search;

import java.util.Arrays;

/**
 * Класс осуществляющий бинарный поиск в отсортированном по возрастанию массиве
 * Гарантируется, что массив переданный в конструктор не может быть изменен извне.
 * Не гарантируется защита от изменений извне объектов внутри массива
 * @param <T>
 */
public class BinarySearch<T extends Comparable & Cloneable>{

    private final T[] sortedArray;

    public BinarySearch(T[] sortedArray) {
        this.sortedArray = Arrays.copyOf(sortedArray, sortedArray.length);
    }


    public int search(T item) {
        int min = 0;
        int max = sortedArray.length;
        int result = -1;
        for(int i = max/2;
                min < max;
                i = min + (max-min)/2){

            int compareResult = sortedArray[i].compareTo(item);
            if(compareResult == 0) {
                result = i;
                break;
            } else if (compareResult > 0){
                max = i;
            } else {
                min = i + 1;
            }
        }
        return result;
    }

}
