/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.animation.Timeline;

/**
 *
 * @author Gio
 */
public class old_sort{
    private int n;
     int[] arr;
     
    /* Sort Function */
    public  old_sort(int[] arr) {
        this.arr = arr;
        heapBuild();
        for (int i = n; i > 0; i--) {
            System.out.println("OLD - swap");
            swap(0, i);
            n = n - 1;
            maxHeapRestore(0);
        }
    }

    /* Function to build a heap */
    public void heapBuild() {
        n = arr.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxHeapRestore(i);
        }
    }

    /* Function to swap largest element in heap */
    public void maxHeapRestore(int i) {
        int left = 2 * i+1;
        int right = 2 * i+2;
        int max = i;

        if (left <= n && arr[left] > arr[i]) {
            max = left;
        }if (left <= n) {
            System.out.println("OLD - "+left+"-"+i);
        }
        if (right <= n) {
            System.out.println("OLD - "+right+"-"+max);
        }
        if (right <= n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(i, max);
            System.out.println("OLD - for");
            maxHeapRestore(max);
        }
    }
        /* Function to swap two numbers in an array */
    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}
