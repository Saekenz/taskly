package at.ac.univie.se2.team0204.model;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.model.room.TaskDao;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.ClosestToDeadlineIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.PriorityIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.AppointmentIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.TaskIterator;
import at.ac.univie.se2.team0204.viewmodel.taskiterator.UserCustomizedIterator;

public class TaskListProxy {
    private List<Task> list;
    private List<TaskDao> daos;

    public TaskListProxy(List<TaskDao> daos) {
        this.list = new ArrayList<>();
        this.daos = daos;
    }

    public void readDataFromDao(TaskDao dao){

    }

    public TaskIterator createIterator(IteratorMode mode){

        switch (mode){
            case PRIORITY: return new PriorityIterator(list);
            case DEADLINE: return new ClosestToDeadlineIterator(list);
            case CUSTOMIZED: return new UserCustomizedIterator(list);
        }

        return null;
    }

    public void insert(Task task){
        TaskDao dao=getTaskDao(task);
        dao.insert(task);
    }

    public void update(Task task){
        TaskDao dao=getTaskDao(task);
        dao.update(task);
    }

    public void delete(Task task){
        TaskDao dao=getTaskDao(task);
        dao.delete(task);
    }

    public List<Task> getAll(){
        return list;
    }

    public TaskDao getTaskDao(Task task){
        return null;
    }
}
