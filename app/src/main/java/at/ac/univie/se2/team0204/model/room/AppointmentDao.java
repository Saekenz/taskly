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
public interface AppointmentDao {

    /**
     * Deletes an {@link Appointment} from the database.
     * @param appointment The {@link Appointment} to be deleted.
     */
    @Delete
    void delete(Appointment appointment);

    /**
     * Returns all {@link Appointment} objects in the table.
     * @return All {@link Appointment} objects in the table as a LiveData object.
     */
    @Query("SELECT * FROM appointment")
    LiveData<List<Appointment>> getAll();

    /**
     * Returns a single {@link Appointment}.
     * @param taskId The id of the {@link Appointment}.
     * @return Either the {@link Appointment}, if one can be found using the id. Else null is returned.
     */
    @Query("SELECT * FROM appointment WHERE taskId = :taskId LIMIT 1")
    Appointment getAppointment(String taskId);

    /**
     * Inserts a new {@link Appointment} into the table.
     * @param appointment The {@link Appointment} to be inserted.
     */
    @Insert
    void insert(Appointment appointment);

    /**
     * Inserts an {@link Appointment} into the table.
     * @param appointment The {@link Appointment} to be inserted.
     * @return The ID of the inserted {@link Appointment}.
     */
    @Insert
    long insertAndReturnID(Appointment appointment);

    /**
     * Updates an {@link Appointment}.
     * @param appointment The {@link Appointment} to be updated.
     */
    @Update
    void updateAppointment(Appointment appointment);

    /**
     * Returns all {@link Appointment} objects with attachments.
     * @return All {@link Appointment} objects with attachments in a list.
     */
    @Transaction
    @Query("SELECT * FROM appointment")
    List<AppointmentWithAttachments> getAppointmentsWithAttachments();
}
