package at.ac.univie.se2.team0204.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskVM;

public class ButtonAdapter extends BaseAdapter {

    /**
     * The {@link Context} needed to export tasks
     */
    private final Context context;

    /**
     * {@link Task}s that will be displayed in the list view
     */
    private final List<? extends Task> data;

    /**
     * The {@link androidx.lifecycle.ViewModel} holding all the logic for exporting tasks.
     */
    private final TaskVM taskVM;

    /**
     * Tag used by the logger to identify this class
     */
    private static final String TAG = "ButtonAdapter";

    /**
     * Creates a ButtonAdapter that adapts {@link Task}s to the layout of the exporting list view.
     * @param context The {@link Context} needed to export {@link Task}s.
     * @param data {@link Task}s that will be displayed in the list view.
     * @param taskVM The {@link androidx.lifecycle.ViewModel} holding all the logic for exporting {@link Task}s.
     */
    public ButtonAdapter(Context context, List<? extends Task> data, TaskVM taskVM) {
        super();
        this.context = context;
        this.data = data;
        this.taskVM = taskVM;
    }

    /**
     * The number of items displayed in the ListView.
     * @return The number of items displayed in the ListView.
     */
    @Override
    public int getCount() {
        return data.size();
    }

    /**
     * The item identified by the list index, that is passed as an argument.
     * @param i The list index.
     * @return The item at the list index i.
     */
    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    /**
     * The id of the item identified by the list index.
     * @param i The list index.
     * @return The id of the item at list index i.
     */
    @Override
    public long getItemId(int i) {
        return data.get(i).getTaskId();
    }

    /**
     * The function adapting a data item to the list row component. Each row consists of the {@link Task} data and three buttons.
     * The buttons let the user export the task, either as an XML file or as a JSON file, or get to the TaskView.
     * @param i The list index of the current item.
     * @param view The view the items will be added to.
     * @param viewGroup The ViewGroup the items belong to.
     * @return The row representation of a task.d
     */
    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Get task information.
        Task task = data.get(i);
        int taskId = task.getTaskId();
        String taskType = "";
        if(task instanceof Appointment) {
            taskType = "Appointment";
        } else {
            taskType = "Todo";
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_export, viewGroup, false);

        // Fill the adapter with information and create buttons.
        TextView textView = view.findViewById(R.id.task1);
        textView.setText(task.getTitle());

        Button button = view.findViewById(R.id.taskBtn);
        String finalTaskType = taskType;
        button.setOnClickListener(btn -> {
            Log.i(TAG, "Exporting task into JSON file");
            this.taskVM.export(taskId, "json", finalTaskType);
        });

        Button xmlButton = view.findViewById(R.id.xmlBtn);
        xmlButton.setOnClickListener(btn -> {
            Log.i(TAG, "Exporting task into XML file");
            this.taskVM.export(taskId, "xml", finalTaskType);
        });

        Button showTaskButton = view.findViewById(R.id.showTaskBtn);
        showTaskButton.setOnClickListener(btn -> {
            Intent intent = new Intent(context, TaskViewActivity.class);
            intent.putExtra("taskId", taskId);
            intent.putExtra("taskType", finalTaskType);
            context.startActivity(intent);
        });

        return view;
    }

}
