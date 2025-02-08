package org.example;

import java.util.Random;

public class App {
    private static final Random rand = new Random();

    // Algorithm 1: Bad Shuffle
    public static void badShuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = rand.nextInt(array.length); // Swap with a completely random index
            swap(array, i, j);
        }
    }

    // Algorithm 2: Slightly Better Shuffle
    public static void slightlyBetterShuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = rand.nextInt(i + 1); // Swap within the seen portion
            swap(array, i, j);
        }
    }

    // Algorithm 3: Fisher-Yates Shuffle
    public static void fisherYatesShuffle(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // Swap within the shrinking boundary
            swap(array, i, j);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void testShuffle(int[] array, String algorithm) {
        long startTime = System.nanoTime();
        
        switch (algorithm) {
            case "Bad Shuffle":
                badShuffle(array);
                break;
            case "Slightly Better Shuffle":
                slightlyBetterShuffle(array);
                break;
            case "Fisher-Yates Shuffle":
                fisherYatesShuffle(array);
                break;
        }
        
        long endTime = System.nanoTime();
        System.out.println(algorithm + " took " + (endTime - startTime) / 1e6 + " ms");
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};
        String[] algorithms = {"Bad Shuffle", "Slightly Better Shuffle", "Fisher-Yates Shuffle"};

        for (int size : sizes) {
            System.out.println("\nTesting array size: " + size);
            for (String algorithm : algorithms) {
                int[] array = new int[size];
                for (int i = 0; i < size; i++) array[i] = i;
                testShuffle(array, algorithm);
            }
        }
    }
}
