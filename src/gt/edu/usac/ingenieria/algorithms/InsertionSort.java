package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;

public class InsertionSort extends Algorithm {
    public InsertionSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo) {
        super(countries, values, ascendent, executionInfo);
    }

    @Override
    public void run() {
        synchronized (execInfo) {
            sort(execInfo);
        }
    }

    public void sort(ExecutionInfo execInfo){

    }
}
