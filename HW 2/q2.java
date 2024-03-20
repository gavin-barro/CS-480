import java.util.*;

public class q2 {

    static final int NUMBER_OF_PUZZLES = 5;

    // Define the Node class for A* search
    public static class Node {
        Puzzle state;
        int gScore;
        int fScore;

        public Node(Puzzle state, int gScore, int fScore) {
            this.state = state;
            this.gScore = gScore;
            this.fScore = fScore;
        }
    }

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

        map.put(new Tuple(0, 0), 1);
        map.put(new Tuple(0, 1), 3);
        map.put(new Tuple(0, 2), 4);
        map.put(new Tuple(1, 0), 8);
        map.put(new Tuple(1, 1), 6);
        map.put(new Tuple(1, 2), 2);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), 5);
        map.put(new Tuple(2, 2), null);

        return new Puzzle(map);
    }

    public static Puzzle generatePuzzleFive() {
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 1);
        map.put(new Tuple(0, 1), 3);
        map.put(new Tuple(0, 2), 4);
        map.put(new Tuple(1, 0), 8);
        map.put(new Tuple(1, 1), 2);
        map.put(new Tuple(1, 2), null);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), 6);
        map.put(new Tuple(2, 2), 5);

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
            List<Puzzle> neighbors = generateNeighbors(current);
            for (Puzzle neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        // No solution found, should never reach here
        return null;
    }

    private static List<Puzzle> generateNeighbors(Puzzle puzzle) {
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

                    // Add neighbors to the list
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

        if (solutionOne == null) {
            System.out.println("No solution found (1):");
            return;
        }

        long timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionTwo = solveBFS(puzzleTwo, goal);
        endTime = System.currentTimeMillis();

        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionThree = solveBFS(puzzleThree, goal);
        endTime = System.currentTimeMillis();

        if (solutionThree == null) {
            System.out.println("No solution found (3):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionFour = solveBFS(puzzleFour, goal);
        endTime = System.currentTimeMillis();

        if (solutionFour == null) {
            System.out.println("No solution found (4):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionFive = solveBFS(puzzleFive, goal);
        endTime = System.currentTimeMillis();

        if (solutionFive == null) {
            System.out.println("No solution found (5):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        // Calculate and print the average time taken
        long averageTimeMillis = totalTime / NUMBER_OF_PUZZLES;
        double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds
        System.out.println("Average time taken for BFS: " + averageTimeSeconds + " seconds");
    }

    public static List<String> depthFirstIterativeDeepening(Puzzle start, Puzzle goal) {
        boolean found = false;
        int depth = 1;
        List<String> solution = null;

        while (!found) {
            solution = depthLimitedSearch(start, goal, depth);
            if (solution != null) {
                found = true; // Solution found
            }
            depth++; // Increase the depth limit for the next iteration
        }

        return solution;
    }

    public static List<String> depthLimitedSearch(Puzzle start, Puzzle goal, int depthLimit) {
        Stack<Puzzle> stack = new Stack<>();
        Set<Puzzle> visited = new HashSet<>();
        Map<Puzzle, Puzzle> parent = new LinkedHashMap<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Puzzle current = stack.pop();

            if (current.equals(goal)) {
                return reconstructPath(current, parent);
            }

            if (parent.size() >= depthLimit) {
                continue; // Skip exploring further if depth limit is reached
            }

            List<Puzzle> neighbors = generateNeighbors(current);
            for (Puzzle neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    stack.push(neighbor);
                }
            }
        }

        return null; // Solution isn't found within the depth limit
    }

    private static List<String> reconstructPath(Puzzle node, Map<Puzzle, Puzzle> parent) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.toString()); // Add the puzzle state to the path
            node = parent.get(node); // Move to the parent state
        }
        return path;
    }



    public static void runDFID(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                               Puzzle puzzleFive, Puzzle goal) {

        long totalTime = 0;

        long startTime = System.currentTimeMillis();
        List<String> solutionOne = depthFirstIterativeDeepening(puzzleOne, goal);
        long endTime = System.currentTimeMillis();

        if (solutionOne == null) {
            System.out.println("No solution found (1):");
            return;
        }
        System.out.println("1");

        long timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionTwo = depthFirstIterativeDeepening(puzzleTwo, goal);
        endTime = System.currentTimeMillis();

        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
            return;
        }
        System.out.println("2");

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionThree = depthFirstIterativeDeepening(puzzleThree, goal);
        endTime = System.currentTimeMillis();

        if (solutionThree == null) {
            System.out.println("No solution found (3):");
            return;
        }
        System.out.println("3");

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;


        startTime = System.currentTimeMillis();
        List<String> solutionFour = depthFirstIterativeDeepening(puzzleFour, goal);
        endTime = System.currentTimeMillis();

        if (solutionFour == null) {
            System.out.println("No solution found (4):");
            return;
        }
        System.out.println("4");

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionFive = depthFirstIterativeDeepening(puzzleFive, goal);
        endTime = System.currentTimeMillis();

        if (solutionFive == null) {
            System.out.println("No solution found (5):");
            return;
        }
        System.out.println("5");

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        // Calculate and print the average time taken
        long averageTimeMillis = totalTime / NUMBER_OF_PUZZLES;
        double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds
        System.out.println("Average time taken for DFID: " + averageTimeSeconds + " seconds");
    }


    // Define the heuristic function (Manhattan distance)
    public static int heuristic(Puzzle current, Puzzle goal) {
        int distance = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Object value = current.getValue(row, col);
                if (value != null) {
                    Tuple goalPosition = goal.findPosition((int) value);
                    distance += Math.abs(row - goalPosition.getFirst()) + Math.abs(col - goalPosition.getSecond());
                }
            }
        }
        return distance;
    }

    public static List<String> searchAStar(Puzzle start, Puzzle goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        Map<Puzzle, Integer> gScore = new HashMap<>();
        Map<Puzzle, Puzzle> cameFrom = new HashMap<>();

        // Initialize the start node
        openSet.add(new Node(start, 0, heuristic(start, goal)));

        gScore.put(start, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            Puzzle currentPuzzle = current.state;

            if (currentPuzzle.equals(goal)) {
                // Reconstruct the path
                List<String> path = new ArrayList<>();
                while (cameFrom.containsKey(currentPuzzle)) {
                    path.add(0, currentPuzzle.toString());
                    currentPuzzle = cameFrom.get(currentPuzzle);
                }
                path.add(0, start.toString());
                return path;
            }

            for (Puzzle neighbor : generateNeighbors(currentPuzzle)) {
                int tentativeGScore = gScore.getOrDefault(currentPuzzle, Integer.MAX_VALUE) + 1;
                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, currentPuzzle);
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + heuristic(neighbor, goal);
                    openSet.add(new Node(neighbor, tentativeGScore, fScore));
                }
            }
        }

        // No path found, should never reach here
        return null;
    }

    public static void runAStar(Puzzle puzzleOne, Puzzle puzzleTwo, Puzzle puzzleThree, Puzzle puzzleFour,
                                Puzzle puzzleFive, Puzzle goal) {

        long totalTime = 0;

        long startTime = System.currentTimeMillis();
        List<String> solutionOne = searchAStar(puzzleOne, goal);
        long endTime = System.currentTimeMillis();

        if (solutionOne == null) {
            System.out.println("No solution found (1):");
            return;
        }

        long timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionTwo = searchAStar(puzzleTwo, goal);
        endTime = System.currentTimeMillis();

        if (solutionTwo == null) {
            System.out.println("No solution found (2):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionThree = searchAStar(puzzleThree, goal);
        endTime = System.currentTimeMillis();

        if (solutionThree == null) {
            System.out.println("No solution found (3):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionFour = searchAStar(puzzleFour, goal);
        endTime = System.currentTimeMillis();

        if (solutionFour == null) {
            System.out.println("No solution found (4):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        startTime = System.currentTimeMillis();
        List<String> solutionFive = searchAStar(puzzleFive, goal);
        endTime = System.currentTimeMillis();

        if (solutionFive == null) {
            System.out.println("No solution found (5):");
            return;
        }

        timeTakenMillis = endTime - startTime;
        totalTime += timeTakenMillis;

        // Calculate and print the average time taken
        long averageTimeMillis = totalTime / NUMBER_OF_PUZZLES;
        double averageTimeSeconds = averageTimeMillis / 1000.0; // Convert milliseconds to seconds
        System.out.println("Average time taken for A* search: " + averageTimeSeconds + " seconds");
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
        runAStar(puzzleOne, puzzleTwo, puzzleThree, puzzleFour, puzzleFive, goalState);

    }

}