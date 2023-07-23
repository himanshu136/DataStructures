package com.datastructures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SlidingWindow {


    public static int maximumSumWindowSizeofK(int [] arr, int k){

        int sum=0;
        int max;

        for (int i = 0; i < k; i++) {
            sum=sum+arr[i];
        }
        max=sum;
        for (int i = k; i <arr.length; i++) {
            sum += arr[i]-arr[i-k];
            max=Math.max(sum,max);
        }
        return max;
    }
    public static int maximumSumWindowSizeofKUsingTemplate(int [] arr, int k){

        int sum=0;
        int i=0;
        int max=Integer.MIN_VALUE;

        for (int j = 0; j < arr.length; j++) {
            sum+=arr[j];
            while (j-i+1>k){
                sum-=arr[i];
                i++;
            }
            max=Math.max(max,sum);
        }
        return max;
    }

    public static int maximumSumWindowSizeofKUsingWhile(int [] arr, int k){

        int sum=0;
        int max=Integer.MIN_VALUE;
        int i=0,j=0;
        while (j<arr.length){
            sum = sum+arr[j];
            if(j-i+1<k){
                j++;
            }else if(j-i+1==k){
                max = Math.max(max,sum);
                sum=sum-arr[i];
                i++;
                j++;
            }
        }
        return max;
    }

    public static void findFirstNegativeIntegerInSlidingWindow(int [] arr, int k){
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;
        Queue<Integer> queue = new ArrayDeque<>();
        while (j<arr.length){
            if(arr[j]<0){
                queue.offer(arr[j]);
            }
            if(j-i+1==k){
                if(queue.size()==0){
                    list.add(0);
                } else {
                    list.add(queue.peek());
                    if(arr[i]==queue.peek()){
                        queue.poll();
                    }
                }
                i++;
                j++;
            }else {
                j++;
            }
        }
        list.forEach(System.out::print);
    }

  public static int minimumCardPickUp(int [] cards){
      int i=0,j=0;
      int n = cards.length;
      Set<Integer> set = new HashSet<>();
      int max = Integer.MAX_VALUE;
      while(j<n){
          if(!set.contains(cards[i])){
              set.add(cards[i]);
              i++;
          }else{
              set.remove(cards[i]);
              j++;
              max=Math.min(max,i);
              i=j;
          }
      }
      return max==Integer.MAX_VALUE?-1:max;
  }

    //1,1,1,0,0,0,1,1,1,1,0
    public static int longestOnes(int[] nums, int k) {
        int max=1;
        for(int i=0;i<nums.length;i++){
            int j=i;
            int count=0;
            int flip=0;
            while(j<nums.length){
                if(nums[j]==1){
                    count+=1;
                }
                else if(nums[j]==0 &&flip<k){
                    flip++;
                    count+=1;
                }else{
                    break;
                }
                max=Math.max(max,count);
                j++;
            }
        }
        return max;
    }

    public static int maximumUniqueSubarray(int[] nums) {
        int i=0,ans=0,sum=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int j=0;j<nums.length;j++){
            sum+=nums[j];
            map.put(nums[j],map.getOrDefault(nums[j],0)+1);
            while(map.get(nums[j])>1){
                sum-=nums[i];
                map.put(nums[i],map.get(nums[i])-1);
                i++;
            }
            ans = Math.max(ans,sum);
        }
        return ans;
    }


    public static void main(String[] args) {
//        System.out.println(maximumSumWindowSizeofK(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20},4));
//        System.out.println(maximumSumWindowSizeofKUsingWhile(new int[]{1, 4, 2, 10, 23, 3, 1, 0, 20},4));
//        findFirstNegativeIntegerInSlidingWindow(new int[]{ 12, -1, -7, 8, -15, 30, 16, 28 },3);
//        minimumCardPickUp(new int[]{3,4,2,3,4,7});
        System.out.println(maximumUniqueSubarray(new int[]{4,2,4,5,6}));

    }
}
