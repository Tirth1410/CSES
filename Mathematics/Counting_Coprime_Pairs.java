package CSES.Mathematics;

import java.io.*;
import java.util.*;
public class Counting_Coprime_Pairs {
    //    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }

    static int CustomCompare(long a, long b){
        if(a <= b) return -1;
        return 1;
    }

    static long BinaryExpo(long x, long y) {
        if (y == 0) return 1;
        long temp = BinaryExpo(x, y / 2);
        temp = ModMul(temp, temp);
        if (y % 2 != 0) return ModMul(temp, x);
        return temp;
    }
    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        //        long SqxtasrtTime = System.currentTimeMillis();
        while (t-- > 0) {
            Solve();
        }
        //        long endTime = System.currentTimeMillis();
        //        System.out.println("It took " + (endTime - StartTime) + " milliseconds");
        out.close();
    }

    //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
//    static class FastReader {
//        BufferedReader br;
//        StringTokenizer st;
//        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in));}
//        String next()
//        {
//            while (st == null || !st.hasMoreElements()) {
//                try { st = new StringTokenizer(br.readLine()); }
//                catch (IOException e) { e.printStackTrace(); }
//            }
//            return st.nextToken();
//        }
//        int nextInt() { return Integer.parseInt(next()); }
//        long nextLong() { return Long.parseLong(next()); }
//        double nextDouble() { return Double.parseDouble(next()); }
//        String nextLine()
//        {
//            String str = "";
//            try {
//                if(st.hasMoreTokens()){str = st.nextToken("\n");}
//                else{str = br.readLine();}
//            }
//            catch (IOException e) {e.printStackTrace();}
//            return str;
//        }
//
//    }
    static int MOD = (int)(1e9)+7;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}