/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 22, 2023)
 */

import edu.duke.*;
public class CaesarBreaker {
    
    public int[] countLetters (String encrypted){
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
    
    public int maxIndex (int[] values){
        int maxDex = 0;
        for(int k=0; k < values.length; k++){
            if(values[k] > values[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
     
    public String decrypt (String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4){
            dkey = 26 - (4-maxDex);
        }

        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void testDecrypt (){
        String messageEncrypted = "Cfopq Ibdflk";
        int key = getKey(messageEncrypted);
        String messageDecrypted = decrypt(messageEncrypted);
        System.out.println("Key: "+key+" Decrypt: "+messageDecrypted);
    }
    
    public String halfOfString (String message, int start){
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2){
            char a = message.charAt(i);
            half.append(a);
        }
        return half.toString();
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public void decryptTwoKeys(String encrypted){
        int dkey1 = getKey(halfOfString(encrypted, 0));
        int dkey2 = getKey(halfOfString(encrypted, 1));
        System.out.println(dkey1 + "\t" + dkey2);
        CaesarCipher cc = new CaesarCipher();
        String decrypt = cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
        System.out.println(decrypt);
    }
    
    public void testDecryptTwoKeys(){
        decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
    }
    
    public void testQuiz (){
        //9
        decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        
        //10
        FileResource resource = new FileResource("data/mysteryTwoKeysPractice.txt");
        decryptTwoKeys(resource.asString());
    }
}
