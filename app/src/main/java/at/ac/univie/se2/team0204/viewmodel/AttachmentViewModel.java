package at.ac.univie.se2.team0204.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import at.ac.univie.se2.team0204.model.TaskRepository;
import at.ac.univie.se2.team0204.model.room.AppointmentAttachment;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.model.room.TaskDatabase;
import at.ac.univie.se2.team0204.model.room.TodoAttachment;

public class AttachmentViewModel extends AndroidViewModel {

    private final TaskRepository repository;

    public AttachmentViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase database = TaskDatabase.getRoomDatabase(application);
        repository = new TaskRepository(database.toDoDao(), database.appointmentDao(), database.attachmentDao());
    }

    public long insertAttachment(Attachment attachment){
        return this.repository.insertAttachment(attachment);
    }

    /**
     * Calls the repository to insert an {@link Attachment} and get its rowID.
     * @param attachment The {@link Attachment} to be inserted.
     * @return The rowID of the inserted {@link Attachment} (returns the attachmentID if already exists).
     */
    public long insertAttachmentAndGetID(Attachment attachment){
        return this.repository.insertAttachmentAndGetID(attachment);
    }

    /**
     * Calls the repository to insert into the {@link TodoAttachment} table.
     * @param todoAttachment The object to be inserted.
     * @return RowID if successful else returns -1.
     */
    public long insertTodoAttachment(TodoAttachment todoAttachment){
        return this.repository.insertTodoAttachment(todoAttachment);
    }

    /**
     * Calls the repository to insert into the {@link AppointmentAttachment} table.
     * @param appointmentAttachment The object to be inserted.
     * @return RowID if successful else returns -1.
     */
    public long insertAppointmentAttachment(AppointmentAttachment appointmentAttachment){
        return this.repository.insertAppointmentAttachment(appointmentAttachment);
    }

}