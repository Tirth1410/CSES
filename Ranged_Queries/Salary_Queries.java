import java.io.*;
import java.util.*;
 
public class Salary_Queries {
    static Reader fr =  new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    
    static long [] list;
    static long [] Seq;
    static HashMap<Long, Integer> map;
    
    public static void Solve() throws IOException{
        // int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            int n=fr.nextInt();
            int q=fr.nextInt();
            
            list = new long [n+(q<<1)];
            int ptr=0;
            long [] arr = new long [n];
            for(int i=0; i<n; i++) {
                arr[i]=fr.nextLong();
                list[ptr++]=arr[i];
            }
            
            long [][] query=new long [q][3];
            for(int i=0; i<q; i++) {
                char ch=fr.nextChar();
                long a=fr.nextLong();
                long b=fr.nextLong();
                
                if(ch=='!') {
                    query[i][0]=0;
                    query[i][1]=a-1;
                    query[i][2]=b;
 
                    list[ptr++]=b;
                } else {
                    query[i][0]=1;
                    query[i][1]=a;
                    query[i][2]=b;
                    list[ptr++]=a;
                    list[ptr++]=b;
                }
            }
            
            Arrays.sort(list, 0, ptr);
            
            int cnt=0;
            long prev=0;
            map=new HashMap<>();
            for(int i=0; i<ptr; i++) {
                if(prev != list[i]) map.put(list[i], cnt++);
                prev = list[i];
            }
            
            BIT bt = new BIT(cnt);
            
            
            for(long ele : arr) {
                bt.update(map.get(ele), 1);
            }
            
            StringBuilder SB = new StringBuilder();
            for(long [] qr : query) {
                if(qr[0] == 0) {
                    bt.update(map.get(arr[(int)qr[1]]), -1);
                    arr[(int)qr[1]]=qr[2];
                    bt.update(map.get(arr[(int)qr[1]]), 1);
                } else {
                    int ans = bt.get(map.get(qr[1]), map.get(qr[2]));
                    
                    SB.append(ans).append('\n');
                }
            }
            
            out.println(SB);
        }
    }
    
    static int CustomCompare(long [] a, long [] b) {
        if(a[0] < b[0]) return -1;
        else if(a[0] > b[0]) return 1;
        return 1;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }
 
 
    //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
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
 
        public long nextLong() throws IOException {
            long ret = 0;
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
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
 
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
 
class BIT {
    int [] bit;
    int size;
    
    public BIT(int n) {
        this.size = n;
        bit=new int[n+1];
    }
    int sum(int r) {
        int res=0;
        for(; r>=0; r=(r&(r+1))-1) {
            res+=bit[r];
        }
        
        return res;
    }
    
    void update(int x, int delta) {
        for(; x<size; x|=x+1) {
            bit[x]+=delta;
        }
    }
    
    int get(int l, int r) {
        return sum(r)-sum(l-1);
    }
}