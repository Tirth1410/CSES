import java.io.*;
import java.util.*;

public class Knight_Tour {
    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);

    static int [] nr = new int [] {-1, -2, -2, -1, 1, 2, 2, 1};
    static int [] nc = new int [] {-2, -1, 1, 2, 2, 1, -1, -2};
    static int [][] vis;

    static boolean isValid(int r, int c) {
        if(r>=0 && c>=0 && r<8 && c<8 && vis[r][c] == 0) return true;
        return false;
    }

    static int GetDeg(int x, int y) {
        if(vis[x][y] != 0) return 0;
        int deg=0;
        for(int i=0; i<8; i++) {
            int r = x+nr[i];
            int c = y+nc[i];

            if(isValid(r, c)) {
                deg++;
            }
        }
        return deg;
    }

    static boolean dfs(int cx, int cy, int turn) {
        vis[cx][cy]=turn;
        if(turn == 64) return true;

        List<int []> list = new ArrayList<>();
        for(int i=0; i<8; i++) {
            int r=nr[i]+cx;
            int c=nc[i]+cy;

            if(isValid(r, c)) {
                list.add(new int [] {r, c, GetDeg(r, c)});
            }
        }

        Collections.sort(list, (x, y) -> x[2]-y[2]);
        for(int [] ele : list) {
            if(dfs(ele[0], ele[1], turn+1)) return true;
        }
        vis[cx][cy]=0;
        return false;
    }
    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            vis = new int [8][8];
            
            int x = fr.nextInt()-1;
            int y = fr.nextInt()-1;


            dfs(y, x, 1);
            for(int i=0;i<8; i++) {
                for(int j=0; j<8; j++) {
                    out.print(vis[i][j]+" ");
                }
                out.println();
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
    static int MOD = (int)(1e9+7);
}