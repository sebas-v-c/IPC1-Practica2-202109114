package gt.edu.usac.ingenieria.algorithms;

import gt.edu.usac.ingenieria.mainWindow.WindowController;

public abstract class Algorithm implements Runnable{
    private String[] countries;
    private int[] values;
    private long movements;
    private WindowController controller;
    private double totalTime = 0;


    // TODO constructor method
    // Pass a WindowController object to update the view


    @Override
    public abstract void run();

    protected abstract void sort();

    protected void stepFinished(double startTime, double endTime) {
        this.totalTime = endTime - startTime;
        this.controller.moveFinished(getCountries(), getValues(), getMovements(), endTime - startTime);
    }

    private double getTotalTime() {
        return this.totalTime;
    }

    public String[] getCountries() {
        return countries;
    }

    public int[] getValues() {
        return values;
    }

    public long getMovements() {
        return movements;
    }
}
