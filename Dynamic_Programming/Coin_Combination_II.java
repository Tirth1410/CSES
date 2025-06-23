package CSES.Dynamic_Programming;

import java.io.*;
import java.util.*;


public class Coin_Combination_II {
    //    static Reader fr = new Reader();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //    ---------------------------------------GCD OF TWO NUMBERS-----------------------------------------------

    public static void Solve() throws IOException {
//        int t = fr.nextInt();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = 1;
        for(int test = 0; test<t; test++) {
            int n = Integer.parseInt(st.nextToken());
            int sum = Integer.parseInt(st.nextToken());
            int [] coins = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) coins[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(coins);
            int prev [] = new int [sum+1];
            prev[0]=1;
            for(int i=0; i<n; i++) {
                for(int j=1; j<=sum; j++) {
                    if(coins[i] > j) continue;
                    prev[j] = (prev[j]+prev[j-coins[i]])%MOD;
                }
            }
            out.println(prev[sum]);
        }
    }


    //    public static List<Integer> GivePrime(int n) {
//        boolean flag [] = new boolean[n+1];
//        for(int i=2; i*i <= n; i++) {
//            if(flag[i]) continue;
//            for(int j=2*i; j<=n; j+=i) flag[j] = true;
//        }
//        int counter=0;
//        List<Integer> p = new ArrayList<>();
//        for(int i=2; i<=n; i++) {
//            if(flag[i]) continue;
//            p.add(i);
//        }
//        return p;
//    }
    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        for(int i=0; i<t; i++) {
            Solve();
        }
        out.close();
    }

    //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
//    static class FastReader {
//        BufferedReader br;
//        StringTokenizer st;
//        public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}
//        String next() {
//            while (st == null || !st.hasMoreElements()) {
//                try {st = new StringTokenizer(br.readLine());}
//                catch (IOException e) {e.printStackTrace();}
//            }
//            return st.nextToken();
//        }
//        int nextInt() {return Integer.parseInt(next());}
//        long nextLong() {return Long.parseLong(next());}
//        double nextDouble() {return Double.parseDouble(next());}
//        String nextLine() {
//            String str = "";
//            try {
//                if (st.hasMoreTokens()) {str = st.nextToken("\n");}
//                else {str = br.readLine();}
//            } catch (IOException e) {e.printStackTrace();}
//            return str;
//        }
//    }

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




    static int MOD = (int)1e9+7;
}