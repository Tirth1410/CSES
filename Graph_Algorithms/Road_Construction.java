import java.io.*;
import java.util.*;

public class Road_Construction {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    
    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            DSU dsu = new DSU(n);
            StringBuilder SB = new StringBuilder();
            for(int i=0; i<m; i++) {
                int u = fr.nextInt();
                int v = fr.nextInt();

                dsu.Join(u, v);
                SB.append(dsu.components).append(" ").append(dsu.maxSize).append("\n");
            }
            out.println(SB);
        }
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
