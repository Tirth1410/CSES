package CSES.Mathematics;

import java.io.*;
import java.util.*;
public class Christmas_Party {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [] GivePrimes(int n){
        int [] primes = new int [n+1];
        for(int i=0; i<=n; i++) primes[i] = i;
        primes[1]=0;
        for(int i=2; i*i<=n; i++){
            if(primes[i]!=i) continue;
            primes[i]=0;
            for(int j=2*i; j<=n; j+=i){
                if(primes[j] == j) primes[j] = i;
            }
        }
        return primes;
    }
    public static int len(long num){
        int l=0;
        while (num != 0) {
            l++;
            num>>=1;
        }
        return l;
    }
    public static void Solve() throws IOException {
        int n = fr.nextInt();

        if(n==0) {
            out.println(0);
            return;
        }

        if(n==1) {
            out.println(0);
            return;
        }

        if(n==2) {
            out.println(1);
            return;
        }

        long [] derangements = new long[n+1];
        derangements[0]=0l;
        derangements[1]=0l;
        derangements[2]=1l;

        for(int i=3; i<=n; i++){
            derangements[i] = ModMul(i-1l, ((derangements[i-1]%MOD) + (derangements[i-2]%MOD)) % MOD);
        }

        out.println(derangements[n]);
    }

//    static long [] fact;
//    public static void Factorial(int n){
//        fact = new long [n+1];
//        fact[0] = 1l;
//        fact[1] = 1l;
//        for(int i=2; i<=n; i++){
//            fact[i] = ModMul(fact[i-1], i);
//        }
//    }

//    static int UpperBound(long target, List<Long> list){
//        int s=0, e=list.size()-1;
//        int idx = 0;
//        while (s <= e){
//            int mid = s + (e-s)/2;
//            if(list.get(mid) >= target) {
//                idx = mid;
//                e = mid-1;
//            } else {
//                s = mid+1;
//            }
//        }
//        return idx;
//    }

//    static int LowerBound(long target, List<Long> list){
//        int s=0, e=list.size()-1;
//        int idx = -1;
//        while (s <= e){
//            int mid = s + (e-s)/2;
//            if(list.get(mid) < target) {
//                idx = mid;
//                s = mid+1;
//            } else {
//                e = mid-1;
//            }
//        }
//        return idx;
//    }

//    static int CustomCompare(long [] a, long [] b, long [] nums){
//        if(a[0] > b[0]) return 1;
//        if(a[0] < b[0]) return -1;
//
//        if(nums[(int)a[1]] < nums[(int)b[1]]) return -1;
//        else if(nums[(int)a[1]] > nums[(int)b[1]]) return 1;
//        return Integer.compare((int)a[1], (int)b[1]);
//    }

//    static int CustomCompare2(int a, int b, int [] Seq){
//        if(Seq[a] > Seq[b]) return -1;
//        if(Seq[a] < Seq[b]) return 1;
//        if(a < b) return 1;
//        return -1;
//    }

    //    static long BinaryExpo(long x, long y) {
//        if (y == 0) return 1;
//        long temp = BinaryExpo(x, y / 2);
//        temp = ModMul(temp, temp);
//        if (y % 2 != 0) return ModMul(temp, x);
//        return temp;
//    }
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
    static int MOD = (int)1e9+7;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
