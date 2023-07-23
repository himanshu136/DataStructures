
package com.datastructures;


import java.util.*;

public class StackPractice {


    public static void main(String[] args) {
        findNextSmallestElementIndex(new int[]{4, 8, 5, 2, 25});
        reverseAString("string");
        findNextGreatestElementIndex(new int[]{4, 8, 5, 2, 25});
        findLargestRectangleAreaInHistogram(new int[]{2,1,5,6,2,3});
        Arrays.stream(stockSpanProblem()).forEach(System.out::println);
        System.out.println("Next Smallest Element :::::::::\n");
        Arrays.stream(nextSmallestElement(new int []{3,1,2,4})).forEach(System.out::println);

    }

    //Find Next Smallest Element
    public static int[] findNextSmallestElementIndex(int [] nums){
        int size = nums.length;
        int ans[]= new int[size];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = size-1; i >= 0; i--) {
            int curr = nums[i];
            while (stack.peek()>=curr){
                stack.pop();
            }
            ans[i]=stack.peek();
            stack.push(curr);
        }
        Arrays.stream(ans).forEach(System.out::println);
        return ans;
    }
    //Find Next Smallest Element
    public static int[] findNextGreatestElementIndex(int [] nums){
        //4, 8, 5, 2, 25
        int size = nums.length;
        int ans[]= new int[size];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = size-1; i >= 0; i--) {
            int curr = nums[i];
            while (!stack.isEmpty() && stack.peek()<=curr){
                stack.pop();
            }
            ans[i]=stack.empty() ? -1 : stack.peek();
            stack.push(curr);
        }
        Arrays.stream(ans).forEach(System.out::println);
        return ans;
    }
    //Reverse a string using stack
    public static void reverseAString(String s){
        Stack<Character> stack = new Stack<>();
        for (int i =0;i<s.length();i++) {
            stack.push(s.charAt(i));
        }
        StringBuilder reversedString = new StringBuilder();
        while (!stack.isEmpty()){
            reversedString.append(stack.pop());
        }
        System.out.println(reversedString);
    }
    //find largest RectangleArea in Histogram
    public static int findLargestRectangleAreaInHistogram(int[] nums){
        int length = nums.length;
        int[] next = findNextSmallestElementIndex(nums ,length);
        int [] prev = findPreviousSmallestElement(nums,length);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            int l = nums[i];
            if(next[i]==-1){
                next[i]=length;
            }
            int breadth = next[i]-prev[i]-1;
            int area= l*breadth;
            max=Math.max(max,area);
        }
        System.out.println("max is "+max);
        return max;
    }

    //Find Next Smallest Element
    public static int[] findNextSmallestElementIndex(int [] nums, int length){
        int ans[]= new int[length];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = length-1; i >= 0; i--) {
            int curr = nums[i];
            while (stack.peek()!=-1 && nums[stack.peek()]>=curr){
                stack.pop();
            }
            ans[i]=stack.peek();
            stack.push(i);
        }
        return ans;
    }

    public static int[] findPreviousSmallestElement(int [] nums,int length){
        int ans[]= new int[length];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i <length; i++) {
            int curr = nums[i];
            while (stack.peek()!=-1 && nums[stack.peek()]>=curr){
                stack.pop();
            }
            ans[i]=stack.peek();
            stack.push(i);
        }
        return ans;
    }

    public static int[] stockSpanProblem(){
       int price[] = {100, 80, 60, 70, 60, 75, 85};
       int n=7;
       int [] vector = new int[n];
       int index =0;
       Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <n ; i++) {
            int curr = price[i];
            while (!stack.isEmpty() && price[stack.peek()]<=curr){
                stack.pop();
            }
            vector[i] = stack.isEmpty()?index+1:index-stack.peek();
            index++;
            stack.push(i);
        }
        return vector;
    }
    static Stack<Integer> s = new Stack<>();
    static Stack<Integer> supportStack = new Stack<>();
    public static void minimumElementInStack(){


    }
    public static void push(int a){
        if(supportStack.size()==0 || supportStack.peek()<=a){
            supportStack.push(a);
        }
        s.push(a);
    }
    public static void pop(){
        if(supportStack.peek()==s.peek()){
            supportStack.pop();
        }
        s.pop();
    }
    public static int getMin(){
        if(supportStack.isEmpty()){
            return -1;
        }else {
         return supportStack.peek();
        }
    }
    public static  int [] nextSmallestElement(int []num){
        int n = num.length;
        int [] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && num[stack.peek()]>num[i]){
                int top = stack.pop();
                ans[top]=num[i];
            }
            stack.push(i);
        }
        return ans;
    }

}
