package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AppTest {
    
    private boolean isShuffled(int[] original, int[] shuffled) {
        // Check if arrays have the same elements (ignoring order)
        Set<Integer> originalSet = new HashSet<>();
        Set<Integer> shuffledSet = new HashSet<>();
        
        for (int num : original) originalSet.add(num);
        for (int num : shuffled) shuffledSet.add(num);
        
        return originalSet.equals(shuffledSet) && !Arrays.equals(original, shuffled);
    }

    @Test
    void testBadShuffle() {
        int[] array = {1, 2, 3, 4, 5};
        int[] original = array.clone();
        App.badShuffle(array);
        assertTrue(isShuffled(original, array), "Bad Shuffle should modify the array but keep all elements.");
    }

    @Test
    void testSlightlyBetterShuffle() {
        int[] array = {1, 2, 3, 4, 5};
        int[] original = array.clone();
        App.slightlyBetterShuffle(array);
        assertTrue(isShuffled(original, array), "Slightly Better Shuffle should modify the array but keep all elements.");
    }

    @Test
    void testFisherYatesShuffle() {
        int[] array = {1, 2, 3, 4, 5};
        int[] original = array.clone();
        App.fisherYatesShuffle(array);
        assertTrue(isShuffled(original, array), "Fisher-Yates Shuffle should modify the array but keep all elements.");
    }

    @Test
    void testPerformance() {
        int size = 10000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) array[i] = i;

        long start, end;

        start = System.nanoTime();
        App.badShuffle(array.clone());
        end = System.nanoTime();
        System.out.println("Bad Shuffle took: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        App.slightlyBetterShuffle(array.clone());
        end = System.nanoTime();
        System.out.println("Slightly Better Shuffle took: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        App.fisherYatesShuffle(array.clone());
        end = System.nanoTime();
        System.out.println("Fisher-Yates Shuffle took: " + (end - start) / 1e6 + " ms");
    }
}