import java.io.*;
import java.util.*;

public class Appartments {
//    static FastReader fr =  new FastReader();
    static BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));

    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException{
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {

            String [] t = fr.readLine().split(" ");
            int n = Integer.parseInt(t[0]);
            int m= Integer.parseInt(t[1]);
            long k= Integer.parseInt(t[2]);

            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b= new ArrayList<>();

            for(String ele : fr.readLine().split(" ")) {
                a.add(Integer.parseInt(ele));
            }

            for(String ele : fr.readLine().split(" ")) {
                b.add(Integer.parseInt(ele));
            }

            Collections.sort(a);
            Collections.sort(b);

            int p1=0;
            int p2=0;

            int ans=0;
            while(p1 < n && p2 < m) {
                if(Math.abs(a.get(p1) - b.get(p2)) <= k) {
                    p1++;
                    p2++;
                    ans++;
                    continue;
                }
                if(a.get(p1) < b.get(p2)) p1++;
                else p2++;
            }
            out.println(ans);
        }
    }


    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
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
    static int MOD = (int)998244353;
    static int maxI = Integer.MAX_VALUE;
    static int minI = Integer.MIN_VALUE;
    static long maxL = Long.MAX_VALUE;
    static long minL = Long.MIN_VALUE;
    public static int max(int a, int b) {return Math.max(a, b);}
    public static int min(int a, int b) {return Math.min(a, b);}
    public static long max(long a, long b) {return Math.max(a, b);}
    public static long min(long a, long b) {return Math.min(a, b);}
}
