import java.io.*;
import java.util.*;

public class Teleporters_Path {

    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static List<Integer> list;

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();
            ArrayDeque<Integer> adj [] = new ArrayDeque [n+1];
            int [] inDeg = new int [n+1];
            int [] outDeg = new int [n+1];
            for(int i=0; i<m; i++) {
                int u = fr.nextInt();
                int v = fr.nextInt();
                if(adj[u] == null) adj[u] = new ArrayDeque<>();
                adj[u].add(v);
                inDeg[v]++;
                outDeg[u]++;
            }

            boolean poss = true;
            inDeg[1]++;
            outDeg[n]++;
            for(int i=1; i<=n; i++) {
                poss &= (inDeg[i] == outDeg[i]);
            }

            if(!poss) {
                out.println("IMPOSSIBLE");
                continue;
            }

            Stack<Integer> st = new Stack<>();
            st.add(1);

            List<Integer> list = new ArrayList<>();
            while(!st.isEmpty()) {
                int curr = st.peek();
                if(adj[curr] == null) adj[curr] = new ArrayDeque<>();
                if(adj[curr].size() == 0) {
                    st.pop();
                    list.add(curr);
                } else {
                    int nb = adj[curr].pollFirst();
                    st.add(nb);
                }
            }

            for(int i=1; i<=n; i++) {
                if(adj[i] == null) continue;
                poss &= adj[i].size() == 0;
            }

            if(!poss) out.println("IMPOSSIBLE");
            else {
                for(int i=list.size()-1; i>=0; i--) out.print(list.get(i) + " ");
            }
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
}