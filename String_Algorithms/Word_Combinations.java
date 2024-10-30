package CSES.String_Algorithms;

import java.io.*;
import java.util.*;
public class Word_Combinations {
    //    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void Solve() throws IOException {
        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String [] Seq = new String [n];
        Trie T = new Trie();
        for(int i=0; i<n; i++) {
            Seq[i] = br.readLine();
        }

        for(int i=0; i<n; i++) T.insert(Seq[i]);
        long [] dp = new long[target.length()+1];
        dp[target.length()]=1l;

        for(int i=target.length()-1; i>=0; i--) {
            long ways = 0l;
            int curr = 1;
            for(int j=i; j<target.length(); j++){
                if(T.nodes.get(curr).ch[target.charAt(j)-'a']==0){
                    break;
                }
                curr = T.nodes.get(curr).ch[target.charAt(j)-'a'];
//                System.out.println(T.nodes[curr].str);
                if(T.nodes.get(curr).EOS) {
                    ways += dp[j + 1];
                    if(ways >= MOD) ways -= MOD;
                }
            }
            dp[i] = ways;
        }
        out.println(dp[0]);
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        //        long SqxtasrtTime = System.currentTimeMillis();
        while (t-- > 0) {
            Solve();
        }
        //        long endTime = System.currentTimeMillis();
        //        System.out.println("It took " + (endTime - StartTime) + " milliseconds");
        out.close();
    }

    //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
    static int MOD = (int)(1e9)+7;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}

class Node{
    //    int fail;
    int [] ch;
    //    String str;
    boolean EOS;
    public Node(){
        this.ch = new int [26];
//        Arrays.fill(ch, -1);
//        this.str = "";
    }
}
class Trie{
    List<Node> nodes;
    public Trie(){
        nodes  = new ArrayList<>();
        nodes.add(new Node());
        nodes.add(new Node());
    }
    int size=1;

    //    INSERTING STRING TO THE TRIE
    public void insert(String s){
        int curr = 1;
        for(char ch : s.toCharArray()){
            if(nodes.get(curr).ch[ch-'a']==0){
                nodes.get(curr).ch[ch-'a']=++size;
            }

            if(size >= nodes.size()) nodes.add(new Node());
//            nodes.get(nodes.get(curr).ch[ch-'a']).str = nodes.get(curr).str + ch;
            curr = nodes.get(curr).ch[ch-'a'];
        }
        nodes.get(curr).EOS=true;
    }

//    public void Build_fails(){
//        Queue<Integer> q = new LinkedList<>();
//        int curr=1;
//        nodes[curr].fail=1;
//
//        for(int i=0; i<26; i++){
//            if(nodes[curr].ch[i]!=-1) {
//                nodes[nodes[curr].ch[i]].fail = 1;
//                q.add(nodes[curr].ch[i]);
//            } else {
//                nodes[curr].ch[i] = 1;
//            }
//        }
//
//
//        while (!q.isEmpty()){
//            curr = q.poll();
//
//            for(int i=0; i<26; i++){
//                if(nodes[curr].ch[i] != -1){
//                    nodes[nodes[curr].ch[i]].fail = nodes[nodes[curr].fail].ch[i];
//                    q.add(nodes[curr].ch[i]);
//                } else {
//                    nodes[curr].ch[i] = nodes[nodes[curr].fail].ch[i];
//                }
//            }
//        }
//    }
}
