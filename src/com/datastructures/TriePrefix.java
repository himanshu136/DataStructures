package com.datastructures;

import java.util.HashMap;
import java.util.Map;

public class TriePrefix {
    public class Node{
        Node [] links = new Node[26];
        int countPrefix=0;
        int ending=0;

        public Node(){

        }
        boolean containsKey(char c){
           return links[c-'a']!=null;
        }
        void put(char c, Node node){
            links[c-'a']=node;
        }
        Node get(char c){
           return links[c-'a'];
        }
        void increasePrefix(){
            countPrefix++;
        }
        void reducePrefix(){
            countPrefix--;
        }
        void increaseEnd(){
            ending++;
        }
        void decreaseEnd(){
            ending--;
        }
        int getEnding(){
            return ending;
        }
        int getCountPrefix(){
            return countPrefix;
        }
    }
    public static  Node root;
    TriePrefix(){
        root = new Node();
    }
    void insert(String word){
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new Node());
            }
            node = node.get(word.charAt(i));
            node.increasePrefix();
        }
        node.increaseEnd();
    }


}
