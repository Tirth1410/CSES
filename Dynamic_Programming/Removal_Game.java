package CSES.Dynamic_Programming;

import java.io.*;
import java.util.*;
public class Removal_Game {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void Solve() throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        for(int tests=0; tests<t; tests++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long [] Seq = new long [n];
            long [] prefix = new long [n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                Seq[i] = Long.parseLong(st.nextToken());
                prefix[i] = (i==0?0:prefix[i-1]) + Seq[i];
            }
            long [][] dp = new long [n][n];
            for(int l=n-1; l>=0; l--) {
                for(int r=0; r<n; r++) {
                    if(r < l) continue;
                    if(r == l) dp[r][l] = Seq[r];
                    else {
                        dp[l][r] = Math.max(Seq[l] + prefix[r]-prefix[l]-dp[l+1][r], Seq[r] + prefix[r-1] - (l==0?0:prefix[l-1])-dp[l][r-1]);
                    }
                }
            }

            out.println(dp[0][n-1]);
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
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in));}
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine()
        {
            String str = "";
            try {
                if(st.hasMoreTokens()){str = st.nextToken("\n");}
                else{str = br.readLine();}
            }
            catch (IOException e) {e.printStackTrace();}
            return str;
        }

    }
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
