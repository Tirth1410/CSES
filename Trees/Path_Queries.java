package CSES.Trees;
import java.io.*;
import java.util.*;
public class Path_Queries {
    static Reader r = new Reader();
    //    static PrintWriter out = new PrintWriter(System.out);
    static int timer;
    static List<Integer> [] adj;
    static long [] Seq;
    static int [] index;
    static int [] end;
    static int [] subSize;
    public static void getSubtreeSize(int [] inDeg) {
        Queue<Integer> q = new LinkedList<>();
        for(int i=2; i<inDeg.length; i++) {
            if(inDeg[i] == 1) q.add(i);
        }
        subSize = new int [inDeg.length];
        while (!q.isEmpty()) {
            int curr = q.poll();
            subSize[curr]++;
            inDeg[curr]=-1;
            for(int ele : adj[curr]) {
                if(ele == 1 || inDeg[ele] != -1) {
                    subSize[ele] += subSize[curr];
                    inDeg[ele]--;
                    if((inDeg[ele] == 1 && ele != 1) || (inDeg[ele]==0 && ele==1)) q.add(ele);
                }
            }
//            System.out.println(Arrays.toString(subSize));
        }
//        System.out.println(Arrays.toString(subSize));
    }
    public static void EularTour(int [] inDeg) {
        getSubtreeSize(inDeg);
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        int [] done = new int [inDeg.length];
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            while (Seq[timer] != 0) timer++;

            Seq[timer] = Seq[curr];
            index[curr]=timer;
            Seq[timer + ((subSize[curr]-1) * 2) + 1] = -1l * Seq[curr];
            end[curr] = timer + ((subSize[curr]-1) * 2) + 1;
            for(int ele : adj[curr]) {
                if(index[ele] == 0) stack.add(ele);
            }
        }
    }

    static void Update(long [] Seq, int index, long val) {
        Seq[index] = val;
        for(int i=0; index!=0;i++) {
            Seq[index>>1] = Seq[index] + Seq[index^1];
            index >>= 1;
        }
    }

    static long Query(long [] Seq, int n, int index) {
        int s = n;
        int e = index;

        long ans = 0l;
        for(int i=s, j=e; i<j; i>>=1, j>>=1) {
            if((i & 1) != 0) ans += Seq[i++];
            if((j & 1) != 0) ans += Seq[--j];
        }
        return ans;
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
        int n = r.nextInt();
        int q = r.nextInt();
        adj = new ArrayList[n+1];
        Seq = new long[(4*n)+5];
        index = new int [n+1];
        end = new int [n+1];
        timer = 2*n;
        for(int i=1; i<=n; i++) {
            Seq[i] = r.nextInt();
        }
        adj[0] = new ArrayList<>();
        int [] inDeg = new int [n+1];
        for(int i=0; i<n-1; i++) {
            int u = r.nextInt();
            int v = r.nextInt();
            inDeg[u]++;
            inDeg[v]++;
            if(adj[u] == null) adj[u] = new ArrayList<>();
            if(adj[v] == null) adj[v] = new ArrayList<>();
            adj[u].add(v);
            adj[v].add(u);
        }
        EularTour(inDeg);

        List<long []> queries = new ArrayList<>();
        for(int i=0; i<q; i++) {
            int c = r.nextInt();
            int node = r.nextInt();

            if(c == 2) queries.add(new long [] {c, node});
            else {
                long value = r.nextInt();
                queries.add(new long [] {c, node, value});
            }
        }
        for(int i=1; i<=n; i++) {
            Seq[index[i]] = Seq[i];
            Seq[end[i]] = -1L * Seq[i];
        }
        for(int i=(2*n)-1; i>0; i--) {
            Seq[i] = Seq[i<<1] + Seq[(i<<1)|1];
        }
        StringBuilder SB = new StringBuilder();
        int temp = 2 * n;
        for(int i=0; i<q; i++) {
            if(queries.get(i)[0] == 1) {
                long value = (int)queries.get(i)[2];
                Update(Seq, index[(int)queries.get(i)[1]], value);
                Update(Seq, end[(int)queries.get(i)[1]], -1l * value);
            } else {
                int node = (int)queries.get(i)[1];
                SB.append(Query(Seq, temp, index[node]+1));
                SB.append("\n");
            }
        }
        System.out.println(SB.toString());
//        out.close();
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
