import java.util.*;
import java.io.*;

public class Longest_Flight_Route {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static boolean dfs(int curr, ArrayList<Integer> [] adj, int [][] dp) {
        if(dp[curr][2] != -1) return dp[curr][2] == 0 ? false : true;

        if(curr == adj.length-1) {
            dp[curr][0] = 1;
            dp[curr][1] = -1;
            dp[curr][2] = 1;
            return true;
        }
        if(adj[curr] == null) adj[curr] = new ArrayList<>();
        for(int ele : adj[curr]) {
            boolean reach = dfs(ele, adj, dp);
            if(reach && dp[ele][0] + 1 > dp[curr][0]) {
                dp[curr][0] = dp[ele][0] + 1;
                dp[curr][1] = ele;
                dp[curr][2] = 1;
            }
        }
        if(dp[curr][2] == -1) dp[curr][2] = 0;
        return dp[curr][2] == 0 ? false : true;
    }
    public static void Solve() {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            int [][] dist = new int [n+1][3];
            ArrayList<Integer> [] adj = new ArrayList [n+1];
            for(int i=0; i<m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                if(adj[a] == null) adj[a] = new ArrayList<>();
                adj[a].add(b);
            }
            for(int [] row : dist) Arrays.fill(row, -1);
            dfs(1, adj, dist);

            out.println(dist[1][2] == 0 ? "IMPOSSIBLE" : dist[1][0]);
            if(dist[1][2] == 1) {
                int curr = 1;
                while(curr != -1) {
                    out.print(curr + " ");
                    curr = dist[curr][1];
                }
                out.println();
            }
        }
    }

    static long ModMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long BinaryExpo(long a, long pow) {
        long ans = 1;
        a %= MOD;
        while(pow > 0){
            if((pow & 1) == 1){
                ans = ans * a % MOD;
            }
            pow = pow >> 1;
            a = a * a % MOD;
        }
        return ans;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
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
    static int MOD = (int)998244353;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
