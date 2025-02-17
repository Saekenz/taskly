package at.ac.univie.se2.team0204.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.viewmodel.ListRepresentationVM;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;
import at.ac.univie.se2.team0204.viewmodel.listview.ItemMoveCallback;
import at.ac.univie.se2.team0204.viewmodel.listview.TaskListAdapter;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ViewTaskActivity";

    private TaskVM taskVM;
    private ListRepresentationVM listVM;

    private RecyclerView recyclerView;
    private Button infoButton;
    private Button hideTasksButton;
    private Button goMainPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        this.taskVM = new ViewModelProvider(this).get(TaskVM.class);
        this.listVM = new ViewModelProvider(this).get(ListRepresentationVM.class);

        recyclerView = findViewById(R.id.recyclerView);

        infoButton=findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListViewInformationDialog();
            }
        });

        hideTasksButton=findViewById(R.id.hideTasksButton);
        hideTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HideTasksActivity.class);
                startActivity(intent);
            }
        });

        goMainPageButton=findViewById(R.id.goToMainButton);
        goMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        taskVM.getTodoLiveData().observe(this, notes -> {
            //called every time data changes
            Log.d(TAG,"Todo Data changed");

            TaskListAdapter adapter=new TaskListAdapter(this, notes, listVM);

            //The following code is based on the ideas shown in: https://www.digitalocean.com/community/tutorials/android-recyclerview-drag-and-drop
            ItemTouchHelper.Callback callback =
                    new ItemMoveCallback(adapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    private void showListViewInformationDialog(){
        String title="Information";

        DialogFragment dialog=new DialogFragment(title,getString(R.string.list_view_information_text));
        dialog.show(getSupportFragmentManager(),"list view information dialog");
    }
}