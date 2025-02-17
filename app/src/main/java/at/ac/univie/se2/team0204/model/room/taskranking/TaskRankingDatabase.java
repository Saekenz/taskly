package at.ac.univie.se2.team0204.model.room.taskranking;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.AppointmentDao;
import at.ac.univie.se2.team0204.model.room.TaskDatabase;
import at.ac.univie.se2.team0204.model.room.ToDoDao;
import at.ac.univie.se2.team0204.model.room.Todo;




import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    /**
     * Defines the database object.
     *
     */
    @Database(entities = {TaskRanking.class}, version = 1)
    public abstract class TaskRankingDatabase extends RoomDatabase{
        public abstract TaskRankingDao taskRankingDao();

        // TAKEN FROM https://developer.android.com/codelabs/android-room-with-a-view#7
        private static volatile at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingDatabase TASK_RANKING_DATABASE;
        private static final int NUMBER_OF_THREADS = 2;
        public static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);


        public static at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingDatabase getRoomDatabase(final Context context) {
            if(TASK_RANKING_DATABASE == null) {
                synchronized (at.ac.univie.se2.team0204.model.room.taskranking.TaskRankingDatabase.class) {
                    if (TASK_RANKING_DATABASE == null) {
                        TASK_RANKING_DATABASE = Room.databaseBuilder(context.getApplicationContext(),
                                TaskRankingDatabase.class, "task_ranking_database").allowMainThreadQueries().build();}
                }
            }

            return TASK_RANKING_DATABASE;
        }

}
