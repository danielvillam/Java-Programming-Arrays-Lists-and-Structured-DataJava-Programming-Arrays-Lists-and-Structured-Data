/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 25, 2023)
 */

import edu.duke.*;
import java.util.ArrayList;
public class CharactersInPlay 
{
    private ArrayList<String> myNames;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay () {
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update (String person){
        person = person.toLowerCase();
        int index = myNames.indexOf(person);
        if (index == -1){
            myNames.add(person);
            myFreqs.add(1);
        }else{
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
        }
    }
    
    public void findAllCharacters (){
        myNames.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        
        for(String s : resource.lines()){
            s = s.toLowerCase();
            int index = s.indexOf(".");
            if (index != -1){
                String possibleName = s.substring(indexFirstLetter(s), index);
                update(possibleName);
            }
        }
    }
    
    public int indexFirstLetter (String line){
        StringBuilder newString = new StringBuilder(line);
        for(int i = 0; i < line.length(); i++) {
            char currChar = line.charAt(i);
            if(Character.isLetter(currChar)){
                return i;
            }
        }
        return -1;
    }
    
    public void tester (){
        findAllCharacters();
        //for(int k=0; k < myFreqs.size(); k++){
          //  System.out.println("person/freq: "+myNames.get(k)+" "+myFreqs.get(k));
        //}
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts (int num1, int num2){
        for(int k=0; k < myFreqs.size(); k++){
            if(myFreqs.get(k) >= num1 && myFreqs.get(k) <= num2){
                System.out.println("person/freq: "+myNames.get(k)+" "+myFreqs.get(k));
            }
        }
    }
}
