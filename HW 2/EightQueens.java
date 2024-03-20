import java.util.Arrays;
import java.util.Random;

public class EightQueens {

    public static final int N = 8;


    public static int[] generateRandomState() {
        int[] state = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            state[i] = rand.nextInt(N);
        }
        return state;
    }

    public static void placeQueens(int[][] board, int[] state) {
        for (int i = 0; i < N; i++) {
            board[state[i]][i] = 1;
        }
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static boolean isGoalState(int[] state) {
        return calculateAttackingPairs(state) == 0;
    }

    public static int calculateAttackingPairs(int[] state) {
        int attackingPairs = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == j - i) {
                    attackingPairs++;
                }
            }
        }
        return attackingPairs;
    }

    public static boolean hillClimbing(int[][] board, int[] state) {
        Random rand = new Random();
        while (!isGoalState(state)) {
            int[] nextState = getBestNeighbor(state);
            if (calculateAttackingPairs(nextState) >= calculateAttackingPairs(state)) {
                state[rand.nextInt(N)] = rand.nextInt(N);
            } else {
                state = nextState;
            }
        }
        placeQueens(board, state);
        return isGoalState(state); // Return true if a valid solution is found, false otherwise
    }

    public static int[] getBestNeighbor(int[] state) {
        int minAttackingPairs = calculateAttackingPairs(state);
        int[] bestNeighbor = Arrays.copyOf(state, N);
        Random rand = new Random();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i] != j) {
                    int[] neighborState = Arrays.copyOf(state, N);
                    neighborState[i] = j;
                    int attackingPairs = calculateAttackingPairs(neighborState);
                    if (attackingPairs < minAttackingPairs) {
                        minAttackingPairs = attackingPairs;
                        bestNeighbor = Arrays.copyOf(neighborState, N);
                    }
                }
            }
        }

        return bestNeighbor;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        int[] state = generateRandomState();
        placeQueens(board, state);
        boolean isValidSolution = hillClimbing(board, state);
        if (isValidSolution) {
            System.out.println("Valid solution found:");
            printBoard(board);
        } else {
            System.out.println("No valid solution found.");
        }
    }
}
