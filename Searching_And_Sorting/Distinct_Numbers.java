import java.io.*;
import java.util.*;

public class Distinct_Numbers {
    static BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void Solve() throws IOException{
//        int test = fr.nextInt();
        int test=1;
        for (int tests = 0; tests < test; tests++) {
            HashSet<Integer> set = new HashSet<>();
            int n = Integer.parseInt(fr.readLine());
            String temp [] = fr.readLine().split(" ");
            for(int i=0; i<n; i++) set.add(Integer.parseInt(temp[i]));
            out.println(set.size());
        }
    }


    public static void main (String[] args) throws java.lang.Exception
    {
        Solve();
        out.close();
    }
}