package at.ac.univie.se2.team0204.viewmodel.listview;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.model.room.taskranking.TaskRanking;

public class ListRepresentationHelperTest{

    @Test
    public void list_of_two_rankings_should_return_correct_map() {

        List<TaskRanking> list= new ArrayList<>();
        list.add(new TaskRanking(1,2,null,false));
        list.add(new TaskRanking(2,1,null,false));

        Map<Integer,Integer> result=ListRepresentationHelper.convertTaskRankingsToMap(list);

        Map<Integer,Integer> expected=new HashMap<>();
        expected.put(1,2);
        expected.put(2,1);

        assertEquals(expected, result);
    }

    @Test
    public void empty_list_should_return_empty_map() {

        List<TaskRanking> list= new ArrayList<>();

        Map<Integer,Integer> result=ListRepresentationHelper.convertTaskRankingsToMap(list);

        Map<Integer,Integer> expected=new HashMap<>();

        assertEquals(expected, result);
    }

    @Test
    public void list_with_negativ_values_should_return_empty_map() {

        List<TaskRanking> list= new ArrayList<>();
        list.add(new TaskRanking(1,-1,null,false));
        list.add(new TaskRanking(2,-1,null,false));

        Map<Integer,Integer> result=ListRepresentationHelper.convertTaskRankingsToMap(list);

        Map<Integer,Integer> expected=new HashMap<>();

        assertEquals(expected, result);
    }
}