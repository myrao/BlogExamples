public class Solution {
    public String reverseString(String s) {
        ArrayList<Char> collectionList = new ArrayList<Char>();
        for (int index = 0; index < s.length(); index++){
            collectionList.add(s.charAt(index);
        }
        String outputWord = "";
        for (String charStr : collectionList) {
            outputWord += charStr;
        }
    }

    public static void main(String[] args) {
        System.out.println("outputWord=> " + outputWord);
    }
}