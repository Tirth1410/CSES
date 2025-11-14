package Graph_Algorithms;

import java.io.*;
import java.util.*;
 
public class Flight_Route {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static long maxL = Long.MAX_VALUE;
 
    public static void main (String[] args) throws java.lang.Exception
    {
 
        int n = fr.nextInt();
        int m= fr.nextInt();
        int k = fr.nextInt();
 
        ArrayList<int []> adj [] = new ArrayList[n+1];
        for(int i=0; i<m; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            int c = fr.nextInt();
            if(adj[a] == null) adj[a] = new ArrayList<>();
            adj[a].add(new int [] {b, c});
        }
 
        StringBuilder SB = new StringBuilder();
        long [][] dist = new long [n+1][k];
        for(long [] row : dist) Arrays.fill(row, maxL);
        adj[0] = new ArrayList<>();
        adj[0].add(new int [] {1, 0});
        PriorityQueue<long []> pq = new PriorityQueue<>((x, y) -> CustomCompare(x, y));
        pq.add(new long [] {0, 0});
 
        while(!pq.isEmpty()) {
            long [] curr = pq.poll();
            int node = (int)curr[0];
            long d = curr[1];
            if(dist[node][k-1] < d) continue;
            if(adj[node] == null) adj[node] = new ArrayList<>();
            boolean found = false;
            for(int [] ele : adj[node]) {
                if(dist[ele[0]][k-1] <= d + 0l + ele[1]) continue;
                long rep = d + 0l + ele[1];
                for(int i=0; i<k; i++) {
                    if(dist[ele[0]][i] <= rep) continue;
                    long temp = rep;
                    rep = dist[ele[0]][i];
                    dist[ele[0]][i] = temp;
                }
                pq.add(new long [] {ele[0], d + 0l + ele[1]});
            }
            if(found) break;
        }
        for(long ele : dist[n]) SB.append(ele + " ");
        out.println(SB);
        out.close();
    }
 
    static int CustomCompare(long [] a, long [] b) {
        if(a[1] <= b[1]) return -1;
        return 1;
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
}
