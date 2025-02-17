package at.ac.univie.se2.team0204.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.model.room.TodoAttachment;
import at.ac.univie.se2.team0204.viewmodel.AttachFileStrategy;
import at.ac.univie.se2.team0204.viewmodel.AttachSketchStrategy;
import at.ac.univie.se2.team0204.viewmodel.AttachmentViewModel;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoCreationActivity extends AppCompatActivity {

    private static final String TAG = "TodoCreationActivity";

    private TaskVM taskVM;
    private AttachmentViewModel attachmentViewModel;

    private Button taskCreationButton;
    private Button changeToAppointmentCreationButton;
    private Button goToMainButton;
    private Button attachmentMenuButton;
    private Spinner parentIDspinner;
    private EditText titleEditText;
    private EditText descriptionEditText;

    private AddAttachmentActivity addAttachmentActivity;
    private List<Long> attachmentIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_todo_creation);

        parentIDspinner = findViewById(R.id.parentIDSpinner);

        addAttachmentActivity = new AddAttachmentActivity();
        attachmentIDs = new ArrayList<>();

        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        this.attachmentViewModel = new ViewModelProvider(this).get(AttachmentViewModel.class);

        changeToAppointmentCreationButton = (Button) findViewById(R.id.changeToAppointmentButton);
        changeToAppointmentCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToAppointmentCreation();
            }
        });

        goToMainButton=(Button) findViewById(R.id.buttonMainPage);
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
                            addAttachmentActivity.setStrategy(new AttachFileStrategy(TodoCreationActivity.this));
                        }
                        if(item.getItemId() == R.id.item_addNote){
                            addAttachmentActivity.setStrategy(new AttachSketchStrategy(TodoCreationActivity.this));
                        }
                        addAttachmentActivity.addAttachment();
                        return true;
                    }
                });
                attachmentMenu.show();
            }
        });

        titleEditText=(EditText) findViewById(R.id.editTextTaskTitle);
        descriptionEditText=(EditText)findViewById(R.id.editTextDescription);

        taskCreationButton = (Button) findViewById(R.id.buttonTaskCreation);
        taskCreationButton.setOnClickListener(view -> createNewTask());

        getTodosAndFillSpinner();
    }

    /**
     * Used for saving Todos to the DB.
     */
    private void createNewTask(){

        String title= String.valueOf(titleEditText.getText());
        String description = String.valueOf(descriptionEditText.getText());
        int parentID;
        if(parentIDspinner.getSelectedItem()!=null) {
            parentID = (int) parentIDspinner.getSelectedItem();
        } else {parentID = 0;}

        Todo todo = new Todo(title, description);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setParentID(parentID);
        if(attachmentIDs.isEmpty()) {
            taskVM.insertTask(todo);
        }
        else{
            long taskID = taskVM.insertTaskAndReturnID(todo);
            for(long attachmentID : attachmentIDs) {
                attachmentViewModel.insertTodoAttachment(new TodoAttachment(taskID, attachmentID));
                Log.d(TAG, "Todo: " + taskID + ", Attachment: " + attachmentID);
            }

        }
        Toast.makeText(TodoCreationActivity.this, "To Do created",
                Toast.LENGTH_SHORT).show();

        getTodosAndFillSpinner();
        attachmentIDs.clear();
        titleEditText.setText("");
        descriptionEditText.setText("");
    }

    private void changeToAppointmentCreation(){
        Intent intent=new Intent(this, AppointmentCreationActivity.class);
        startActivity(intent);
    }

    private void goToMain(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Takes a list of {@link Todo} objects uses each TodoID to fill the spinner.
     * @param todos The list of {@link Todo} objects.
     */
    private void fillParentIDSpinner(List<Todo> todos) {
        ArrayList<Integer> todoIDs = new ArrayList<>();
        todoIDs.add(0); // default value -> if parentID == 0 this Todo has no parent
        for(Todo todo : todos) {
            todoIDs.add(todo.getTaskId());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(TodoCreationActivity.this, android.R.layout.simple_list_item_1, todoIDs);
        parentIDspinner.setAdapter(arrayAdapter);
    }

    /**
     * Observes the LiveData containing all currently saved {@link Todo} objects in the database.
     */
    private void getTodosAndFillSpinner() {
        taskVM.getTodoLiveData().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                fillParentIDSpinner(todos);
            }
        });
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
            for (long attachment : attachmentIDs) Log.d("List contains", attachment + "");
        }
    }
}