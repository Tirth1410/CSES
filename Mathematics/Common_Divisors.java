import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class Common_Divisors {
    public static int maxGCD(int [] arr, int max){
        int [] freq = new int [max+1];
        for(int ele : arr) freq[ele]++;
        int ans=1;
        for(int i=2;i<=max; i++){
            int t=0;
            for(int j=i; j<=max; j+=i){
                t+=freq[j];
                if(t>1) {
                    ans=i;
                    break;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        // int t = sc.nextInt();
        int t=1;
        while (t-->0) {
            int n = sc.nextInt();
            int [] Seq = new int [n];
            int max=0;
            for(int i=0;i<n;i++){
                Seq[i]=sc.nextInt();
                max = Math.max(max, Seq[i]);
            }
            int ans = maxGCD(Seq, max);
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
