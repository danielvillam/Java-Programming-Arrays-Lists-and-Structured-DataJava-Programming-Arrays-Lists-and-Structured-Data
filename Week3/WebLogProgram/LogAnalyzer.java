/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 27, 2023)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
                records.add(WebLogParser.parseEntry(line));
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs (){
         HashMap<String,Integer> counts = countVisitsPerIP();
         return counts.size();
     }
     
     public void printAllHigherThanNum (int num){
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay (String someday){       
       ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for(LogEntry le : records){
             String strDate = le.getAccessTime().toString();
             String ipAddr = le.getIpAddress();
             
             if(strDate.substring(4,7).equals(someday.substring(0,3)) &&
                strDate.substring(8,10).equals(someday.substring(4)) &&
                !uniqueIPsOnDay.contains(ipAddr)){
                 uniqueIPsOnDay.add(ipAddr);
             }
         }
         return uniqueIPsOnDay;
    }
    
    public int countUniqueIPsInRange (int low, int high){
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        int count = 0;
        for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             String ipAddr = le.getIpAddress();
             
             if(statusCode >= low && statusCode <= high && !uniqueIPsInRange.contains(ipAddr)){
                 uniqueIPsInRange.add(ipAddr);
                 count++;
             }
         }
         return count;
    }
    
    public HashMap<String,Integer> countVisitsPerIP (){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!counts.containsKey(ipAddr)){
                 counts.put(ipAddr,1);
             }else{
                 counts.put(ipAddr,counts.get(ipAddr) + 1);
             }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP (HashMap<String,Integer> counts){
        int maxVisits = 0;
        for(String ip : counts.keySet()){
            int value = counts.get(ip);
            if(value > maxVisits){
                maxVisits = value;
            }
        }
        return maxVisits;
    }
    
    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts){
        ArrayList<String> maxIPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for(String ip : counts.keySet()){
            int value = counts.get(ip);
            if(value == maxVisits){
                maxIPs.add(ip);
            }
        }
        return maxIPs;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays (){
        HashMap<String,ArrayList<String>> ipsForDays = new HashMap<String,ArrayList<String>>();
        for(LogEntry le : records){
             String strDate = le.getAccessTime().toString().substring(4,10);
             String ipAddr = le.getIpAddress();
             
             if(!ipsForDays.containsKey(strDate)){
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ipAddr);
                 ipsForDays.put(strDate,ips);
             }else{
                 ArrayList<String> ips = ipsForDays.get(strDate);
                 ips.add(ipAddr);
                 ipsForDays.put(strDate,ips);
             }
        }
        return ipsForDays;
    }
    
    public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsForDay){
        String dayMostIP = "";
        int max = 0;
        for(String day : iPsForDay.keySet()){
            int value = iPsForDay.get(day).size();
            if(value > max){
                dayMostIP = day;
                max = value;
            }
        }
        return dayMostIP;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> iPsForDay, String someday){
        ArrayList<String> iPsSomeday = iPsForDay.get(someday);
        HashMap<String, Integer> counts = new HashMap<String, Integer>(); 
        for(String ip : iPsSomeday){
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }else{
                 counts.put(ip,counts.get(ip) + 1);
             }
        }
        return iPsMostVisits(counts);
    }
}
