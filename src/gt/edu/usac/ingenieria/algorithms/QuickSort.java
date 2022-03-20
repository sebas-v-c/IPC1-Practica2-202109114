package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;

public class QuickSort extends Algorithm{
    public QuickSort(String[] countries, int[] values, boolean ascendent, ExecutionInfo executionInfo) {
        super(countries, values, ascendent, executionInfo);
    }

    @Override
    public void run() {
        synchronized (execInfo) {
            try {
                sort(execInfo, values, 0, values.length-1);
                execInfo.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            execInfo.setSorted(true);
        }
    }

    private void sort(ExecutionInfo execInfo, int[] values, int left, int right) throws InterruptedException {
        if (left >= right) {
            return;
        }

        int pivot = values[(left+right)/2];
        int index = division(values, pivot, left, right);

        // Sort left side and right side
        sort(execInfo, values, left, index - 1);
        sort(execInfo, values, index, right);
    }


    private int division(int[] values, int pivot, int left, int right) throws InterruptedException {
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
                    execInfo.notifyAll();
                    execInfo.wait();
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
                    execInfo.notifyAll();
                    execInfo.wait();
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
