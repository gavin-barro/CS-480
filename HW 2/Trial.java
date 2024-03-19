import java.util.*;

public class Trial {


    // Check to make sure the puzzle works as intended when initialized
    public static void main(String[] args) {

        // Adding goal state
        Map<Tuple, Object> map = new LinkedHashMap<>();

        // 0,0
        map.put(new Tuple(0, 0), 1);

        // 0,1
        map.put(new Tuple(0, 1), 2);

        // 0,2
        map.put(new Tuple(0, 2), 3);

        // 1,0
        map.put(new Tuple(1, 0), 8);

        // 1,1
        map.put(new Tuple(1, 1), null);

        // 1,2
        map.put(new Tuple(1, 2), 4);

        // 2,0
        map.put(new Tuple(2, 0), 7);

        // 2,1
        map.put(new Tuple(2, 1), 6);

        // 2,2
        map.put(new Tuple(2, 2), 5);


        Puzzle puzzle = new Puzzle(map);

        System.out.println(puzzle.getValue(0, 0));
        System.out.println(puzzle.getValue(0, 1));
        System.out.println(puzzle.getValue(0, 2));
        System.out.println(puzzle.getValue(1, 0));
        System.out.println(puzzle.getValue(1, 1));
        System.out.println(puzzle.getValue(1, 2));
        System.out.println(puzzle.getValue(2, 0));
        System.out.println(puzzle.getValue(2, 1));
        System.out.println(puzzle.getValue(2, 2));

        System.out.println(puzzle);

    }
}
