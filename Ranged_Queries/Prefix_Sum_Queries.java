import java.io.*;
import java.util.*;

public class Prefix_Sum_Queries {
    static Reader fr = new Reader();
    static PrintWriter out = new PrintWriter(System.out);

    static long[][] Seg;
    static int n;
    static int len;

    public static void Solve() throws IOException {
        // int test = fr.nextInt();
        int test = 1;
        for (int tests = 0; tests < test; tests++) {
            n = fr.nextInt();
            int q = fr.nextInt();

            len = n << 1;

            int[] arr = new int[n];
            Seg = new long[(n << 1) + 5][4];

            for (int i = 0; i < n; i++) {
                arr[i] = fr.nextInt();
                Seg[i][1] = -1;
                Seg[i][2] = -1;
                Seg[i + n][0] = arr[i] + 0L + Seg[i + n - 1][0];
                Seg[i + n][1] = i + n;
                Seg[i + n][2] = i + n;
                Seg[i + n][3] = 0L;
            }

            Build();
            StringBuilder SB = new StringBuilder();
            for (int i = 0; i < q; i++) {
                int a = fr.nextInt();
                if (a == 1) {
                    int id = fr.nextInt() - 1;
                    int val = fr.nextInt();
                    Update(id, val - arr[id]);
                    arr[id] = val;
                } else {
                    int l = fr.nextInt() - 1;
                    int r = fr.nextInt() - 1;
                    SB.append(max(Query(l, r) - Query(l - 1, l - 1), 0L)).append('\n');
                }
            }

            out.println(SB.toString());
        }
    }

    static List<Integer> indices;

    static void Build() {
        for (int i = n - 1; i > 0; i--) {
            long[] l = Seg[i << 1];
            long[] r = Seg[(i << 1) | 1];

            if (l[1] == -1 || r[1] == -1 || l[2] + 1 != r[1])
                continue;
            Seg[i][0] = max(l[0], r[0]);
            Seg[i][1] = min(l[1], r[1]);
            Seg[i][2] = max(l[2], r[2]);
        }

        indices = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            if (Seg[i][1] == -1)
                continue;
            if (Seg[i >> 1][1] == -1)
                indices.add(i);
        }
    }

    static void Update(int idx, long delta) {
        idx += n;
        for (int ind : indices) {
            UpdateRange(ind, idx, len - 1, delta);
        }
    }

    static void UpdateRange(int index, int l, int r, long delta) {

        if (Seg[index][2] < l || Seg[index][1] > r)
            return;

        if (l <= Seg[index][1] && Seg[index][2] <= r) {
            Seg[index][3] += delta;
            Seg[index][0] += delta;
            return;
        }

        if (index < n) {
            UpdateRange(index << 1, l, r, delta);
            UpdateRange((index << 1) | 1, l, r, delta);
            long case1 = Seg[index << 1][0];
            long case2 = Seg[(index << 1) | 1][0];
            long mx = max(case1, case2);
            Seg[index][0] = mx + Seg[index][3];
        }
    }

    static long Query(int l, int r) {
        if (l < 0)
            return 0L;
        l += n;
        r += n;
        long curr = minL;
        for (int ind : indices) {
            curr = max(curr, QueryRange(ind, l, r));
        }
        return curr;
    }

    static long QueryRange(int index, int l, int r) {
        if (Seg[index][2] < l || Seg[index][1] > r)
            return minL;

        if (l <= Seg[index][1] && Seg[index][2] <= r) {
            return Seg[index][0];
        } else {
            long max = minL;
            if (index < n) {
                Seg[index << 1][0] += Seg[index][3];
                Seg[index << 1][3] += Seg[index][3];
                Seg[(index << 1) | 1][0] += Seg[index][3];
                Seg[(index << 1) | 1][3] += Seg[index][3];
                Seg[index][3] = 0L;

                max = max(max, QueryRange(index << 1, l, r));
                max = max(max, QueryRange((index << 1) | 1, l, r));
            }
            return max;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        Solve();
        out.close();
    }

    // --------------------------------FOR TAKING FASTER
    // INPUTS----------------------------------------
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
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }

        // Reads the next long from input
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
        }

        // Reads the next byte from the buffer
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        // Fills the buffer with new data
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
    }

    static int MOD = (int) 998244353;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    public static long min(long a, long b) {
        return Math.min(a, b);
    }
}