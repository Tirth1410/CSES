import java.io.*;
import java.util.*;
public class Prime_Multiples{
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    //    ---------------------------------------------MAIN CODE-------------------------------------------------
    public static void  Solve() {
        long n = fr.nextLong();
        int k = fr.nextInt();
        long [] Seq = new long [k];
        for(int i=0; i<k; i++) Seq[i]=fr.nextLong();
        long ans=0l;
        int pow = (int)Math.pow(2l, k);
        for(int i=0; i<pow; i++){
            boolean  curr = false;
            long prod = 1l;
            int counter = 0;
            for(int j=0; j<k; j++){
                if(((1l << j)&i) == 0) continue;
                counter++;

                if(prod * 1d * Seq[j] > n) {
                    curr=true;
                    break;
                }
                prod *= Seq[j]*1l;
            }
            if(counter==0 || curr) continue;
            if(counter%2==0){
                ans -= 1l*(n/prod);
            } else {
                ans += 1l*(n/prod);
            }
        }
        out.println(ans);
    }

    public static double LCM(double a, long b){
        return ((a * 1d * b) / GCD(a,b));
    }

    public static double GCD(double a, double b){
        if(a==0d) return b;
        return GCD(b%a, a);
    }
    //    -----------------------------x ^ y binary exponentiation-----------------------------------------
    static long BinaryExpo(long x, long y, int MOD) {
        if (y == 0) return 1;
        long temp = BinaryExpo(x, y / 2, MOD);
        temp = ModMul(temp, temp, MOD);
        if (y % 2 != 0) return ModMul(temp, x, MOD);
        return temp;
    }

    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b, long MOD){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long AddMod(long a,long b, int MOD){
        return ((a%MOD) + (b%MOD)) % MOD;
    }

    public static long SubMod(long a,long b, int MOD){
        return ((a%MOD) - (b%MOD) + MOD) % MOD;
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
    public static class FastReader {
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