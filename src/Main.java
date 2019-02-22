class NewThread implements Runnable {
    Thread t;

    NewThread() {
        //Create a new thread
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
    }

    //New thread calls this version of run() when instantiated
    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child thread interrupted");
        }
        System.out.println("Exiting child thread");
    }
}

public class Main {

    public static void main(String[] args) {
        NewThread nt = new NewThread();

        nt.t.start();

        try {
            for (int n = 5; n > 0; n--) {
                System.out.println("Main thread: " + n);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread exiting");
    }
}
