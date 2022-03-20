package gt.edu.usac.ingenieria.execution;

public class ExecutionInfo {
    private long execTime;
    private long totalTime;
    private long moves;
    private boolean sorted;
    private boolean algorithmsFinished = false;
    private boolean displayFinished = false;


    public ExecutionInfo(long execTime, long moves) {
        this.execTime = execTime;
        this.moves = moves;
        totalTime = 0;
        sorted = false;
    }

    public boolean isAlgorithmsFinished() {
        return algorithmsFinished;
    }

    public void setAlgorithmsFinished(boolean algorithmsFinished) {
        this.algorithmsFinished = algorithmsFinished;
    }

    public boolean isDisplayFinished() {
        return displayFinished;
    }

    public void setDisplayFinished(boolean displayFinished) {
        this.displayFinished = displayFinished;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public double getExecTime() {
        return execTime;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public void setExecTime(long execTime) {
        this.totalTime += execTime;
        this.execTime = execTime;
    }

    public long getMoves() {
        return moves;
    }

    public void setMoves(long moves) {
        this.moves += moves;
    }
}
