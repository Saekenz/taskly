package at.ac.univie.se2.team0204.viewmodel.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.viewmodel.ListRepresentationVM;

public class TaskViewHolder extends RecyclerView.ViewHolder{

    boolean spinnerEnabled;

    View taskHolderView;
    View cardView;
    ImageView imageView;
    TextView title, description;
    Spinner spinner;

    public TaskViewHolder(@NonNull View itemView) {
        //grabbing views from recycler_view_task_holder and assigning values to them
        super(itemView);


        //Todo Stephan replace color with default
        //currentColor=itemView.findViewById(R.id.cardView).getSolidColor();
        spinnerEnabled=false;

        taskHolderView=itemView;
        cardView=itemView.findViewById(R.id.cardView);
        imageView=itemView.findViewById(R.id.imageView);
        title=itemView.findViewById(R.id.title);
        description=itemView.findViewById(R.id.description);

        //Set the color Spinner:
        spinner=itemView.findViewById(R.id.colorSpinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(itemView.getContext(),R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int initialSelectedPosition=spinner.getSelectedItemPosition();
        spinner.setSelection(initialSelectedPosition, false); //clear selection

    }

    public void setBackgroundColor(Context context, String color){
        switch (color){
            case "green":{
                cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.olvie_green));
                setTexts(Color.BLACK);
                break;
            }
            case "blue":{
                cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_blue_600));
                setTexts(Color.BLACK);
                break;
            }
            case "red":{
                cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.wine_red));
                setTexts(Color.WHITE);
                break;
            }
            default:{
                cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200));
                setTexts(Color.BLACK);
                break;
            }

        }
    }

    public void setVisibility(boolean isVisible){
        taskHolderView.setVisibility(View.GONE);
    }

    private void setTexts(int color){
        title.setTextColor(color);
        description.setTextColor(color);
    }


}
