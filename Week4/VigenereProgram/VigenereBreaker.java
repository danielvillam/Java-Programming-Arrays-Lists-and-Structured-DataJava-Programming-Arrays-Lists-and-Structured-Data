/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 29, 2023)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        StringBuilder mess = new StringBuilder(message);
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(mess.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }

    public void breakVigenere () {
        HashMap<String,HashSet<String>> dictionaries = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr);
            if(!dictionaries.containsKey(f.getName())){
                dictionaries.put(f.getName(),dictionary);
            }
            System.out.println("Dictionary " + f.getName()+" read...");
        }

        FileResource fr = new FileResource();
        String frString = fr.asString();
        
        breakForAllLangs(frString,dictionaries);
        
        //int[] keys = tryKeyLength(frString,4,'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //String vcDecrypt = vc.decrypt(frString);
        //System.out.println(vcDecrypt);
    }
    
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String w : fr.lines()){
            String word = w.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    public int countWords (String message, HashSet<String> dictionary){
        String[] stringArray = message.split("\\W+");
        int count = 0;
        for(int i = 0; i < stringArray.length; i++){
            if(dictionary.contains(stringArray[i].toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary){
        int maxCount = 0;
        String decrypt = "";
        int keyLength = 0;
        char mostCommonChar = mostCommonCharIn (dictionary);
        System.out.println(mostCommonChar);
        for(int i = 1; i < 100; i++){
            int[] key = tryKeyLength(encrypted,i,mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String tryDecrypt = vc.decrypt(encrypted);
            int count = countWords(tryDecrypt,dictionary);
            if(count > maxCount){
                maxCount = count;
                decrypt = tryDecrypt;
                keyLength = i;
            }
        }
        System.out.println("Key length: "+keyLength+" Valid words: "+maxCount);
        return decrypt;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (String w : dictionary) {
            for(int k=0; k < w.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(w.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }
        }
        int maxCount = 0;
        int idx = 0;
        for(int k=0; k < counts.length; k++){
            if (counts[k] > maxCount){
                maxCount = counts[k];
                idx = k;
            }
        }
        return alph.charAt(idx);
    }
    
    public void breakForAllLangs (String encrypted, HashMap<String,HashSet<String>> languages){
        int maxCount = 0;
        String decrypt = "";
        String languajeEncrypted = "";
        for(String languaje : languages.keySet()){
            HashSet<String> dictionary = languages.get(languaje);
            System.out.print("the most common character in "+languaje+" ");
            String tryDecrypt = breakForLanguage(encrypted, dictionary);
            int validWords = countWords(tryDecrypt,dictionary);
            if(validWords > maxCount){
                maxCount = validWords;
                decrypt = tryDecrypt;
                languajeEncrypted = languaje;
            }
        }
        System.out.println(decrypt);
        System.out.println("Valid words: "+maxCount);
        System.out.println("Languaje: "+languajeEncrypted);
    }
}
