package CSES.Dynamic_Programming;

import java.io.*;
import java.util.*;


public class Coin_Combination_I {
    //    static Reader fr = new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //    ---------------------------------------GCD OF TWO NUMBERS-----------------------------------------------

    public static void Solve() throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        for(int test = 0; test<t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int sum = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int [] coins = new int[n];
            for(int i=0; i<n; i++) coins[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(coins);
            long [] dp = new long [sum+1];
            dp[0]=1l;
            for(int i=1; i<=sum; i++) {
                for(int j=0; j<n; j++) {
                    if(i-coins[j] < 0) break;
                    dp[i] += dp[i-coins[j]];
                    if(dp[i] >= MOD) dp[i] -= MOD;
                }
            }
            out.println(dp[sum]);
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
