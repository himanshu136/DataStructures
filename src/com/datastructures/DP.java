package com.datastructures;

import com.sun.xml.internal.ws.addressing.WsaTubeHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class DP {
    static int mod= 1000000007;
//
//    public static void main(String[] args) {
//        int[] nums = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] multiple = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//        int [][] dp = new int[nums.length][multiple.length];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < multiple.length; j++) {
//                dp[i][j]=Integer.MIN_VALUE;
//            }
//        }
//        System.out.println(solve(nums, multiple, 0, 0,nums.length-1));
//        System.out.println(solveRecursive(nums, multiple, 0, 0,nums.length-1,dp));
//    }

    public static int solve(int[] arr1, int[] arr2, int start, int j,int end) {
        if(start>arr2.length-1 || j>arr2.length-1 || end<0) return 0;
        int val1 = solve(arr1,arr2,start+1,j+1,end) + arr1[start]*arr2[j];
        int val2 = solve(arr1,arr2,start,j+1,end-1) + arr1[end]*arr2[j];
        return Math.max(val1,val2);
    }

    public static int solveRecursive(int[] arr1, int[] arr2, int start, int j,int end, int [][] dp) {
        System.out.println("Inside solve recursive start and end values are"+start+" "+end);
        if(start>arr2.length-1 || j>arr2.length-1 || end<0) return 0;
        if(dp[start][j]!=Integer.MIN_VALUE) return dp[start][j];
        int val1 = solveRecursive(arr1,arr2,start+1,j+1,end,dp) + arr1[start]*arr2[j];
        int val2 = solveRecursive(arr1,arr2,start,j+1,end-1,dp) + arr1[end]*arr2[j];
        dp[start][j]= Math.max(val1,val2);
        return dp[start][j];
    }

    public static void main(String[] args) {
        int [][] arr = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        String s = "horse";
        String q= "ros";
        System.out.println(editDistanceRecursive(s,q,s.length()-1,q.length()-1));
        int m = arr.length;
        int n =arr[0].length;
        System.out.println(solveGrid(arr,m,n,0,0));
        s = "ABCDGH";
        String t = "ACDGHR";
        longestCommonSubsequence(s,t);
        solveSubset();
        System.out.println(solveLongestCommonSubstring(s,t,s.length(),t.length(),0));
        sovleMCM();
        pallindromePartinioing();
        booleanParanthesization();
        System.out.println(isScrambleString("coder","ocred"));
        eggDroppingProblem();
        System.out.println(longestIncreasingSubsequence(new int[]{10,9,2,5,3,7,101,18}));
        solveRectangle();
        System.out.println("binomial coefficient is "+binomialCoefficient(2,0));
        System.out.println("permutation coefficient is "+permutationCoefficient(10,2));
        oranges();
        System.out.println(longestValidParentheses(")()())"));

    }

    public static String LPS(String s){
        int n =s.length();
        boolean dp[][] = new boolean[n+1][n+1];
        int start=0;
        int max=1;
        for (int i = 0; i < n; i++) {
            dp[i][i]=true;
        }
        for (int i = 0; i < n-1; i++) {
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=true;
                max=2;
                start=i;
            }
        }
        for (int k = 3; k <=n; k++) {
            for (int i = 0; i < n-k+1; i++) {
                int j = i+k-1;
                if(dp[i+1][j-1] && s.charAt(i)==s.charAt(j)){
                    dp[i][j]=true;
                    if(k>max){
                        max=k;
                        start=i;
                    }
                }
            }
        }
        return s.substring(start,start+max);

    }
    public static int solveGrid(int [][]grid,int m, int n, int i, int j){
        if(i>=m || j>=n) return Integer.MAX_VALUE;
        if(i==m-1 && j==n-1) return grid[i][j];
        return grid[i][j]+Math.min(solveGrid(grid, m, n, i+1, j),solveGrid(grid,m,n,i,j+1));
    }

    public static int editDistanceRecursive(String s, String q, int m, int n){
        if(m==0) return n;
        if(n==0) return m;
        if(s.charAt(m-1)==q.charAt(n-1)){
            return editDistanceRecursive(s,q,m-1,n-1);
        }
        return 1+Math.min(editDistanceRecursive(s,q,m-1,n-1),Math.min(editDistanceRecursive(s,q,m-1,n)
        ,editDistanceRecursive(s,q,m,n-1)));
    }
    public static void painterFenceProblem(int n, int k){
        System.out.println(solvePainterFenceProblemRecursive(n,k));
    }
    public static int solvePainterFenceProblemRecursive(int n, int k){
        //base Case
        if(n==1) return k;

        if(n==2) return k*(k-1);

        return solvePainterFenceProblemRecursive(n-2,k)*k-1 + solvePainterFenceProblemRecursive(n-1,k)*k-1;
    }
    public static int solvePainterFenceProblemTab(int n, int k){
        int[] dp= new int[n+1];
        dp[0]=k;
        dp[1]=k*(k-1);
        for (int i = 2; i < n; i++) {
            dp[i]=(dp[i-2]*k-1)+(dp[i-1]*k-1);
        }
        return dp[n-1];
    }
    public static void  longestCommonSubsequence(String s , String t){
        System.out.println(solveLongestCommonSubsequence(s,t,s.length()-1,t.length()-1));
        System.out.println(solveLongestCommonSubsequenceDPWay(s,t));
    }

    public static int solveLongestCommonSubsequence(String s, String t, int i, int j){
        if(i<0 || j<0) return 0;
        if(s.charAt(i)==t.charAt(j)){
            return 1+solveLongestCommonSubsequence(s,t,i-1,j-1);
        }
        return Math.max(solveLongestCommonSubsequence(s,t,i-1,j),
                solveLongestCommonSubsequence(s,t,i,j-1));
    }
    public static int solveLongestCommonSubsequenceDPWay(String s, String t){
        int [][]dp = new int[s.length()+1][t.length()+1];
        for (int i = 1; i <=s.length(); i++) {
            for (int j = 1; j <=t.length(); j++) {
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else {
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    public static void solveKnapsack(int [] values, int [] weights, int weight){
        solveKnapsackRecursiveWay(values,weights,weight,values.length-1);
    }

    public static int solveKnapsackRecursiveWay(int [] values, int [] weights, int weight,int n){
        if(n==0 || weight==0)
            return 0;
        if(weights[n-1]>weight)
            return solveKnapsackRecursiveWay(values,weights,weight,n-1);
        else
            return
                Math.max(values[n-1]+solveKnapsackRecursiveWay(values,weights,weight-values[n-1],n-1),
                        solveKnapsackRecursiveWay(values,weights,weight,n-1));
    }

    public static void solveSubset(){
        int []subset=new int []{3, 34, 4, 12, 5, 2};
        System.out.println(solveSubsetSum(subset,30,0));
    }
    public static boolean solveSubsetSum(int [] subset, int sum, int start){
        if(start>=subset.length) return false;
        if(sum==0) return true;
        if(sum<0)return false;
        return solveSubsetSum(subset, sum-subset[start],start+1) ||
                solveSubsetSum(subset,sum,start+1);
    }


    public static int solveLongestCommonSubstringDP(String s, String t, int i, int j,int max){
        int [][]dp= new int[i+1][j+1];
        int result =Integer.MIN_VALUE;
        for(int k=0;k<i;k++){
            for (int l=0;l<j;l++){
                if(k==0 || l==0){
                   dp[k][l]=0;
                }
               else if(s.charAt(k-1)==t.charAt(l-1)){
                    dp[k][l]=1+dp[k-1][l-1];
                    result=Math.max(result,dp[k][l]);
                }else {
                    dp[k][l]=0;
                }
            }
        }
        return dp[i][j];
    }

    public static int solveLongestCommonSubstring(String s, String t, int i, int j,int max){
        if(i==0 || j==0) return max;
        if(s.charAt(i-1)==t.charAt(j-1)){
           max= solveLongestCommonSubstring(s,t,i-1,j-1,max+1);
        }
        max=Math.max(max,Math.max(solveLongestCommonSubstring(s,t,i-1,j,0),
                solveLongestCommonSubstring(s,t,i,j-1,0)));
        return max;
    }
    public static void sovleMCM(){
        int p[] = {40, 20, 30, 10, 30};
        System.out.println(sovleMCMRecursiveWay(p,1,p.length-1));
    }
    public static int sovleMCMRecursiveWay(int []arr, int i , int j){
        if(i>=j) return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <j; k++) {
            int temp = sovleMCMRecursiveWay(arr,i,k)+sovleMCMRecursiveWay(arr,k+1,j)+
                    arr[i-1]*arr[k]*arr[j];
            min=Math.min(min,temp);
        }
        return min;
    }

    public static void pallindromePartinioing(){
        String  s="ababbbabbababa";
        int [][]dp = new int[s.length()+1][s.length()+1];
        for (int[] i:dp) {
            Arrays.fill(i,-1);
        }
        System.out.println(solvePallindromePartioningRecursiveWay(s,0,s.length()-1,dp));
    }
    public static int solvePallindromePartioningRecursiveWay(String s, int i, int j,int [][]dp){
        if(i>j)return 0;
        if(isPallindrome(s, i,j)){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <j; k++) {
            int  left;
            int right;
            if(dp[i][k]!=-1){
                left=dp[i][k];
            }else {
                left=solvePallindromePartioningRecursiveWay(s,i,k,dp);
            }
            if(dp[k+1][j]!=-1){
               right=dp[k+1][j];
            }else {
                right=solvePallindromePartioningRecursiveWay(s,k+1,j,dp);
            }
            int temp = 1+ left+right;
            min=Math.min(temp,min);
        }
        return min;
    }
    public static boolean isPallindrome(String s, int i,int j){
        if(i>j)return false;
        if(i==j)return true;
        while (i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static void booleanParanthesization(){
        String s ="T|T&F^T";
        System.out.println(booleanParanthesization(s,0,s.length()-1,1));
    }
    public static  int booleanParanthesization(String s,int i,int j,int isTrue){
        if(i>j)return  0;
        if(i==j){
            if(isTrue==1){
                return (s.charAt(i)=='T')?1:0;
            }else {
                return (s.charAt(i)=='F')?1:0;
            }
        }
        int temp=0;
        int leftTrue, rightTrue, leftFalse, rightFalse;
        for (int k = i+1; k <=j-1 ; k=k+2) {
            leftTrue = booleanParanthesization(s,i,k-1,1);
            leftFalse = booleanParanthesization(s,i,k-1,0);
            rightFalse= booleanParanthesization(s,k+1,j,0);
            rightTrue = booleanParanthesization(s,k+1,j,1);

            if(s.charAt(k)=='&'){
                if(isTrue==1){
                    temp = temp+ (leftTrue*rightTrue);
                }else {
                    temp=temp+ (leftTrue*rightFalse)+(leftFalse*rightTrue)+(leftFalse*rightFalse);
                }
            }
           else if(s.charAt(k)=='|'){
                if (isTrue==1){
                    temp=temp+
                            (leftTrue*rightTrue) +
                            (rightFalse*leftTrue)+
                            (leftFalse*rightTrue);
                }else {
                    temp=temp+(leftFalse*rightFalse);
                }
            }else if(s.charAt(k)=='^'){
                if (isTrue==1){
                    temp=temp+(leftTrue*rightFalse) + (leftFalse*rightTrue);
                }else {
                    temp=temp+(leftFalse*rightFalse)+(leftTrue*rightTrue);
                }
            }
        }
        return temp;
    }
    public static boolean isScrambleString(String s, String t){
        if(s.length()!=t.length()){
            return false;
        }
        int n = s.length();
        if(s.length()==0){
            return true;
        }
        if(s.equals(t)){
            return true;
        }
        //Check if a string is anagram
        if(!checkAnagram(s,t)){
         return false;
        }

        for (int i = 1; i <s.length(); i++) {
            if(isScrambleString(s.substring(0,i),t.substring(0,i))&&
                    isScrambleString(s.substring(i,n),t.substring(i,n))){
                return true;
            }
            if(isScrambleString(s.substring(n-i,n),t.substring(0,i))&&
                    isScrambleString(s.substring(0,n-i),t.substring(i,n))){
                return true;
            }
        }
        return false;
    }
    public static boolean checkAnagram(String s, String t){
        int[] s_array= new int[256];
        int [] t_array = new int[256];
        for (int i = 0; i < s.length(); i++) {
            s_array[s.charAt(i)]++;
            t_array[t.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(s_array[i]!=t_array[i]){
                return false;
            }
        }
        return true;
    }

    public static void eggDroppingProblem(){
        int floor=5;
        int eggs=3;
        int [][] dp = new int[eggs+1][floor+1];
        for (int i = 0; i <eggs+1; i++) {
            for (int j = 0; j <floor+1; j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solveEggDropRecursiveWay(floor,eggs,dp));
    }
    public static int solveEggDropRecursiveWay(int floor, int eggs,int [][]dp){

        if(eggs==1) return floor;

        if(floor<=1) return floor;


        if(dp[eggs][floor]!=-1){
            return dp[eggs][floor];
        }

        int min= Integer.MAX_VALUE;

        for (int i = 1; i <=floor ; i++) {
            int temp = 1+ Math.max(solveEggDropRecursiveWay(floor-1,eggs-1,dp)
                    ,solveEggDropRecursiveWay(floor-i,eggs,dp));
            min=Math.min(temp,min);
            dp[eggs][floor]=min;
        }
     return min;
    }

    public static int  longestIncreasingSubsequence(int arr[]){
        int []lis = new int[arr.length];
        lis[0]=1;
        int max=1;
        for (int i = 1; i < arr.length; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j] && lis[i]<lis[j]+1){
                    lis[i]=1+lis[j];
                    max=Math.max(max,lis[i]);
                }
            }
        }
        return max;
    }
    public static int  longestIncreasingSubsequenceRecursive(int arr[],int index, int previous){
        if(index==arr.length) return 0;
        //not take condition
        int length = longestIncreasingSubsequenceRecursive(arr,index+1,previous);
        //take condition
        if(previous==-1 || arr[index]>arr[previous]){
            length = 1+longestIncreasingSubsequenceRecursive(arr,index+1,index);
        }
        return length;
    }

    public static int  longestIncreasingSubsequenceII(int arr[], int k){
        int []lis = new int[arr.length];
        lis[0]=1;
        int max=1;
        for (int i = 1; i < arr.length; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if(arr[i]>arr[j] && arr[i]-arr[j]<=k&& lis[i]<lis[j]+1){
                    lis[i]=1+lis[j];
                    max=Math.max(max,lis[i]);
                }
            }
        }
        return max;
    }


    public static void solveRectangle(){
        char [][] matrix = {{'0', '1', '1', '0'},{'1','1','1','1'},{'1','1','1','1'},
                {'1','1','0','0'}};
        int m = matrix.length;
        int n = matrix[0].length;
        int [] heights= new int[n];
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            heights[j]=matrix[0][j];
        }
        max=Math.max(max,StackPractice.findLargestRectangleAreaInHistogram(heights));

        for (int i = 1; i <m; i++) {
            for (int j = 0; j <n; j++) {
                if(matrix[i][j]=='1'){
                    heights[j]=1+heights[i-1];
                }else {
                    heights[j]=0;
                }
            }
            max = Math.max(max,StackPractice.findLargestRectangleAreaInHistogram(heights));
        }
        System.out.println(max);
    }
// Binomial Coefficient
    public static int binomialCoefficient(int n , int k){
        if(k>n) return 0;
        if(k==0||k==1) return 1;
        //(n-1,k-1)+(n-1,k)
        return binomialCoefficient(n-1,k-1)+binomialCoefficient(n-1,k);
    }

//Permutation Coefficient
    public static int permutationCoefficient(int n, int k){
        int[] fact = new int[n+1];
        fact[0]=1;
        for (int i = 1; i <=n ; i++) {
            fact[i]=i*fact[i-1];
        }
        //n!/(n-k)! factorial
        return fact[n]/fact[n-k];
    }

    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m  = grid[0].length;
        int dp[][]=new int [n+1][m+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i][j]=-1;
            }
        }
        int ans =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                ans = (ans+dfs(grid,i,j,n,m,-1,dp))%mod;
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int i, int j , int n ,int m,int prev,int [][]dp ){
        if(i<0||j<0 || i>=n|| j>=m || grid[i][j]<=prev) return 0;
        if(dp[i][j]!=-1) return dp[i][j];
        int a = dfs(grid,i+1,j,n,m,grid[i][j],dp);
        int b = dfs(grid,i,j+1,n,m,grid[i][j],dp);
        int c = dfs(grid,i-1,j,n,m,grid[i][j],dp);
        int d = dfs(grid,i,j-1,n,m,grid[i][j],dp);

        return dp[i][j]=(((a+b+c+d)%mod)+1)%mod;

    }
    public static void oranges(){
        int n = 9209408;
        int []dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(solve(n,Integer.MAX_VALUE,dp));
    }
    static int solve(int n, int min,int []dp) {
        if (n <= 1) return 1;
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n % 2 == 0) {
            min = 1+Math.min(min, Math.min(1 + solve(n - n / 2, min, dp), 1 + solve(n - 1, min, dp)));
        }
            if (n % 3 == 0) {
                min = Math.min(min, Math.min(1 + solve(n - (2 * (n / 3)), min, dp), 1 + solve(n - 1, min, dp)));
            } else {
                min = Math.min(min, 1 + solve(n - 1, min, dp));
            }
            return dp[n] = min;
        }

    public static int longestValidParentheses(String s) {
        int max=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max=Math.max(max,i-stack.peek());
                }
                System.out.println("Printing stack Values");
                stack.forEach(System.out::println);
                System.out.println(" ");
            }
        }
        return max;
    }

    public static int trappingRainWater(int [] height){
        int n = height.length;
        int left_max[]= new int[n];
        int right_max[] = new int[n];
        left_max[0]=height[0];
        for (int i = 1; i <n ; i++) {
            left_max[i]= Math.max(left_max[i-1],height[i]);
        }
        right_max[n-1]=height[n-1];
        for (int i = n-2; i >=0 ; i--) {
            right_max[i]=Math.max(right_max[i+1],height[i]);
        }
        int ans=0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(left_max[i],right_max[i])-height[i];
        }
        return ans;
    }


    }

