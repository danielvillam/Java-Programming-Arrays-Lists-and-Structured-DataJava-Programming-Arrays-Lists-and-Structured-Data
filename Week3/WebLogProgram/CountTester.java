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
    }
}
