/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 27, 2023)
 */

import java.util.*;
import edu.duke.*;
public class CountTester {
    public void testCounts (){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        
        la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP: "+maxVisits);
        
        ArrayList<String> ips = la.iPsMostVisits(counts); 
        System.out.println(ips);
        
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(iPsForDays);
        
        String dayMostIP = la.dayWithMostIPVisits(iPsForDays);
        System.out.println(dayMostIP);
        
        ArrayList<String> iPsMaxDay = la.iPsWithMostVisitsOnDay(iPsForDays,"Sep 30");
        System.out.println(iPsMaxDay);
    }
}
