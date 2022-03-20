package gt.edu.usac.ingenieria.execution;

import gt.edu.usac.ingenieria.mainWindow.WindowController;

public class Timer implements Runnable{
    private final WindowController controller;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
    private long elapseTime = 0;

    public Timer(WindowController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (running) {
            synchronized (pauseLock) {
                if (!running) {
                    break;
                }
                if (paused) {
                    try {
                        synchronized (pauseLock) {
                            pauseLock.wait();
                        }
                    } catch (InterruptedException ex) {
                        break;
                    }
                    if (!running) {
                        break;
                    }
                }
            }
            long startTime = System.nanoTime();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            long endTime = System.nanoTime();

            elapseTime += endTime - startTime;
            controller.setTimeText("00:" + elapseTime + " s");
        }
    }

    public void stop() {
        running = false;
        resume();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public long getElapseTime() {
        return this.elapseTime;
    }

    public void resetTimer() {
        this.elapseTime = 0;
    }
}
