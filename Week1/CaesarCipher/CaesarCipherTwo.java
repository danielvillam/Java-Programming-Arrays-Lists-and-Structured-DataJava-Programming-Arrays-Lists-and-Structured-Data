/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 24, 2023)
 */

import edu.duke.*;
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo (int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < sb.length(); i += 2) {
            char currChar = sb.charAt(i);
            if (Character.isLowerCase(currChar) == true){
                int idx = alphabet.toLowerCase().indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet1.toUpperCase().charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet1.charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
        }
        for(int i = 1; i < sb.length(); i += 2) {
            char currChar = sb.charAt(i);
            if (Character.isLowerCase(currChar) == true){
                int idx = alphabet.toLowerCase().indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet2.toUpperCase().charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet2.charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo a = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return a.encrypt(input);
    }
}
