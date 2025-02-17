package at.ac.univie.se2.team0204.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.AppointmentAttachment;
import at.ac.univie.se2.team0204.model.room.TodoAttachment;
import at.ac.univie.se2.team0204.viewmodel.AttachFileStrategy;
import at.ac.univie.se2.team0204.viewmodel.AttachSketchStrategy;
import at.ac.univie.se2.team0204.viewmodel.AttachmentViewModel;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class AppointmentCreationActivity extends AppCompatActivity {

    private static final String TAG = "ApptCreationActivity";

    private TaskVM taskVM;
    private AttachmentViewModel attachmentViewModel;

    private Button taskCreationButton;
    private Button changeToTodoCreationButton;
    private Button goToMainButton;
    private Button attachmentMenuButton;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText placeEditText;
    private DatePicker datePicker;

    private AddAttachmentActivity addAttachmentActivity;
    private List<Long> attachmentIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_appointment_creation);
        addAttachmentActivity = new AddAttachmentActivity();
        attachmentIDs = new ArrayList<>();

        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        this.attachmentViewModel = new ViewModelProvider(this).get(AttachmentViewModel.class);

        taskCreationButton = (Button) findViewById(R.id.buttonTaskCreation);
        changeToTodoCreationButton=(Button) findViewById(R.id.changeToTodoButton);
        goToMainButton=(Button) findViewById(R.id.buttonMainPage);
        titleEditText=(EditText) findViewById(R.id.editTextTaskTitle);
        descriptionEditText=(EditText)findViewById(R.id.editTextDescription);
        placeEditText=(EditText)findViewById(R.id.editTextPlace);
        datePicker=(DatePicker)findViewById(R.id.datePickerTaskCreation);

        taskCreationButton.setOnClickListener(view -> createNewTask());
        changeToTodoCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToTodoCreation();
            }
        });
        goToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMain();
            }
        });
        attachmentMenuButton = findViewById(R.id.buttonAttachmentMenu);
        attachmentMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu attachmentMenu = new PopupMenu(getApplicationContext(), attachmentMenuButton);
                attachmentMenu.getMenuInflater().inflate(R.menu.attachment_menu, attachmentMenu.getMenu());
                attachmentMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.item_addFile){
                            addAttachmentActivity.setStrategy(new AttachFileStrategy(AppointmentCreationActivity.this));
                        }
                        if(item.getItemId() == R.id.item_addNote){
                            addAttachmentActivity.setStrategy(new AttachSketchStrategy(AppointmentCreationActivity.this));
                        }
                        addAttachmentActivity.addAttachment();
                        return true;
                    }
                });
                attachmentMenu.show();
            }
        });

        taskVM.getAppointmentLiveData().observe(this, notes -> { //called every time data changes
            Log.d(TAG,"Data changed");

        });

    }

    private void createNewTask(){

        String title= String.valueOf(titleEditText.getText());
        String description = String.valueOf(descriptionEditText.getText());
        String place = String.valueOf(placeEditText.getText());
        int year  = datePicker.getYear();
        int month  = datePicker.getMonth();
        int day  = datePicker.getDayOfMonth();
        String date=String.format("%s.%s.%s",day,month+1,year);

        Appointment task = new Appointment(title, description,place,date);
        task.setTitle(title);
        task.setDescription(description);

        if(attachmentIDs.isEmpty()){
            taskVM.insertTask(task);
        }
        else {
            long taskID = taskVM.insertTaskAndReturnID(task);
            for(long attachmentID : attachmentIDs) {
                attachmentViewModel.insertAppointmentAttachment(new AppointmentAttachment(taskID, attachmentID));
                Log.d(TAG, "Appointment: " + taskID + ", Attachment: " + attachmentID);
            }
        }

        Toast.makeText(AppointmentCreationActivity.this, "Appointment created",
                Toast.LENGTH_SHORT).show();
    }

    private void changeToTodoCreation(){
        Intent intent=new Intent(this, TodoCreationActivity.class);
        startActivity(intent);
    }

    private void goToMain(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            long attachmentID = data.getLongExtra("attachmentID", -1);
            if (!attachmentIDs.contains(attachmentID)) {
                attachmentIDs.add(attachmentID);
            }
            Log.d(TAG, "Attachment recieved: " + attachmentID);
            for (long attachment : attachmentIDs) Log.d(TAG, "List contains: "+attachment);
        }
    }
}