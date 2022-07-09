package com.practise.algorithm;

public class SortAlgorithmService {
    //非线性

    //交换排序：冒泡排序、快速排序
    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        print(arr);
    }

    /*
        10,9,12,8,23,11
        8 9 10 12 23 11
     */

    public static void quickSort(int arr[], int start, int end) {
        if (start < end) {
            int index = splitIndex(arr, start, end);
            quickSort(arr, start, index);
            quickSort(arr, index + 1, end);
        }
    }

    public static int splitIndex(int arr[], int start, int end) {
        int target = arr[start];
        int i = start;
        int j = end - 1;

        while (start < end) {
            while (arr[j] > target) {
                --j;
            }
            arr[i] = arr[j];
            while (arr[i] < target) {
                ++i;
            }
            arr[j] = arr[i];
            arr[i] = target;
            return i;
        }
        return start;
    }

    //插入排序： 简单插入排序、shell排序
    public static void insertSort(int arr[], int insert) {
        int newArr[] = new int[arr.length + 1];
        int position = newArr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= insert) {
                position = i;
                break;
            }
        }
        for (int i = 0; i < newArr.length; i++) {
            if (i < position) {
                newArr[i] = arr[i];
            } else if (i == position) {
                newArr[i] = insert;
            } else {
                newArr[i] = arr[i - 1];
            }
        }
        print(newArr);
    }

    public static void optimizationInsertSort(int arr[]) {
        for (int i = arr.length-1; i >= 0; i++) {
            int j = i - 1;
            int key = arr[i];

            //move back
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        print(arr);
    }

    //Dimonishing Increment Sort
    public static void shellSort(int arr[]) {
        int gap = 2;

    }

    //选择排序： 简单选择排序、堆排序
    public static void selectSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j <arr.length ; j++) {
                if( arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
        print(arr);
    }

    /*10,    9,   5,   12,   8,   23,   11

       0     1    2     3    4     5    6

     */
    //fix
    public static void heapSort(int arr[],int length) {
        int i = length/2 -1;
        while( i >=0){
            if( 2*i +2 <= length-1) {
                if (arr[2 * i + 1] < arr[2 * i + 2]) {
                    if (arr[2 *i +2] > arr[i]){
                        int tmp = arr[i];
                        arr[i] = arr[2* i +2];
                        arr[2*i +2] = tmp;
                    }
                }else {
                    if( arr[2*i +1] > arr[i]) {
                        int tmp = arr[i];
                        arr[i] = arr[2 * i + 1];
                        arr[2 * i + 1] = tmp;
                    }
                }
            }
            --i;
        }
        int tmp = arr[0];
        arr[0] = arr[length-1];
        arr[length-1] = tmp;

        if( length >1 ){
            heapSort(arr, length-1);
        }

    }

    //归并排序：二路归并排序、多路归并排序
    //gap =5 , 3, 1
    public static void mergeSort(int arr[]) {

    }


    //// 线性时间

    public static void bucketSort(int arr[]) {

    }

    public static void radixSort(int arr[]) {

    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
        ;
    }

    public static void main(String[] args) {
//        int sortBrr[] = {3, 8, 10, 15, 20};
//        insertSort(sortBrr, 18);
//
//        int arr1[] = {10, 9, 5, 12, 8, 23, 11};
//        optimizationInsertSort(arr1);
//
//        int arr2[] = {10, 9, 5, 12, 8, 23, 11};
//        bubbleSort(arr2);

        int arr3[] = {10, 9, 5, 12, 8, 23, 11};
        quickSort(arr3, 0, arr3.length);
        print(arr3);

        int arr4[] = {10, 9, 5, 12, 8, 23, 11};
        heapSort(arr4,arr4.length);
        print(arr4);

        int arr5[] = {10, 9, 5, 12, 8, 23, 11};
        selectSort(arr5);
    }
}
