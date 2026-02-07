import java.io.*;
import java.util.*;

public class Pizzeria_Queries {
    static FastReader fr =  new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    static long ModMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    static long BinaryExpo(long a, long pow) {
        long ans = 1;
        a %= MOD;
        while(pow > 0){
            if((pow & 1) == 1){
                ans = ans * a % MOD;
            }
            pow = pow >> 1;
            a = a * a % MOD;
        }
        return ans;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        int n=fr.nextInt();
        int k=fr.nextInt();
        
        int [] val = new int [n];
        int [] sg1 = new int [(n<<1)+5];
        int [] sg2 = new int [(n<<1)+5];
        
        for(int i=0; i<n; i++) {
            val[i] = fr.nextInt();
            sg1[i+n]=val[i]-i;
            sg2[i+n]=val[i]+i;
        }
        
        Build(sg1, n);
        Build(sg2, n);
        
        for(int i=0; i<k; i++) {
            int type=fr.nextInt();
            int id=fr.nextInt()-1;
            
            if(type == 2) {
                int min = min(query(sg1, n, 0, id+1)+id, query(sg2, n, id, n)-id);
                out.println(min);
            } else {
                int x=fr.nextInt();
                val[id] = x;
                
                Update(sg1, n, id, val[id]-id);
                Update(sg2, n, id, val[id]+id);
            }
        }
        out.close();
    }
    
    static void Build(int [] seg, int n) {
        for(int i=n-1; i>=0; i--) {
            seg[i] = min(seg[i<<1], seg[(i<<1)|1]);
        }
    }
    
    static void Update(int [] seg, int n, int idx, int val) {
        idx += n;
        seg[idx] = val;
        
        while(idx > 0) {
            seg[idx>>1] = min(seg[idx], seg[idx^1]);
            idx>>=1;
        }
    }
    
    static int query(int [] seg, int n, int l, int r) {
        l+=n;
        r+=n;
        
        int ans=maxI;
        for(int i=l, j=r; i<j; i>>=1, j>>=1) {
            if((i&1)!=0) ans = min(ans, seg[i++]);
            if((j&1)!=0) ans = min(ans, seg[--j]);
        }
        
        return ans;
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