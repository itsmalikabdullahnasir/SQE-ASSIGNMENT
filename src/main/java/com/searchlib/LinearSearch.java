package com.searchlib;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  LinearSearch.java  —  ADDED BY STUDENT  (Algorithm 2)     ║
 * ╠══════════════════════════════════════════════════════════════╣
 * ║  ALGORITHM  : Sequential / Brute Force                      ║
 * ║  REQUIRES   : NOTHING — works on sorted OR unsorted arrays  ║
 * ║  TIME       : Best O(1)  |  Average/Worst O(n)              ║
 * ║  SPACE      : O(1)                                          ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  HOW IT WORKS:
 *  ─────────────
 *  Start at index 0.  Check every element left to right.
 *  Return the index the moment a match is found.
 *  If the whole array is checked with no match, return -1.
 *
 *  KEY ADVANTAGE OVER BINARY SEARCH:
 *  ──────────────────────────────────
 *  Binary Search REQUIRES a sorted array — it gives wrong
 *  answers on unsorted data.  Linear Search does not care
 *  about ordering at all, making it more versatile.
 *
 *  WHEN TO USE:
 *  ────────────
 *  ✅  Small arrays (< 100 elements)
 *  ✅  Unsorted data where sorting would cost more than searching
 *  ✅  Finding the FIRST occurrence in an array with duplicates
 *  ❌  Large arrays where speed matters (use Binary/Interpolation)
 *
 *  EXAMPLE:
 *  ────────
 *  Array  = [9, 3, 7, 1, 5]    Target = 7
 *  Step 1 : index 0 → 9 ≠ 7
 *  Step 2 : index 1 → 3 ≠ 7
 *  Step 3 : index 2 → 7 == 7  → FOUND index 2 ✓
 */
public class LinearSearch {

    /**
     * Searches for {@code target} in an integer array.
     * Works on both sorted and unsorted arrays.
     *
     * @param arr    array of integers (any order)
     * @param target the value to search for
     * @return       index of the FIRST occurrence of target, -1 if not found
     */
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
