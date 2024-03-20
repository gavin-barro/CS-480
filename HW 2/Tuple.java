import java.util.*;


public class Tuple {
    private final int FIRST;
    private final int SECOND;

    public Tuple(int first, int second) {
        assert(first >= 0 && first <= 2);
        assert(second >= 0 && second <= 2);

        this.FIRST = first;
        this.SECOND = second;
    }

    public int getFirst() {
        return this.FIRST;
    }

    public int getSecond() {
        return this.SECOND;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tuple tuple = (Tuple) o;
        return Objects.equals(this.FIRST, tuple.FIRST) && Objects.equals(this.SECOND, tuple.SECOND);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.FIRST, this.SECOND);
    }

    @Override
    public String toString() {
        return "(" + this.FIRST + ", " + this.SECOND + ")";
    }
}
