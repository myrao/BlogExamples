public class Solution {
    static String[] vowelsArray = {"a", "e", "i", "o", "u"};
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder();
        char vowelChar = 'x';
        for (int index = 0; index <= s.length() - 1 ; index++){
            if (!checkIsVowel(s.charAt(index))){
                if (String.valueOf(vowelChar).equals("x")){
                    sb.append(s.charAt(index));
                }else {
                    sb.append(vowelChar);
                    vowelChar = 'x';
                }
            } else {
                for (int indexMore = index + 1; indexMore <= s.length() - 1; ++indexMore){
                    if (checkIsVowel(s.charAt(indexMore))){
                        vowelChar = s.charAt(index);
                        sb.append(s.charAt(indexMore));
                        continue;
                    }
                }
            }
        }
        return sb.toString();
    }
    
    private boolean checkIsVowel(char judgedWord){
        for (String word : vowelsArray){
            // return judgedWord.equals(word) ? true : false;
            if (String.valueOf(judgedWord).equals(word)){
                return true;
            }
        }
        return false;
    }
}