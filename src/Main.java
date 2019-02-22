class Q {
    private int n;

    synchronized void get() {
        System.out.println("Got: " + n);
    }

    synchronized void put(int n) {
        this.n = n;
        System.out.println("Put: " + n);
    }
}

class Producer implements Runnable {
    private Q q;
    Thread t;

    Producer(Q q) {
        this.q = q;
        t = new Thread(this, "Producer");
    }

    @Override
    public void run() {
        int i = 0;

        while (i < 10) {
            q.put(i++);
        }
    }
}

class Consumer implements Runnable {
    private Q q;
    Thread t;

    Consumer(Q q) {
        this.q = q;
        t = new Thread(this, "Consumer");
    }

    @Override
    public void run() {
        int i = 0;

        while (i < 10) {
            q.get();
            i++;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);

        p.t.start();
        c.t.start();
    }
}
