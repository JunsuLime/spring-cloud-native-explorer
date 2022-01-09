package junsulime.cloud.concurrency;

public class UnsynchronizedCounter {
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
