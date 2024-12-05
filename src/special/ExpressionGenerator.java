package special;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lijianhua.
 */
public class ExpressionGenerator {
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    static String[] operator = {"+", "-", "*", "/"};


    public static String randomExpression(int size, int beginRange, int endRange) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(beginRange, endRange);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
            if (i != arr.length - 1) {
                stringBuilder.append(operator[random.nextInt(0, 4)]);
            }
        }
        return stringBuilder.toString();
    }


    public static String priorityExpression(int size, int beginRange, int endRange) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(beginRange, endRange);
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (i < size-1) {
            int step = random.nextInt((size - i) / 2) + 1;
            stringBuilder.append("(");
            for (int j = 0; j < step; j++, i++) {
                stringBuilder.append(arr[i]);
                if (j != step - 1) {
                    stringBuilder.append(operator[random.nextInt(0, 4)]);
                }
            }
            stringBuilder.append(")");
            if (i != size - 1) {
                stringBuilder.append(operator[random.nextInt(0, 4)]);
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {

            System.out.println(randomExpression(10, 1, 9));
            System.out.println(priorityExpression(10, 1, 9));

        }
    }
}
