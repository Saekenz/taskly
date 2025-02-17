package at.ac.univie.se2.team0204.viewmodel.listview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.taskranking.TaskRanking;

public class ListRepresentationHelper {

    public static Map<Integer,Integer> convertTaskRankingsToMap(List<TaskRanking> taskRankings){

        Map<Integer,Integer> result=new HashMap<Integer,Integer>();

        for(TaskRanking r : taskRankings) {
            if (r.getRank() >= 0) {
                result.put(r.getTaskId(), r.getRank());
            }
        }

        return result;
    }

    public static Map<Integer,Boolean> convertIsHiddenToMap(List<TaskRanking> taskRankings){

        Map<Integer,Boolean> result=new HashMap<>();

        for(TaskRanking r : taskRankings) {
            if(r.getIsHidden())
                result.put(r.getTaskId(), r.getIsHidden());
        }

        return result;
    }
}
