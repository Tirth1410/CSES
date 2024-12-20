package CSES.Ranged_Queries;

import java.io.*;
import java.util.*;

public class Range_XOR_Queries {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String curr = "";
    public static int compare(String a, String b) {
        char [] temp = a.toCharArray();
        int p1 = a.length()-1, p2=b.length()-1;
        while (p2 >= 0 && p1 >= 0) {
            if(a.charAt(p1) == b.charAt(p2)) {
                temp[p1]='0';
            } else {
                temp[p1]='1';
            }
            p1--;
            p2--;
        }

        for(int i=0; i<a.length(); i++) {
            if(curr.charAt(i) > temp[i]) return 1;
            else if(curr.charAt(i) < temp[i]) {
                StringBuilder SB = new StringBuilder();
                for(char ch : temp) SB.append(ch);
                curr = SB.toString();
                return -1;
            }
        }
        return -1;
    }
    public static void Solve() throws IOException {
        int n = fr.nextInt();
        int q = fr.nextInt();

        long [] x = new long[2*n+10];
        for(int i=0; i<n; i++) x[i+n] = fr.nextLong();

        build(x, n);

        StringBuilder ans = new StringBuilder();

        while (q-- > 0) {
            int l = fr.nextInt();
            int r = fr.nextInt();

            ans.append(query(x, n, l-1, r) + "\n");
        }

        out.println(ans.toString());
    }

    static void build(long [] Seq, int n) {
        for(int i=n-1; i>=0; i--) {
            Seq[i] = Seq[i<<1] ^ Seq[(i<<1)|1];
        }
    }

    static long query(long [] Seq, int n, int l, int r) {
        long XOR = 0l;
        for(int i=l+n, j=r+n; i<j; i>>=1, j>>=1) {
            if((i&1) != 0) XOR ^= Seq[i++];
            if((j&1) != 0) XOR ^= Seq[--j];
        }

        return XOR;
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

    //    -------------------------------(X * Y) % MOD-------------------------------------------------------
    public static long ModMul(long a, long b){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long ModMul(long a, long b, long MOD){
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static int MOD = (int) 1e9 + 7;
    static int MOD2 = (int) 1e9 + 9;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;

    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}

