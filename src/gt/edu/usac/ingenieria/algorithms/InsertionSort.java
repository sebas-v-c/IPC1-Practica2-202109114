package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;

public class InsertionSort extends Algorithm {
    public InsertionSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo) {
        super(countries, values, ascendent, executionInfo);
    }

    @Override
    public void run() {
        synchronized (execInfo) {
            try {
//                sort(execInfo, values, 0, values.length-1);
                execInfo.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            execInfo.setSorted(true);
        }    }

    public void sort(ExecutionInfo execInfo){

    }
}
