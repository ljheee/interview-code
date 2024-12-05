package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lijianhua04 on 2020/5/6.
 */
public class StringGenerator {

    static ThreadLocalRandom random = ThreadLocalRandom.current();


    public static String generateRandomLowerCase(int size) {
        char[] arr = new char[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) random.nextInt(97, 122);
        }
        return String.valueOf(arr);
    }

    public static String generateRandomUpperCase(int size) {
        char[] arr = new char[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) random.nextInt(65, 90);
        }
        return String.valueOf(arr);
    }


    public static void main(String[] args) {
        System.out.println(generateRandomLowerCase(8));
        System.out.println(generateRandomUpperCase(18));
    }


}
