package com.searchlib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class SearchLibraryTest {


    @Nested
    @DisplayName("1. BinarySearch — All-Paths Coverage")
    class BinarySearchTests {

        private BinarySearch bs;

        @BeforeEach
        void setUp() { bs = new BinarySearch(); }

        @Test
        @DisplayName("TC-BS-01 | null array → -1")
        void nullArray() {
            assertEquals(-1, bs.search(null, 5));
        }

        @Test
        @DisplayName("TC-BS-02 | empty array → -1")
        void emptyArray() {
            assertEquals(-1, bs.search(new int[]{}, 5));
        }

        @Test
        @DisplayName("TC-BS-03 | target IS the mid element on first probe")
        void targetAtMid() {
            // [1,3,5,7,9]  target=5  mid=2  arr[2]=5  → found immediately
            int[] arr = {1, 3, 5, 7, 9};
            assertEquals(2, bs.search(arr, 5));
        }

        @Test
        @DisplayName("TC-BS-04 | target in RIGHT half (low moves up)")
        void targetRightHalf() {
            // [1,3,5,7,9]  target=7
            // Step1: mid=2 arr[2]=5 < 7 → low=3
            // Step2: mid=3 arr[3]=7 == 7 → return 3
            int[] arr = {1, 3, 5, 7, 9};
            assertEquals(3, bs.search(arr, 7));
        }

        @Test
        @DisplayName("TC-BS-05 | target in LEFT half (high moves down)")
        void targetLeftHalf() {
            // [1,3,5,7,9]  target=1
            // Step1: mid=2 arr[2]=5 > 1 → high=1
            // Step2: mid=0 arr[0]=1 == 1 → return 0
            int[] arr = {1, 3, 5, 7, 9};
            assertEquals(0, bs.search(arr, 1));
        }

        @Test
        @DisplayName("TC-BS-06 | target NOT present → -1")
        void notFound() {
            int[] arr = {1, 3, 5, 7, 9};
            assertEquals(-1, bs.search(arr, 4));
        }

        @Test
        @DisplayName("TC-BS-07 | single-element array — target FOUND")
        void singleFound() {
            assertEquals(0, bs.search(new int[]{42}, 42));
        }

        @Test
        @DisplayName("TC-BS-08 | single-element array — target NOT found")
        void singleNotFound() {
            assertEquals(-1, bs.search(new int[]{42}, 10));
        }

        @Test
        @DisplayName("TC-BS-09 | target at LAST index")
        void lastIndex() {
            assertEquals(4, bs.search(new int[]{2, 4, 6, 8, 10}, 10));
        }

        @Test
        @DisplayName("TC-BS-10 | array with NEGATIVE numbers")
        void negativeNumbers() {
            assertEquals(1, bs.search(new int[]{-10, -5, 0, 5, 10}, -5));
        }
    }


    @Nested
    @DisplayName("2. LinearSearch — All-Paths Coverage")
    class LinearSearchTests {

        private LinearSearch ls;

        @BeforeEach
        void setUp() { ls = new LinearSearch(); }

        @Test
        @DisplayName("TC-LS-01 | null array → -1")
        void nullArray() {
            assertEquals(-1, ls.search(null, 5));
        }

        @Test
        @DisplayName("TC-LS-02 | empty array → -1")
        void emptyArray() {
            assertEquals(-1, ls.search(new int[]{}, 3));
        }

        @Test
        @DisplayName("TC-LS-03 | target at FIRST position (index 0)")
        void targetFirst() {
            // arr[0]=7 == 7 → loop exits on first iteration
            assertEquals(0, ls.search(new int[]{7, 2, 9, 4}, 7));
        }

        @Test
        @DisplayName("TC-LS-04 | target at LAST position")
        void targetLast() {
            // Loop checks all elements before finding target at the end
            assertEquals(3, ls.search(new int[]{7, 2, 9, 4}, 4));
        }

        @Test
        @DisplayName("TC-LS-05 | target in MIDDLE")
        void targetMiddle() {
            assertEquals(2, ls.search(new int[]{3, 6, 9, 12, 15}, 9));
        }

        @Test
        @DisplayName("TC-LS-06 | target NOT present → -1")
        void notFound() {
            assertEquals(-1, ls.search(new int[]{1, 2, 3, 4, 5}, 99));
        }

        @Test
        @DisplayName("TC-LS-07 | UNSORTED array — Linear Search still works!")
        void unsorted() {
            // Key advantage: Binary and Interpolation would FAIL here
            assertEquals(2, ls.search(new int[]{15, 3, 7, 1, 9}, 7));
        }

        @Test
        @DisplayName("TC-LS-08 | DUPLICATE values — returns first occurrence")
        void duplicates() {
            assertEquals(0, ls.search(new int[]{5, 5, 5}, 5));
        }
    }



    @Nested
    @DisplayName("3. InterpolationSearch — All-Paths Coverage")
    class InterpolationSearchTests {

        private InterpolationSearch is;

        @BeforeEach
        void setUp() { is = new InterpolationSearch(); }

        @Test
        @DisplayName("TC-IS-01 | null array → -1")
        void nullArray() {
            assertEquals(-1, is.search(null, 5));
        }

        @Test
        @DisplayName("TC-IS-02 | empty array → -1")
        void emptyArray() {
            assertEquals(-1, is.search(new int[]{}, 5));
        }

        @Test
        @DisplayName("TC-IS-03 | target BELOW minimum → while loop skipped")
        void belowMin() {
            // target=5 < arr[0]=10 → while condition false immediately
            assertEquals(-1, is.search(new int[]{10, 20, 30, 40, 50}, 5));
        }

        @Test
        @DisplayName("TC-IS-04 | target ABOVE maximum → while loop skipped")
        void aboveMax() {
            // target=60 > arr[4]=50 → while condition false immediately
            assertEquals(-1, is.search(new int[]{10, 20, 30, 40, 50}, 60));
        }

        @Test
        @DisplayName("TC-IS-05 | target found at CALCULATED probe position")
        void foundAtProbe() {
            // [10,20,30,40,50]  target=30
            // pos = 0 + [(30-10)*(4-0)]/(50-10) = 0+80/40 = 2
            // arr[2]=30 == 30 → FOUND in one step
            assertEquals(2, is.search(new int[]{10, 20, 30, 40, 50}, 30));
        }

        @Test
        @DisplayName("TC-IS-06 | target in RIGHT portion (low moves up)")
        void rightPortion() {
            assertEquals(8, is.search(new int[]{1,2,3,4,5,6,7,8,9,10}, 9));
        }

        @Test
        @DisplayName("TC-IS-07 | target in LEFT portion (high moves down)")
        void leftPortion() {
            // Non-uniform data forces probe to overshoot right → adjust left
            assertEquals(1, is.search(new int[]{1,10,20,30,40,50,60,70,80,100}, 10));
        }

        @Test
        @DisplayName("TC-IS-08 | single element — target FOUND (low==high branch)")
        void singleFound() {
            assertEquals(0, is.search(new int[]{7}, 7));
        }

        @Test
        @DisplayName("TC-IS-09 | single element — target NOT found (low==high branch)")
        void singleNotFound() {
            assertEquals(-1, is.search(new int[]{7}, 3));
        }

        @Test
        @DisplayName("TC-IS-10 | target within range but ABSENT → -1")
        void absentInRange() {
            // 25 is between 10 and 50 but not actually in the array
            assertEquals(-1, is.search(new int[]{10, 20, 30, 40, 50}, 25));
        }
    }



    @Nested
    @DisplayName("4. SearchOrder — MC/DC + All-Paths Coverage")
    class SearchOrderTests {

        // Sorted array used across all routing tests
        // Index : 0  1  2  3   4   5
        // Value : 2  4  6  8  10  12
        private int[] arr;

        @BeforeEach
        void setUp() {
            arr = new int[]{2, 4, 6, 8, 10, 12};
        }


        @Test
        @DisplayName("TC-SO-01 | PATH P1 | MC/DC C1=T | Empty array → -1")
        void emptyArrayGuard() {
            SearchOrder o = new SearchOrder(new int[]{}, 5, SearchOrder.Algorithm.BINARY);
            assertEquals(-1, o.execute(),
                "Empty array must return -1 before any algorithm runs");
        }

        // ── PATH P2  /  MC/DC  C2 = TRUE ─────────────────────────────
        //  Proves: C2 (BINARY) INDEPENDENTLY routes to BinarySearch.
        @Test
        @DisplayName("TC-SO-02 | PATH P2 | MC/DC C2=T | BINARY — target FOUND")
        void binaryFound() {
            // arr=[2,4,6,8,10,12]  target=8  → index 3
            SearchOrder o = new SearchOrder(arr, 8, SearchOrder.Algorithm.BINARY);
            assertEquals(3, o.execute());
        }

        @Test
        @DisplayName("TC-SO-03 | PATH P2 | MC/DC C2=T | BINARY — target NOT FOUND")
        void binaryNotFound() {
            SearchOrder o = new SearchOrder(arr, 7, SearchOrder.Algorithm.BINARY);
            assertEquals(-1, o.execute());
        }

        // ── PATH P3  /  MC/DC  C3 = TRUE ─────────────────────────────
        //  Proves: C3 (LINEAR) INDEPENDENTLY routes to LinearSearch.
        @Test
        @DisplayName("TC-SO-04 | PATH P3 | MC/DC C3=T | LINEAR — target FOUND")
        void linearFound() {
            // arr=[2,4,6,8,10,12]  target=10  → index 4
            SearchOrder o = new SearchOrder(arr, 10, SearchOrder.Algorithm.LINEAR);
            assertEquals(4, o.execute());
        }

        @Test
        @DisplayName("TC-SO-05 | PATH P3 | MC/DC C3=T | LINEAR — target NOT FOUND")
        void linearNotFound() {
            SearchOrder o = new SearchOrder(arr, 3, SearchOrder.Algorithm.LINEAR);
            assertEquals(-1, o.execute());
        }

        // ── PATH P4  /  MC/DC  C4 = TRUE ─────────────────────────────
        //  Proves: C4 (INTERPOLATION) INDEPENDENTLY routes to InterpolationSearch.
        @Test
        @DisplayName("TC-SO-06 | PATH P4 | MC/DC C4=T | INTERPOLATION — target FOUND")
        void interpolationFound() {
            // arr=[2,4,6,8,10,12]  target=6  → index 2
            SearchOrder o = new SearchOrder(arr, 6, SearchOrder.Algorithm.INTERPOLATION);
            assertEquals(2, o.execute());
        }

        @Test
        @DisplayName("TC-SO-07 | PATH P4 | MC/DC C4=T | INTERPOLATION — target NOT FOUND")
        void interpolationNotFound() {
            SearchOrder o = new SearchOrder(arr, 5, SearchOrder.Algorithm.INTERPOLATION);
            assertEquals(-1, o.execute());
        }

        // ── CONSTRUCTOR GUARD TESTS ───────────────────────────────────
        @Test
        @DisplayName("TC-SO-08 | null data → IllegalArgumentException")
        void nullDataThrows() {
            assertThrows(IllegalArgumentException.class,
                () -> new SearchOrder(null, 5, SearchOrder.Algorithm.BINARY));
        }

        @Test
        @DisplayName("TC-SO-09 | null algorithm → IllegalArgumentException")
        void nullAlgorithmThrows() {
            assertThrows(IllegalArgumentException.class,
                () -> new SearchOrder(arr, 5, null));
        }
    }
}
