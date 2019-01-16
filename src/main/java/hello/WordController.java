package hello;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class WordController {
    
   @GetMapping("/words/{word}")
    String palindromeCheck(@PathVariable String word){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("word",word);
        objectNode.put("palindrome",checkPalindromeStr(word));
        objectNode.put("anagramOfPalindrome",checkAnagramOfPalindromeStr(word));
        return objectNode.toString();
    }
    
    /**
     *
     * @param inputStr
     * @return
     */
    public static boolean checkAnagramOfPalindromeStr(String inputStr)
    {
        int [] count = new int[26];
        for( int i = 0; i < inputStr.length(); i++ )
        {
            char ch = inputStr.charAt(i);
            count[ch-'a']++;
        }
        int oddOccur = 0;
        for( int cnt:count )
        {
            if( oddOccur > 1)
                return false;
            if( cnt%2 == 1 )
                oddOccur++;
        }
        return true;
    }

    /**
     *
     * @param inputStr
     * @return
     */
    public static boolean checkPalindromeStr(String inputStr){
        String rev = "";
        int length = inputStr.length();
        for ( int i = length - 1; i >= 0; i-- )
            rev = rev + inputStr.charAt(i);
        if (inputStr.equals(rev))
            return true;
        return false;
    }
}
