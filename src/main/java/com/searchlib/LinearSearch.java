package com.searchlib;


public class LinearSearch {


    public int search(int[] arr, int target) {

        // Guard: null or empty → nothing to search
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == target) {
                return i;    // ✓  FOUND at index i
            }
        }

        return -1;   // NOT FOUND after checking every element
    }
}
