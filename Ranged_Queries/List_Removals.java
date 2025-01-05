package CSES.Ranged_Queries;

import java.io.*;
import java.util.*;

public class List_Removals {
    static PrintWriter out = new PrintWriter(System.out);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void Solve() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int q = n;
        String nums [] = br.readLine().split(" ");
        String nums2 [] = br.readLine().split(" ");
        long [] Seq = new long [n];
        long [] queries = new long [n];
        for(int i=0; i<n; i++) {
            Seq[i] = Long.parseLong(nums[i]);
            queries[i] = Integer.parseInt(nums2[i]);
        }
        int [] temp = new int [2*n+1];
        int [] present = new int [n];
        StringBuilder ans = new StringBuilder();
        for (int i=0; i<q; i++) {
            long idx = queries[i];
            int l=0, r=n-1;
            int now = 0;
            while (l <= r) {
                int mid = (l + r) >> 1;
                int del = query(temp, n, 0, mid+1);
                if(mid - del + 1 == idx) {
                    if(present[mid]==0) {
                        now = mid;
                        break;
                    }
                    r = mid-1;
                } else if(mid - del+1 < idx) l = mid+1;
                else r = mid-1;
            }
            present[now]=1;
            Update(temp, now+n, 1);
            ans.append(Seq[now] + " ");
        }
        out.println(ans);
    }

    static void build(int [] Seq, int n) {
        for(int i=n-1; i>0; i--) {
            Seq[i] = Seq[i<<1] + Seq[(i<<1)|1];
        }
    }

    static int query(int [] Seq, int n, int l, int r) {
        int curr = 0;
        for(int i=l+n, j=r+n; i<j; i>>=1, j>>=1){
            if((i & 1) != 0) curr += Seq[i++];
            if((j & 1) != 0) curr += Seq[--j];
        }
        return curr;
    }

    static void Update(int [] Seq, int idx, int val) {
        Seq[idx] += val;
        idx >>= 1;
        for(int i=idx; i>0; i>>=1){
            Seq[i] = Seq[i<<1] + Seq[(i<<1)|1];
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
}
