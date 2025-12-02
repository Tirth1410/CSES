import java.io.*;
import java.util.*;

public class Road_Preparation {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    
    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            List<long []> list = new ArrayList<>();
            for(int i=0; i<m; i++) {
                long u = fr.nextLong();
                long v = fr.nextLong();
                long wt = fr.nextLong();
                list.add(new long [] {u, v, wt});
            }
            Collections.sort(list, (x,y) -> CustomComparator(x, y));
            DSU dsu = new DSU(n);
            int max = 0;
            long ans = 0L;
            for(long [] e : list) {
                int Join = dsu.Join((int)e[0], (int)e[1]);
                if(Join != -1) {
                    ans += e[2];
                    max = max(max, Join);
                }
            }
            if(max == n) out.println(ans);
            else out.println("IMPOSSIBLE");
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
    public DSU(int n) {
        parent = new int [n+1];
        size = new int [n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
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
        return sz;
    }
}
