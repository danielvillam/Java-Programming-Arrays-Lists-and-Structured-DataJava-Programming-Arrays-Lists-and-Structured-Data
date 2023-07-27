/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 26, 2023)
 */

import edu.duke.*;
import java.util.*;

public class GladLib {
    
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategoryList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLib(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        usedList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country","noun","animal","adjective","name",
            "color","timeframe","verb","fruit"};
        for(String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        
        int index = usedCategoryList.indexOf(w.substring(first+1,last));
        if(index == -1){
            usedCategoryList.add(w.substring(first+1,last));
        }
        
        String sub = getSubstitute(w.substring(first+1,last));
        while(true){
            sub = getSubstitute(w.substring(first+1,last));
            if(!usedList.contains(sub)){
                usedList.add(sub);
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap (){
        int total = 0;
        for(String w : myMap.keySet()){
            total += myMap.get(w).size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for(String w : myMap.keySet()){
            if(usedCategoryList.indexOf(w) != -1){
                total += myMap.get(w).size();
            }
        }
        return total;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nthe total number of words that were replaced is "+usedList.size());
    }
}
