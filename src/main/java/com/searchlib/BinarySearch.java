package com.searchlib;


public class BinarySearch {


    public int search(int[] arr, int target) {

        // Guard: null or empty → nothing to search
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {

            // Safe mid-point formula (avoids integer overflow)
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;              // ✓  FOUND

            } else if (arr[mid] < target) {
                low = mid + 1;           //    go RIGHT

            } else {
                high = mid - 1;          //    go LEFT
            }
        }

        return -1;   // NOT FOUND
    }
}
