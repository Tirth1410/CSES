package CSES.Ranged_Queries;

import java.io.*;
import java.util.*;

public class Dynamic_Range_Minimum_Queries {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void Solve() throws IOException {
        int n = fr.nextInt();
        int m = fr.nextInt();
        long [] Seq = new long [2*n+10];

        for(int i=0; i<n; i++) Seq[i+n] = fr.nextLong();
        int [][] q = new int [m][3];
//        for(int i=0; i<m; i++){
//            q[i][0] = fr.nextInt();
//            q[i][1] = fr.nextInt();
//            q[i][2] = fr.nextInt();
//        }

        StringBuilder ans = new StringBuilder();
        SegTree st = new SegTree(n);
        st.build(Seq, n);

        for(int i=0; i<m; i++){
            int temp = fr.nextInt();
            if(temp == 2) {
                int l = fr.nextInt();
                int r = fr.nextInt();
                ans.append(st.query(Seq, n,  l-1, r) + "\n" );
//                out.println();
            } else {
                int k = fr.nextInt();
                int u = fr.nextInt();
                st.update(Seq, k-1, n, u);
            }
        }
        out.println(ans);
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

//    static int MOD = (int) 1e9 + 7;
//    static int maxI = Integer.MAX_VALUE;
//    static int minI = Integer.MIN_VALUE;
//    static long maxL = Long.MAX_VALUE;
//    static long minL = Long.MIN_VALUE;
//
//    public static int max(int a, int b) {return Math.max(a, b);}
//    public static int min(int a, int b) {return Math.min(a, b);}
//    public static long max(long a, long b) {return Math.max(a, b);}
//    public static long min(long a, long b) {return Math.min(a, b);}
}

class Node {
    long min;
    Node left;
    Node right;
    public Node(long a) {
        this.min = a;
    }

    public Node() {
        this.min=Integer.MAX_VALUE;
    }
}

class SegTree {
    public SegTree(int n) {

    }

    void build(long[] Seq, int n) {
        for(int i=n-1; i>0; i--) {
            Seq[i] = Math.min(Seq[i<<1], Seq[(i<<1)|1]);
        }
    }

    void update(long [] Seq, int idx, int n, long val) {
        Seq[idx+n] = val;
        for(int i = idx+n; i > 1; i>>=1) {
            Seq[i>>1] = Math.min(Seq[i], Seq[i^1]);
        }
    }

    long query(long [] Seq, int n, int l, int r) {
        long min = (long) 1e18;

        for(int i=l+n, j=r+n; i<j; i>>=1, j>>=1){
            if((i&1) != 0) min = Math.min(min, Seq[i++]);
            if((j&1) != 0) min = Math.min(min, Seq[--j]);
        }

        return min;
    }
}
