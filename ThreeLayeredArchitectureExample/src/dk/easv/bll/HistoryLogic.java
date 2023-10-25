package dk.easv.bll;

import dk.easv.be.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryLogic {
    private final List<History> historyArray = new ArrayList<>();
    /**
     * Adds a new history to the arraylist containing whole history.
     * @param value is the value you want to add vat to.
     * @param vat is the percentage to be added to the value.
     * @param result is the new value where the percentage has been added.
     */
    public void AddHistory(double value, double vat, double result)
    {
        historyArray.add(new History(value, vat, result));
    }

    /**
     * Gets the history just before the last added.
     * @return the last shown History business entity.
     */
    public History GetLatestHistory()
    {
        return historyArray.get(historyArray.size() - 2);
    }

    /**
     * Tells if the history is empty or not.
     * @return true if there is a history and false if it is empty.
     */
    public boolean HasAny()
    {
        return !historyArray.isEmpty();
    }
}
