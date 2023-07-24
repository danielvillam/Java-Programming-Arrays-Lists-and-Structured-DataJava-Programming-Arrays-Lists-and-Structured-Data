/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 24, 2023)
 */

import edu.duke.*;
public class TestCaesarCipher {
    private int[] countLetters (String encrypted){
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        int[] freqs = new int[26];
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = Character.toLowerCase(encrypted.charAt(i));
            int idx = alphabetLower.indexOf(currChar);
            if (idx != -1) {
                freqs[idx] += 1;
            }
	}
	return freqs;
    }
    
    private int maxIndex (int[] values){
        int maxDex = 0;
        for(int k=0; k < values.length; k++){
            if(values[k] > values[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public void simpleTests (){
        FileResource resource = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        
        String stringEncrypt = cc.encrypt(resource.asString());
        System.out.println(stringEncrypt);
        
        String stringDecrypt = cc.decrypt(stringEncrypt);
        System.out.println(stringDecrypt);
        
        System.out.println(breakCaesarCipher(stringEncrypt));
    }
    
    public String breakCaesarCipher (String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        System.out.println("dkey: "+dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
}
