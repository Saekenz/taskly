package at.ac.univie.se2.team0204.view;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.AppointmentWithAttachments;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.model.room.TodoWithAttachments;
import at.ac.univie.se2.team0204.model.room.TodoWithSubTodos;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class AllTasksViewActivity extends AppCompatActivity {
    private static final String TAG = "AllTasksView";

    private TaskVM taskVM;

    private LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        layout = findViewById(R.id.main);
        printAppointmentsWithAttachments();
        printTodosWithAttachments();
        printTodosWithSubTodos();
    }

    /**
     * Used for testing - prints every Appointment + all Attachments of this specific Appointment
     */
    private void printAppointmentsWithAttachments(){
        List<AppointmentWithAttachments> list = taskVM.getAppointmentWithAttachments();
        for(AppointmentWithAttachments appointment : list){
            if(appointment != null){
                StringBuilder sb = new StringBuilder();
                sb.append("AppointmentID: "+appointment.appointment.getTaskId());
                sb.append(", Title: "+appointment.appointment.getTitle());
                if(!appointment.attachments.isEmpty()) sb.append(", Attachments: ");
                for(Attachment a : appointment.attachments){
                    sb.append(a.getFileType()+" ");
                }
                TextView view = new TextView(this);
                view.setText(sb.toString());
                layout.addView(view);
            }
        }
    }

    /**
     * Used for testing - prints every Todo + all Attachments of this specific Todo
     */
    private void printTodosWithAttachments() {
        List<TodoWithAttachments> list = taskVM.getTodoWithAttachments();
        for(TodoWithAttachments todo : list){
            if(todo != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("TodoID: "+todo.todo.getTaskId());
                sb.append(", Title: "+todo.todo.getTitle());
                sb.append(", ParentID: "+todo.todo.getParentID());
                if(!todo.attachments.isEmpty()) sb.append(", Attachments: ");
                for(Attachment a : todo.attachments){
                    sb.append(a.getFileType()+" ");
                }
                TextView view = new TextView(this);
                view.setText(sb.toString());
                layout.addView(view);
            }
        }
    }

    /**
     * Used for testing - prints every Todo + all children of this specific Todo
     */
    private void printTodosWithSubTodos() {
        List<TodoWithSubTodos> list = taskVM.getTodosWithSubTodos();
        for(TodoWithSubTodos todo : list) {
            if(todo != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("TodoID: "+todo.todo.getTaskId());
                sb.append(", Title: "+todo.todo.getTitle());
                if(!todo.subTodos.isEmpty()) sb.append(", Subtodos: ");
                for(Todo t : todo.subTodos){
                    sb.append("\nID: "+t.getTaskId()).append(", Title: "+t.getTitle()).append(", Parent: "+t.getParentID());
                }
                TextView view = new TextView(this);
                view.setText(sb.toString());
                layout.addView(view);
            }
        }
    }
}
