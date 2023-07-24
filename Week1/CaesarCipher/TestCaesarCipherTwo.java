/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 24, 2023)
 */

import edu.duke.*;
public class TestCaesarCipherTwo {
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
    
    private String halfOfString (String input, int start){
        StringBuilder half = new StringBuilder();
        for (int i = start; i < input.length(); i += 2){
            char a = input.charAt(i);
            half.append(a);
        }
        return half.toString();
    }
    
    public void simpleTests (){
        FileResource resource = new FileResource();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        
        String stringEncrypt = cc.encrypt(resource.asString());
        System.out.println(stringEncrypt);
        
        String stringDecrypt = cc.decrypt(stringEncrypt);
        System.out.println(stringDecrypt);
        
        System.out.println(breakCaesarCipher(stringEncrypt));
    }
    
    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String breakCaesarCipher(String input){
        int dkey1 = getKey(halfOfString(input, 0));
        int dkey2 = getKey(halfOfString(input, 1));
        System.out.println("dkey1: "+dkey1 + "\ndkey2: " + dkey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.decrypt(input);
    }

    public void testQuiz(){
        //1
        CaesarCipher cc = new CaesarCipher(15);
        String stringEncrypt = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(stringEncrypt);
        
        //2
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(21,8);
        stringEncrypt = ccTwo.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(stringEncrypt);
        
        //6
        ccTwo = new CaesarCipherTwo(14,24);
        stringEncrypt = ccTwo.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
        System.out.println(stringEncrypt);
        
        System.out.println(breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
        
        //8
        FileResource resource = new FileResource("data/mysteryTwoKeysQuiz.txt");
        System.out.println(breakCaesarCipher(resource.asString()));
    }
}
