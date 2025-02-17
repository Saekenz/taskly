package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.AppointmentWithAttachments;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.model.room.TodoWithAttachments;
import at.ac.univie.se2.team0204.model.room.TodoWithSubTodos;
import at.ac.univie.se2.team0204.viewmodel.CurrentThemeManager;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class MainActivity extends AppCompatActivity {

    private Button goToTaskCreationButton;
    private Button goToViewTasksButton;
    private Button goToCalendarViewButton;
    private Button importTasksButton;
    private Button exportTasksButton;
    private Button goToAllTasksButton;


    private boolean nightModeActivated;
    private Switch nightModeSwitch;

    /**
     * Tag used to choose which action is taken in the importTasksInterface
     */
    private static final int CHOOSE_FILE = 0;

    /**
     * ViewModel used by the application. This class holds most of the logic.
     */
    private TaskVM taskVM;

    /**
     * Tag used by the logger to identify this class
     */
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        goToTaskCreationButton = findViewById(R.id.buttonStartTaskCreation);
        goToTaskCreationButton.setOnClickListener(view -> openTaskCreationActivity());

        goToViewTasksButton = findViewById(R.id.buttonStartViewTasks);
        goToViewTasksButton.setOnClickListener(view -> openViewTasksActivity());

        goToCalendarViewButton=findViewById(R.id.buttonStartCalendarView);
        goToCalendarViewButton.setOnClickListener(view -> openCalendarView());

        importTasksButton = findViewById(R.id.buttonImportTasks);
        importTasksButton.setOnClickListener(view -> importTasksInterface());

        exportTasksButton = findViewById(R.id.buttonExportTasks);
        exportTasksButton.setOnClickListener(view -> openExportTasksActivity());

        goToAllTasksButton = findViewById(R.id.buttonTestTasks);
        goToAllTasksButton.setOnClickListener(view -> openAllTaskViewActivity());

        nightModeActivated=CurrentThemeManager.initAndGetInformation(this);
        setStoredColorMode();
        nightModeSwitch=findViewById(R.id.switchNightMode);
        nightModeSwitch.setChecked(nightModeActivated);
        nightModeSwitch.setOnClickListener(view -> storeNewColorMode());


        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
    }

    /**
     * Creates the intent for importing tasks and opens the file explorer on the phone.
     */
    private void importTasksInterface() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // CODE CITATION
        // SRC https://stackoverflow.com/questions/27367272/how-can-i-setup-an-android-intent-for-multiple-types-of-files-pdf-office-imag
        // AUTHOR JulianHarty
        String [] supportedTypes = {"text/xml", "application/json"};
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, supportedTypes);
        // CITATION END
        startActivityForResult(intent, CHOOSE_FILE);
    }

    /**
     * Decides how an activity result is handled.  The requestCode decides what action will be taken.
     *
     * @param requestCode If the request code is 0, a file will be read in.
     * @param resultCode Please refer to the documentation of the super class for this parameter.
     * @param resultData Please refer to the documentation of the super class for this parameter.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == CHOOSE_FILE && resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "Importing new Tasks");

            if(resultData != null) {
                Log.d(TAG, "Valid file chosen");
                Uri uri = resultData.getData();
                String mimeType = getContentResolver().getType(uri);
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
                    taskVM.importTask(inputStream, mimeType);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void setStoredColorMode(){

        if(nightModeActivated)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    private void storeNewColorMode(){
        if(nightModeActivated) {
            CurrentThemeManager.writeNightModeStatus(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else {
            CurrentThemeManager.writeNightModeStatus(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public void openTaskCreationActivity() {

        Intent intent=new Intent(this, AppointmentCreationActivity.class);
        startActivity(intent);

    }

    public void openViewTasksActivity() {

        Intent intent=new Intent(this, ListViewActivity.class);
        startActivity(intent);

    }

    private void openCalendarView(){

        Intent intent=new Intent(this, CalendarViewActivity.class);
        startActivity(intent);

    }

    private void openExportTasksActivity() {
        Intent intent=new Intent(this, ExportActivity.class);
        startActivity(intent);
    }

    /**
     * Opens a view that shows all Tasks /w Attachments and subtasks (for testing)
     */
    private void openAllTaskViewActivity() {
        Intent intent = new Intent(this, AllTasksViewActivity.class);
        startActivity(intent);
    }
}