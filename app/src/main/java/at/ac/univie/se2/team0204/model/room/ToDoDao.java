package at.ac.univie.se2.team0204.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    /**
     * Creates a new row in the table {@link Todo}.
     * @param task The newly created {@link Todo} entry.
     */
    @Insert
    void insert(Todo task);

    /**
     * Creates a new {@link Todo} in the database and returns its id.
     * @param task The newly created {@link Todo}.
     * @return The identifier of the newly created entry.
     */
    @Insert
    long insertAndReturnID(Todo task);

    /**
     * Deletes a {@link Todo} from the database.
     * @param task The {@link Todo} to be deleted.
     */
    @Delete
    void delete(Todo task);

    /**
     * Returns all {@link Todo} objects from the table.
     * @return All {@link Todo} objects in a LiveData object.
     */
    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAll();

    /**
     * Returns a {@link Todo} from the database identified by its id.
     * @param taskId The id of the {@link Todo}.
     * @return Either the identified {@link Todo}, or if the id does not exist null.
     */
    @Query("SELECT * FROM todo WHERE taskId = :taskId LIMIT 1")
    Todo getTodo(String taskId);

    /**
     * Returns all {@link Todo} objects with an attachment.
     * @return All {@link Todo} objects with attachment in a List.
     */
    @Transaction
    @Query("SELECT * FROM todo")
    List<TodoWithAttachments> getTodosWithAttachments();

    /**
     * Updates a specific {@link Todo}.
     * @param task The {@link Todo} that should be updated.
     */
    @Update
    void updateTodo(Todo task);

    /**
     * Returns a {@link Todo} identified by its title and description.
     * @param title The title used to identify the {@link Todo}.
     * @param description The description used to identify the {@link Todo}.
     * @return Either all ids of the found tasks, or null if none were found.
     */
    @Query("SELECT taskId FROM TODO WHERE title = :title AND description = :description")
    int getTodoByTitleAndDesc(String title, String description);

    /**
     * Returns the {@link Todo} identified by the task id.
     * @param taskID The id of the identified {@link Todo}.
     * @return The to-do found by the query.
     */
    @Query("SELECT * FROM TODO WHERE taskId = :taskID")
    Todo getTodoByID(int taskID);

    /**
     * Returns a specific {@link Todo} and all its children.
     * @param id The identifier of the {@link Todo}.
     * @return The {@link Todo} and all its children identified by the id.
     */
    @Query("SELECT * FROM todo WHERE taskId =:id")
    TodoWithSubTodos getTodoWithSubTodos(int id);

    /**
     * Returns all {@link TodoWithSubTodos} objects containing each {@link Todo} and its children from the database.
     * @return All {@link Todo} objects and their children in the database.
     */
    @Query("SELECT * FROM todo")
    List<TodoWithSubTodos> getTodosWithSubTodos();

    /**
     * Deletes a {@link Todo} by its id.
     * @param id The id of the {@link Todo} to be deleted.
     * @return True if the {@link Todo} was deleted, else false.
     */
    @Query("DELETE FROM todo WHERE taskId =:id")
    int deleteTodoById(int id);

    /**
     * Deletes a {@link Todo} by its parents' id.
     * @param pId The id its parent.
     * @return True if the {@link Todo} was deleted, else false.
     */
    @Query("DELETE FROM todo WHERE parentID =:pId")
    int deleteTodoByParentId(int pId);

    /**
     * Deletes a {@link Todo} and all its children.
     * @param id The id of the deleted {@link Todo}.
     */
    @Transaction
    default void deleteTodoAndSubTodosById(int id) {
        deleteTodoByParentId(id);
        deleteTodoById(id);
    }
}
