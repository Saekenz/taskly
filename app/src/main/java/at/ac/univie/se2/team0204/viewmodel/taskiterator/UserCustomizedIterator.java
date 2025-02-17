package at.ac.univie.se2.team0204.viewmodel.taskiterator;

import java.util.List;

import at.ac.univie.se2.team0204.model.room.Task;

public class UserCustomizedIterator extends TaskIterator{

    public UserCustomizedIterator(List<Task> list) {
        super(list);
    }

    @Override
    public Task next() {
        return null;
    }

    @Override
    public Task first() {
        return null;
    }

    @Override
    public Task current() {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
