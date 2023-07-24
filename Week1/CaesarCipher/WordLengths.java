/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 22, 2023)
 */

import edu.duke.*;
public class WordLengths {
    
    public void countWordLengths (FileResource resource, int[] counts){
        for(String word : resource.words()){
            int length = countLetters(word,counts.length-1);
            counts[length] += 1;
        }
    }
    
    public int countLetters (String word, int maxLength){
        int length = word.length();
        if(length == 1 && Character.isDigit(word.charAt(0))==true){
            return length;
        }
        if(Character.isLetter(word.charAt(0)) == false){
            length--;
        }
        if(Character.isLetter(word.charAt(word.length()-1)) == false){
            length--;
        }
        if(length > maxLength){
            length = maxLength;
        }
        return length;
    }
    
    public int indexOfMax (int[] values){
        int indexOfMax = 0;
        for(int k=0; k < values.length; k++){
            if(values[k] > indexOfMax){
                indexOfMax = values[k];
            }
        }
        return indexOfMax;
    }
    
    public void testCountWordLengths (){
        FileResource resource = new FileResource("data/lotsOfWords.txt");
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        for(int k=0; k < counts.length; k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        int indexOfMax = indexOfMax(counts);
        System.out.println("Index of max is: " + indexOfMax);
    }
    
    public void testQuiz(){
        //4
        FileResource resource = new FileResource("data/errors.txt");
        int[] counts = new int[31];
        countWordLengths(resource,counts);
        for(int k=0; k < counts.length; k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        int indexOfMax = indexOfMax(counts);
        System.out.println("Index of max is: " + indexOfMax);
        
        //5
        resource = new FileResource("data/manywords.txt");
        counts = new int[31];
        countWordLengths(resource,counts);
        for(int k=0; k < counts.length; k++){
            if(counts[k] != 0){
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        indexOfMax = indexOfMax(counts);
        System.out.println("Index of max is: " + indexOfMax);
    }
}  
