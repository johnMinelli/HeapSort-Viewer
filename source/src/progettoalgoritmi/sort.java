/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoalgoritmi;

import javafx.animation.Timeline;

/**
 *
 * @aoke
 */
public class sort {
    private int n;
    int[] arr;
    int isort;
    int iheap;
    int sheap=3;
    int sheap2=3;
    int sheap3=3;
    
    int left;
    int right;
    int max;
    int oldMax;
    boolean building;
    
    public sort(int[] arr){
        this.arr = arr;
    }
    
//se qualcosanellif allora bisogna chiamare ancora START(2) cioè heapbuild(3)
//se forSORT allora bisogna chiamare prima START(5) START(6) poi ancora START(4)<--for
//se qualcosafine allora fine
    /* START Function */
    public String start( int status) {
        switch (status){
            case 1:
                if(iheap>= 0){
                    building=true;
                    return heapBuild(1);
                }
            case 2:
               // if(qualcosanellif){
                return heapBuild(3);
               // }
            case 3:
                building=false;
                isort = n;
                return start(4);
            case 4:     //<--for
                if(isort > 0){
                    isort--;
                    n = n - 1;
                    swap(0, isort+1);
                    return "start "+0+"swap"+(isort+1);
                }
                return "endSORT";
            case 5:
                sheap3=3;
                return maxHeapRestore(0,1);
            case 6:
                if(sheap3<6){sheap3++;
                    return maxHeapRestore(0,sheap3-1);
                }else{
                    sheap3=6;
                    return maxHeapRestore(0,sheap3);
                }
        }
        return "ERR";
    }

    /* Function to build a heap */
    public String heapBuild(int status) {
        switch (status){
            case 1:
                n = arr.length - 1;
                iheap = (int) (n / 2.0-0.5);
                return heapBuild(2);
            case 2:    //<--for
                if(iheap>= 0){
                    iheap--;
                    return maxHeapRestore(iheap+1,1);
                }
                return start(3);
            case 3:
                if(sheap<6){
                    sheap++;
                    return maxHeapRestore(iheap+1,sheap-1);   //<--in teoria iheap+1
                }else{
                    sheap=6;
                    return maxHeapRestore(iheap+1,sheap);     //<--in teoria iheap+1
                }
        }
        return "ERR";
    }

    /* Function to swap largest element in heap */
    public String maxHeapRestore(int i, int status) {
        switch (status){
            case 1:
                left = 2 * i+1;
                right = 2 * i + 2;
                max = i;
                return maxHeapRestore(i,2);
            case 2:
                //ALG
                if (left <= n) {
                    if (arr[left] > arr[i]) {
                        max = left;
                    }
                    return left+"-"+i;
                }
                //RET
                sheap++;
                sheap2++;
                sheap3++;
                return maxHeapRestore(i,3);
            case 3:
                //ALG
                if (right <= n) {
                    int x=max;
                    if (arr[right] > arr[max]) {
                        max = right;
                    }
                    return right+"-"+x;
                }
                //RET
                sheap++;
                sheap2++;
                sheap3++;
                return maxHeapRestore(i,4);
            case 4:
                if (max != i) {
                    swap(i, max);
                    return "heap "+i+"swap"+max;
                }
                sheap=3;
                sheap2=3;
                sheap3=3;
                if(building){
                    return heapBuild(2);
                }else{
                    return start(4);
                }
            case 5:
                sheap2=3;
                oldMax=max;
                return maxHeapRestore(max,1);
            case 6:
                if(sheap2<6){sheap2++;
                    return maxHeapRestore(oldMax,sheap2-1);
                }else{
                    sheap2=5;
                    return maxHeapRestore(oldMax,sheap2);
                }
        }
        return "ERR";
    }
        /* Function to swap two numbers in an array */
    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}





