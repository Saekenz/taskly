package at.ac.univie.se2.team0204.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import at.ac.univie.se2.team0204.view.CreateSketchActivity;

public class AttachSketchStrategy implements AttachmentStrategy {
    private Context context;

    public AttachSketchStrategy(Context context){
        this.context = context;
    }

    /**
     * Creates an Intent in order to launch the Handwritten Note taking functionality.
     */
    @Override
    public void addAttachment() {
        Intent attachSketchIntent = new Intent(context, CreateSketchActivity.class);
        Activity activity = (Activity) context;
        activity.startActivityForResult(attachSketchIntent,1);
    }
}
