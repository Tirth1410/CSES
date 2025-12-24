import java.util.*;
import java.io.*;

public class De_Bruijn_Sequence {
    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static List<Integer> list;

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            if(n == 1) {
                out.println("10");
                continue;
            }

            ArrayDeque<Integer> adj [] = new ArrayDeque [(1 << (n-1))];
            int nd = 1 << (n-1);
            for(int i=0; i<nd; i++) {
                if(adj[i] == null) adj[i] = new ArrayDeque<>();
                int x = (i << 1) & ((1 << (n-1))-1);
                int y = ((i << 1) + 1) & ((1 << (n-1))-1);
                adj[i].add(x);
                adj[i].add(y);
            }

            Stack<Integer> st = new Stack<>();
            st.add(0);
            StringBuilder SB = new StringBuilder();
            for(int i=0; i<n-1; i++) SB.append("0");
            List<Integer> list = new ArrayList<>();
            while(!st.isEmpty()) {
                int curr = st.peek();
                if(adj[curr].size() == 0) {
                    st.pop();
                    list.add(curr);
                } else {
                    int nb = adj[curr].pollFirst();
                    st.add(nb);
                }
            }
            for(int i=list.size()-2; i>=0; i--) {
                SB.append((list.get(i) & 1));
            }

            out.println(SB);
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