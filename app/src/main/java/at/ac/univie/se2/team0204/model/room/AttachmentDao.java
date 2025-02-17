package at.ac.univie.se2.team0204.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttachmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Attachment attachments);

    @Update
    void update(Attachment attachment);

    @Delete
    void delete(Attachment appointment);

    @Query("SELECT * FROM attachment")
    List<Attachment> getAllAttachments();

    @Query("DELETE FROM attachment")
    void deleteAllAttachments();

    @Query("SELECT * FROM attachment WHERE path =:filepath")
    Attachment getAttachmentByPath(String filepath);

    @Transaction
    default long insertAndGetID(Attachment attachment){
        long id = insert(attachment);
        if(id == -1){
            return getAttachmentByPath(attachment.getPath()).getAttachmentID();
        }
        return id;
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertTodoAttachment(TodoAttachment todoAttachment);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertAppointmentAttachment(AppointmentAttachment appointmentAttachment);
}
