package com.datastructures;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.StreamSupport;

public class PQ {
//    Divide Array in Sets of K Consecutive Numbers
    public static boolean isPossibleToDivide(int []nums,int k){
        if(nums.length%k!=0) return false;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i:nums) {
            pq.add(i);
        }
        while (!pq.isEmpty()){
            int start = pq.poll();
            start++;
            int j=0;
            while (j++<k-1){
                if(pq.contains(start)){
                    pq.remove(start++);
                }else {
                    return false;
                }
            }
        }
        return true;

    }

    public static int kthSmallest(int[] arr, int l, int r, int k)
    {
        //Your code here
        Queue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int i=l;i<r;i++){
            pq.add(arr[i]);

            if(pq.size()>k){
                pq.remove();
            }
        }
        System.out.println(pq.peek());
        return pq.peek();
    }

    //Function to return the minimum cost of connecting the ropes.
    long minCost(long arr[], int n)
    {
        // your code here
        Queue<Long> pq = new PriorityQueue<>();
        for(long l: arr){
            pq.add(l);
        }
        long count=0;
        while(pq.size()>=2){
            long first = pq.poll();
            long second = pq.poll();
            count +=first+second;
            pq.add(first+second);
        }
        return count;
    }

    public static int[] solve(int[] A, int[] B, int C) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <A.length ; j++) {
                int sum = A[i]+B[j];
                list.add(sum);
            }
        }
        Collections.sort(list,(a,b)->b-a);
        int []result = new int[C];
        for (int i = 0; i < C; i++) {
            result[i]=list.get(i);
        }
        return result;
    }
    public static Object[] solvePQ(int[] A, int[] B, int C) {
        Queue<Pair<Integer,Pair<Integer,Integer>>> pq = new PriorityQueue<>();
        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;
        pq.add(new Pair(A[n-1]+B[n-1],new Pair<>(n-1,n-1)));
        Set<Pair<Integer,Integer>> set = new HashSet<>();
        set.add(new Pair<>(n-1,n-1));
        List<Integer> result = new ArrayList<>();
        while (C>0){
            Pair<Integer,Pair<Integer,Integer>> top = pq.poll();
            int sum = top.getKey();
            result.add(sum);
            int i = top.getValue().getKey();
            int j = top.getValue().getValue();
            if(!set.contains(new Pair<>(i-1,j))){
               pq.add(new Pair<>(A[i-1]+B[j],new Pair<>(i-1,j)));
               set.add(new Pair<>(i-1,j));
            }
            if(set.contains(new Pair<>(i,j-1))){
                pq.add(new Pair<>(A[i]+B[j-1],new Pair<>(i,j-1)));
                set.add(new Pair<>(i,j-1));
            }
           C--;
        }
       return result.toArray();
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> count = new HashMap<>();
        for(int i:nums){
            count.put(i,count.getOrDefault(i,0)+1);
        }
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(count::get));
        for(int n : count.keySet()){
            pq.add(n);
            if(pq.size()>k) pq.poll();
        }
        int[] top = new int[k];
        for(int i=0;i<k;i++){
            top[i]=pq.poll();
        }
        return top;

    }
    public static void main(String[] args) {
        System.out.println(isPossibleToDivide(new int[]{1,2,3,3,4,4,5,6},4));
        solve(new int[]{1, 4, 2, 3}, new int[]{2, 5, 1, 6}, 4);
//        solvePQ(new int[]{1, 4, 2, 3}, new int[]{2, 5, 1, 6}, 4);
        kthSmallest(new int[]{7, 10, 4, 20, 15},0,5,4);
        topKFrequent(new String[]{"i","love","leetcode","i","love","coding"},2)
                .forEach(System.out::println);

    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word,map.getOrDefault(word,0)+1);
        }
        Queue<String>pq = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));
        pq.addAll(map.keySet());
        List<String> ans = new ArrayList<>();
        while(k>=0){
            ans.add(pq.poll());
            k--;
        }
        return ans;
    }
    //a+b
    //1,4,2,3
    //2,5,1,6
    //1.2 1.5,1.1,1.6 4.2,4.5,4.6,4.1,2.2,2.5,2.1,2.6
    //[10,9,9,8]
}
