import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class Couting_Divisors {
    public static int [] Primes(int n){
        int [] p = new int [n+1];
        for(int i=2;i<=n; i++){
            if(p[i]!=0) continue;
            for(int j=2*i; j<=n; j+=i){
                p[j]=i;
            }
        }
        return p;
    }
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int [] p = Primes(1000000);
        int t = sc.nextInt();
        while (t-->0) {
            int n = sc.nextInt();
            if(n==1){
                out.println("1");
                continue;
            }
            long ans=1l;
            while (p[n]!=0) {
                int prime = p[n];
                int f=0;
                while (n%prime==0) {
                    f++;
                    n/=prime;
                }
                ans *= f+1l;
            }
            if(n>1) ans*=2l;
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
