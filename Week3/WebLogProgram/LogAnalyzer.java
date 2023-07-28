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
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
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
     
}
