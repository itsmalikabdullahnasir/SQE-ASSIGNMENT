package com.searchlib;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  BinarySearch.java  —  PROVIDED BY TEACHER                  ║
 * ╠══════════════════════════════════════════════════════════════╣
 * ║  ALGORITHM  : Divide and Conquer                            ║
 * ║  REQUIRES   : Array must be SORTED (ascending)              ║
 * ║  TIME       : Best O(1)  |  Average/Worst O(log n)          ║
 * ║  SPACE      : O(1)  — iterative, no recursion stack         ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  HOW IT WORKS:
 *  ─────────────
 *  1. Look at the MIDDLE element of the current range
 *  2. If middle == target  →  FOUND, return index
 *  3. If middle  < target  →  target is in the RIGHT half, move low up
 *  4. If middle  > target  →  target is in the LEFT  half, move high down
 *  5. Repeat until found or low > high (search space empty)
 *
 *  EXAMPLE:
 *  ────────
 *  Array  = [1, 3, 5, 7, 9]    Target = 7
 *  Step 1 : low=0 high=4 mid=2 → arr[2]=5  <  7  → low = 3
 *  Step 2 : low=3 high=4 mid=3 → arr[3]=7  == 7  → FOUND index 3 ✓
 */
public class BinarySearch {

    /**
     * Searches for {@code target} in a sorted integer array.
     *
     * @param arr    sorted array of integers (ascending order)
     * @param target the value to search for
     * @return       index of target if found, -1 otherwise
     */
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
