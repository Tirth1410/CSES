package CSES.Ranged_Queries;

import java.io.*;
import java.util.*;

public class Hotel_Queries {
    static FastReader fr = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);


    public static void Solve() throws IOException {
        int n = fr.nextInt();
        int q = fr.nextInt();

        int [][] Seq = new int [2*n+1][3];

        for(int i=0; i<n; i++) {
            Seq[i+n][0] = fr.nextInt();
            Seq[i+n][1] = i;
            Seq[i+n][2] = i;
        }
        StringBuilder ans = new StringBuilder();
        if(n == 1) {
            int curr = Seq[n][0];
            for(int i=0; i<q; i++) {
                int temp = fr.nextInt();
                if(temp <= curr) {
                    ans.append("1 ");
                    curr -= temp;
                } else ans.append("0 ");
            }
            out.println(ans);
            return;
        }
        build(Seq, n);
        Seq[0][0] = -1;
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<n; i++) {
            if(Seq[i][0] == -1) continue;
            if(Seq[i>>1][0] == -1) list.add(i);
        }
        Collections.sort(list, Comparator.comparingInt(a -> Seq[a][1]));
//        System.out.println(list);
        while (q-- > 0) {
            int val = fr.nextInt();

            boolean temp = true;
            for(int i=0; i<list.size(); i++) {
                int res = query(Seq, n, val, list.get(i));
                if(res != 0) {
                    ans.append(res + " ");
                    temp = false;
                    break;
                }
            }
            if(!temp) continue;
            ans.append("0 ");
        }
        out.println(ans);
    }

    static void build(int [][] Seq, int n) {
        for(int i=n-1; i>=0; i--) {
            if(Seq[i<<1][0] == -1 || Seq[(i<<1)|1][0] == -1 || Seq[i<<1][2] >= Seq[(i<<1)|1][1]) {
                Seq[i][0] = -1;
//                System.out.println(Arrays.toString(Seq[i]));
                continue;
            }
            Seq[i][0] = max(Seq[i<<1][0], Seq[(i<<1)|1][0]);
            Seq[i][1] = min(Seq[i<<1][1], Seq[(i<<1)|1][1]);
            Seq[i][2] = max(Seq[i<<1][2], Seq[(i<<1)|1][2]);
//            System.out.println(Arrays.toString(Seq[i]));
        }
    }

    static int query(int [][] Seq, int n, int target, int start) {
        int curr = start;
        while (curr < n) {
//            System.out.println(curr + " " + Seq[curr]);
            if(Seq[curr][0] < target) return 0;

            if(Seq[curr<<1][0] >= target) curr = curr<<1;
            else curr = (curr<<1)|1;
        }

        Update(Seq, curr, Seq[curr][0] - target);
        return curr-n+1;
    }

    static void Update(int [][] Seq, int idx, int val) {
        Seq[idx][0] = val;
        while (idx > 1) {
            idx >>= 1;
            Seq[idx][0] = max(Seq[idx<<1][0], Seq[(idx<<1)|1][0]);
        }
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

    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}


