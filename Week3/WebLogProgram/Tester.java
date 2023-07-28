/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 27, 2023)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer (){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int count = la.countUniqueIPs ();
        System.out.println("The file contains "+ count +" unique IP's");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAllHigherThanNum(100);
    }
    
    public void testUniqueIPVisitsOnDay (){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        
        ArrayList<String> uniqueIPOnDay = la.uniqueIPVisitsOnDay("Sep 14");
        System.out.println("Sep 14 has: "+uniqueIPOnDay.size());
        
        uniqueIPOnDay = la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Sep 30 has: "+uniqueIPOnDay.size());
    }
    
    public void testCountUniqueIPsInRange (){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int count = la.countUniqueIPsInRange(200,299);
        System.out.println("IP's unique in range 200-299 is: "+count);
        
        count = la.countUniqueIPsInRange(300,399);
        System.out.println("IP's unique in range 300-399 is: "+count);
    }
    
    public void testQuiz (){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        
        //2
        la.printAllHigherThanNum(400);
        
        //3
        ArrayList<String> uniqueIPOnDay = la.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Mar 24 has: "+uniqueIPOnDay.size());
        
        //4
        int count = la.countUniqueIPsInRange(300,399);
        System.out.println("IP's unique in range 300-399 is: "+count);
    }
    
}
