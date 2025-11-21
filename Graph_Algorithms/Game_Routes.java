import java.util.*;
import java.io.*;

public class Game_Routes {    
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static long dfs(int curr, ArrayList<Integer> [] adj, long [] ways) {
        if(ways[curr] != -1) return ways[curr];
        if(curr == adj.length-1) {
            ways[curr]= 1;
            return 1L;
        }

        ways[curr] = 0L;
        if(adj[curr] == null) adj[curr] = new ArrayList<>();
        for(int ele : adj[curr]) {
            ways[curr] += dfs(ele, adj, ways);
            if(ways[curr] >= MOD) ways[curr] -= MOD;
        }
        return ways[curr];
    }

    public static void Solve() {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            long [] ways = new long [n+1];
            ArrayList<Integer> [] adj = new ArrayList<>[n+1];
            for(int i=0; i<m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                if(adj[a] == null) adj[a] = new ArrayList<>();
                adj[a].add(b);
            }

            Arrays.fill(ways, -1);
            dfs(1, adj, ways);

            out.println(ways[1]);
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
    static int MOD = (int)1e9+7;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
