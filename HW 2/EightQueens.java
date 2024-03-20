import java.util.Arrays;
import java.util.Random;

public class EightQueens {
    public static final int N = 8; // Size of the chessboard

    // Configure the initial state randomly
    public static void configureInitialRandomly(int[][] board, int[] state) {
        Random rand = new Random();

        // Assign a random row for each queen in the state
        for (int i = 0; i < N; i++) {
            state[i] = rand.nextInt(N);
            board[state[i]][i] = 1; // Mark the position of the queen on the board
        }
    }

    // Compare two states
    public static boolean compareStates(int[] state1, int[] state2) {
        return Arrays.equals(state1, state2);
    }

    // Method to print the chessboard
    public static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Fill the board with a specific value
    public static void fill(int[][] board, int value) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = value;
            }
        }
    }

    // Calculate the number of attacking pairs
    public static int calculateObjective(int[][] board, int[] state) {
        int attackingPairs = 0;

        // Iterate through each queen
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // Check if two queens are in the same row, column, or diagonal
                if (state[i] == state[j] || i == j || Math.abs(state[i] - state[j]) == Math.abs(i - j)) {
                    attackingPairs++;
                }
            }
        }

        return attackingPairs;
    }

    // Generate the board based on the state
    public static void generateBoard(int[][] board, int[] state) {
        fill(board, 0);
        for (int i = 0; i < N; i++) {
            board[state[i]][i] = 1;
        }
    }

    // Copy the state of one array to another
    private static void copyState(int[] state1, int[] state2) {
        System.arraycopy(state2, 0, state1, 0, N);
    }

    // Find a neighboring state
    public static void findNeighboringState(int[][] board, int[] state) {
        int[][] opBoard = new int[N][N];
        int[] opState = new int[N];
        copyState(opState, state);
        generateBoard(opBoard, opState);

        int opObjective = calculateObjective(opBoard, opState);

        int[][] neighborBoard = new int[N][N];
        int[] neighborState = new int[N];
        copyState(neighborState, state);
        generateBoard(neighborBoard, neighborState);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i] != j) {
                    neighborState[i] = j;
                    neighborBoard[neighborState[i]][i] = 1;
                    neighborBoard[state[i]][i] = 0;

                    int objective = calculateObjective(neighborBoard, neighborState);

                    if (objective <= opObjective) {
                        opObjective = objective;
                        copyState(opState, neighborState);
                        generateBoard(opBoard, opState);
                    }

                    neighborBoard[neighborState[i]][i] = 0;
                    neighborState[i] = state[i];
                    neighborBoard[state[i]][i] = 1;
                }
            }
        }

        copyState(state, opState);
        fill(board, 0);
        generateBoard(board, state);
    }

    // Implementing the hill climbing algorithm
    public static void hillClimbing(int[][] board, int[] state) {
        int[][] neighborBoard = new int[N][N];
        int[] neighborState = new int[N];
        generateBoard(neighborBoard, neighborState);

        do {
            copyState(state, neighborState);
            generateBoard(board, state);
            findNeighboringState(neighborBoard, neighborState);

            // Check if the current state is the goal state
            if (compareStates(state, neighborState)) {
                printBoard(board);
                break;
            } else if (calculateObjective(board, state) == calculateObjective(neighborBoard,
                    neighborState)) {
                neighborState[(int) (Math.random() * N)] = (int) (Math.random() * N);
                generateBoard(neighborBoard, neighborState);
            }
        } while (true);
    }

    public static void main(String[] args) {
        int[] state = new int[N];
        int[][] board = new int[N][N];

        configureInitialRandomly(board, state);
        hillClimbing(board, state);

        if (calculateObjective(board, state) == 0) {
            System.out.println("Valid solution found.");
        } else {
            System.out.println("No valid solution found.");
        }
    }

}