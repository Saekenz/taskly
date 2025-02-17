package at.ac.univie.se2.team0204.viewmodel;

import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Task;

public class TaskImportIterator {

    /**
     * List containing all raw {@link Task}s ready for importing
     */
    private final List<Map<String, String>> rawImportTasks;

    /**
     * Index of the next returned object.
     */
    private int currentIndex;

    /**
     * Creates a new iterator.
     * @param tasks The tasks the iterator will iterate.
     */
    public TaskImportIterator(List<Map<String, String>> tasks) {
        this.rawImportTasks = tasks;
        currentIndex = 0;
    }

    /**
     * Checks if the iterator has more objects to return.
     *
     * @return Returns true if the iterator has not reached the last element, yet.
     */
    public boolean hasNext() {
        return currentIndex < rawImportTasks.size();
    }

    /**
     * Grabs the next element in the iterator.
     *
     * @return Returns the next available {@link Task}.
     */
    public Map<String, String> next() {
        return rawImportTasks.get(currentIndex++);
    }

    /**
     * Returns the elements held by the iterator.
     *
     * @return Returns the size of the iterator.
     */
    public int getIteratorSize() {
        return this.rawImportTasks.size();
    }
}
