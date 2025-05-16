package CSES.Mathematics;

import java.io.*;
import java.util.*;
public class Josephus_Queries {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    //    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static long Calc(List<Integer> list, long index) {
        long curr = index;
        for(int i=list.size()-1; i>=0; i--) {
            if(list.get(i) == 0) {
                curr = 2 * curr -1;
            } else {
                curr = 2 * curr +1;
            }
        }
        return curr;
    }

    public static void Solve(int index) throws IOException {
        int q = fr.nextInt();
        for(int i=0; i<q; i++) {
            long n = fr.nextInt();
            long k = fr.nextInt();

            long curr = 2l;
            long size = n;
            if(k <= n/2) {
                out.println(2 * k);
                continue;
            }
            if(k == n/2 + 1 && n % 2 != 0) {
                out.println(1);
                continue;
            }
            k -= n/2 + (n % 2 != 0 ? 1 : 0);
            List<Integer> list = new ArrayList<>();
            while (k > 0) {
                if((size & 1) == 0) list.add(0);
                else list.add(1);
                long now = size/2;
                long can = now/2;
                if(now % 2 != 0) can++;
                if(can < k) {
                    k -= can;
                    size = now;
//                    curr *= 2l;
                    continue;
                }
                if(now % 2 != 0) {
                    if(k == can) out.println(Calc(list, 1));
                    else out.println(Calc(list, 2*k));
                } else {
                    if(now == 1) out.println(Calc(list, 1));
                    else out.println(Calc(list, 2*k));
                }
                k=0;
            }
        }
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

    static long AddMul(long a, long b) {return (a % MOD) + (b % MOD) % MOD;}
    static long SubMod(long a, long b) {return ((a % MOD) - (b % MOD) + MOD) % MOD;}
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

