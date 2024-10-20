package CSES.Mathematics;

import java.io.*;
import java.util.*;
public class Creating_Strings_II {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static long [] fact;

    public static void Factorial(int n){
        fact = new long [n+1];

        fact[0] = 1l;
        fact[1] = 1l;
        for(int i=2; i<=n; i++){
            fact[i] = ModMul(fact[i-1], i);
        }
    }
    public static void Solve() throws IOException {
        String s = fr.next();
        int [] freq = new int [26];
        for(char ch : s.toCharArray()) freq[ch-'a']++;

        long ans = fact[s.length()];
        for(int ele : freq){
            if(ele == 0) continue;
            ans = ModMul(ans, BinaryExpo(fact[ele], MOD-2l));
        }
        out.println(ans);
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
        Factorial((int)1e6);
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
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in));}
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine()
        {
            String str = "";
            try {
                if(st.hasMoreTokens()){str = st.nextToken("\n");}
                else{str = br.readLine();}
            }
            catch (IOException e) {e.printStackTrace();}
            return str;
        }

    }
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
