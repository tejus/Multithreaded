class CallMe {
    synchronized void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    private String msg;
    private CallMe target;
    Thread t;

    public Caller(CallMe target, String msg) {
        this.msg = msg;
        this.target = target;
        t = new Thread(this);
    }

    @Override
    public void run() {
        target.call(msg);
    }
}

public class Main {

    public static void main(String[] args) {
        CallMe target = new CallMe();
        Caller caller1 = new Caller(target, "Hello");
        Caller caller2 = new Caller(target, "Synchronised");
        Caller caller3 = new Caller(target, "World");

        caller1.t.start();
        caller2.t.start();
        caller3.t.start();

        try {
            System.out.println("Waiting for threads to finish");
            caller1.t.join();
            caller2.t.join();
            caller3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
}
