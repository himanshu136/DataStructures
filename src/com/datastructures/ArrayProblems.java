package com.datastructures;

import java.util.Arrays;

public class ArrayProblems {
/**
    I/P : [1,-1,-2,0,4,0,5,6,8,9]
    O/p : [-1,-2,0,0,1,4,5,6,8,9]

    Arrange the list such that it follows the order <-ve elements><zeros><+ve elements>

    TC - 0(N)
    SC - O(1)
 **/
    public static void main(String[] args) {
        int [] arr = {1,-1,-2,0,4,0,5,6,8,9};
        int positiveCount=0,negativeCount=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]<0){
                int temp = arr[i];
                arr[i]=arr[positiveCount];
                arr[negativeCount] = temp;
                negativeCount++;
            }
                positiveCount=i;
        }
        for (int i = negativeCount+1; i < arr.length; i++) {
            if(arr[i]==0){
                int temp = arr[negativeCount];
                arr[negativeCount]=arr[i];
                arr[i]=temp;
                negativeCount++;
            }
        }
        Arrays.stream(arr).forEach(System.out::println);

        String s="poiinter";
        System.out.println(s.substring(0,2));
    }

}
