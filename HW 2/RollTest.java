import java.util.*;

public class RollTest {

    public static void main(String[] args) {
        // tracing to solve puzzleTwo
        Puzzle goal = Puzzle.generateGoalState();
        Puzzle puzzleTwo =  q2.generatePuzzleTwo();
        System.out.println("Goal: " + goal);

        puzzleTwo.moveTileDown(1,0);
        puzzleTwo.moveTileLeft(0,0);
        puzzleTwo.moveTileUp(0, 1);
        puzzleTwo.moveTileRight(1, 1);
        puzzleTwo.moveTileDown(1, 0);
        puzzleTwo.moveTileLeft(0,0);
        puzzleTwo.moveTileUp(0, 1);

//        System.out.println("Puzzle 2: " + puzzleTwo);
//        System.out.println(goal.equals(puzzleTwo));

        Puzzle puzzleThree = q2.generatePuzzleThree();
        puzzleThree.moveTileLeft(2,0);
        puzzleThree.moveTileDown(2,1);
        puzzleThree.moveTileLeft(1,1);
        puzzleThree.moveTileDown(1,2);
        puzzleThree.moveTileRight(0,2);
        puzzleThree.moveTileUp(0, 1);

//        System.out.println("Puzzle 3: " + puzzleThree);
        System.out.println(goal.equals(puzzleThree));
    }
}
