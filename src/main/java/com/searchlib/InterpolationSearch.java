package com.searchlib;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  InterpolationSearch.java  —  ADDED BY STUDENT (Algorithm 3)║
 * ╠══════════════════════════════════════════════════════════════╣
 * ║  ALGORITHM  : Improved Divide and Conquer                   ║
 * ║  REQUIRES   : Array SORTED + values uniformly distributed   ║
 * ║  TIME       : Best O(1)  |  Average O(log log n)  |         ║
 * ║               Worst O(n) (non-uniform data)                 ║
 * ║  SPACE      : O(1)                                          ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  HOW IT WORKS:
 *  ─────────────
 *  Like Binary Search, but instead of always picking the MIDDLE,
 *  it ESTIMATES where the target probably is using a formula —
 *  similar to how you find a name in a phone book:
 *  you don't open it to the exact middle to find "Smith",
 *  you open it near the END because S comes late in the alphabet.
 *
 *  THE FORMULA:
 *  ────────────
 *  pos = low + [ (target - arr[low]) * (high - low) ]
 *              ───────────────────────────────────────
 *                       (arr[high] - arr[low])
 *
 *  This calculates a proportional position within the current range.
 *
 *  EXAMPLE:
 *  ────────
 *  Array  = [10, 20, 30, 40, 50]    Target = 30
 *  low=0  high=4  arr[low]=10  arr[high]=50
 *  pos = 0 + [(30-10)*(4-0)] / (50-10)
 *      = 0 + [20 * 4] / 40
 *      = 0 + 80/40 = 2
 *  arr[2] = 30 == 30  → FOUND index 2 in ONE step ✓
 *  (Binary Search would need 2–3 steps for the same result)
 *
 *  WHEN TO USE:
 *  ────────────
 *  ✅  Large sorted arrays with UNIFORM distribution
 *      (e.g. student IDs, timestamps, sequential prices)
 *  ❌  Non-uniform data with big gaps or clusters
 *      (use Binary Search instead — safer)
 */
public class InterpolationSearch {

    /**
     * Searches for {@code target} in a sorted integer array
     * using the interpolation position formula.
     *
     * @param arr    sorted array of integers (ascending, ideally uniform)
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
