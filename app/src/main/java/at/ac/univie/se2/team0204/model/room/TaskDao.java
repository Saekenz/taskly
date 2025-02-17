package at.ac.univie.se2.team0204.model.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface TaskDao {

    /**
     * Inserts a new {@link Task} into the database.
     * @param task The {@link Task} to be inserted.
     */
    void insert(Task task);

    /**
     * Updates a {@link Task} that already exists in the database.
     * @param task The {@link Task} that should be updated.
     */
    void update(Task task);

    /**
     * Deletes a {@link Task} from the database.
     * @param task The {@link Task} that should be deleted.
     */
    void delete(Task task);

    /**
     * Retrieves all {@link Task} objects in the database.
     * @return All {@link Task} objects in a LiveData object.
     */
    LiveData<List<Task>> getAll();

}
