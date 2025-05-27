package CSES.String_Algorithms;

import java.io.*;
import java.util.*;
public class Finding_Borders {
    static FastReader fr = new FastReader();
    //    static Reader r = new Reader();
    static PrintWriter out = new PrintWriter(System.out);
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void Solve(int index) throws IOException {
        String s = fr.next();
        int length = s.length();
        long [] hash = new long[length];
        long [] inverse = new long [length];
        long p = 31l;
        long inv = BinaryExpo(p, MOD-2l);
        long pow = 1l;
        long mul = 1l;
        for(int i=0; i<length; i++) {
            hash[i] = ((ModMul(s.charAt(i)-'a'+1, pow) % MOD) + (i == 0 ? 0l : hash[i-1]) % MOD) % MOD;
            inverse[i] = mul;
            mul = ModMul(mul, inv);
            pow = ModMul(pow, 31l);
        }

        List<Integer> sizes = new ArrayList<>();
        for(int i=0; i<length-1; i++) {
            int start = length-i-1;
            long suffux_Hash = ((hash[length-1] % MOD) - (hash[start-1] % MOD) + MOD) % MOD;
            suffux_Hash = ModMul(suffux_Hash, inverse[start]);

            if(suffux_Hash == hash[i]) sizes.add(i+1);
        }

        Collections.sort(sizes);
        StringBuilder ans = new StringBuilder();
        for(int ele : sizes) ans.append(ele + " ");
        out.println(ans);
    }

    //    -------------------------------------------------------------------------------------------------------
    public static void main(String args[]) throws IOException {
//        int t = fr.nextInt();
        int t = 1;

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