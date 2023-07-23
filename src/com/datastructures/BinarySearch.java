package com.datastructures;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        minimumNumberOfBooks();
        EKOSPOJ();
        aggressiveCows();
        minionChef();
        paintersPartitionProblem();
        //interview bit
        paint(3,10,new int[]{640, 435, 647, 352, 8, 90, 960, 329, 859});
        minimumShip();
    }
    //Allocate minimum Number of books
    public static void  minimumNumberOfBooks(){
        int [] pages = {12, 34, 67, 90};//this is sorted
        int m = 2;//number of students
        int n = pages.length-1;
        if(m>n) System.out.println(-1);
        int result=0;
        int start = pages[n];
        int end = Arrays.stream(pages).sum();
        while (start<=end){
            int mid = start+(end-start)/2;
            if(isValid(pages,m,n,mid)) {
                result = mid;
                end = mid - 1;
            }else{
                start=mid+1;
            }
        }
        System.out.println(result);
    }
    public static boolean isValid(int []pages,int m, int n,int limit){
        int s =1;
        int sum=0;
        for (int i = 0; i <=n; i++) {
            sum += pages[i];
            if(sum>limit){
                s++;
                sum=pages[i];
            }
        }
        return s<=m;
    }

    //EKO Spoj Problem for cutting trres
    public static void  EKOSPOJ(){
        int [] pages = {20, 15, 10, 17};//this is sorted
        Arrays.sort(pages);
        int m = 7;//number of students
        int n = pages.length-1;
        int start = 0;
        int end = pages[n];
        while (end-start>1){
            int mid = start+(end-start)/2;
            if(isAllowed(pages,m,n,mid)) {
                start = mid;
            }else{
                end=mid-1;
            }
        }
        if(isAllowed(pages,m,n,end)){
            System.out.println(end);
        }else {
            System.out.println(start);
        }
    }
    //Predicate function
    public static boolean isAllowed(int []woods,int m, int n,int limit){
        int wood=0;
        for (int i = 0; i <=n; i++) {
            if(woods[i]>limit){
                wood += woods[i]-limit;
            }
        }
        return wood>=m;
    }
    //Aggressive Cows
    public static void aggressiveCows(){
        int cows=3;
        int n=5;
        int stalls[]={1,2,8,4,9};
        Arrays.sort(stalls);
        int start=0;
        int end = stalls[n-1];
        while (end-start>1){
            int mid = start+(end-start)/2;
            if(checkCanPlaceCows(stalls,n,cows,mid)){
                start=mid;
            }else {
                end=mid-1;
            }
        }
        if(checkCanPlaceCows(stalls,n,cows,end)){
            System.out.println(end);
        }else {
            System.out.println(start);
        }
    }

    public static boolean checkCanPlaceCows(int []stalls, int n,int cows, int limit){
        int lastPosition=-1;
        for (int i = 0; i < n; i++) {
            if(stalls[i]-lastPosition>=limit || lastPosition==-1){
                cows--;
                lastPosition=stalls[i];
            }
            if (cows==0) break;
        }
        return cows==0;
    }
    //Minion Chef Bananas
    public static void minionChef(){
        int n=4;
        int h=5;
        int []pile={4 ,3 ,2 ,7};
        Arrays.sort(pile);
        int start=1;
        int end = pile[n-1];
        int ans =-1;
        while (start<=end){
            int mid = start +(end-start)/2;
            if(canEatBananas(pile,n,h,mid)){
                ans=mid;
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        System.out.println(ans);
    }
    public static boolean canEatBananas(int []pile,int n , int h , int limit){
        int canEatTotalBananas=0;
        for (int i = 0; i < n; i++) {
            canEatTotalBananas+=(pile[i]/limit);
            if((pile[i]%limit)!=0) canEatTotalBananas+=1;
        }
        return canEatTotalBananas<=h;
    }

    //Painter's Partition Problem
    //No need to sort as it is required to maintain the order
    public static void paintersPartitionProblem(){
        int ar[] = {10,20,30,40};
        int numberOfPainters = 2;
        int start=1;
        for (int i = 0; i < ar.length; i++) {
            start=Math.max(start,ar[i]);
        }
        int end = Arrays.stream(ar).sum();
        int answer=-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(canPartition(ar,numberOfPainters,mid)){
                answer=mid;
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        System.out.println(answer);
    }
    public static boolean canPartition(int ar[],int numberOfPainters,int limit){
        int total=0; int painters=1;
        for (int i = 0; i < ar.length; i++) {
            total+=ar[i];
            if(total>limit){
                painters++;
                total=ar[i];
            }
        }
        return painters<=numberOfPainters;
    }

    public static int paint(int A, int B, int[] C) {
        long m =10000003;
        int start=1;
        for (int i = 0; i < C.length; i++) {
             start=Math.max(start,C[i]);
        }
        int end = Arrays.stream(C).sum();
        int answer=-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(canPartition(C,A,mid)){
                answer=mid;
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        long ans = ((long) answer *B)%m;
        return (int) ans;
    }

    public static void minimumShip(){
        int[] ar= new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int start = ar[ar.length-1];
        int end = Arrays.stream(ar).sum();
        int answer = -1;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(canPort(ar,mid,days)){
                answer=mid;
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        System.out.println(answer);
    }
    public static boolean canPort(int[] ar,int mid, int days){
        int count=0; int totalDays=1;
        for (int i = 0; i < ar.length; i++) {
            count+=ar[i];
            if(count>mid){
                totalDays++;
                count=ar[i];
            }
        }
        return totalDays<=days;
    }

}
