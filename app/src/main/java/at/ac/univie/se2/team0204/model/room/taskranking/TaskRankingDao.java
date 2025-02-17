package at.ac.univie.se2.team0204.model.room.taskranking;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Todo;

@Dao
public interface TaskRankingDao {
    @Insert
    void insertAll(TaskRanking... taskRankings);

    @Delete
    void delete(TaskRanking taskRanking);
//#7
    @Query("SELECT * FROM task_ranking")
    List<TaskRanking> getAll();

    @Update
    public void updateTaskRankings(TaskRanking... taskRankings);

    @Insert
    void insert(TaskRanking taskRanking);

    @Query("SELECT COUNT(*) FROM task_ranking")
    int getRowCount();

    @Query("SELECT * FROM task_ranking WHERE taskId = :id")
    TaskRanking getTaskRankingByID(int id);
}
