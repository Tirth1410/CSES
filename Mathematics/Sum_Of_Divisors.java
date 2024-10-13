import java.io.*;
import java.util.*;
public class Sum_Of_Divisors {
    public static long sum(long a, long b, long inv2){
        long one = ModMul(b, b+1l);
        one = ModMul(one, inv2);
        long two = ModMul(a, a+1l);
        two = ModMul(two, inv2);
        return ((one%MOD)-(two%MOD)+MOD) % MOD;
    }
    //    ---------------------------------------------MAIN CODE-------------------------------------------------
    public static void  Solve(FastReader fr, PrintWriter out) {
        long n = fr.nextLong();
        long ans=0l;
        long curr=1l;
        long inv2 = BinaryExpo(2l, MOD-2l);
        while (curr <= n){
            long fact = n/curr;
            long till = n/fact;
            long s = sum(curr-1l, till, inv2);
            curr=till+1l;
            ans += ModMul(s, fact);
            ans %= MOD;
        }
        out.println(ans);
    }
 
    //    -----------------------------x ^ y binary exponentiation-----------------------------------------
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
        FastReader fr = new FastReader();
//        int t = fr.nextInt();
        int t = 1;
        PrintWriter out = new PrintWriter(System.out);
        //        long SqxtasrtTime = System.currentTimeMillis();
        while (t-- > 0) {
            Solve(fr, out);
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
