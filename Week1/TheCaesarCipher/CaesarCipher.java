/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 20, 2023)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);

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
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testCaesarModified() {
        String encrypted = encrypt("First Legion", 23);
        System.out.println(encrypted);
        encrypted = encrypt("First Legion", 17);
        System.out.println(encrypted);
    }
    
    public String encryptTwoKeys (String input, int key1, int key2){
        String encryptedKey1 = encrypt(input, key1);
        String encryptedKey2 = encrypt(input, key2);
        StringBuilder encrypted = new StringBuilder(input);

        for(int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if(i % 2 == 0){
                encrypted.setCharAt(i, encryptedKey1.charAt(i));
            }else{
                encrypted.setCharAt(i, encryptedKey2.charAt(i));
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys (){
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }
    
    public void testQuiz (){
        //5
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
        //6
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
    }
}   
