/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 28, 2023)
 */

import java.util.*;
import edu.duke.*;
public class QuizTester {
    public void testQuiz1 (){
        //1
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP: "+maxVisits);
        
        //2
        ArrayList<String> ips = la.iPsMostVisits(counts); 
        System.out.println(ips);
        
        //3
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays(); 
        String dayMostIP = la.dayWithMostIPVisits(iPsForDays);
        System.out.println(dayMostIP);
        
        //4
        ArrayList<String> iPsMaxDay = la.iPsWithMostVisitsOnDay(iPsForDays,"Mar 17");
        System.out.println(iPsMaxDay);
    }
    
    public void testQuiz2 (){
        //4
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPs ();
        System.out.println(count);
        
        //5
        ArrayList<String> uniqueIPOnDay = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Sep 24 has: "+uniqueIPOnDay.size());
        
        //6
        count = la.countUniqueIPsInRange(200,299);
        System.out.println("IP's unique in range 200-299 is: "+count);
        
        //7
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP: "+maxVisits);
        
        //8
        ArrayList<String> ips = la.iPsMostVisits(counts); 
        System.out.println("IP's most visits: "+ips);
        
        //9
        HashMap<String,ArrayList<String>> iPsForDays = la.iPsForDays(); 
        String dayMostIP = la.dayWithMostIPVisits(iPsForDays);
        System.out.println("Day most IP's: "+dayMostIP);
        
        //10
        ArrayList<String> iPsMaxDay = la.iPsWithMostVisitsOnDay(iPsForDays,"Sep 29");
        System.out.println("IP with most visits on Sep 29"+iPsMaxDay);
    }
}

