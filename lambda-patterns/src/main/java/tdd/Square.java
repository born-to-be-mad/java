package tdd;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
public class Square implements Shape {

    private final int numberOfCorners;
    private final int numberOfEdges;

    public Square() {
        numberOfCorners = 4;
        numberOfEdges = 4;
    }

    @Override
    public int getNumberOfCorners() {
        return numberOfCorners;
    }

    @Override
    public int getNumberOfEdges() {
        return numberOfEdges;
    }
}
