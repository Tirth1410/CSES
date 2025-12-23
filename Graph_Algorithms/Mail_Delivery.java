
import java.io.*;
import java.util.*;

public class Mail_Delivery {
    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);
 
    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n = fr.nextInt();
            int m = fr.nextInt();
            
            TreeSet<Integer> adj [] = new TreeSet[n+1];
            int [] deg = new int [n+1];
            
            for(int i=0; i<m; i++) {
                int u = fr.nextInt();
                int v=fr.nextInt();
                
                if(adj[u] == null) adj[u] = new TreeSet<>();
                if(adj[v] == null) adj[v] = new TreeSet<>();
                
                adj[u].add(v);
                adj[v].add(u);
                deg[u]++;
                deg[v]++;
            }
            
            boolean poss = true;
            for(int i=1; i<=n; i++) poss &= ((deg[i] & 1) == 0);
            
            if(!poss) out.println("IMPOSSIBLE");
            else {
                Stack<Integer> stack = new Stack<>();
                StringBuilder ans = new StringBuilder();
                stack.push(1);
                while(!stack.isEmpty()) {
                    int curr = stack.peek();
                    if(deg[curr] == 0) {
                        ans.append(curr).append(" ");
                        stack.pop();
                    } else {
                        int nb = 0;
                        for(int ele : adj[curr]) {
                            nb = ele;
                            break;
                        }
                        deg[curr]--;
                        deg[nb]--;
                        adj[curr].remove(nb);
                        adj[nb].remove(curr);
                        stack.push(nb);
                    }
                }
                
                
                for(int i=1; i<=n; i++) {
                    if(adj[i] == null) continue;
                    poss &= adj[i].size() == 0;
                }
                if(poss) out.println(ans);
                else out.println("IMPOSSIBLE");
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

