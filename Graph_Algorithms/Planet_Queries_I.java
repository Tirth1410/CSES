import java.io.*;
import java.util.*;

public class Planet_Queries_I {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

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

            for(int i=1; i<30; i++) {
                for(int node=1; node<=n; node++) {
                    next[node][i] = next[next[node][i-1]][i-1];
                }
            }

            StringBuilder SB = new StringBuilder();
            while(q-- > 0) {
                int node = fr.nextInt();
                int d = fr.nextInt();

                for(int i=29; i>=0; i--) {
                    if(((1 << i) & d) != 0) node = next[node][i];
                }
                SB.append(node).append("\n");
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