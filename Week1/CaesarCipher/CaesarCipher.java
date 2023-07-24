/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 24, 2023)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher (int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt (String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (Character.isLowerCase(currChar) == true){
                int idx = alphabet.toLowerCase().indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet.toUpperCase().charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
            else{
                int idx = alphabet.indexOf(currChar);
                if (idx != -1){
                    currChar = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(i, currChar);
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt (String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
