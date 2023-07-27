/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 26, 2023)
 */

import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    private  HashMap<String,ArrayList<String>> myMap;
    
    public WordsInFiles(){
        myMap = new HashMap<String,ArrayList<String>>();
    }
    
    public void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        String name = f.getName();
        
        for(String w : fr.words()){
            if(!myMap.containsKey(w)){     
                ArrayList<String> list = new ArrayList<String>();
                list.add(name);
                myMap.put(w, list);
            }       
            else if (myMap.containsKey(w) && !myMap.get(w).contains(name)) {
                ArrayList<String> list = myMap.get(w);
                list.add(name);
                myMap.put(w, list);
            }
        }
    }
    
    public void buildWordFileMap (){
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber (){
        int maxNumber = 0;
        for(String w : myMap.keySet()){
            int ocurrences = myMap.get(w).size();
            if(ocurrences > maxNumber){
                maxNumber = ocurrences;
            }
        }
        return maxNumber;
    }
    
    public ArrayList<String> wordsInNumFiles (int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String w : myMap.keySet()){
            int ocurrences = myMap.get(w).size();
            if(ocurrences == number){
                words.add(w);
            }
        }
        return words;
    }
    
    public void printFilesIn (String word){
        if (myMap.containsKey(word)){
            ArrayList<String> list = myMap.get(word);
            for(int k=0; k < list.size(); k++){
                System.out.println(list.get(k));
            }
        }
    }
    
    public void tester (){
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("The greatest number of files a word appears in is "+max);
        ArrayList<String> words = wordsInNumFiles(max-1);
        System.out.println("The number of words that occur in the "+(max-1)+" files are: "+words.size());
        System.out.println("The words are: ");
        //for(int k=0; k < words.size(); k++){
          //  System.out.println(words.get(k)+" appears in the files: ");
          //  printFilesIn(words.get(k));
        //}
    }
    
    public void testQuiz(){
        buildWordFileMap();
        //4
        int max = maxNumber();
        System.out.println("The greatest number of files a word appears in is "+max);
        ArrayList<String> words = wordsInNumFiles(max);
        System.out.println("The number of words that occur in the "+max+" files are: "+words.size());
        
        //5
        words = wordsInNumFiles(max-1);
        System.out.println("The number of words that occur in the "+(max-1)+" files are: "+words.size());
        
        //6
        System.out.println("The word sad appears in the following files:");
        printFilesIn("sad");
        
        //7
        System.out.println("The word red appears in the following files:");
        printFilesIn("red");
       
    }
    
    public void testQuiz2(){
        buildWordFileMap();
        
        //12
        ArrayList<String> words = wordsInNumFiles(7);
        System.out.println("The number of words that occur in the 7 files are: "+words.size());
        
        //13
        words = wordsInNumFiles(4);
        System.out.println("The number of words that occur in the 4 files are: "+words.size());
        
        //14
        System.out.println("The word laid appears in the following files:");
        printFilesIn("laid");
        
        //15
        System.out.println("The word tree appears in the following files:");
        printFilesIn("tree");
       
    }
}
