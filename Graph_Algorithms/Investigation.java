import java.io.*;
import java.util.*;

public class Investigation {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            long [][] dist = new long [n+1][4];
            ArrayList<int []> [] adj = new ArrayList [n+1];
            for(int i=0; i<m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                int c = fr.nextInt();
                if(adj[a] == null) adj[a] = new ArrayList<>();
                adj[a].add(new int [] {b, c});
            }

            for(int i=1; i<=n; i++) {
                dist[i][0] = maxL;
                dist[i][1] = maxL;
                dist[i][2] = 0;
                dist[i][3] = 0;
            }
            PriorityQueue<long []> pq = new PriorityQueue<>((x, y) -> CustomCompare(x, y));
            pq.add(new long [] {1, 0, 0, 0, 1});
            dist[1][0] = 0;
            dist[1][1] = 0;
            dist[1][2] = 0;
            dist[1][3] = 1;
            while(!pq.isEmpty()) {
                int node = (int)pq.peek()[0];
                long minP = pq.peek()[1];
                long minN = pq.peek()[2];
                long maxN = pq.peek()[3];
                long ways = pq.peek()[4];
                pq.poll();

                if(dist[node][0] < minP) continue;

                if(adj[node] == null) adj[node] = new ArrayList<>();
                for(int [] ele : adj[node]) {
                    int nb = ele[0];
                    long w = ele[1];
                    if(dist[nb][0] > dist[node][0] + w) {
                        dist[nb][0] = dist[node][0] + w;
                        dist[nb][1] = dist[node][1]+1;
                        dist[nb][2] = dist[node][2]+1;
                        dist[nb][3] = dist[node][3];
                        pq.add(new long [] {nb, dist[node][0] + w, dist[node][1]+1, dist[node][2]+1, dist[node][3]});
                    } else if(dist[nb][0] == dist[node][0] + w) {
                        dist[nb][3] += dist[node][3];
                        if(dist[nb][3] >= MOD) dist[nb][3] -= MOD;
                        dist[nb][1] = min(dist[nb][1], dist[node][1]+1);
                        dist[nb][2] = max(dist[nb][2], dist[node][2]+1);
                    }
                }
            }

            out.println(dist[n][0] + " " + dist[n][3] + " " + dist[n][1] + " " + dist[n][2]);
        }
    }

    static int CustomCompare(long [] a, long [] b) {
        if(a[1] <= b[1]) return -1;
        return 1;
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