package java9;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:28
 * @since : 2019.10
 **/
public class ValueHolder {
    private int x;

    private ValueHolder(int x) {
        this.x = x;
    }

    public static ValueHolder of(int x) {
        return new ValueHolder(x);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}