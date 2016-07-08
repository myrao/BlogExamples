public class AddDigits {
    public static int addDigits(int num) {
      if (num < 10) return num;
        String numStr = String.valueOf(num);
        int charCount = 0;
        do {
            for (int index = 0; index <= numStr.length() - 1; index++){
                charCount += Integer.parseInt(String.valueOf(numStr.charAt(index)));
            }
            numStr = String.valueOf(charCount);
        }while (numStr.length() >= 2);
        return Integer.parseInt(numStr);
    }

    public static void main(String[] args) {
        System.out.print(addDigits(222222222));
    }
}