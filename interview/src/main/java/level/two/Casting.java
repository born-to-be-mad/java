package level.two;

/**
 * What is the output?
 * a) as it is
 * b) when line A commented, but line B uncommented
 * @impNote Result: Infinity
 */
public class Casting {
    public static void main(String... doYourBest) {
        int number1 = (int) (long) 1.0D;
        short number2 = 1;
        Short number3 = (Short) (short) (float) 1.0D;
        //ClassCastException: class java.lang.Double cannot be cast to class java.lang.Integer
        Object number4 = (int) (Number) (Double) (Object) (double) 2.0D; // LINE A
        // Object number4 = (Number) (Double) (Object) (double) 2.0D; // LINE B
        Double number5 = (double) (int) (char) 2.0D;
        Short number6 = getNumber(Short.valueOf("2"));

        System.out.println(
                number1 + number2
                + number3.doubleValue()
                + Integer.valueOf(number4.toString()) // LINE C NumberFormatException: For input string: "2.0"
                + number5.doubleValue()
                + number6.doubleValue());
    }

    static <T> T getNumber(T number) {
        return number;
    }


}
