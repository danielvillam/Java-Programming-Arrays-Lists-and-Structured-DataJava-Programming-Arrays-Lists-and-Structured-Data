/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 29, 2023)
 */

import edu.duke.*;
import java.util.*;
public class TesterQuiz {
    public void testQuiz1 (){
        //1
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        int[] keys = vb.tryKeyLength(fr.asString(),4,'e');
        
        for(int i = 0; i < 4; i++){
            if(i==0){
                System.out.print("{"+keys[i]+",");
            }
            else if(i==keys.length-1){
                System.out.print(keys[i]+"}");
            }
            else{
                System.out.print(keys[i]+",");
            }
        }
        
        //2
        vb.breakVigenere();
    }
    
    public void testQuiz2 (){
        VigenereBreaker vb = new VigenereBreaker();
        
        //1, 2 y 3
        vb.breakVigenere();
        
        //4
        FileResource frDictionary = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(frDictionary);
        FileResource fr = new FileResource();
        String frString = fr.asString();
        int[] key = vb.tryKeyLength(frString,38,'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypt = vc.decrypt(frString);
        int count = vb.countWords(decrypt,dictionary);
        System.out.println(count);
    }
}
