import java.util.*;

public class Puzzle {

    // NOTE INDEXING STARTS AT 0 AND WE USE (3 X 3) TUPLE

    protected Map<Tuple, Object> puzzle;

    public Puzzle(Map<Tuple, Object> puzzle) {
        this.puzzle = puzzle;
    }

    public static Puzzle generateGoalState() {
        // LinkedHashMap used to maintain order of insertion
        Map<Tuple, Object> map = new LinkedHashMap<>();

        map.put(new Tuple(0, 0), 1);
        map.put(new Tuple(0, 1), 2);
        map.put(new Tuple(0, 2), 3);
        map.put(new Tuple(1, 0), 8);
        map.put(new Tuple(1, 1), null);
        map.put(new Tuple(1, 2), 4);
        map.put(new Tuple(2, 0), 7);
        map.put(new Tuple(2, 1), 6);
        map.put(new Tuple(2, 2), 5);

        return new Puzzle(map);
    }

    public Map<Tuple, Object> getPuzzle() {
        return this.puzzle;
    }

    public Object getValue(int row, int column) {
        assert (row <= 2 && row >= 0);
        assert (column <= 2 && column >= 0);

        Tuple key = new Tuple(row, column);
        return puzzle.get(key);
    }

    public void moveTileUp(int row, int column) {
        assert (row <= 2 && row >= 0);
        assert (column <= 2 && column >= 0);

        Object valueAtMiddle = getValue(1, 1);
        boolean check = isColumnFull(column);

        if (valueAtMiddle != null) {
            if (!check) {
                // Perform the move
                Object value = this.puzzle.get(new Tuple(row + 1, column));
                this.puzzle.put(new Tuple(row, column), value);
                this.puzzle.put(new Tuple(row + 1, column), null); // Clear the original position
                return;
            }
            // Grab the column, rows, and their corresponding values
            Object value = this.puzzle.get(new Tuple(row, column));
            int newRow = (row - 1 + 3) % 3;

            Object nextValue = this.puzzle.get(new Tuple(newRow, column));
            int nextRow = (newRow - 1 + 3) % 3;

            Object lastValue = this.puzzle.get(new Tuple(nextRow, column));
            int lastRow = (nextRow - 1 + 3) % 3;

            // Place the values in their new row, column spot
            this.puzzle.put(new Tuple(newRow, column), value);
            this.puzzle.put(new Tuple(nextRow, column), nextValue);
            this.puzzle.put(new Tuple(lastRow, column), lastValue);
        } else {
            // Perform the move
            Object value = this.puzzle.get(new Tuple(row + 1, column));
            this.puzzle.put(new Tuple(row, column), value);
            this.puzzle.put(new Tuple(row + 1, column), null); // Clear the original position
        }
    }


    public void moveTileDown(int row, int column) {
        assert (row <= 2 && row >= 0);
        assert (column <= 2 && column >= 0);

        Object valueAtMiddle = getValue(1, 1);
        boolean check = isColumnFull(column);
        if (valueAtMiddle != null) {
            if (!check) {
                Object value = this.puzzle.get(new Tuple(row - 1, column));
                this.puzzle.put(new Tuple(row, column), value);
                this.puzzle.put(new Tuple(row - 1, column), null); // Clear the original position
                return;
            }
            // Grab the column, rows, and their corresponding values
            Object value = this.puzzle.get(new Tuple(row, column));
            int newRow = (row + 1) % 3;

            Object nextValue = this.puzzle.get(new Tuple(newRow, column));
            int nextRow = (newRow + 1) % 3;

            Object lastValue = this.puzzle.get(new Tuple(nextRow, column));
            int lastRow = (nextRow + 1) % 3;

            // Place the values in their new row, column spot
            this.puzzle.put(new Tuple(newRow, column), value);
            this.puzzle.put(new Tuple(nextRow, column), nextValue);
            this.puzzle.put(new Tuple(lastRow, column), lastValue);
        } else {
            // Perform the move
            Object value = this.puzzle.get(new Tuple(row - 1, column));
            this.puzzle.put(new Tuple(row, column), value);
            this.puzzle.put(new Tuple(row - 1, column), null); // Clear the original position
        }

    }

    public void moveTileLeft(int row, int column) {
        assert (row <= 2 && row >= 0);
        assert (column <= 2 && column >= 0);

        Object valueAtMiddle = getValue(1, 1);
        boolean check = isRowFull(row);
        if (valueAtMiddle != null) {
            if (!check) {
                // Perform the move
                Object value = this.puzzle.get(new Tuple(row, column + 1));
                this.puzzle.put(new Tuple(row, column), value);
                this.puzzle.put(new Tuple(row, column + 1), null); // Clear the original position
                return;
            }
            // Grab the row, columns, and their corresponding values
            Object value = this.puzzle.get(new Tuple(row, column));
            int newColumn = (column - 1 + 3) % 3;

            Object nextValue = this.puzzle.get(new Tuple(row, newColumn));
            int nextColumn = (newColumn - 1 + 3) % 3;

            Object lastValue = this.puzzle.get(new Tuple(row, nextColumn));
            int lastColumn = (nextColumn - 1 + 3) % 3;

            // Place the values in their new row, column spot
            this.puzzle.put(new Tuple(row, newColumn), value);
            this.puzzle.put(new Tuple(row, nextColumn), nextValue);
            this.puzzle.put(new Tuple(row, lastColumn), lastValue);
        } else {
            // Perform the move
            Object value = this.puzzle.get(new Tuple(row, column + 1));
            this.puzzle.put(new Tuple(row, column), value);
            this.puzzle.put(new Tuple(row, column + 1), null); // Clear the original position
        }
    }

    public void moveTileRight(int row, int column) {
        assert (row <= 2 && row >= 0);
        assert (column <= 2 && column >= 0);

        Object valueAtMiddle = getValue(1, 1);
        boolean check = isRowFull(row);
        if (valueAtMiddle != null) {
            if (!check) {
                // Perform the move
                Object value = this.puzzle.get(new Tuple(row, column - 1));
                this.puzzle.put(new Tuple(row, column), value);
                this.puzzle.put(new Tuple(row, column - 1), null); // Clear the original position
                return;
            }
            // Grab the row, columns, and their corresponding values
            Object value = this.puzzle.get(new Tuple(row, column));
            int newColumn = (column + 1) % 3;

            Object nextValue = this.puzzle.get(new Tuple(row, newColumn));
            int nextColumn = (newColumn + 1) % 3;

            Object lastValue = this.puzzle.get(new Tuple(row, nextColumn));
            int lastColumn = (nextColumn + 1) % 3;

            // Place the values in their new row, column spot
            this.puzzle.put(new Tuple(row, newColumn), value);
            this.puzzle.put(new Tuple(row, nextColumn), nextValue);
            this.puzzle.put(new Tuple(row, lastColumn), lastValue);
        } else {
            // Perform the move
            Object value = this.puzzle.get(new Tuple(row, column - 1));
            this.puzzle.put(new Tuple(row, column), value);
            this.puzzle.put(new Tuple(row, column - 1), null); // Clear the original position
        }
    }

    public boolean isRowFull(int row) {
        assert (row >= 0 && row <= 2);

        for (int column = 0; column <= 2; column++) {
            if (getValue(row, column) == null) {
                // At least one cell in the row is empty
                return false;
            }
        }
        // All cells in the row are full
        return true;
    }

    public boolean isColumnFull(int column) {
        assert (column >= 0 && column <= 2);

        for (int row = 0; row <= 2; row++) {
            Object value = getValue(row, column);
            if (value == null) {
                // At least one cell in the column is empty
                return false;
            }
        }
        // All cells in the column are full
        return true;
    }

    public Tuple findPosition(int value) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Object tile = getValue(row, col);
                if (tile != null && (int) tile == value) {
                    return new Tuple(row, col);
                }
            }
        }
        return null; // Value not found
    }

    public int getColumn(Tuple tuple) {
        return tuple.getFirst();
    }

    public int getRow(Tuple tuple) {
        return tuple.getSecond();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Puzzle puzzle = (Puzzle) o;
        return Objects.equals(this.puzzle, puzzle.puzzle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.puzzle);
    }

    @Override
    public String toString() {
        return "Puzzle = " + this.puzzle ;
    }
}
