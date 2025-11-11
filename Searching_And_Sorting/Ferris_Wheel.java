import java.io.*;
import java.util.*;

public class Ferris_Wheel {
    static BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));

    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException{
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            String [] t = fr.readLine().split(" ");
            int n = Integer.parseInt(t[0]);
            int k= Integer.parseInt(t[1]);

            ArrayList<Integer> list = new ArrayList<>();
            for(String ele : fr.readLine().split(" ")) {
                list.add(Integer.parseInt(ele));
            }

            Collections.sort(list);

            int s=0;
            int e=n-1;

            int ans=0;
            while(s <= e) {
                ans++;
                if(list.get(s) + 0L + list.get(e) <= k) {
                    s++;
                    e--;
                } else e--;
            }
            out.println(ans);
        }
    }


    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }
}
