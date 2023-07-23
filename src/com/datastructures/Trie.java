package com.datastructures;



public class Trie {

    public  class Node{
        Node [] links = new Node[26];
        boolean isEnd;

        public Node(){

        }

        boolean containsKey(Character c){
            return links[c-'a']!=null;
        }
        void put(char c , Node node){
            links[c-'a']=node;
        }
        Node get(char c){
            return links[c-'a'];
        }
        void setEnd(){
            isEnd=true;
        }
        boolean isEnd(){
            return isEnd;
        }
    }
   private Node root;
    Trie(){
        root= new Node();
    }

    void insert(String word){
        Node node=root;
        for (int i = 0; i < word.length(); i++) {
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new Node());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }

    boolean search(String word){
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if(!node.containsKey(word.charAt(i))){
                return false;
            }
            node=node.get(word.charAt(i));
        }
        return node.isEnd();
    }
    boolean startsWith(String prefix){
        Node node=root;
        for (int i = 0; i < prefix.length(); i++) {
            if(!node.containsKey(prefix.charAt(i))){
                return false;
            }
            node=node.get(prefix.charAt(i));
        }
        return true;
    }
    boolean checkIfPrefixExists(String word){
        Node node = root;
        boolean flag =true;
        for (int i = 0; i < word.length() && flag; i++){
            if(node.containsKey(word.charAt(i))){
                node = node.get(word.charAt(i));
                flag = flag & node.isEnd();
            }else {
                return false;
            }
        }
        return flag;
    }

    int countDistinctSubstrings(String s){
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            Node node=root;
            for (int j = i; j < s.length(); j++) {
                if(!node.containsKey(s.charAt(j))){
                    count++;
                    node.put(s.charAt(j),new Node());
                }
                node = node.get(s.charAt(j));
            }
        }
        return count+1;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("apex");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("apex"));
        System.out.println(trie.search("linda"));
        // insert number of strings and check if any combination is lexographically bigger
        Trie lexographical = new Trie();
        String [] s = {"n","ninja","ninj","nin","ninga"};
        for (int i = 0; i < s.length; i++) {
            lexographical.insert(s[i]);
        }
        String longest = "";
        for (int i = 0; i < s.length; i++) {
            String word =s[i];
            if(lexographical.checkIfPrefixExists(word)){
                if(word.length()>longest.length()){
                    longest=word;
                }else if(word.length()==longest.length() && word.compareTo(longest)<0){
                    longest=word;
                }
            }
        }
        if (longest.equals("")) System.out.println("None");
        System.out.println(longest);

        Trie distinctTrie = new Trie();
        System.out.println(distinctTrie.countDistinctSubstrings("abab"));
    }

}
