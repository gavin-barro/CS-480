import java.util.Arrays;
import java.util.Random;

public class EightQueensSimulatedAnnealing {

    private static final int BOARD_SIZE = 8;
    private static final int MAX_ITERATIONS = 10000;
    private static final double INITIAL_TEMPERATURE = 100;
    private static final double COOLING_RATE = 0.003;

    public static void main(String[] args) {
        int[] solution = solve();
        if (solution != null) {
            System.out.println("Solution found:");
            printBoard(solution);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static int[] solve() {
        int[] currentSolution = generateRandomSolution();
        double temperature = INITIAL_TEMPERATURE;
        Random random = new Random();

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            if (isSolution(currentSolution)) {
                return currentSolution;
            }

            int[] neighbor = generateNeighbor(currentSolution);
            int currentCost = calculateCost(currentSolution);
            int neighborCost = calculateCost(neighbor);

            if (neighborCost < currentCost) {
                currentSolution = neighbor;
            } else {
                double acceptanceProbability = Math.exp((currentCost - neighborCost) / temperature);
                if (random.nextDouble() < acceptanceProbability) {
                    currentSolution = neighbor;
                }
            }

            temperature *= 1 - COOLING_RATE;
        }

        return null;
    }

    private static int[] generateRandomSolution() {
        int[] solution = new int[BOARD_SIZE];
        Random random = new Random();
        for (int i = 0; i < BOARD_SIZE; i++) {
            solution[i] = random.nextInt(BOARD_SIZE);
        }
        return solution;
    }

    private static int[] generateNeighbor(int[] solution) {
        Random random = new Random();
        int[] neighbor = Arrays.copyOf(solution, solution.length);
        int row = random.nextInt(BOARD_SIZE);
        int col = random.nextInt(BOARD_SIZE);
        neighbor[row] = col;
        return neighbor;
    }

    private static int calculateCost(int[] solution) {
        int cost = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = i + 1; j < BOARD_SIZE; j++) {
                if (solution[i] == solution[j] || Math.abs(solution[i] - solution[j]) == j - i) {
                    cost++;
                }
            }
        }
        return cost;
    }

    private static boolean isSolution(int[] solution) {
        return calculateCost(solution) == 0;
    }

    private static void printBoard(int[] solution) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (solution[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
