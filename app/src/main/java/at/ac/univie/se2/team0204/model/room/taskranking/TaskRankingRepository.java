package at.ac.univie.se2.team0204.model.room.taskranking;

import androidx.lifecycle.LiveData;

import java.util.List;

import at.ac.univie.se2.team0204.model.TaskListProxy;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.AppointmentDao;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.model.room.TaskDao;
import at.ac.univie.se2.team0204.model.room.TaskDatabase;
import at.ac.univie.se2.team0204.model.room.ToDoDao;
import at.ac.univie.se2.team0204.model.room.Todo;

public class TaskRankingRepository {


    private static final String TAG = "TaskRankingRepository";

    private TaskRankingDao taskRankingDao;
//#5
    public List<TaskRanking> taskRankingLiveData;

    public TaskRankingRepository(TaskRankingDao taskRankingDao) {
        this.taskRankingDao = taskRankingDao;
        //#6
        taskRankingLiveData = taskRankingDao.getAll();

    }

    public void insertTaskRanking(TaskRanking taskRanking) {
        this.taskRankingDao.insertAll(taskRanking);
    }

    public void deleteTaskRanking(TaskRanking taskRanking) {
        this.taskRankingDao.delete(taskRanking);

    }

    public void updateTaskRanking(TaskRanking taskRanking) {
        this.taskRankingDao.updateTaskRankings(taskRanking);
        System.out.println("Updated TaskInformation. ID: "+taskRanking.getTaskId()+", Color: "+taskRanking.getColor());
    }

    public List<TaskRanking> getTaskRankings() {
        return this.taskRankingLiveData;
    }

    // And all other insert, delete, update.. method calls to the DAO


    public TaskRanking getTaskRanking(int id) {

        return this.taskRankingDao.getTaskRankingByID(id);
    }

}


