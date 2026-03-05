package com.searchlib;


public class InterpolationSearch {


    public int search(int[] arr, int target) {

        // Guard: null or empty → nothing to search
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low  = 0;
        int high = arr.length - 1;

        // Continue ONLY while target is within the current value range.
        // If target < arr[low] or target > arr[high] it CANNOT be in the array.
        while (low <= high && target >= arr[low] && target <= arr[high]) {

            // Edge case: only ONE element left in the search range
            if (low == high) {
                return (arr[low] == target) ? low : -1;
            }

            // ── INTERPOLATION FORMULA ──────────────────────────────────
            int pos = low + ((target - arr[low]) * (high - low))
                          / (arr[high] - arr[low]);
            // ──────────────────────────────────────────────────────────

            if (arr[pos] == target) {
                return pos;              // ✓  FOUND

            } else if (arr[pos] < target) {
                low = pos + 1;           //    target is to the RIGHT

            } else {
                high = pos - 1;          //    target is to the LEFT
            }
        }

        return -1;   // NOT FOUND
    }
}
