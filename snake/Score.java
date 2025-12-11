package snake;


public class Score {

    private int value = 0;

    public void increment() {
        value++;
    }

    public void reset() {
        value = 0;
    }

    public int getValue() {
        return value;
    }
}
