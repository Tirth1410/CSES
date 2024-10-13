import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class Exponentiation_II {
    static int MOD = (int)1e9+7;
    public static long ModMul(long a, long b, long MOD) {
        return ((a%MOD) * (b%MOD)) % MOD;
    }
    public static long BinExpo(long a, long pow, long MOD){
        if(pow == 0l) return 1l;
        long res = BinExpo(a, pow/2l, MOD);
        res = ModMul(res, res, MOD);
        if(pow%2 !=0) res = ModMul(res, 1l*a, MOD);
        return res;
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t-->0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long c = sc.nextLong();
            long ans = BinExpo(a, BinExpo(b, c, MOD-1l), MOD);
            out.println(ans);            
        }
        out.close();
    }
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
}
