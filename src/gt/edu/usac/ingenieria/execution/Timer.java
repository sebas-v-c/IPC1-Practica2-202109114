package gt.edu.usac.ingenieria.execution;

import gt.edu.usac.ingenieria.mainWindow.WindowController;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Timer implements Runnable{
    private final WindowController controller;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
    private long elapseTime = 0;
    private long startTime = 0;
    private long endTime = 0;
    private long dif = 0;

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
            startTime = System.nanoTime();
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            endTime = System.nanoTime();

            dif = endTime - startTime;
            elapseTime += dif;
            dif =0;
//            long milli = TimeUnit.MICROSECONDS.convert(elapseTime, TimeUnit.NANOSECONDS);
            startTime = System.nanoTime();
//            long seconds = TimeUnit.SECONDS.convert(elapseTime, TimeUnit.NANOSECONDS);
            long minutes = TimeUnit.MINUTES.convert(elapseTime, TimeUnit.NANOSECONDS);
            controller.setTimeText(minutes + " : " + convertDouble(elapseTime) + " s");
            endTime = System.nanoTime();
            dif = endTime - startTime;
            elapseTime += dif;
            dif = 0;
        }
    }

    private String convertDouble(long elapseTime) {
        double milli = ((double) elapseTime/1_000_000_000.0);
        if (milli < 1) {
            DecimalFormat df = new DecimalFormat("#");
            df.setMaximumFractionDigits(8);
            String milliS = df.format(milli);
            String[] nums = milliS.split(",");
            return "0."+nums[1];
        } else {
            return Double.toString(milli);
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
