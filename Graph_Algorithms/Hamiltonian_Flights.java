import java.io.*;
import java.util.*;

public class Hamiltonian_Flights {
    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static List<Integer> list;

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();

            ArrayList<Integer> adj [] = new ArrayList [n];
            int msk = 1 << n;
            for(int i=0; i<m; i++) {
                int u = fr.nextInt()-1;
                int v = fr.nextInt()-1;
                if(adj[u] == null) adj[u] = new ArrayList<>();
                adj[u].add(v);
            }

            long [][] dp = new long [n][msk];
            dp[n-1][msk-1] = 1L;

            for(int i=(1<<(n-1))-1; i>=1; i-=2) {
                for(int node=0; node<n; node++) {
                    if(((1 << node) & i) != 0) {
                        if(adj[node] == null) adj[node] = new ArrayList<>();
                        for(int ele : adj[node]) {
                            if(((1<<ele) & i) != 0) continue;
                            dp[node][i] += dp[ele][i | (1 << ele)];
                            if(dp[node][i] >= MOD) dp[node][i] -= MOD;
                        }
                    }
                }
            }

            out.println(dp[0][1] == 0 ? "IMPOSSIBLE" : dp[0][1]);
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }

    static class Reader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        // Reads the next integer from input
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }

        public char nextChar() throws IOException {
            char ch = '#';
            byte c = read();
            while(c <= ' ') {
                c = read();
            }
            return (char)c;
        }

        // Reads the next byte from the buffer
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        // Fills the buffer with new data
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }
    }
    static int max(int a, int b) {
        return Math.max(a, b);
    }
    static int MOD = (int)(1e9+7);
}