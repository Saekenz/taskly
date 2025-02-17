package at.ac.univie.se2.team0204.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class, Appointment.class, Attachment.class, TodoAttachment.class, AppointmentAttachment.class}, version = 5)
public abstract class TaskDatabase extends RoomDatabase {

    /**
     * Returns a new {@link AppointmentDao} object.
     * @return The new Dao object.
     */
    public abstract AppointmentDao appointmentDao();

    /**
     * Returns a new {@link ToDoDao} object.
     * @return The new Dao object.
     */
    public abstract ToDoDao toDoDao();

    /**
     * Returns a new {@link AttachmentDao} object.
     * @return The new Dao object.
     */
    public abstract AttachmentDao attachmentDao();

    // TAKEN FROM https://developer.android.com/codelabs/android-room-with-a-view#7

    /**
     * The Database class holds itself. It is a singleton.
     */
    private static volatile TaskDatabase TASK_DATABASE;

    /**
     * Number of threads used by the database executor.
     */
    private static final int NUMBER_OF_THREADS = 2;

    /**
     * Executes database writes.
     */
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    /**
     * Creates or returns a new {@link TaskDatabase}.
     * @param context The context of the application.
     * @return A {@link TaskDatabase} object.
     */
    public static TaskDatabase getRoomDatabase(final Context context) {
        if(TASK_DATABASE == null) {
            synchronized (TaskDatabase.class) {
                if (TASK_DATABASE == null) {
                    //Todo: .allowMainThreadQueries() below should maybe be deleted, as it is not best practice
                    TASK_DATABASE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }

        return TASK_DATABASE;
    }
    // END CITATION
}
