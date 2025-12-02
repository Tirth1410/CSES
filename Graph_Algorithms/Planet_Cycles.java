import java.io.*;
import java.util.*;
 
public class Planet_Cycles {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
 
    static int dfs(int curr, int timer, int [] vis, int [][] next, int [] dist, int [] pathvis) {
//        out.println(curr + " " + timer);
        if(pathvis[curr] != 0) {
            dist[curr] = timer-pathvis[curr];
            return curr;
        }
        if(vis[curr] == 1) return 0;
        pathvis[curr] = timer;
        vis[curr]=1;
 
        int temp=dfs(next[curr][0], timer+1, vis, next, dist, pathvis);
        pathvis[curr] = 0;
        if(temp == 0) dist[curr] = dist[next[curr][0]]+1;
        else dist[curr] = dist[next[curr][0]];
        if(curr == temp) return 0;
        return temp;
    }
 
    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int [][] next = new int [n+1][31];
            for(int i=1; i<=n; i++) {
                next[i][0] = fr.nextInt();
            }
            int [] vis = new int [n+1];
            int [] pathvis = new int [n+1];
            int len [] = new int [n+1];
            for(int i=1; i<=n; i++) {
                if(vis[i] == 1) continue;
                dfs(i, 1, vis, next, len, pathvis);
            }
 
            StringBuilder SB = new StringBuilder();
 
            for(int i=1; i<=n; i++) {
                SB.append(len[i]).append(" ");
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