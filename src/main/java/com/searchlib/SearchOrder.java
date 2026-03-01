package com.searchlib;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║  SearchOrder.java  —  THE "ORDER CLASS"                     ║
 * ╠══════════════════════════════════════════════════════════════╣
 * ║  PURPOSE  : Manager / router for all three algorithms       ║
 * ║  CFG      : execute() is the subject of CFG construction    ║
 * ║  MC/DC    : execute() contains all conditions under test    ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 *  WHAT IT DOES:
 *  ─────────────
 *  You give it:
 *    1. An integer array to search in
 *    2. The target value to find
 *    3. Your choice of algorithm (BINARY / LINEAR / INTERPOLATION)
 *
 *  It validates the input, then routes to the correct algorithm.
 *
 * ════════════════════════════════════════════════════════════════
 *  CONTROL FLOW GRAPH  —  execute() method
 * ════════════════════════════════════════════════════════════════
 *
 *  [N1: START]
 *       │
 *       ▼
 *  [N2: data.length == 0 ?] ──YES──► [N3: return -1] ──────────────┐
 *       │                                                           │
 *      NO                                                           │
 *       │                                                           │
 *       ▼                                                           │
 *  [N4: algorithm == BINARY ?] ──YES──► [N5: BinarySearch.search]──┤
 *       │                                                           │
 *      NO                                                           │
 *       │                                                           │
 *       ▼                                                           │
 *  [N6: algorithm == LINEAR ?] ──YES──► [N7: LinearSearch.search]──┤
 *       │                                                           │
 *      NO                                                           │
 *       │                                                           │
 *       ▼                                                           │
 *  [N8: algorithm == INTERPOLATION ?]                               │
 *       │                  │                                        │
 *      NO                 YES                                       │
 *       │                  │                                        │
 *       ▼                  ▼                                        │
 *  [N10: throw]   [N9: InterpolationSearch.search] ────────────────┘
 *       │                                                           │
 *       └───────────────────────────────────────────────────────────┘
 *                                                                   │
 *                                                            [N11: EXIT]
 *
 * ════════════════════════════════════════════════════════════════
 *  MC/DC CONDITIONS  —  execute() method
 * ════════════════════════════════════════════════════════════════
 *  C1  :  data.length == 0            (empty-array guard)
 *  C2  :  algorithm == BINARY         (routes to BinarySearch)
 *  C3  :  algorithm == LINEAR         (routes to LinearSearch)
 *  C4  :  algorithm == INTERPOLATION  (routes to InterpolationSearch)
 * ════════════════════════════════════════════════════════════════
 */
public class SearchOrder {

    /** The three available search algorithms. */
    public enum Algorithm {
        BINARY,
        LINEAR,
        INTERPOLATION
    }

    // ── Fields ───────────────────────────────────────────────────
    private final int[]     data;
    private final int       target;
    private final Algorithm algorithm;

    // ── Constructor ──────────────────────────────────────────────

    /**
     * Creates a new SearchOrder.
     *
     * @param data      the array to search (must not be null)
     * @param target    the value to find
     * @param algorithm the algorithm to use (must not be null)
     * @throws IllegalArgumentException if data or algorithm is null
     */
    public SearchOrder(int[] data, int target, Algorithm algorithm) {

        if (data == null) {
            throw new IllegalArgumentException("Data array must not be null.");
        }
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm must not be null.");
        }

        this.data      = data;
        this.target    = target;
        this.algorithm = algorithm;
    }

    // ── execute() ────────────────────────────────────────────────

    /**
     * Runs the chosen search algorithm and returns the result.
     *
     * Decision Point 1  ←  Condition C1
     *   Is the array empty?  If YES return -1 immediately.
     *
     * Decision Point 2  ←  Conditions C2, C3, C4
     *   Which algorithm should run?
     *
     * @return index of target in data[], or -1 if not found
     */
    public int execute() {

        // ── C1: empty-array guard ────────────────────────────────
        if (data.length == 0) {
            return -1;
        }

        // ── C2 / C3 / C4: algorithm routing ─────────────────────
        switch (algorithm) {

            case BINARY:
                return new BinarySearch().search(data, target);

            case LINEAR:
                return new LinearSearch().search(data, target);

            case INTERPOLATION:
                return new InterpolationSearch().search(data, target);

            default:
                throw new UnsupportedOperationException(
                        "Unknown algorithm: " + algorithm);
        }
    }

    // ── Getters ──────────────────────────────────────────────────
    public int[]     getData()       { return data;      }
    public int       getTarget()     { return target;    }
    public Algorithm getAlgorithm()  { return algorithm; }
}
