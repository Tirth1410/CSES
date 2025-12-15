import java.io.*;
import java.util.*;

public class Sliding_Window_Inversions {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException {
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            String temp = br.readLine();
            StringTokenizer tok = new StringTokenizer(temp);

            int n = Integer.parseInt(tok.nextToken());
            int k = Integer.parseInt(tok.nextToken());

            temp = br.readLine();
            tok = new StringTokenizer(temp);
            int [] Seq = new int [n];
            int [] indices = new int [n];
            List<Integer> list = new ArrayList<>();

            for(int i=0; i<n; i++) {
                Seq[i] = Integer.parseInt(tok.nextToken());
                list.add(Seq[i]);
            }

            Collections.sort(list);
            HashMap<Integer, Integer> index = new HashMap<>();

            int ct=0;
            for(int i=0; i<n; i++) {
                if(index.containsKey(list.get(i))) continue;
                index.put(list.get(i), ct++);
            }

            for(int i=0; i<n; i++) {
                indices[i] = index.get(Seq[i]);
            }


            int len = index.size();
            int [] seg = new int [(len<<1) + 5];

            int s=0;
            int e=0;
            long ans = 0L;
            
            StringBuilder SB = new StringBuilder();
            while(e < n) {
                int idx = indices[e];
                while(e-s+1 > k) {
                    int id = indices[s];
                    ans -= Query(seg, len, 0, id);
                    Update(seg, len, id, seg[id+len]-1);
                    s++;
                }

                ans += Query(seg, len, idx+1, len);
                Update(seg, len, idx, seg[idx+len]+1);

                if(e-s+1 == k) {
                    SB.append(ans).append(" ");
                }
                e++;
            }
            out.println(SB);
        }
    }

    static void Update(int [] seg, int n, int idx, int val) {
        idx += n;
        seg[idx] = val;
        while(idx > 0) {
            seg[idx>>1] = seg[idx] + seg[idx^1];
            idx >>= 1;
        }
    }

    static int Query(int [] seg, int n, int l, int r) {
        l += n;
        r += n;
        int ans = 0;
        for(int i=l, j=r; i<j; i>>=1, j>>=1) {
            if((i&1) != 0) ans += seg[i++];
            if((j&1) != 0) ans += seg[--j];
        }
        return ans;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }
    static int max(int a, int b) {
        return Math.max(a, b);
    }
}