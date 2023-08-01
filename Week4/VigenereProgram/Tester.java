/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 29, 2023)
 */

import edu.duke.*;
public class Tester {
    public void testSliceString (){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testTryKeyLength (){
        VigenereBreaker vb = new VigenereBreaker();
        String key = "flute";
        FileResource fr = new FileResource();
        int[] keys = vb.tryKeyLength(fr.asString(),key.length(),'e');
        
        for(int i = 0; i < keys.length; i++){
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
    }
    
    public void testBreakVigenere (){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
