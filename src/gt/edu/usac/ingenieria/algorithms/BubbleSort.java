package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;

public class BubbleSort extends Algorithm {
    private WindowController controller;
    public BubbleSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo, WindowController controller) {
        super(countries, values, ascendent, executionInfo);
        this.controller = controller;
    }

    public void sort() {
        execInfo.setSorted(false);
        sort(execInfo);
        execInfo.setSorted(true);
        controller.moveFinished(countries, values, execInfo);
    }

    public void sort(ExecutionInfo execInfo, ){
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
}
