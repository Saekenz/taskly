package at.ac.univie.se2.team0204.viewmodel.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.model.room.Todo;
import at.ac.univie.se2.team0204.viewmodel.ListRepresentationVM;

public class TaskListAdapter extends RecyclerView.Adapter<TaskViewHolder> implements ItemTouchHelperContract{

    private Context context;
    private List<Todo> tasks;
    private ListRepresentationVM listVM;

    public TaskListAdapter(Context context, List<Todo> tasks, ListRepresentationVM listVM){
        this.listVM=listVM;
        this.context=context;
        //Initialize the tasks in the order, the ListRepresentationVM has stored.
        listVM.tidyUpVM(tasks);
        this.tasks=listVM.getSortedTodos();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //filling the layout/the viewHolders
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_view_task_holder,parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        //assigning values to our viewHolders when they come back to the screen (when scrolling)
        //base on position of recycler view



        holder.title.setText(tasks.get(position).getTitle());
        holder.description.setText(tasks.get(position).getDescription());
        holder.setBackgroundColor(context,listVM.getColor(tasks.get(position).getTaskId()));

        //Set Spinner for Task Color
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int spinnerPosition, long id) {
                Todo todo=tasks.get(holder.getAdapterPosition());
                String color=parentView.getAdapter().getItem(spinnerPosition).toString();
                listVM.updateColorInDatabase(todo, color);
                holder.setBackgroundColor(context,color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull TaskViewHolder holder){

    }

    @Override
    public void onDetachedFromRecyclerView (RecyclerView recyclerView){

    }

    @Override
    public int getItemCount() {
        //how many items does my recycler view have in total?
        return tasks.size();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(tasks, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(tasks, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        listVM.registerChangeInRanking(fromPosition,toPosition);
    }

    @Override
    public void onRowSelected(TaskViewHolder myViewHolder) {
        //Todo Stephan Put Back
        //myViewHolder.setBackgroundColor(Color.GRAY);
    }



    @Override
    public void onRowClear(TaskViewHolder myViewHolder) {
        //myViewHolder.setBackgroundColor();
    }


}
