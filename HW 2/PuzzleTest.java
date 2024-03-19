import java.util.LinkedHashMap;
import java.util.Map;

public class PuzzleTest {
    public static void main(String[] args) {
        // Creating a new puzzle with initial configuration
        Map<Tuple, Object> initialConfiguration = new LinkedHashMap<>();
        initialConfiguration.put(new Tuple(0, 0), 1);
        initialConfiguration.put(new Tuple(0, 1), 2);
        initialConfiguration.put(new Tuple(0, 2), 3);
        initialConfiguration.put(new Tuple(1, 0), null);
        initialConfiguration.put(new Tuple(1, 1), 4);
        initialConfiguration.put(new Tuple(1, 2), 5);
        initialConfiguration.put(new Tuple(2, 0), 6);
        initialConfiguration.put(new Tuple(2, 1), 7);
        initialConfiguration.put(new Tuple(2, 2), 8);

        Puzzle puzzle = new Puzzle(initialConfiguration);

        // Printing initial puzzle state
        System.out.println("Initial Puzzle State:");
        System.out.println(puzzle);

        // Moving tile at (0,2) right
        System.out.println("Moving tile at (0,2) right:");
        puzzle.moveTileRight(0, 2);
        System.out.println(puzzle);

//        // Moving tile at (2,1) left
//        System.out.println("Moving tile at (2,1) left:");
//        puzzle.moveTile(2, 1, "Left");
//        System.out.println(puzzle);

//        // Moving tile at (0,0) up
//        System.out.println("Moving tile at (0,0) up:");
//        puzzle.moveTile(0, 0, "Up");
//        System.out.println(puzzle);
//
//        // Moving tile at (1,1) down
//        System.out.println("Moving tile at (1,1) down:");
//        puzzle.moveTile(1, 1, "Down");
//        System.out.println(puzzle);
//
//        // Moving tile at (1,2) right
//        System.out.println("Moving tile at (1,2) right:");
//        puzzle.moveTile(1, 2, "Right");
//        System.out.println(puzzle);
//

        // Attempting to move tile at (1,1) right (no effect since it's already at the rightmost position)
//        System.out.println("Attempting to move tile at (1,1) right:");
//        puzzle.moveTile(1, 1, "Right");
//        System.out.println(puzzle);

    }
}

