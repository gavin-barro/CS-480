import java.util.*;

public class q2 {

    public static Puzzle generatePuzzleOne() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 1);
        map.put(new Tuple(0, 1), 2);
        map.put(new Tuple(0, 2), 3);
        map.put(new Tuple(1, 0), 8);
        map.put(new Tuple(1, 1), 4);
        map.put(new Tuple(1, 2), null);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), 6);
        map.put(new Tuple(2, 2), 5);

        return new Puzzle(map);
    }

    public static Puzzle generatePuzzleTwo() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 2);
        map.put(new Tuple(0, 1), 8);
        map.put(new Tuple(0, 2), 3);
        map.put(new Tuple(1, 0), null);
        map.put(new Tuple(1, 1), 1);
        map.put(new Tuple(1, 2), 4);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), 6);
        map.put(new Tuple(2, 2), 5);

        return new Puzzle(map);
    }

    public static Puzzle generatePuzzleThree() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 1);
        map.put(new Tuple(0, 1), 3);
        map.put(new Tuple(0, 2), 4);
        map.put(new Tuple(1, 0), 8);
        map.put(new Tuple(1, 1), 6);
        map.put(new Tuple(1, 2), 2);
        map.put(new Tuple(2, 0), null);
        map.put(new Tuple(2, 1), 7);
        map.put(new Tuple(2, 2), 5);

        return new Puzzle(map);
    }

    public static Puzzle generatePuzzleFour() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 4);
        map.put(new Tuple(0, 1), 1);
        map.put(new Tuple(0, 2), 3);
        map.put(new Tuple(1, 0), 6);
        map.put(new Tuple(1, 1), 8);
        map.put(new Tuple(1, 2), 2);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), null);
        map.put(new Tuple(2, 2), 5);

        return new Puzzle(map);
    }

    public static Puzzle generatePuzzleFive() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 8);
        map.put(new Tuple(0, 1), 7);
        map.put(new Tuple(0, 2), 6);
        map.put(new Tuple(1, 0), 5);
        map.put(new Tuple(1, 1), null);
        map.put(new Tuple(1, 2), 4);
        map.put(new Tuple(2, 0), 3);
        map.put(new Tuple(2, 1), 2);
        map.put(new Tuple(2, 2), 1);

        return new Puzzle(map);
    }


    public static List<String> solveBFS(Puzzle initial, Puzzle goal) {
        Queue<Puzzle> queue = new LinkedList<>();
        Set<Puzzle> visited = new HashSet<>();
        Map<Puzzle, Puzzle> parent = new LinkedHashMap<>();
        List<String> solution = new ArrayList<>();

        queue.offer(initial);
        visited.add(initial);

        while (!queue.isEmpty()) {
            Puzzle current = queue.poll();

            if (current.equals(goal)) {
                // Reconstruct the path from initial state to goal state
                while (current != null) {
                    solution.add(0, current.toString()); // Add the puzzle state to the solution
                    current = parent.get(current); // Move to the parent state
                }
                return solution;
            }

            // Generate successor states and enqueue them if not visited
            List<Puzzle> successors = generateNeighborsBFS(current);
            for (Puzzle successor : successors) {
                if (!visited.contains(successor)) {
                    visited.add(successor);
                    parent.put(successor, current);
                    queue.offer(successor);
                }
            }
        }

        // No solution found
        return null;
    }

    private static List<Puzzle> generateNeighborsBFS(Puzzle puzzle) {
        List<Puzzle> neighbors = new ArrayList<>();

        // Generate successor states by performing valid moves
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (puzzle.getValue(row, column) == null) {
//                  Perform moves and add resulting puzzles to successors
                    Puzzle up = new Puzzle(new LinkedHashMap<>(puzzle.getPuzzle()));
                    Puzzle down = new Puzzle(new LinkedHashMap<>(puzzle.getPuzzle()));
                    Puzzle left = new Puzzle(new LinkedHashMap<>(puzzle.getPuzzle()));
                    Puzzle right = new Puzzle(new LinkedHashMap<>(puzzle.getPuzzle()));

                    up.moveTileUp(row, column);
                    down.moveTileDown(row, column);
                    left.moveTileLeft(row, column);
                    right.moveTileRight(row, column);

                    // Add successors to the list
                    neighbors.add(up);
                    neighbors.add(down);
                    neighbors.add(left);
                    neighbors.add(right);

//                     Break since only one empty cell is allowed
                    break;
                }
            }
        }

        return neighbors;
    }



    public static void runBFS(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                              Puzzle puzzleFive, Puzzle goal) {

        long totalTime = 0;

        long startTime = System.currentTimeMillis();
        List<String> solutionOne = solveBFS(puzzleOne, goal);
        long endTime = System.currentTimeMillis();
//        System.out.println(solutionOne);

        if (solutionOne == null) {
            System.out.println("No solution found (1):");
            return;
        }

        long timeTakenMillis = endTime - startTime;
        double timeTakenSeconds = timeTakenMillis / 1000.0; // Convert milliseconds to seconds
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionTwo = solveBFS(puzzleTwo, goal);
        endTime = System.currentTimeMillis();

        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        timeTakenSeconds = timeTakenMillis / 1000.0; // Convert milliseconds to seconds
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionThree = solveBFS(puzzleThree, goal);
        endTime = System.currentTimeMillis();

        if (solutionThree == null) {
            System.out.println("No solution found (3):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        timeTakenSeconds = timeTakenMillis / 1000.0; // Convert milliseconds to seconds
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionFour = solveBFS(puzzleFour, goal);
        endTime = System.currentTimeMillis();

        if (solutionFour == null) {
            System.out.println("No solution found (4):");
            return;
        }
//
//        timeTakenMillis = endTime - startTime;
//        timeTakenSeconds = timeTakenMillis / 1000.0; // Convert milliseconds to seconds
//        System.out.println("Time taken for puzzleFour: " + timeTakenSeconds + " seconds");
//        totalTime += timeTakenMillis;
//
//        startTime = System.currentTimeMillis();
//        List<String> solutionFive = solveBFS(puzzleFive, goal);
//        endTime = System.currentTimeMillis();
//
//        if (solutionFive == null) {
//            System.out.println("No solution found (5):");
//            return;
//        }
//
//        timeTakenMillis = endTime - startTime;
//        timeTakenSeconds = timeTakenMillis / 1000.0; // Convert milliseconds to seconds
//        System.out.println("Time taken for puzzleFive: " + timeTakenSeconds + " seconds");
//        totalTime += timeTakenMillis;
//
//        // Calculate and print the average time taken
//        long averageTimeMillis = totalTime / 5;
//        double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds
//        System.out.println("Average time taken: " + averageTimeSeconds + " seconds");
    }

    public static List<Puzzle> depthFirstIterativeSearch(Puzzle initial, Puzzle goal) {
       return null;
    }

    private static List<Puzzle> generateSuccessorsDFID(Puzzle puzzle) {
        return null;
    }

    public static void runDFID(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                                Puzzle puzzleFive, Puzzle goal) {

        List<Puzzle> solutionOne = depthFirstIterativeSearch(puzzleOne, goal);

        // Solution check
        if (solutionOne == null) {
            System.out.println("No solution found (1):");
        }

        List<Puzzle> solutionTwo = depthFirstIterativeSearch(puzzleTwo, goal);

        // Solution check
        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
        }


    }


    public static void searchAStar(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                                Puzzle puzzleFive, Puzzle goal) {

    }

    public static void runAstar(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                               Puzzle puzzleFive, Puzzle goal) {

        List<Puzzle> solutionOne = depthFirstIterativeSearch(puzzleOne, goal);

        // Solution check
        if (solutionOne == null) {
            System.out.println("No solution found (1):");
        }

        List<Puzzle> solutionTwo = depthFirstIterativeSearch(puzzleTwo, goal);

        // Solution check
        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
        }


    }

    public static void main(String[] args) {
        // LinkedHashMap used to maintain order

        Puzzle puzzleOne = generatePuzzleOne();
        Puzzle puzzleTwo = generatePuzzleTwo();
        Puzzle puzzleThree = generatePuzzleThree();
        Puzzle puzzleFour = generatePuzzleFour();
        Puzzle puzzleFive = generatePuzzleFive();

        Puzzle goalState = Puzzle.generateGoalState();


        runBFS(puzzleOne, puzzleTwo, puzzleThree, puzzleFour, puzzleFive, goalState);
//        runDFID(puzzleOne, puzzleTwo, puzzleThree, puzzleFour, puzzleFive, goalState);
//        searchAStar(puzzleOne, puzzleTwo, puzzleThree, puzzleFour, puzzleFive, goalState);

    }

}