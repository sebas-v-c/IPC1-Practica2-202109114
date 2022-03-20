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
                    execInfo.setAlgorithmsFinished(false);
                    execInfo.setDisplayFinished(false);
                    while (!execInfo.isAlgorithmsFinished()) {
                        execInfo = execInfo;
                    }
                    double execTime = execInfo.getTotalTime() / 1_000_000_000;
                    controller.setMovesText(String.valueOf(execInfo.getMoves()));
                    controller.setTimeText(String.valueOf(execTime));
                    System.out.println(execTime);
                    System.out.println(execInfo.getMoves());

                    // TODO update the table


                    execInfo.setDisplayFinished(true);
                    execInfo.notifyAll();
                }
                // Wait until is called
            } catch (Exception e) {
                e.printStackTrace();
            }

            controller.getView().setButtonsLock(true);
            controller.getView().setReportButtonVisible(true);
        }
    }
}
