public class ReverseInteger {
    public int reverse(int x) {
        boolean isNegativeNum = false;
        if (x == 0) return 0;
        if (x < 0) {
            isNegativeNum = true;
            x = Math.abs(x);
        }
        String xString = String.valueOf(x);
        String result = "";
        int index = xString.length();
        for (int i = index - 1; i >= 0; i--){
            result += String.valueOf(xString.charAt(i));
        }
        try{
            if (!isNegativeNum) return Integer.valueOf(result);
            else return (- Integer.parseInt(result));
        } catch (NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}