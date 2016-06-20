public class Solution {
    static String[] vowelsArray = {"a", "e", "i", "o", "u"};
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder();
        String[] stringArray = new String[s.length()];
        String vowelWord = null;
        for (int index = 0; index <= s.length() - 1; ++index){
            stringArray[index] = String.valueOf(s.charAt(index));
        }
        
        for ( int index2 = 0; index2 <= stringArray.length - 1; index2++){
             if (!checkIsVowel(stringArray[index2])){
                    sb.append(stringArray[index2]);
                }else {
                    vowelWord = stringArray[index2];
                     for ( int index3 = index2 + 1; index3 < stringArray.length - 1; ++index3){
                            if (checkIsVowel(stringArray[index3])){
                                  String vowelChar = stringArray[index2];
                                  sb.append(stringArray[index3]);
                                  stringArray[index3] = vowelChar;
                                continue;
                            }
                        }
                     }else {
                            sb.append(stringArray[index2]);
                        }
        }
        return sb.toString();
    }
        
    private boolean checkIsVowel(String judgedWord){
        for (String word : vowelsArray){
            // return judgedWord.equals(word) ? true : false;
            if (judgedWord.equals(word)){
                return true;
            }
        }
        return false;
    }
}