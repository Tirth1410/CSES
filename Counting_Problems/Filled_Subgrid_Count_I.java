package CSES.Counting_Problems;
import java.math.*;
import java.io.*;
import java.util.*;

public class Filled_Subgrid_Count_I {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args []) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String [] grid = new String[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            grid[i] = st.nextToken();
        }
        long [][] dp = new long[n][n];
        long [] ans = new long [k];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                    ans[grid[i].charAt(j)-'A'] += dp[i][j];
                }
                else {
                    long case1 = grid[i-1].charAt(j-1) == grid[i].charAt(j) ? dp[i-1][j-1] : 0;
                    long case2 = Math.min(grid[i-1].charAt(j) == grid[i].charAt(j) ? dp[i-1][j] : 0, grid[i].charAt(j-1) == grid[i].charAt(j) ? dp[i][j-1] : 0);
                    dp[i][j] = Math.min(case1, case2)+1;
                    ans[grid[i].charAt(j)-'A'] += dp[i][j];
                }
            }
        }
        for(long ele : ans) out.println(ele);
//        out.println(ans);
        out.close();
    }

    static int [][][] prefixSum(String [] grid, int n, int k) {
        int [][][] prefix = new int [n][n][k];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for(int x=0; x<k; x++) {
                    int case1 = (i-1>=0) ? prefix[i-1][j][x] : 0;
                    int case2 = (j-1>=0) ? prefix[i][j-1][x] : 0;
                    int case3 = (i-1>=0 && j-1>=0) ? prefix[i-1][j-1][x] : 0;
                    prefix[i][j][x] = case1 + case2 - case3;
                    if(grid[i].charAt(j)-'A'==x) prefix[i][j][x]++;
                }
            }
        }
        return prefix;
    }
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

