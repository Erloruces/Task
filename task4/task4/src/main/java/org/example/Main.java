package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);

        int[] nums = pars(file);

        int result = round(Arrays.stream(nums).sum()/ nums.length);
        int count = 0;
        for (int a : nums) {
            count = count + abs(a - result);
        }
        System.out.println(count);
    }

    static int[] pars(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line = "";
        while (scanner.hasNextLine()) {
            line = line + " " + scanner.nextLine();
        }

        String[] numsString = line.split(" ");
        int[] nums = new int[numsString.length - 1];
        int count = 0;

        for (String num : numsString) {
            if (num != "") {
                nums[count++] = Integer.parseInt(num);
            }
        }
        scanner.close();

        return nums;
    }
}