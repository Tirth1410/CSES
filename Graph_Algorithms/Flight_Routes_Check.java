import java.io.*;
import java.util.*;

public class Flight_Routes_Check {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static Stack<Integer> stack;
    static void dfs(int curr, int [] vis, ArrayList<Integer> [] adj) {
        if(vis[curr] == 1) return;

        vis[curr] = 1;
        if(adj[curr] == null) adj[curr] = new ArrayList<>();

        for(int ele : adj[curr]) {
            if(vis[ele] == 1) continue;
            dfs(ele, vis, adj);
        }
        stack.add(curr);
    }

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();
            stack = new Stack<>();
            ArrayList<Integer> [] inv = new ArrayList[n+1];
            ArrayList<Integer> [] adj = new ArrayList[n+1];
            for(int i=0; i<m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                if(adj[a] == null) adj[a] = new ArrayList<>();
                if(inv[b] == null) inv[b] = new ArrayList<>();
                adj[a].add(b);
                inv[b].add(a);
            }

            int [] vis = new int [n+1];
            for(int i=1; i<=n; i++) {
                if(vis[i] == 1) continue;
                dfs(i, vis, adj);
            }

            boolean flag = true;
            int a=0, b=0;
            int prev = 0;
            vis = new int [n+1];

            while(!stack.isEmpty()) {
                int curr = stack.pop();
                if(vis[curr] == 1) continue;
                if(prev != 0) {
                    flag = false;
                    a = curr;
                    b = prev;
                    break;
                }
                prev = curr;
                dfs(curr, vis, inv);
            }

            if(flag) out.println("YES");
            else {
                out.println("NO");
                out.println(a + " " + b);
            }
        }
    }

    static int CustomComparator(long [] a, long [] b) {
        if(a[2] <= b[2]) return -1;
        return 1;
    }

    static int Jump(int node, int k, int [][] next) {
        if(k < 0) return -1;
        int ptr=0;
        while(k > 0) {
            if((k&1) != 0) {
                node = next[node][ptr];
            }
            ptr++;
            k >>= 1;
        }

        return node;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }

    static class FastReader {

        // BufferedReader to read input
        BufferedReader b;

        // StringTokenizer to tokenize input
        StringTokenizer s;

        // Constructor to initialize BufferedReader
        public FastReader() {
            b = new BufferedReader(new InputStreamReader(System.in));
        }

        // Method to read the next token as a string
        String next() {
            while (s == null || !s.hasMoreElements()) {
                try {
                    s = new StringTokenizer(b.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s.nextToken();
        }

        // Method to read the next token as an integer
        int nextInt() {
            return Integer.parseInt(next());
        }

        // Method to read the next token as a long
        long nextLong() {
            return Long.parseLong(next());
        }

        // Method to read the next token as a double
        double nextDouble() {
            return Double.parseDouble(next());
        }

        // Method to read the next line as a string
        String nextLine() {
            String str = "";
            try {
                if (s.hasMoreTokens()) {
                    str = s.nextToken("\n");
                } else {
                    str = b.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static int max(int a, int b) {
        return Math.max(a, b);
    }
}

class DSU {
    int [] parent;
    int [] size;
    int components;
    int maxSize;
    public DSU(int n) {
        parent = new int [n+1];
        size = new int [n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        maxSize=1;
        components=n;
    }

    public int FindP(int x) {
        if(parent[x] == x) return x;
        parent[x] = FindP(parent[x]);
        return parent[x];
    }

    public int Join(int a, int b) {
        int pa = FindP(a);
        int pb = FindP(b);

        if(pa == pb) return -1;
        components--;
        int sz=0;
        if(size[pa] > size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb];
            sz = size[pa];
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
            sz = size[pb];
        }
        maxSize = Math.max(maxSize, sz);
        return sz;
    }
}