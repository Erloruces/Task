package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);

        float[] nums1 = pars(file1);
        float[] nums2 = pars(file2);

        float x_circle = nums1[0];
        float y_circle = nums1[1];
        float r_circle = nums1[2];

        for (int i = 0; i < nums2.length; i = i + 2) {
            float x = nums2[i];
            float y = nums2[i + 1];

            double h = sqrt(pow((x - x_circle), 2) + pow((y - y_circle), 2));

            if (h == r_circle) {
                System.out.println(0);
            } else if (h < r_circle) {
                System.out.println(1);
            } else if (h > r_circle) {
                System.out.println(2);
            } else {
                System.out.println("error");
            }
        }
    }
    static float[] pars(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line = "";
        while (scanner.hasNextLine()) {
            line = line + " " + scanner.nextLine();
        }

        String[] numsString = line.split(" ");
        float[] nums = new float[numsString.length - 1];
        int count = 0;

        for (String num : numsString) {
            if (num != "") {
                nums[count++] = Float.parseFloat(num);
            }
        }
        scanner.close();

        return nums;
    }
}