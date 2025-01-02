package CSES.Ranged_Queries;

import java.io.*;
import java.util.*;

public class Dynamic_Range_Sum_Queries {
   static FastReader fr = new FastReader();
   static PrintWriter out = new PrintWriter(System.out);
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

   public static void Solve() throws IOException {
       int n = fr.nextInt();
       int q = fr.nextInt();
       long [] Seq = new long [n];
       for(int i=0; i<n; i++) Seq[i] = fr.nextInt();

       StringBuilder ans = new StringBuilder();
       SegTree st = new SegTree(n);
       st.build(Seq, 1, 0, n-1);

       for(int i=0; i<q; i++){
           int temp = fr.nextInt();

           if(temp == 2) {
               int l = fr.nextInt();
               int r = fr.nextInt();
               ans.append(st.query(1, 0, n-1, l-1, r-1) + "\n");
//                out.println();
           } else {
               int k = fr.nextInt();
               int u = fr.nextInt();
               st.update(1, 0, n-1, k-1, u);
           }
       }
       out.println(ans.toString());
   }

   //    -------------------------------------------------------------------------------------------------------
   public static void main(String args[]) throws IOException {
//        Factorial((int)1e6);
//        int t = fr.nextInt();
       int t = 1;
       //        long SqxtasrtTime = System.currentTimeMillis();
       while (t-- > 0) {
           Solve();
       }
       out.close();
   }

   //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
   static class FastReader {
       BufferedReader br;
       StringTokenizer st;
       public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}
       String next() {
           while (st == null || !st.hasMoreElements()) {
               try {st = new StringTokenizer(br.readLine());}
               catch (IOException e) {e.printStackTrace();}
           }
           return st.nextToken();
       }
       int nextInt() {return Integer.parseInt(next());}
       long nextLong() {return Long.parseLong(next());}
       double nextDouble() {return Double.parseDouble(next());}
       String nextLine() {
           String str = "";
           try {
               if (st.hasMoreTokens()) {str = st.nextToken("\n");}
               else {str = br.readLine();}
           } catch (IOException e) {e.printStackTrace();}
           return str;
       }
   }

   static int MOD = (int) 1e9 + 7;
   static int maxI = Integer.MAX_VALUE;
   static int minI = Integer.MIN_VALUE;
   static long maxL = Long.MAX_VALUE;
   static long minL = Long.MIN_VALUE;

   public static int max(int a, int b) {return Math.max(a, b);}
   public static int min(int a, int b) {return Math.min(a, b);}
   public static long max(long a, long b) {return Math.max(a, b);}
   public static long min(long a, long b) {return Math.min(a, b);}
}

class Node {
   long sum;
   Node left;
   Node right;
   public Node(long a) {
       this.sum = a;
   }

   public Node() {
       this.sum=0;
   }
}

class SegTree {
   Node [] node;
   public SegTree(int n) {
       node = new Node [4*n];
   }
   void build(long [] Seq, int idx, int l, int r){
       if(l == r) {
           node[idx] = new Node(Seq[l]);
           return;
       }
       int mid = l + (r-l)/2;
       this.build(Seq, idx*2, l, mid);
       this.build(Seq, 2*idx+1, mid+1, r);
       node[idx] = this.merge(node[idx*2], node[idx*2+1]);
   }

   Node merge(Node a, Node b){
       Node curr = new Node();
       curr.sum = a.sum + b.sum;
//        System.out.println(curr.sum);
       return curr;
   }

   void update(int idx, int l, int r, int pos, int val){
       if(pos < l || pos > r) return;
       if(l == r) {
           node[idx].sum = val;
           return;
       }
       int mid = l + (r-l)/2;
       this.update(idx*2, l, mid, pos, val);
       this.update(2*idx+1, mid+1, r, pos, val);
       node[idx] = this.merge(node[idx*2], node[idx*2+1]);
   }

   long query(int idx, int l, int r, int lq, int rq) {
       if(l > rq || r < lq) return 0;
       if(l >= lq && rq >= r) return this.node[idx].sum;
       int mid = l + (r-l)/2;
       return this.query(idx*2, l, mid, lq, rq) + this.query(idx*2+1, mid+1, r, lq, rq);
   }
}