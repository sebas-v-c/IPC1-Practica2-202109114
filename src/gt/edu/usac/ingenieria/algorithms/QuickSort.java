package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;

public class QuickSort extends Algorithm{
    private WindowController controller;
    public QuickSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo, WindowController controller) {
        super(countries, values, ascendent, executionInfo);
        this.controller = controller;
    }

    public void sort() {
        execInfo.setSorted(false);
        sort(execInfo, values, 0, values.length-1);
        execInfo.setSorted(true);
        controller.moveFinished(countries, values, execInfo);
    }

    private void sort(ExecutionInfo execInfo, int[] values, int left, int right){
        if (left >= right) {
            return;
        }

        int pivot = values[(left+right)/2];
        int index = division(values, pivot, left, right);

        // Sort left side and right side
        sort(execInfo, values, left, index - 1);
        sort(execInfo, values, index, right);
    }


    private int division(int[] values, int pivot, int left, int right){
        if (ascendent) {
            while (left <= right) {
                startTime = System.nanoTime();
                while (values[left] < pivot) {
                    left++;
                }

                while (values[right] > pivot) {
                    right--;
                }
                if (left <= right) {
                    swap(values, left, right);
                    left++;
                    right--;
                    endTime = System.nanoTime();
                    execInfo.setExecTime(endTime - startTime);
                    execInfo.setMoves(1);
                    controller.moveFinished(countries, values, execInfo);
                }
            }
        } else {
            while (left <= right) {
                startTime = System.nanoTime();
                while (values[left] > pivot) {
                    left++;
                }

                while (values[right] < pivot) {
                    right--;
                }
                if (left <= right) {
                    swap(values, left, right);
                    left++;
                    right--;
                    endTime = System.nanoTime();
                    execInfo.setExecTime(endTime - startTime);
                    execInfo.setMoves(1);
                    controller.moveFinished(countries, values, execInfo);
                }
            }
        }

        return left;
    }

    private void swap(int[] values, int left, int right) {
        int tempInt = values[left];
        String tempString = countries[left];
        values[left] = values[right];
        countries[left] = countries[right];
        values[right] = tempInt;
        countries[right] = tempString;
    }
}
