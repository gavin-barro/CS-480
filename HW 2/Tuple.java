import java.util.*;


public class Tuple {
    private final int first;
    private final int second;

    public Tuple(int first, int second) {
        assert(first >= 0 && first <= 2);
        assert(second >= 0 && second <= 2);

        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tuple tuple = (Tuple) o;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + this.getFirst() + ", " + this.getSecond() + ")";
    }
}
