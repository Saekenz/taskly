package at.ac.univie.se2.team0204.model.room.taskranking;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_ranking")
public class TaskRanking {

    @PrimaryKey
    private int taskId;

    //@NonNull
    //private Task task;

    private int rank;
    private String color;
    private boolean isHidden;

    public TaskRanking(int taskId, int rank, String color, boolean isHidden){
        this.taskId=taskId;
        this.rank=rank;
        this.color=color;
        this.isHidden=isHidden;
    }

    /*public void setTask(Task task) {
           this.task = task;
       }*/

    public void setRank(int rank) {
            this.rank = rank;
        }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

        /*public int getTask() {
            return task;
        }*/

    public int getRank() {
            return rank;
        }

    public int getTaskId() {
        return taskId;
    }

    public String getColor() {
        return color;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    }


