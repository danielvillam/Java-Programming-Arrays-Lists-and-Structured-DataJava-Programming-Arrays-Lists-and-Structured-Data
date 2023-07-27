/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 25, 2023)
 */

import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> myMap;
    
    public CodonCount (){
        myMap = new HashMap<String,Integer>();
    }
    
    
    public void buildCodonMap (int start, String dna){
        myMap.clear();
        for(int i = start; i < dna.length() ;i=i+3){
            if(i+3 <= dna.length()){
                String codon = dna.substring(i,i+3);
                if (!myMap.containsKey(codon)){
                    myMap.put(codon,1);
                }
                else {
                    myMap.put(codon,myMap.get(codon)+1);
                }
            }
        }
    }
    
    public String getMostCommonCodon (){
        int maxOcurrences = 0;
        String maxOcurrenceCodon = "";
        for(String c : myMap.keySet()){
            int ocurrences = myMap.get(c);
            if(ocurrences > maxOcurrences){
                maxOcurrences = ocurrences;
                maxOcurrenceCodon = c;
            }
        }
        return maxOcurrenceCodon;
    }
    
    public void printCodonCounts (int start, int end){
        for(String c : myMap.keySet()){
            int ocurrences = myMap.get(c);
            if(ocurrences >= start && ocurrences <= end){
                System.out.println(c+" : "+ocurrences);
            }
        }
    }
    
    public void test (){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        
        //Reading frame starting with 0
        buildCodonMap(0,dna);
        System.out.println("Reading frame starting with 0 results in "+myMap.size()+" unique codons");
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is "+mostCommon+" with count "+myMap.get(mostCommon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts (1, 5);
        
        //Reading frame starting with 1
        buildCodonMap(1,dna);
        System.out.println("Reading frame starting with 1 results in "+myMap.size()+" unique codons");
        mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is "+mostCommon+" with count "+myMap.get(mostCommon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts (1, 5);
        
        //Reading frame starting with 2
        buildCodonMap(2,dna);
        System.out.println("Reading frame starting with 2 results in "+myMap.size()+" unique codons");
        mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is "+mostCommon+" with count "+myMap.get(mostCommon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts (1, 5);
    }
    
    public void testQuiz (){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        
        //9
        buildCodonMap(1,dna);
        System.out.println("Reading frame starting with 1 results in "+myMap.size()+" unique codons");
    
        //10
        buildCodonMap(2,dna);
        System.out.println("Reading frame starting with 2 results in "+myMap.size()+" unique codons");
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is "+mostCommon+" with count "+myMap.get(mostCommon));
        
        //11
        buildCodonMap(0,dna);
        System.out.println("Reading frame starting with 0 results in "+myMap.size()+" unique codons");
        mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is "+mostCommon+" with count "+myMap.get(mostCommon));
        System.out.println("Counts of codons between 7 and 7 inclusive are:");
        printCodonCounts (7, 7);
    }
}
