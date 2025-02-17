package at.ac.univie.se2.team0204.viewmodel.taskiterator;

import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Task;

public abstract class TaskIterator {

    private Map<Integer, Task> tasks;
    private int index;

    public TaskIterator(List<Task> list){


    }

    public abstract Task next();

    public abstract Task first();

    public abstract Task current();

    public abstract boolean isDone();

}
