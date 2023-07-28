package com.datastructures;




import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;


public class GraphAdjacencyList {

    static class AdjacencyList{
        private int v;
        private int weight;
        public AdjacencyList(){

        }
        public AdjacencyList (int v, int weight){
            this.v=v;
            this.weight=weight;
        }
        public int getV(){
            return this.v;
        }
        public int getWeight(){
            return this.weight;
        }

    }

    static void addWeightedEdge(List<List<AdjacencyList>> adj,int u, int v, int weight){
        adj.get(u).add(new AdjacencyList(v,weight));
    }


    static void  addEdge(List<List<Integer>> adj, int u, int v){
             adj.get(u).add(v);
             adj.get(v).add(u);
    }

    static void  addEdgeForDirectedGraph(List<List<Integer>> adj, int u, int v){
        adj.get(u).add(v);
    }

    static void printGraph(List<List<Integer>>adjacencyList){
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                System.out.print(adjacencyList.get(i).get(j)+" ");
            }
            System.out.println("");
        }
    }

    static void BFS(List<List<Integer>> adjacencyList, int v, int s){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];
        visited[s]=true;
        queue.add(s);
        while (!queue.isEmpty()){
            int u = queue.poll();
            System.out.println("The vertice is : "+u);
            for (int a:adjacencyList.get(u)) {
                if (!visited[a]){
                    queue.add(a);
                    visited[a]=true;
                }
            }
        }
    }
    static void BFS(List<List<Integer>> adjacencyList, int v, int s,boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        visited[s]=true;
        queue.add(s);
        while (!queue.isEmpty()){
            int u = queue.poll();
            System.out.println("The vertice is : "+u);
            for (int a:adjacencyList.get(u)) {
                if (!visited[a]){
                    queue.add(a);
                    visited[a]=true;
                }
            }
        }
    }
    static void BFSDisconnectedGraph(List<List<Integer>> adjacencyList,int V,int s){
        boolean [] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
                BFS(adjacencyList,V,i,visited);
            }
        }
    }
    static void DFS(List<List<Integer>> adjacencyList, int v, int s){
        boolean [] visited =  new boolean[v];
        DFSRecursive(adjacencyList,s,visited);
    }
    static void DFSRecursive(List<List<Integer>> adjacencyList,  int s, boolean[] visited){
        visited[s]=true;
        System.out.println("the vertex is :"+s);
        for (int u: adjacencyList.get(s)) {
            if(!visited[u]){
                DFSRecursive(adjacencyList,u,visited);
            }
        }
    }
    static void DFSDisconnectedGraph(List<List<Integer>> adjacencyList, int v, int s){
        boolean [] visited =  new boolean[v];
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                DFSRecursive(adjacencyList,i,visited);
            }
        }
    }

    static void shortestPathOfUnweightedGraph(List<List<Integer>> adjacencyList, int v, int s) {
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[]=new boolean[v];
        visited[s]=true;
        int distance[] = new int[v];
        distance[s]=0;
        queue.add(s);
        while (!queue.isEmpty()){
            int u = queue.poll();
            System.out.println("The vertice is : "+u);
            for (int a:adjacencyList.get(u)) {
                if (!visited[a]){
                    distance[u] = distance[s]+1;
                    queue.add(a);
                    visited[a]=true;
                }
            }
        }
    }


    static boolean detectCycleInUndirectedGraph(List<List<Integer>>adjacencyList,boolean [] visited ,
                                                int source, int parent){
        visited[source]=true;
        for (int v:adjacencyList.get(source)) {
            if(visited[v]!=false){
                if(detectCycleInUndirectedGraph(adjacencyList,visited,source,parent)) return true;
            }else if(parent!=v){
                return true;
            }
        }
      return false;
    }

    static boolean cycleInAGraph(List<List<Integer>> adjacencyList, int v) {
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            if(visited[i]==false){
                if(detectCycleInUndirectedGraph(adjacencyList,visited,i,-1))
                    return true;
            }
        }
        return false;
    }

    static boolean cycleInADirectedGraph(List<List<Integer>> adjacencyList, int v) {
        boolean [] visited = new boolean[v];
        boolean [] recursion = new boolean[v];
        for (int i = 0; i < v; i++) {
            if(!visited[i]){
                if(detectCycleInDirectedGraph(adjacencyList,visited,i,recursion))
                    return true;
            }
        }
        return false;
    }
    static boolean detectCycleInDirectedGraph(List<List<Integer>>adjacencyList,boolean [] visited ,
                                              int source, boolean [] recursion){
        visited[source]=true;
        recursion[source]=true;
        for (int v:adjacencyList.get(source)) {
            if(!visited[v] && detectCycleInDirectedGraph(adjacencyList,visited,v,recursion)) return true;
            else if(recursion[v]) return true;
        }
        recursion[source]=false;
        return false;
    }

    static void topologicalSortUsingBFS(List<List<Integer>> adj, int V) {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int x: adj.get(i)) {
                inDegree[x]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int u = queue.remove();
            System.out.println(u);
            for (int v: adj.get(u)) {
                if(--inDegree[v]==0){
                    queue.add(v);
                }
            }
        }
    }

    static void topologicalSortUsingDFS(List<List<Integer>> adj, int V){
        boolean []visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if(!visited[i]) dfsForTopologicalSort(adj,visited,i,stack);
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    static void dfsForTopologicalSort(List<List<Integer>>adj, boolean visited[],int s, Stack<Integer>stack){
        visited[s]=true;
        for (int u:adj.get(s)) {
            if(!visited[u]) dfsForTopologicalSort(adj,visited,u,stack);
        }
        stack.push(s);
    }

    static Stack<Integer> topologicalSortWeightedUsingDFS(List<List<AdjacencyList>> adj, int V){
        boolean []visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if(!visited[i]) dfsForTopologicalSortWeighted(adj,visited,i,stack);
        }
        return stack;
    }

    static void dfsForTopologicalSortWeighted(List<List<AdjacencyList>>adj, boolean visited[],int s, Stack<Integer>stack){
        visited[s]=true;
        for (AdjacencyList u:adj.get(s)) {
            if(!visited[u.getV()]) dfsForTopologicalSortWeighted(adj,visited,u.getV(),stack);
        }
        stack.push(s);
    }
//    static void shortestPathUsingTopologicalSort(List<List<AdjacencyList>> adj, int V, int s){
//        int [] distance = new int[V];
//        distance[s]=0;
//        Stack<Integer> stack = topologicalSortWeightedUsingDFS(adj,V);
//        while (!stack.isEmpty()){
//            int u = stack.pop();
//            for (AdjacencyList adjL: adj.get(u)) {
//                if(distance[adjL.getV()]==)
//            }
//
//        }
//
//    }



    public static void main(String[] args) {
        int v=4;
        List<List<Integer>> adjacencyList = new ArrayList<>(v);
        intializeAdjacencyList(v, adjacencyList);
        addEdge(adjacencyList, 0,1);
        addEdge(adjacencyList,0,2);
        addEdge(adjacencyList,1,2);
        addEdge(adjacencyList,1,3);
        printGraph(adjacencyList);
        BFS(adjacencyList,v,0);
        DFS(adjacencyList,v,0);
        List<List<Integer>> adjacencyList1 = new ArrayList<>(v);
        intializeAdjacencyList(v, adjacencyList1);
        addEdge(adjacencyList1,0,1);
        addEdge(adjacencyList1,0,2);
        addEdge(adjacencyList1,1,2);
        addEdge(adjacencyList1,1,3);
        addEdge(adjacencyList1,2,3);
        shortestPathOfUnweightedGraph(adjacencyList1,4,0);
        List<List<Integer>>adjacencyList3 = new LinkedList<>();
        intializeAdjacencyList(6,adjacencyList3);
        addEdge(adjacencyList3,0,1);
        addEdge(adjacencyList3,1,2);
        addEdge(adjacencyList3,1,3);
        addEdge(adjacencyList3,2,3);
        addEdge(adjacencyList3,2,4);
        addEdge(adjacencyList3,4,5);
        System.out.println(cycleInAGraph(adjacencyList3,6));
        List<List<Integer>>adjacencyList4 = new LinkedList<>();
        intializeAdjacencyList(6,adjacencyList4);
        addEdge(adjacencyList4,0,1);
        addEdge(adjacencyList4,2,1);
        addEdge(adjacencyList4,2,3);
        addEdge(adjacencyList4,3,4);
        addEdge(adjacencyList4,4,5);
        addEdge(adjacencyList4,5,3);
        System.out.println(cycleInADirectedGraph(adjacencyList4,6));
        List<List<Integer>>adjacencyList5 = new LinkedList<>();
        intializeAdjacencyList(5,adjacencyList5);
        addEdgeForDirectedGraph(adjacencyList5,0,2);
        addEdgeForDirectedGraph(adjacencyList5,0,3);
        addEdgeForDirectedGraph(adjacencyList5,1,3);
        addEdgeForDirectedGraph(adjacencyList5,1,4);
        addEdgeForDirectedGraph(adjacencyList5,2,3);
        System.out.println("Topological sort using BFS :");
        topologicalSortUsingBFS(adjacencyList5,5);//Only for acyclic graph - Kahn's algorithim
        List<List<Integer>>adjacencyList6 = new LinkedList<>();
        intializeAdjacencyList(5,adjacencyList6);
        addEdgeForDirectedGraph(adjacencyList6,0,1);
        addEdgeForDirectedGraph(adjacencyList6,1,3);
        addEdgeForDirectedGraph(adjacencyList6,3,4);
        addEdgeForDirectedGraph(adjacencyList6,2,4);
        addEdgeForDirectedGraph(adjacencyList6,2,3);
        System.out.println("Topological sort using DFS :");
        topologicalSortUsingDFS(adjacencyList6,5);//Only for acyclic graph - Kahn's algorithim
        List<List<AdjacencyList>>adj = new LinkedList<>();
        intializeAdjacencyWeightedList(6,adj);
        addWeightedEdge(adj,0, 1, 2);
        addWeightedEdge(adj,0, 4, 1);
        addWeightedEdge(adj,1, 2, 3);
        addWeightedEdge(adj,4, 2, 2);
        addWeightedEdge(adj,4, 5, 4);
        addWeightedEdge(adj,2, 3, 6);
        addWeightedEdge(adj,5, 3, 1);
        char[][] grid = new char[][]{{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        numberOfIslandsDFS(grid);
        System.out.println(coursesSchedule(4,new int[][]{{1,0},{2,0},
                {3,1},{3,2}}));
        int [][] nums = {{1,0},{2,0},{3,1},{3,2}};
        courseScheduleII(4,nums);
        System.out.println(minimumStepByKnight());
        floodFillAlgorithim();
        ratInAMaze();
        makingWiredConnections();
        wordLadder();
        System.out.println("Dijkastra using matrix");
        dijkastraAlgo();
        System.out.println("Dijkastra using priority queue");
        dijkastraUsingPriorityQueue();
        primsAlgo();
        System.out.println("Prims using priority queue");
        primsPriorityQueue();
        snakesAndLadder();
        bellmanFord();
        kosaRajuAlgorithimForSCSS();
        articulationPoint();
        bridgesInAGraph();
        int graph[][] = { { 0, 1, 0, 1 },
                { 1, 0, 1, 0 },
                { 0, 1, 0, 1 },
                { 1, 0, 1, 0 } };
        bipartiteGraph(graph);
        kruskalsAlgorithim();
        twoCliqueProblem();
        System.out.println(findOrder(new String[]{"baa", "abcd", "abca", "cab", "cad"},5,4));
        minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}});
    }

    static boolean coursesSchedule(int numCourses,int [][] arr){
        boolean []visited = new boolean[numCourses];
        boolean [] dfsCurrVisited = new boolean[numCourses];
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int []adj: arr ) {
            adjacencyList.get(adj[0]).add(adj[1]);
        }
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                if(hasCycle(visited,dfsCurrVisited,adjacencyList,i)){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean hasCycle(boolean[] visited, boolean[] dfsCurrVisited, List<List<Integer>> adj, int currNode){
        visited[currNode]=true;
        dfsCurrVisited[currNode]=true;
        for (int neighbour : adj.get(currNode)) {
            if(!visited[neighbour]){
                if(hasCycle(visited,dfsCurrVisited,adj,currNode)){
                    return true;
                }
            }else if(dfsCurrVisited[currNode]){
                return true;
            }
        }
        dfsCurrVisited[currNode]=false;
        return false;
    }

     static void intializeAdjacencyList(int v, List<List<Integer>> adjacencyList) {
        for (int i = 0; i < v; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
     static void intializeAdjacencyWeightedList(int v, List<List<AdjacencyList>> adjacencyList) {
        for (int i = 0; i < v; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
    public static void  numberOfIslandsDFS(char[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j]=='1'){
                   count++;
                   dfs(grid, i, j, n, m);
                }
            }
        }
        System.out.println(count);
    }
    static void dfs(char[][] grid, int i , int j, int n, int m ){
        int []dx = {1,-1,0,0};
        int []dy = {0,0,1,-1};
        grid[i][j]='#';
        for (int k = 0; k < 4; k++) {
            if(isValid(grid,i+dx[k],j+dy[k],n,m)){
                dfs(grid, dx[k]+i, dy[k]+j,n,m);
            }
        }
    }
    static boolean isValid(char [][]grid, int i, int j, int n , int m){
        if(i<0 || i>=n || j<0 || j>=m || grid[i][j]!='1') return false;
        return true;
    }

    //Cycle in directed graph
    static void detectCycleIndirectedGraph(){
        List<List<Integer>> adj = new ArrayList<>();
        int V=6;
        intializeAdjacencyList(V, adj);
        addEdgeForDirectedGraph(adj,0,1);
        addEdgeForDirectedGraph(adj,1,2);
        addEdgeForDirectedGraph(adj,2,0);
        addEdgeForDirectedGraph(adj,3,4);
        addEdgeForDirectedGraph(adj,4,5);

        int [] inDegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            for (int x:adj.get(i)) {
                inDegree[x]++;
            }
        }
        for (int i=0;i<V;i++) {
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
        List<Integer> list = new LinkedList<>();
        int count =0;
        while (!queue.isEmpty()){
            int u = queue.poll();
            list.add(u);
            for (int x:adj.get(u)) {
                if(--inDegree[x]==0){
                    queue.add(x);
                }
            }
            count++;
        }
        if(count!=V) System.out.println("CYCLE EXIST");
        else System.out.println("Cycle doesn't exist");
    }

    //Topological Sort using BFS /Courses Schedule II
    static void courseScheduleII(int numCourses, int[][] nums){
        List<List<Integer>> adj = new ArrayList<>();
        intializeAdjacencyList(numCourses, adj);
        for (int i = 0; i < numCourses; i++) {
                addEdgeForDirectedGraph(adj,nums[i][1],nums[i][0]);

        }

        int [] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            for (int x:adj.get(i)) {
                inDegree[x]++;
            }
        }
        for (int i=0;i<numCourses;i++) {
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
        List<Integer> list = new LinkedList<>();
        int count =0;
        while (!queue.isEmpty()){
            int u = queue.poll();
            list.add(u);
            for (int x:adj.get(u)) {
                if(--inDegree[x]==0){
                    queue.add(x);
                }
            }
            count++;
        }
        if(count!=numCourses) System.out.println("CYCLE EXIST");
        else {
            String s= "";
            System.out.println("Cycle doesn't exist");
            list.forEach(System.out::println);
        }
    }

    static class MapPair {
        int x;
        int y;
        int dis;
        MapPair(int x, int y, int dis) {
        this.x=x;
        this.y=y;
        this.dis=dis;
        }
    }



    // Mininmum Step by Knight using shorestPath in Unweighted Graph
    public static int minimumStepByKnight(){
        int N = 30;
        int [] knightPos = {1, 1};
        int [] targetPos = {30, 30};
        Vector<MapPair> queue = new Vector<>();

        int x = knightPos[0];
        int y = knightPos[1];
        int target_x = targetPos[0];
        int target_y = targetPos[1];
        if(x==target_x && y==target_y) return 0;
        queue.add(new MapPair(x,y,0));
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };


        boolean[][] visited = new boolean[N+1][N+1];
        for (boolean [] temp:visited) {
            Arrays.fill(temp,false);
        }
        visited[x][y]=true;
        while (!queue.isEmpty()){
           MapPair mapPair =  queue.firstElement();
           queue.remove(0);
             x = mapPair.x;
             y = mapPair.y;
             if(x==target_x && y==target_y) return mapPair.dis;
            for (int i = 0; i < 8; i++) {
                x = x+dx[i];
                y=y+dy[i];
                if(isValid(x,y,N,visited)){
                    visited[x][y]=true;
                    queue.add(new MapPair(x,y, mapPair.dis+1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    private static boolean isValid(int x, int y, int n, boolean[][] visited){
        if(x >= 1 && x <= n && y >= 1 && y <= n && visited[x][y] == false)
            return true;
        return false;
    }

    //flood fill algrotihim
    public static void floodFillAlgorithim(){
        int [][] screen = {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int x =4,y=4;
        int N = screen.length;
        int newColor =3;
        floodFillAlgorithim(screen,x,y,N,newColor);
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }
    }

    static void  floodFillAlgorithim(int[][] screen , int x, int y, int N, int newColor){
        int prevColor = screen[x][y];
        if(prevColor==newColor) return;
        floodFillUtil(screen,x,y,N,newColor,prevColor);
    }
   static void floodFillUtil(int[][] screen, int x,int y, int N, int newColor, int prevColor){
        int []dx = {1,0,-1,0};
        int []dy = {0,1,0,-1};
        if(x<0||x>=N||y<0||y>=N) return;
        if(prevColor!=screen[x][y]) return;
        screen[x][y]=newColor;
       for (int i = 0; i < 4; i++) {
           floodFillUtil(screen,x+dx[i],y+dy[i],N,newColor,prevColor);
       }
    }

    static void ratInAMaze(){
        int [][] maze = { { 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 1 },
                { 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 1 } };
        int m = maze.length;
        int n = maze[0].length;
        List<String> result = new ArrayList<>();
        boolean [][] visited = new boolean[m][n];
        solveMaze(maze,0,0,"",visited,result,m,n);
        result.forEach(System.out::println);
    }
    static void solveMaze(int [][] maze, int i, int j, String paths,boolean [][] visited,List<String> result,int m ,int n){
        if(i==m-1 && j==n-1){
            result.add(paths);
        }
        if(isSafe(i+1,j,visited,maze,m,n)){
            visited[i][j]=true;
            solveMaze(maze,i+1,j,paths+"D",visited,result,m,n);
            visited[i][j]=false;
        }
        if(isSafe(i,j-1,visited,maze,m,n)){
            visited[i][j]=true;
            solveMaze(maze,i,j-1,paths+"L",visited,result,m,n);
            visited[i][j]=false;
        }
        if(isSafe(i,j+1,visited,maze,m,n)){
            visited[i][j]=true;
            solveMaze(maze,i,j+1,paths+"R",visited,result,m,n);
            visited[i][j]=false;
        }
        if(isSafe(i-1,j,visited,maze,m,n)){
            visited[i][j]=true;
            solveMaze(maze,i-1,j,paths+"U",visited,result,m,n);
            visited[i][j]=false;
        }
    }
    static boolean isSafe(int i , int j, boolean[][]visited,int [][] matrix,int m , int n){
        if(i<0 || j<0 || i>=m || j>=n || visited[i][j] || matrix[i][j]==0) return false;
        return true;
    }
   //Wired Connections using number of disconnected components
    public static void  makingWiredConnections(){
        int [][] connections = {{0,1},{0,2},{1,2}};
        int n =4;
        int m =connections.length;
        if(m<n-1) System.out.println(-1);
        List<List<Integer>> adj = new ArrayList<>();
        intializeAdjacencyList(n,adj);
        for (int i = 0; i < m; i++) {
            addEdge(adj,connections[i][0],connections[i][1]);
        }
        boolean [] visited = new boolean[n];
        int edge =0;
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                DFSRecursive(adj,i,visited);
                edge=edge+1;
            }
        }
        System.out.println(edge-1);
    }

    //Word Ladder using shortest path in unweighted graph
    public static void wordLadder(){
        String begin="hot",end="dog";
        String [] wordList = {"hot","dog"};
        Set<String> set = new HashSet<>(Arrays.asList(wordList));
        if(!set.contains(end)) System.out.println(0);
        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        int depth =1;
        while (!queue.isEmpty()){
           int size =queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if(word.equals(end)) System.out.println(depth);
                for (int j = 0; j < word.length(); j++) {
                    for (char k = 'a'; k <='z'; k++) {
                        char a[] = word.toCharArray();
                        a[j]=k;
                        String temp = new String(a);
                        if(set.contains(temp) && !visited.contains(temp)){
                            queue.add(temp);
                            visited.add(temp);
                        }
                    }
                }
            }
            ++depth;
        }
        System.out.println(0);
    }

    public static void dijkastraAlgo(){
        int graph[][] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        int V =9;
        int[] dis = new int[V];
        Arrays.fill(dis,Integer.MAX_VALUE);
        boolean vis[] = new boolean[V];
        dis[0]=0;
        for (int i = 0; i < V-1; i++) {
            int u = findMinDistance(dis,vis);
            vis[u]=true;
            for (int v = 0; v < V; v++) {
                if (!vis[v] && graph[u][v] != 0 && (dis[u] + graph[u][v] < dis[v])) {
                    dis[v] = dis[u] + graph[u][v];
                }
            }
        }
        Arrays.stream(dis).forEach(System.out::println);

    }

    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    private static void dijkastraUsingPriorityQueue(){
        int graph[][] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        int n = graph.length;
        Map<Integer,List<Pair<Integer,Integer>>>adj =new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
             if(graph[i][j]!=0){
                 adj.putIfAbsent(i,new ArrayList<>());
                 adj.get(i).add(new Pair(graph[i][j],j));
             }
            }
        }
        int [] distance = new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        Queue<Pair<Integer,Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.add(new Pair(0,0));
        while (!queue.isEmpty()){
            Pair<Integer,Integer> pair = queue.poll();
            int u_weight = pair.getKey();
            int u = pair.getValue();
            if(u_weight>distance[u]) continue;
            if(!adj.containsKey(u)) continue;
            for (Pair<Integer,Integer> edge: adj.get(u)) {
            int v_weight = edge.getKey();
            int v = edge.getValue();
            if(distance[v]>v_weight+u_weight){
                distance[v]=v_weight+u_weight;
                queue.add(new Pair(distance[v],v));
            }
            }
        }
        for (int i = 0; i <n; i++) {
            System.out.println(distance[i]);
        }
    }

    public static  void  primsAlgo(){
        int graph[][]= new int[][] { { 0, 5, 8, 0},
                { 5, 0, 10, 15 },
                { 8, 10, 0, 20 },
                { 0, 15, 20, 0 },};
        int v = graph.length;
        int [] key = new int[v];
        Arrays.fill(key,Integer.MAX_VALUE);
        key[0]=0;
        boolean mset[] = new boolean[v];
        int result = 0;
        for (int i = 0; i < v; i++) {
            int u=-1;
            for (int j = 0; j < v; j++) {
                if(!mset[i] && (u==-1 || key[j]<key[i])){
                    u=i;
                }
            }
            mset[u]=true;
            result =result+key[u];

            for (int c = 0; c < v; c++) {
                if(graph[i][c]!=0 && !mset[c]){
                    key[c]=Math.min(key[c],graph[i][c]);
                }
            }
        }
        System.out.println(result);
    }

    private static void primsPriorityQueue(){
        int graph[][]= new int[][] { { 0, 5, 8, 0},
                { 5, 0, 10, 15 },
                { 8, 10, 0, 20 },
                { 0, 15, 20, 0 },};
        int n = graph.length;
        Map<Integer,List<Pair<Integer,Integer>>>adj =new HashMap<>();
        for (int i = 0; i < n; i++) {
            adj.putIfAbsent(i,new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(graph[i][j]!=0){
                    adj.get(i).add(new Pair(graph[i][j],j));
                    adj.get(j).add(new Pair(graph[i][j],i));
                }
            }
        }
        int [] keys = new int[n];
        Arrays.fill(keys,Integer.MAX_VALUE);
        keys[0]=0;
        Queue<Pair<Integer,Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.add(new Pair(0,0));
        boolean []mst = new boolean[n];
        int result = 0;
        while (!queue.isEmpty()){
            Pair<Integer,Integer> pair = queue.poll();
            int currentNode = pair.getValue();
            int currNodeWeight =pair.getValue();
            if(mst[currNodeWeight]) continue;
            result = result+keys[currentNode];
            mst[currentNode]=true;
            if(!adj.containsKey(currentNode)) continue;
            for (Pair<Integer,Integer> edge: adj.get(currentNode)) {
                int weight = edge.getKey();
                int neighborNode = edge.getValue();
                if (!mst[neighborNode] && keys[neighborNode]>weight){
                    keys[neighborNode]=Math.min(weight,keys[neighborNode]);
                    queue.add(new Pair(keys[neighborNode],neighborNode));
                }

            }
        }
        System.out.println(result);
    }

    private static void  snakesAndLadder(){
        int board[][] = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean [][]vistied = new boolean[n][n];
        int steps =0;
        vistied[n-1][0]=true;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int s = queue.poll();
                if(s ==n*n){
                    System.out.println(steps);
                    return;
                }
                for (int j = 1; j <=6; j++) {
                    if(s+j>n*n) break;
                    int []pos=findPosition(s+j,n);
                    int r = pos[0];
                    int c = pos[1];
                    if(vistied[r][c]) continue;
                    vistied[r][c]=true;
                    if(board[r][c]!=-1){
                        queue.add(board[r][c]);
                    }else {
                        queue.add(s+j);
                    }
                }
            }
            steps++;
        }
        System.out.println(-1);
    }

    private static int[] findPosition(int i, int n) {
        int row = (n - 1) - (i - 1)/n;
        int col = (i-1)%n;
        if(row%2==n%2) return new int[]{row,n-1-col};
        else return new int[]{row,col};
    }

    public static class Edge{
        public int src;
        public int dest;
        public int weight;
        public Edge(int src, int dest, int weight){
            this.src=src;
            this.dest=dest;
            this.weight=weight;
        }
    }

    public static void bellmanFord(){
        int [][]graph = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int V =4;
        int edge = graph.length;
        List<Edge> edgeList = new ArrayList<>(edge);
        for (int i = 0; i < edge; i++) {
            edgeList.add(i,new Edge(graph[i][0],graph[i][1],graph[i][2]));
        }
        int[] parent=new int[V];
        int []cost=new int[V];
        Arrays.fill(cost,Integer.MAX_VALUE);
        parent[0]=-1;
        cost[0]=0;
        boolean isUpdated;
        for (int i = 0; i < V-1; i++) {
             isUpdated = false;
            for (int j=0;j<edge;j++){
                int u = edgeList.get(j).src;
                int v= edgeList.get(j).dest;
                int weight= edgeList.get(j).weight;
                if(cost[i]!= Integer.MAX_VALUE && cost[u]+weight<cost[v]){
                    cost[v]=cost[u]+weight;
                    parent[v]=u;
                    isUpdated=true;
                }
                if(!isUpdated) break;
            }
            for (int j = 0; j < edge && isUpdated; j++) {
                int u = edgeList.get(j).src;
                int v = edgeList.get(j).dest;
                int wt = edgeList.get(j).weight;
                if(cost[u]!=Integer.MAX_VALUE && cost[u]+wt<cost[v])
                {
                    System.out.println("graph has cycles");
                    return;
                }
            }
        }
        System.out.println("Shortest distance using bellmanford is ");
        Arrays.stream(cost).forEach(System.out::println);
    }

    //first topological sort using dfs
    //then reverse/transpose the graph
    //then again do the dfs for disconnected graph
    static void kosaRajuAlgorithimForSCSS(){
        List<List<Integer>> adjList = new ArrayList<>();
        intializeAdjacencyList(5,adjList);
        addEdge(adjList,1,0);
        addEdge(adjList,0,2);
        addEdge(adjList,2,1);
        addEdge(adjList,0,3);
        addEdge(adjList,3,4);
        Stack<Integer> stack = new Stack<>();
        boolean visited[]= new boolean[5];
        for (int i = 0; i < 5; i++) {
            if(!visited[i]){
                dfsForTopologicalSort(adjList,visited,i,stack);
            }
        }
        List<List<Integer>>transposeList=transposeGraph(adjList);
        Arrays.fill(visited,false);
        while (!stack.isEmpty()){
            int v = stack.pop();
            if(!visited[v]){
                dfs(transposeList,v,visited);
                System.out.println();
            }
        }

    }
    public static List<List<Integer>>  transposeGraph(List<List<Integer>>adjList){
        List<List<Integer>> transposedList = new ArrayList<>();
        intializeAdjacencyList(5,transposedList);
        for (int i = 0; i < 5; i++) {
            for(Integer list :adjList.get(i)){
                addEdge(transposedList,list,i);
            }
        }
        return transposedList;
    }

    public static void dfs(List<List<Integer>> adjList, int source, boolean[]visited){
        visited[source]=true;
        System.out.print(source +" ");
        for (Integer i : adjList.get(source)){
            if (!visited[i]){
                dfs(adjList,i,visited);
            }
        }
    }

    //Articulation point for a graph
    //if the children of node is more than 2 then it is an articulation point for a parent node or
    // let's say the source node or we have any node which has more than 2 children
    // is known as the articulation point
    //for the other nodes we need to maintain two discovery time and lowest time to
    // find out whether the node is articulation point
    // or not for a node to be articulation point it must satisfy the condition low[v]>=dis[u]
    public static void articulationPoint(){
        int V =5;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <V; i++) {
            intializeAdjacencyList(V,adjList);
        }
        addEdge(adjList,1,0);
        addEdge(adjList,0,2);
        addEdge(adjList,2,1);
        addEdge(adjList,0,3);
        addEdge(adjList,3,4);
        boolean[]visited = new boolean[V];
        boolean[]ap = new boolean[V];
        int []dis = new int[V];
        int []low = new int[V];
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
                dfs(adjList,visited,i,-1,dis,low,ap,0);
            }
        }
        System.out.println("The articulation points are ");
        for (int i = 0; i < V; i++) {
            if(ap[i]) System.out.println(" "+i);
        }
    }
    public static void dfs(List<List<Integer>>adjList,boolean[]visted, int u, int parent, int []dis, int[]low,
                           boolean[]ap,int time){
        int children =0;//children is for checking the point no.1 the first dfs for source vertex
        visted[u]=true;
        dis[u]=low[u]=time++;
        for (Integer v :adjList.get(u)){
            if(!visted[v]){
                children++;
                visted[v]=true;
                dfs(adjList,visted,v,u,dis,low,ap,time);

                low[u]=Math.min(low[u],low[v]);

                if(parent==-1 && children>1){//the condition checking the above source vertex
                    ap[u]=true;
                }
                if(parent!=-1 && low[v]>=dis[u]){
                    ap[u]=true;
                }
            }else if(v!=parent){
                low[u]=Math.min(low[u],dis[v]);
            }
        }
    }

    //Same as the above like we find the articulation point
    //here we don't do the dfs for source node
    //we will directly check the condition low[u]>disc[v]
    public static void bridgesInAGraph(){
        int V =5;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <V; i++) {
            intializeAdjacencyList(V,adjList);
        }
        addEdge(adjList,1,0);
        addEdge(adjList,0,2);
        addEdge(adjList,2,1);
        addEdge(adjList,0,3);
        addEdge(adjList,3,4);

        int discoveryTime[] = new int[V];
        int low[] = new int[V];
        boolean visited[]= new boolean[V];
        for (int i = 0; i < V; i++) {
            if(!visited[i]) dfsForBridges(adjList,visited,discoveryTime,low,i,-1,0);
        }
    }
    public static void dfsForBridges(List<List<Integer>> adjList,boolean [] visited,int []discoveryTime, int [] low,int u,
                                     int parent,int time){
        visited[u]=true;
        discoveryTime[u]=low[u]=time++;
        for (Integer v:adjList.get(u)) {
            if(!visited[v]){
                visited[v]=true;
                dfsForBridges(adjList,visited,discoveryTime,low,v,u,time);
                low[u]=Math.min(low[u],low[v]);

                if(low[v]>discoveryTime[u]){
                    System.out.println("the bridge is from u:"+u+" to v "+v);
                }
            }else if(v!=parent){
                low[u]=Math.min(low[u],discoveryTime[v]);
            }
        }
    }


    public static void bipartiteGraph(int[][] graph){
        int V=4;
        List<List<Integer>> adjList = new ArrayList<>();
        intializeAdjacencyList(4,adjList);
        boolean visited[] = new boolean[V];
        int color[] = new int[V];
        for (int i = 0; i < V; i++) {
            if(!visited[i]){
                if(!checkBipartited(visited,color,i,0,adjList)){
                    System.out.println("Not a bipartite graph");
                    return;
                }
            }
        }
        System.out.println("Graph is bipartite");
    }
    public static boolean checkBipartited(boolean[]visited, int colorArray[], int u,int color,List<List<Integer>>adj){
        visited[u]=true;
        colorArray[u]=color;
        for (Integer v:adj.get(u)) {
            if(!visited[v]){
                if(!checkBipartited(visited,colorArray,v,color^1,adj)) return false;
            }else if(visited[v]){
                if(colorArray[u]==colorArray[v]){
                    return false;
                }
            }
        }
        return true;
    }

     static class Edges implements Comparable<Edges>{
        int u;
        int v;
        int weight;
         public  Edges(){
         }
        public  Edges(int u, int v, int weight){
            this.u=u;
            this.v=v;
            this.weight=weight;
        }

         @Override
         public int compareTo(Edges compareEdges) {
             return this.weight-compareEdges.weight;
         }
     }

    public static void kruskalsAlgorithim(){
        int[][] graph = {{0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4}};
        int V = 4;
        int edges= graph.length;
        List<Edges> edgesList = new ArrayList<>();
        for (int i = 0; i < edges; i++) {
            edgesList.add(new Edges(graph[i][0],graph[i][1],graph[i][2]));
        }
        Collections.sort(edgesList);
        DisjointSet.initialize(V);
        Edges[] result = new Edges[V];
        Arrays.fill(result, new Edges());
        int e =0;
        int i =0;
        while (e<V-1){
            Edges next_edge = edgesList.get(i++);
            int find_x = DisjointSet.find(next_edge.u);
            int find_y= DisjointSet.find(next_edge.v);
            if(find_x!=find_y){//no cycle Exists
                result[e++]=next_edge;
                DisjointSet.union(find_x,find_y);
            }
        }
        int min =0;
        for (int j = 0; j < e; j++) {
            Edges edges1 =result[j];
            System.out.print("MST IS ");
            min= min +edges1.weight;
            System.out.println(edges1.u+" "+edges1.v+ " weight is "+edges1.weight);
        }
        System.out.println("Total weight of mst is "+min);
    }

    //two Clique problem: A Clique is a subgraph of graph such that all vertices in subgraph
    // are completely connected with each other
    //first Find the complement of a graph
    //second check if the graph is bipartite or not
    static void twoCliqueProblem(){
        int[][] graph = {{0, 1, 1, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 1, 0}};
        int V = graph.length;
       graph= findComplementOfAGraph(graph,V);
       bipartiteGraph(graph);

    }

    //Complement of a graph is diagonals element does not match
    static int [][]  findComplementOfAGraph(int[][] graph,int V){
        int [][] GC = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                GC[i][j]= (i!=j)? -GC[i][j]:0;
            }
        }
        return GC;
    }


    int countDistinctIslands(int[][] grid) {
        // Your Code here
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        Set<List<Pair>> set = new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!vis[i][j] && grid[i][j]==1){
                    List<Pair> list = new ArrayList<>();
                    dfs(grid,vis,i,j,m,n,list,i,j);
                    set.add(list);
                }
            }
        }
        return set.size();
    }

    void dfs(int [][] grid, boolean [][]vis, int i, int j, int m, int n,
             List<Pair> list, int row, int col){
        vis[i][j]=true;
        list.add(new Pair(i-row,j-col));
        int dirRow[] = {-1,0,1,0};
        int dirCol[] = {0,-1,0,1};
        for(int d=0;d<4;d++){
            int nRow = i+dirRow[d];
            int nCol = j+dirCol[d];
            if(nRow>=0 && nRow<m && nCol>=0 && nCol<n && !vis[nRow][nCol] &&
                    grid[nRow][nCol]==1){
                dfs(grid,vis,nRow,nCol,m,n,list,row,col);
            }
        }
    }

    //Alien Dictionary
    public static String findOrder(String [] dict, int N, int K)
    {
        // Write your code here
        List<List<Integer>>list = new ArrayList<>();
        for(int i=0;i<K;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<N-1;i++){
            String s1 = dict[i];
            String s2 = dict[i+1];
            int len = Math.min(s1.length(),s2.length());
            for(int j=0;j<len;j++){
                if(s1.charAt(j)!=s2.charAt(j)){
                    list.get(s1.charAt(j)-'a').add(s2.charAt(j)-'a');
                    break;
                }
            }

        }
        List<Integer>  result = topoSort(list,K);
        String ans="";
        for (int i : result){
            ans=ans+ (char)(i + 'a');
        }
        return ans;
    }

    public static List<Integer> topoSort(List<List<Integer>> adj, int k){
        int [] indegree = new int[k];
        for(int i=0;i<k;i++){
            for(int x:adj.get(i)){
                indegree[x]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<k;i++) {
            if(indegree[i]==0){
                q.add(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            Integer u = q.poll();
            list.add(u);
            for(int v: adj.get(u)){
                indegree[v]--;
                if(indegree[v]==0){
                    q.add(v);
                }
            }
        }
        return list;
    }

    public static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int [] dx ={-1,0,1,0};
        int [] dy={0,1,0,-1};
        int [][] dis = new int [n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dis[i][j]=Integer.MAX_VALUE;
            }
        }
        Queue<Pair<Integer,Pair<Integer,Integer>>> q =
                new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.add(new Pair(0,new Pair(0,0)));
        while(!q.isEmpty()){
            Pair<Integer,Pair<Integer,Integer>> element = q.poll();
            int diff = element.getKey();
            Pair<Integer,Integer> cells = element.getValue();
            int row = cells.getKey();
            int column = cells.getValue();
            if(row==n-1 && column==m-1) return diff;
            for(int i=0;i<4;i++){
                int nRow = row+dx[i];
                int nColumn = column+dy[i];
                if(nRow>=0 && nRow<n && nColumn>=0 && nColumn<m){
                    int ndiff = Math.max(diff, Math.abs(heights[row][column]-heights[nRow][nColumn]));
                    if(ndiff<dis[nRow][nColumn]){
                        dis[nRow][nColumn]=ndiff;
                        q.add(new Pair(ndiff,new Pair(nRow,nColumn)));
                    }
                }
            }
        }
        return 0;
    }
///K FLIGHTS
    class Tuple{
        int stops;
        int u;
        int cost;
        public Tuple(int stops, int u, int cost){
            this.stops = stops;
            this.u=u;
            this.cost=cost;
        }
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair<Integer,Integer>>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int []arr:flights){
            adj.get(arr[0]).add(new Pair(arr[1],arr[2]));
        }
        Queue<Tuple> pq = new LinkedList<>();
        pq.add(new Tuple(0,src,0));
        int[] dis = new int[n];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[src]=0;
        while(!pq.isEmpty()){
            Tuple it = pq.poll();
            int u = it.u;
            int cost = it.cost;
            int stops = it.stops;
            if(stops>k) continue;
            for(Pair<Integer,Integer>list:adj.get(u)){
                int v = list.getKey();
                int vCost = list.getValue();
                int totalCost = vCost+cost;
                if(totalCost<dis[v] && stops<=k){
                    dis[v]=totalCost;
                    pq.add(new Tuple(stops+1,v,totalCost));
                }
            }
        }
        if(dis[dst]==Integer.MAX_VALUE)return -1;
        else return dis[dst];

    }

}

