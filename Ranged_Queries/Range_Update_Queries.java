package CSES.Ranged_Queries;


import java.io.*;
import java.util.*;

public class Range_Update_Queries {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void Solve() throws IOException {
        int n = fr.nextInt();
        int q = fr.nextInt();

        long [] Seq = new long [2*n+10];

        for(int i=0; i<n; i++) Seq[i+n] = fr.nextLong();
        build(Seq, n);
        while (q-- > 0) {
            int temp = fr.nextInt();
            if(temp == 2) {
                int index = fr.nextInt()-1;
                out.println(query(Seq, n, index)) ;
            } else {
                int l = fr.nextInt()-1;
                int r = fr.nextInt()-1;
                long x = fr.nextInt();

                update(Seq, n, l, r+1, x);
            }
        }
    }

    static void build(long [] Seq, int n) {
        for(int i=n-1; i>=0; i--) {
            Seq[i] = 0l;
        }
    }

    static long query(long [] Seq, int n, int idx) {
        long curr = 0;
        idx+=n;
        while (idx > 0) {
            curr += Seq[idx];
            idx >>= 1;
        }
        curr += Seq[idx];

        return curr;
    }

    static void update(long [] Seq, int n, int l, int r, long addon) {
        for(int i=l+n, j=r+n; i<j; i>>=1, j>>=1) {
            if((i&1) != 0) Seq[i++] += addon;
            if((j&1) != 0) Seq[--j] += addon;
        }
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

    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long ModMul(long a, long b, long MOD){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static int MOD = (int) 1e9 + 7;
    static int MOD2 = (int) 1e9 + 9;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;

    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
