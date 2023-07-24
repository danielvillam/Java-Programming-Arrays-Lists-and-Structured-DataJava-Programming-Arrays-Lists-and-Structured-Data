/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 20, 2023)
 */
public class WordPlay {
    public boolean isVowel (char ch){
        char lch = Character.toLowerCase(ch);
        if(lch == 'a' || lch == 'e' || lch == 'i' || lch == 'o' || lch == 'u'){
            return true;
        }
        return false;
    }
    
    public void testIsVowel (){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('e'));
    }
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder newString = new StringBuilder(phrase);
        for(int i = 0; i < newString.length(); i++) {
            char currChar = newString.charAt(i);
            boolean isVl = isVowel(currChar);
            if(isVl == true){
                newString.setCharAt(i, ch);
            }
        }
        return newString.toString();
    }
    
    public void testReplaceVowels (){
        System.out.println(replaceVowels("Hello World",'*'));
        System.out.println(replaceVowels("HELLO, I AM PROGRAMMER",'a'));
    }
    
    public String emphasize (String phrase, char ch){
        StringBuilder newString = new StringBuilder(phrase);
        char lch = Character.toLowerCase(ch);
        char uch = Character.toUpperCase(ch);
        for(int i = 0; i < newString.length(); i++) {
            char currChar = newString.charAt(i);
            if(currChar == lch || currChar == uch){
                if(i % 2 == 0){
                    newString.setCharAt(i, '*');
                }else{
                    newString.setCharAt(i, '+');
                }
            }
        }
        return newString.toString();
    }
    
    public void testEmphasize (){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
