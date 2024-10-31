package CSES.String_Algorithms;
import java.io.*;
import java.util.*;
public class String_Matching {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void Solve() throws IOException {
        String s = br.readLine();
        String pattern = br.readLine();

        long req = 0l;
        long pow = 53l;
        long p = 53l;
        for(char ch : pattern.toCharArray()){
            req = ((req%MOD) + ModMul(ch-'a', pow)) % MOD;
            pow = ModMul(pow, p);
        }
        long [] hash = new long[s.length()];
        int st=0,ed=0;
        pow = 53l;
        int ans=0;
        long prevPow = 1l;
        while (ed < s.length()){
            hash[ed] = (((ed==0?0l:hash[ed-1])%MOD) + ModMul(pow, s.charAt(ed)-'a')) % MOD;
            pow = ModMul(pow ,p);
            if(ed - st + 1 == pattern.length()){
                long curr = ((hash[ed]%MOD) - ((st==0?0l:hash[st-1]) % MOD) + MOD) % MOD;
                if(ModMul(req, prevPow) == curr) ans++;
                prevPow = ModMul(prevPow, p);
                st++;
            }
            ed++;
        }

        out.println(ans);
    }

    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
//        Factorial((int)1e6);
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

