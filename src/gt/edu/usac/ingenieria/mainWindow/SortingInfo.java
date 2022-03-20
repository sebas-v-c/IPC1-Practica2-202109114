package gt.edu.usac.ingenieria.mainWindow;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;

public class SortingInfo implements Runnable{
    private WindowController controller;
    private ExecutionInfo execInfo;
    private static final int SLEEP_TIME = 500;

    public SortingInfo(WindowController controller, ExecutionInfo execInfo) {
        this.controller = controller;
        this.execInfo = execInfo;
    }

    @Override
    public void run() {
        synchronized (execInfo) {
            try {
                while (!execInfo.isSorted()) {
                    // TODO slowly update the timer for 0.5 seconds
                    double execTime = execInfo.getTotalTime() / 1_000_000_000;
                    controller.setTimeText(String.valueOf(execTime));

                    // TODO update the table


                    execInfo.notifyAll();
                    execInfo.wait();
                }
                // Wait until is called
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            controller.getView().setButtonsLock(true);
            controller.getView().setReportButtonVisible(true);
        }
    }
}
