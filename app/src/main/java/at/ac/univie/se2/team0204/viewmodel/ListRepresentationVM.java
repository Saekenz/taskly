package at.ac.univie.se2.team0204.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.model.room.taskranking.TaskRanking;
import at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingDatabase;
import at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingRepository;
import at.ac.univie.se2.team0204.viewmodel.listview.ListRepresentationHelper;

public class ListRepresentationVM extends AndroidViewModel {


    private List<TaskRanking> taskInformationList;
    private List<Todo> currentTodoList;

    private final TaskRankingRepository repository;

    public ListRepresentationVM(Application application) {
        super(application);
        TaskRankingDatabase database = TaskRankingDatabase.getRoomDatabase(application);

        repository = new TaskRankingRepository(database.taskRankingDao());

        taskInformationList = repository.getTaskRankings();
        currentTodoList=null;
    }

    /**
     * Removes old entries of tasks that don't exist anymore.
     * @param existingTodos
     */
    public void tidyUpVM(List<Todo> existingTodos){

        List<TaskRanking> rankings= taskInformationList;

        if(rankings!=null) {
            for (TaskRanking taskRanking : rankings) {
                int taskRankingID = taskRanking.getTaskId();
                boolean stillExists = false;

                for (Todo todo : existingTodos) {
                    if (taskRankingID == todo.getTaskId())
                        stillExists = true;
                }

                if (!stillExists) {
                    repository.deleteTaskRanking(taskRanking);
                }
            }
        }

        currentTodoList=existingTodos;
    }

    /**
     *
     * @return Current List sorted by the rankings chosen by the user.
     */
    public List<Todo> getSortedTodos(){

        List<TaskRanking> taskRankings= taskInformationList;

        //If the current Database for TaskRankings is empty, return the list as it was:
        if(taskRankings==null)
            return currentTodoList;

        //Get Maps to help with following tasks
        Map<Integer,Integer> ranks= ListRepresentationHelper.convertTaskRankingsToMap(taskRankings);
        Map<Integer,Boolean> isHiddenInformationMap= ListRepresentationHelper.convertIsHiddenToMap(taskRankings);

        //Remove Hidden Tasks
        List<Todo> toBeRemoved = new ArrayList<>();
        for(int i=0;i<currentTodoList.size();i++){
            int taskID=currentTodoList.get(i).getTaskId();
            if(isHiddenInformationMap.get(taskID)!=null)
                if(isHiddenInformationMap.get(taskID)==true)
                    toBeRemoved.add(currentTodoList.get(i));
        }
        currentTodoList.removeAll(toBeRemoved);

        //Sort Ranks
        Collections.sort(currentTodoList,(todo1,todo2)->{
            int rank1,rank2;

            if(ranks.get(todo1.getTaskId())==null)
                rank1=Integer.MAX_VALUE;
            else
                rank1=ranks.get(todo1.getTaskId());

            if(ranks.get(todo2.getTaskId())==null)
                rank2=Integer.MAX_VALUE;
            else
                rank2=ranks.get(todo2.getTaskId());

            return rank1 - rank2;
        });

        return currentTodoList;
    }

    public List<TaskRanking> getTaskInformations(){
        return taskInformationList;
    }


    public void registerChangeInRanking(int positionA, int positionB) {

        Todo todoA = currentTodoList.get(positionA);
        Todo todoB = currentTodoList.get(positionB);

        insertRankInDatabase(todoA, positionA);
        insertRankInDatabase(todoB, positionB);
    }

    private void insertRankInDatabase(Todo currentTodo,int rank){
        TaskRanking taskRanking=repository.getTaskRanking(currentTodo.getTaskId());

        if(taskRanking==null) {
            repository.insertTaskRanking(new TaskRanking(currentTodo.getTaskId(), rank, "",false));
        }
        else {
            taskRanking.setRank(rank);
            repository.updateTaskRanking(taskRanking);
        }
    }

    public String getColor(int taskID){
        for(TaskRanking taskInfo:taskInformationList){
            if(taskInfo.getTaskId()==taskID)
                return taskInfo.getColor();
        }
        //#Todo Stephan replace green with default color
        return "green";
    }

    public void updateColorInDatabase(Todo currentTodo, String color) {
        TaskRanking taskInformation=repository.getTaskRanking(currentTodo.getTaskId());

        if(taskInformation==null) {
            //#Todo Stephan set to default color
            repository.insertTaskRanking(new TaskRanking(currentTodo.getTaskId(), -1 , color,false));
        }
        else{
            taskInformation.setColor(color);
            repository.updateTaskRanking(taskInformation);
        }
    }

    public boolean checkIfHidden(int taskID){
        for(TaskRanking taskInfo:taskInformationList){
            if(taskInfo.getTaskId()==taskID)
                return taskInfo.getIsHidden();
        }

        return false;
    }

    public void updateIsHiddenInDatabase(Todo currentTodo,boolean isHidden) {
        TaskRanking taskInformation = repository.getTaskRanking(currentTodo.getTaskId());
        if(taskInformation==null){
            repository.insertTaskRanking(new TaskRanking(currentTodo.getTaskId(), -1 , "",isHidden));
        }
        else{
            taskInformation.setIsHidden(isHidden);
            repository.updateTaskRanking(taskInformation);
        }

    }
}
