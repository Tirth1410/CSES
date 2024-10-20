package CSES.Mathematics;

import java.io.*;
import java.util.*;
public class Counting_Coprime_Pairs {
    //    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println(String.valueOf(br.read()));
        int n = Integer.parseInt(br.readLine());
        int [] Seq = new int [n];
        int max = (int)1e5+1;
        String temp;
        int ptr=0;
        for(String s : br.readLine().split(" ")){
            Seq[ptr] = Integer.parseInt(s);
            if(Seq[ptr] > max) max = Seq[ptr];
            ptr++;
        }

        List<Integer> list [] = new ArrayList[max+1];
        int [] divNums = new int [max+1];
        int [] Primes = new int [max+1];
        list[0] = new ArrayList<>();
        list[1] = new ArrayList<>();
        for(int i=2; i<=max; i++){
            if(list[i]==null) {
                list[i] = new ArrayList<>();
                list[i].add(i);
                for(int j=2*i; j<=max; j+=i){
                    if(list[j]== null) list[j] = new ArrayList<>();
                    list[j].add(i);
                }
            }
        }

        for(int i=0; i<n; i++){
            int combination = 1;
            int divisors = 0;
            for(int j=1; j<(1<<list[Seq[i]].size()); j++){
                for(int k=0; k<list[Seq[i]].size(); k++){
                    if((j&(1<<k)) != 0){
                        combination *= list[Seq[i]].get(k);
                        divisors++;
                    }
                }
                divNums[combination]++;
                Primes[combination]=divisors;
//                System.out.println(divNums[combination]);
                combination = 1;
                divisors = 0;
            }

        }
        long total = (n*1l*(n-1l))/2l;
        long valid = 0l;
        for(int i=0; i<=max; i++){
            if((Primes[i]&1) == 0){
                valid -= (divNums[i]*1l*(divNums[i]-1l)) >> 1l;
            } else {
                valid += (divNums[i]*1l*(divNums[i]-1l)) >> 1l;
            }
        }
        out.println(total - valid);
    }

    static int CustomCompare(long a, long b){
        if(a <= b) return -1;
        return 1;
    }

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
//    static class FastReader {
//        BufferedReader br;
//        StringTokenizer st;
//        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in));}
//        String next()
//        {
//            while (st == null || !st.hasMoreElements()) {
//                try { st = new StringTokenizer(br.readLine()); }
//                catch (IOException e) { e.printStackTrace(); }
//            }
//            return st.nextToken();
//        }
//        int nextInt() { return Integer.parseInt(next()); }
//        long nextLong() { return Long.parseLong(next()); }
//        double nextDouble() { return Double.parseDouble(next()); }
//        String nextLine()
//        {
//            String str = "";
//            try {
//                if(st.hasMoreTokens()){str = st.nextToken("\n");}
//                else{str = br.readLine();}
//            }
//            catch (IOException e) {e.printStackTrace();}
//            return str;
//        }
//
//    }
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