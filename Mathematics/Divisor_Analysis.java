import java.io.*;
import java.util.*;
public class Divisor_Analysis{
    //    ---------------------------------------------MAIN CODE-------------------------------------------------
    public static void  Solve(FastReader fr, PrintWriter out) {
        int n = fr.nextInt();
        int [] z  = new int [n];
        int [] k = new int [n];
        long num = 1l;
 
        long inv2 = BinaryExpo(2l, MOD-3l, MOD-1);
        for(int i=0; i<n; i++){
            z[i]=fr.nextInt();
            k[i]=fr.nextInt();
            num = ModMul(num, k[i]+1l, MOD);
        }
 
        long Sum=1l, Prod=1l;
        long c=1l;
        int idx=-1;
        for(int i=0; i<n; i++){
            long Num = SubMod(BinaryExpo(z[i], k[i]+1l, MOD), 1l, MOD);
            long Denum = SubMod(z[i], 1l, MOD);
            long curr = ModMul(Num, BinaryExpo(Denum, MOD-2l, MOD), MOD);
            Sum = ModMul(Sum, curr, MOD);
            if((k[i]&1) != 0 && idx==-1){
                idx = i;
                c = ModMul(c, (k[i]+1l)/2l, MOD-1l);
            } else {
                c = ModMul(c, k[i]+1l, MOD-1l);
            }
        }
 
        if(idx!=-1){
            for(int i=0; i<n; i++){
                long bc = ModMul(k[i], c, MOD-1);
                Prod = ModMul(Prod, BinaryExpo(z[i], bc, MOD), MOD);
            }
        } else {
            for(int i=0; i<n; i++){
                long bc = ModMul(k[i]/2l, c, MOD-1);
                Prod = ModMul(Prod, BinaryExpo(z[i], bc, MOD), MOD);
            }
        }
 
        out.println(num + " " + Sum + " " + Prod);
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