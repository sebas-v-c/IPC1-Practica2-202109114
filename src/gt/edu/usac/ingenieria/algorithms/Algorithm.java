package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.execution.ExecutionInfo;
import gt.edu.usac.ingenieria.mainWindow.SortingInfo;
import gt.edu.usac.ingenieria.mainWindow.WindowController;

public abstract class Algorithm implements Runnable{
    protected String[] countries;
    protected int[] values;
    protected boolean ascendent;
    protected long movements;
    protected ExecutionInfo execInfo;
    protected long totalTime = 0;
    protected long startTime = 0;
    protected long endTime = 0;


    // TODO constructor method
    Algorithm(String[] countries, int[] values, boolean ascendent, ExecutionInfo execInfo) {
        this.countries = countries;
        this.values = values;
        this.ascendent = ascendent;
        this.execInfo = execInfo;
    }
    // Pass a WindowController object to update the view


    protected void stepFinished(long startTime, long endTime) {
        this.totalTime = endTime - startTime;
//        this.controller.moveFinished(getCountries(), getValues(), getMovements(), endTime - startTime);
    }

    protected double getTotalTime() {
        return this.totalTime;
    }

    protected String[] getCountries() {
        return countries;
    }

    protected int[] getValues() {
        return values;
    }

    protected long getMovements() {
        return movements;
    }

    public ExecutionInfo getExecInfo() {
        return execInfo;
    }
}
