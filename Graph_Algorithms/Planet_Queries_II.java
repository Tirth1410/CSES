import java.io.*;
import java.util.*;

public class Planet_Queries_II {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static void dfs(int curr, int [] vis, int [][] next, int [] dist) {
        if(vis[curr] == 1) return;
        vis[curr]=1;
        dfs(next[curr][0], vis, next, dist);
        dist[curr] = dist[next[curr][0]]+1;
    }

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int q = fr.nextInt();
            int [][] next = new int [n+1][31];
            for(int i=1; i<=n; i++) {
                next[i][0] = fr.nextInt();
            }

            for(int i=1; i<20; i++) {
                for(int node=1; node<=n; node++) {
                    next[node][i] = next[next[node][i-1]][i-1];
                }
            }

            int [] vis = new int [n+1];
            int len [] = new int [n+1];
            for(int i=1; i<=n; i++) {
                if(vis[i] == 1) continue;
                dfs(i, vis, next, len);
            }
            StringBuilder SB = new StringBuilder();
            while(q-- > 0) {
                int a = fr.nextInt();
                int b = fr.nextInt();

                int d = len[a] - len[b];
                int ent = Jump(a, len[a], next);
                if(Jump(a, d, next) == b) {
                    SB.append(d).append("\n");
                } else if(Jump(ent, len[ent]-len[b], next) == b) {
                    SB.append(len[a]+len[ent]-len[b]).append("\n");
                } else SB.append("-1\n");
            }
            out.println(SB);
        }
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
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
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
}