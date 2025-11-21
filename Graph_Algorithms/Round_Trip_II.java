import java.io.*;
import java.util.*;

public class Round_Trip_II {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static boolean dfs(int curr, int par, ArrayList<Integer> [] adj, int [] vis, int [] pathvis, Stack<Integer> stack) {
        vis[curr] = 1;
        pathvis[curr] = 1;
        if(adj[curr] == null) adj[curr] = new ArrayList<>();

        for(int ele : adj[curr]) {
            if(pathvis[ele] == 1) {
                Stack<Integer> stack2 = new Stack<>();
                stack2.push(ele);
                while(!stack.isEmpty() && stack.peek() != ele) {
                    stack2.push(stack.pop());
                }
                stack2.push(ele);
                out.println(stack2.size());
                while (!stack2.isEmpty()) out.print(stack2.pop() + " ");
                out.println();
                return true;
            }
            if(vis[ele] == 1) continue;
            stack.push(ele);
            if(dfs(ele, curr, adj, vis, pathvis, stack)) return true;
            stack.pop();
        }
        pathvis[curr]=0;
        return false;
    }
    public static void Solve() {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            ArrayList<Integer> [] adj = new ArrayList [n+1];

            for(int i=0; i<m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                if(adj[a] == null) adj[a] = new ArrayList<>();
                adj[a].add(b);
            }
            int vis [] = new int [n+1];
            int [] pathvis = new int [n+1];
            boolean cycle = false;
            for(int i=1; i<=n; i++) {
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                cycle |= dfs(i, 0, adj, vis, pathvis, stack);
                if(cycle) break;
            }

            if(!cycle) out.println("IMPOSSIBLE");
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