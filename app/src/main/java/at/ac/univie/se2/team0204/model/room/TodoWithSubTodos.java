package at.ac.univie.se2.team0204.model.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TodoWithSubTodos {
    @Embedded public Todo todo;
    @Relation(
            parentColumn = "taskId",
            entityColumn = "parentID"
    )
    public List<Todo> subTodos;
}
