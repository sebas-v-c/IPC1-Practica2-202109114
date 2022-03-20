package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;

public class BubbleSort extends Algorithm {
    private WindowController controller;
    public BubbleSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo, WindowController controller) {
        super(countries, values, ascendent, executionInfo);
        this.controller = controller;
    }

    @Override
    public void run() {
        execInfo.setSorted(false);
        sort(execInfo);
        execInfo.setSorted(true);
        controller.setCountries(countries);
        controller.setValues(values);
        controller.moveFinished(countries, values, execInfo);
    }

    public void sort(ExecutionInfo execInfo){
        if (ascendent) {
            int n = values.length;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (values[j] > values[j + 1]) {
                        swap(values, j, j + 1);
                        execInfo.setMoves(1);
                        controller.setCountries(countries);
                        controller.setValues(values);
                        controller.moveFinished(countries, values, execInfo);
                    }
                }
            }
        } else {
            int n = values.length;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (values[j] < values[j + 1]) {
                        swap(values, j, j + 1);
                        execInfo.setMoves(1);
                        controller.moveFinished(countries, values, execInfo);
                    }
                }
            }
        }
    }


    private void swap(int[] values, int left, int right) {
        int tempInt = values[left];
        String tempString = countries[left];
        values[left] = values[right];
        countries[left] = countries[right];
        values[right] = tempInt;
        countries[right] = tempString;
        controller.setCountries(countries);
        controller.setValues(values);
    }

}
