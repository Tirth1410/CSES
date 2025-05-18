package CSES.Mathematics;

import java.io.*;
import java.util.*;

public class Nim_Game_I {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    //    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void Solve(int index) throws IOException {
        long n = fr.nextInt();
        long XOR = 0l;
        for(int i=0; i<n; i++) {
            XOR ^= fr.nextLong();
        }

        if(XOR == 0) out.println("second");
        else out.println("first");
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
        int t = fr.nextInt();
//        int t = 1;

        for(int i=0; i<t; i++) {
            Solve(i);
        }
        out.close();
    }

    //    --------------------------------FOR TAKING FASTER INPUTS----------------------------------------
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {st = new StringTokenizer(br.readLine());}
                catch (IOException e) {e.printStackTrace();}
            }
            return st.nextToken();
        }
        int nextInt() {return Integer.parseInt(next());}
        long nextLong() {return Long.parseLong(next());}
        double nextDouble() {return Double.parseDouble(next());}
        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {str = st.nextToken("\n");}
                else {str = br.readLine();}
            } catch (IOException e) {e.printStackTrace();}
            return str;
        }
    }

    static long AddMul(long a, long b) {return (a % MOD) + (b % MOD) % MOD;}
    static long SubMod(long a, long b) {return ((a % MOD) - (b % MOD) + MOD) % MOD;}
    static long ModMul(long a, long b){return ((a % MOD) * (b % MOD)) % MOD;}
    static long BinaryExpo(long x, long y) {
        if (y == 0) return 1l;
        long temp = BinaryExpo(x, y / 2);
        temp = ModMul(temp, temp);
        if (y % 2 != 0) return ModMul(temp, x);
        return temp;
    }
    static int MOD = (int)1e9+7;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
}
