package CSES.String_Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Finding_Period {
    //    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static long ModMul(long a,long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }

    static long getHash(int l, int r, long [] pref, long [] base_pow) {
        long h = pref[r + 1] - (base_pow[r - l + 1] * pref[l] % MOD) % MOD;
        return h < 0 ? h + MOD : h;
    }

    public static void Solve() throws IOException {
//        int t = fr.nextInt();
        int t = 1;
        for(int tests=0; tests<t; tests++) {
            String s = br.readLine();
            int n = s.length();
            int [] Z = new int [n];
            int l=0;
            int r=0;
            for(int i=1; i<n; i++) {
                if(i <= r) {
                    Z[i] = min(Z[i-l], r-i);
                }
                while(i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i+Z[i])) Z[i]++;
                if(i+Z[i] > r) {
                    l = i;
                    r = i+Z[i]-1;
                }
            }

            for(int i=0; i<n; i++) {
//                System.out.println(i + Z[i]);
                if(i + Z[i] >= n) out.print(i + " ");
            }
            out.println(n);
        }
    }

    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
        Solve();
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
//    }
    static int MOD = (int)998244353;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}