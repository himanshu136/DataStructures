package com.datastructures;

public class DisjointSet {
    static int parent[];
    static int rank[];

    public static  void  initialize(int size){
        parent= new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i]=i;
            rank[i]=0;
        }
    }
    public static int find(int index ){
        if(index==parent[index]) return index;
        parent[index]=find(parent[index]);
        return find(parent[index]);
    }

   public static void union(int x, int y){
        int x_rep = find(x);
        int y_rep = find(y);
        if(x_rep == y_rep) return;
        if(rank[x_rep]<rank[y_rep]) parent[x_rep]=parent[y_rep];
        else if(rank[x_rep]>rank[y_rep]) parent[y_rep]=x_rep;
        else {
            parent[y_rep]= x_rep;
            rank[x_rep]++;
        }
   }
}
