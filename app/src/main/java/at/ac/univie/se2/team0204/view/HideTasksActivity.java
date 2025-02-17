package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.model.room.taskranking.TaskRanking;
import at.ac.univie.se2.team0204.viewmodel.ListRepresentationVM;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class HideTasksActivity extends AppCompatActivity {

    private TaskVM taskVM;
    private ListRepresentationVM listVM;
    private LinearLayout linearLayout;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_tasks);

        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        this.listVM = new ViewModelProvider(this).get(ListRepresentationVM.class);

        linearLayout=findViewById(R.id.linearLayoutHideTasks);
        updateButton=findViewById(R.id.updateButtonHideTasks);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ListViewActivity.class);
                startActivity(intent);
            }
        });

        taskVM.getTodoLiveData().observe(this, notes -> {
            //called every time data changes

            List<TaskRanking> taskInformation=listVM.getTaskInformations();

            for(Todo todo:notes){

                boolean isHidden=listVM.checkIfHidden(todo.getTaskId());

                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(todo.getTitle());
                checkBox.setChecked(!isHidden);

                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkBox.isChecked())
                            listVM.updateIsHiddenInDatabase(todo,false);
                        else
                            listVM.updateIsHiddenInDatabase(todo,true);
                    }


                    // Your code to be executed on click
                });

                linearLayout.addView(checkBox);

            }







        });
    }
}