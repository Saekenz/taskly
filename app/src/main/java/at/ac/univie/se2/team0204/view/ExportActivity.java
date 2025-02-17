package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class ExportActivity extends AppCompatActivity {

    private static final String TAG = "ViewTaskActivity";

    /**
     * The ViewModel used by this activity. Holds most of the logic.
     */
    private TaskVM taskVM;

    /**
     * View of this Activity.
     */
    private ListView listView;

    /**
     * Creates the styling of a row of the list view.
     */
    private ButtonAdapter adapter;

    /**
     *
     */
    private boolean isSetToAppointment;

    /**
     * Helps with asking for permission rights to write data to external storage.
     */
    // CODE CITATION
    // SRC https://stackoverflow.com/questions/8854359/exception-open-failed-eacces-permission-denied-on-android
    // AUTHOR Justin Fiedler
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    //CITATION END

    /**
     * Fill the newly created activity with information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);

        listView = findViewById(R.id.export_list);

        verifyStoragePermissions(this);

        isSetToAppointment = true;

        Button toggleTaskType = findViewById(R.id.toggleTaskBtn);
        toggleTaskType.setOnClickListener(btn -> {
            isSetToAppointment = !isSetToAppointment;
            if(isSetToAppointment) {
                taskVM.getAppointmentLiveData().observe(this, app -> {
                    Log.d(TAG,"Data changed. Set to appointment.");
                    adapter = new ButtonAdapter(ExportActivity.this, app, taskVM);
                    listView.setAdapter(adapter);

                });
            } else {
                taskVM.getTodoLiveData().observe(this, todo -> {
                    Log.d(TAG,"Data changed. Set to Todo.");
                    adapter = new ButtonAdapter(ExportActivity.this, todo, taskVM);
                    listView.setAdapter(adapter);

                });
            }
        });

        taskVM.getAppointmentLiveData().observe(this, app -> {
            Log.d(TAG,"Data changed. Set to appointment.");
            adapter = new ButtonAdapter(ExportActivity.this, app, taskVM);
            listView.setAdapter(adapter);

        });
    }

    /**
     * This function checks if the required permission to write data to the phone are given. If they are not given, it asks
     * the user to give permission.
     *
     * @param activity The activity permission is needed for.
     */
    // CODE CITATION
    // SRC https://stackoverflow.com/questions/8854359/exception-open-failed-eacces-permission-denied-on-android
    // AUTHOR Justin Fiedler
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    //CITATION END
}