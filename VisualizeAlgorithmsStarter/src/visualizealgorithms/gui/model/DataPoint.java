package visualizealgorithms.gui.model;

import javafx.beans.property.*;

public class DataPoint {
    private final IntegerProperty input;
    private final LongProperty runTime;

    public DataPoint(Integer input, Long runTime) {
        this.input = new SimpleIntegerProperty(input);
        this.runTime = new SimpleLongProperty(runTime);
    }

    public DataPoint(Integer input) {
        this.input = new SimpleIntegerProperty(input);
        this.runTime = new SimpleLongProperty(0L);
    }


    public int getInput() {
        return input.get();
    }

    public IntegerProperty inputProperty() {
        return input;
    }

    public long getRunTime() {
        return runTime.get();
    }

    public LongProperty runTimeProperty() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime.set(runTime);
    }
}
