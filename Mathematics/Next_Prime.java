package CSES.Mathematics;

import java.io.*;
import java.util.*;
import java.math.*;
public class Next_Prime {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] Give_Primes(int n){
        boolean prime[] = new boolean[n+1];
        for(int j=2;(j*1*j)<=n;j++){
            if(!prime[j]){
                for(int k=j*j;k<=n;k+=j) prime[k]=true;
            }
        }
        int count=0;
        for(int j=2;j<=n;j++){
            if(!prime[j]) count++;
        }
        int arr[]  = new int[count],t=0;
        for(int j=2;j<=n;j++){
            if(!prime[j]) arr[t++]=j;
        }
        return arr;
    }

    public static void main(String args []) throws IOException {
        int t = fr.nextInt();
        int [] primes = Give_Primes((int)1e7);
//        System.out.println(primes.length);
        for(int tests=0;tests<t; tests++){
            long n = fr.nextLong();

            if(n == 1) {
                out.println(2);
                continue;
            }
            int gap = n % 2 == 0 ? 1 : 2;
            long ans = -1;
            for(long i=n+gap; i<=n+900; i+=2) {
                boolean prime = true;
                for(int ele : primes) {
                    if(i == ele) {
                        break;
                    }
                    if(i % ele == 0) {
                        prime = false;
                        break;
                    }
                }
                if(prime) {
                    ans = i;
                    break;
                }
            }
            out.println(ans);
        }
        out.close();
    }

    static int [][][] prefixSum(String [] grid, int n, int k) {
        int [][][] prefix = new int [n][n][k];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                for(int x=0; x<k; x++) {
                    int case1 = (i-1>=0) ? prefix[i-1][j][x] : 0;
                    int case2 = (j-1>=0) ? prefix[i][j-1][x] : 0;
                    int case3 = (i-1>=0 && j-1>=0) ? prefix[i-1][j-1][x] : 0;
                    prefix[i][j][x] = case1 + case2 - case3;
                    if(grid[i].charAt(j)-'A'==x) prefix[i][j][x]++;
                }
            }
        }
        return prefix;
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

