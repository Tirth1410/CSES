import java.io.*;
import java.util.*;

public class Appartments {
    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader fr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String [] t = fr.readLine().split(" ");
        int n = Integer.parseInt(t[0]);
        int m= Integer.parseInt(t[1]);
        long k= Integer.parseInt(t[2]);

        List<Integer> applicants = new ArrayList<>();
        List<Integer> apartments = new ArrayList<>();

        for(String ele : fr.readLine().split(" ")) {
            applicants.add(Integer.parseInt(ele));
        }

        for(String ele : fr.readLine().split(" ")) {
            apartments.add(Integer.parseInt(ele));
        }

        Collections.sort(applicants);
        Collections.sort(apartments);

        int p1=0;
        int p2=0;

        int ans=0;
        while(p1 < n && p2 < m) {
			// Found a suitable apartment for the applicant
            if(Math.abs(applicants.get(p1) - apartments.get(p2)) <= k) {
                p1++;
                p2++;
                ans++;
                continue;
            }

			// If the desired apartment size is too small,
			// skip that applicant and move to the next person
            if(applicants.get(p1) < apartments.get(p2)) p1++;

			// If the desired apartment size of the applicant is too big,
			// move the apartment pointer to the right to find a bigger one
            else p2++;
        }
        out.println(ans);
        out.close();
    }
}