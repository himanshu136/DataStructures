package com.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Greedy {

    static class Meeting{
        int start;
        int end;
        int index;
        public Meeting(int start, int end, int index){
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
    static class MeetingComparator implements Comparator<Meeting>{
        @Override
        public int compare(Meeting o1, Meeting o2) {
            if(o1.end<o2.end){
                return -1;
            }else if(o1.end>o2.end) {
                return 1;
            }else if(o1.index<o2.index){
                return -1;
            }
            return 1;
        }
    }
   static List<Integer> nMeetings(int[]start, int []end ){
        Meeting []meetings = new Meeting[start.length];
        for (int i = 0; i < start.length; i++) {
            Meeting meeting = new Meeting(start[i],end[i],i+1);
            meetings[i]=meeting;
        }
        Arrays.sort(meetings,new MeetingComparator());
        List<Integer> res = new ArrayList<>();
        res.add(meetings[0].index);
        int limit = meetings[0].end;
        for (int i = 1; i < meetings.length; i++) {
            if(meetings[i].start>limit){
                limit=meetings[i].end;
                res.add(meetings[i].index);
            }
        }

        return res;
    }

    public static boolean jumpGame(int [] nums){
        int reachable=0;
        for (int i = 0; i < nums.length; i++) {
            if(i<reachable){
               return false;
            }
            reachable=Math.max(reachable,i+nums[i]);
        }
        return true;
    }


    public static int minimumPlatforms(int []arrival, int [] departure){
        int n=arrival.length;
        Arrays.sort(arrival);
        Arrays.sort(departure);
        int result =1;
        int platform =1;
        int i=1,j=0;
        while (i<n && j<n){
            if(arrival[i]<=departure[j]){
                i++;
                platform++;
            }else if(departure[j]<arrival[i]){
                j++;
                platform--;
            }
            result = Math.max(platform,result);
        }
     return result;
    }

    public static int jumpGameII(int []nums){
        int l=0,r=0,res=0;
        while (r<nums.length-1){
            int farthest=0;
            for (int i = l; i < r+1; i++) {
                farthest=Math.max(farthest,i+nums[i]);
            }
            l=l+1;
            r=farthest;
            res=res+1;
        }
        return res;
    }

    static int SJF(int []jobs) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 1;
        int [] result = new int[jobs.length];
        result[0]=0;
        pq.add(jobs[0]);
        int i=0;
        int time=0;
        while (!pq.isEmpty()){
            int burstTime =pq.poll();
            while (burstTime > 0) {
                time=time+1;
                burstTime--;
                if(count!=jobs.length-1){
                    pq.add(jobs[count]);
                    count++;
                }
            }
            i++;
            result[i]=time;
        }
        int ans = 0;
        for (int a=1;a<jobs.length;a++) {
            ans += result[a]-a;
        }
        return ans/jobs.length;

    }




    public static void main(String[] args) {
        int start[] = new int[]{1,3,0,5,8,5};
        int end[] =  new int[]{2,4,6,7,9,9};
        nMeetings(start,end).forEach(System.out::println);
        int[] arrival = new int[]{900, 940, 950, 1100, 1500, 1800};
        int[] departure  = new int[]{910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(minimumPlatforms(arrival,departure));
        System.out.println("SJF \n"+ SJF(new int[]{1,2,3,4}));

    }
}
