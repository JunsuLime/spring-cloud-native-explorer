package junsulime.cloud.toolkit.counter;

public class UnSynchronizedCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int value() {
        return count;
    }
}
