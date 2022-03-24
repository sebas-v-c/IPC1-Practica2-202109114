package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;

public class InsertionSort extends Algorithm{
    private WindowController controller;

    public InsertionSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo, WindowController controller) {
        super(countries, values, ascendent, executionInfo);
        this.controller = controller;
    }

    @Override
    public void run() {
        execInfo.setSorted(false);
        sort(execInfo, values, 0, values.length-1);
        execInfo.setSorted(true);
        controller.setValues(values);
        controller.setCountries(countries);
        controller.moveFinished(countries, values, execInfo);
    }
}
