package at.ac.univie.se2.team0204.viewmodel.listview;

public interface ItemTouchHelperContract {

    void onRowMoved(int fromPosition, int toPosition);
    void onRowSelected(TaskViewHolder myViewHolder);
    void onRowClear(TaskViewHolder myViewHolder);
}
