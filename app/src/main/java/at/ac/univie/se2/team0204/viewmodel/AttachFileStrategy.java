package at.ac.univie.se2.team0204.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import at.ac.univie.se2.team0204.view.AttachFileActivity;

public class AttachFileStrategy implements AttachmentStrategy{
    private Context context;

    public AttachFileStrategy(Context context){this.context = context;
    }

    /**
     * Creates an Intent in order to launch the choosing a file from the device's storage functionality.
     */
    @Override
    public void addAttachment() {
        Intent attachFileIntent = new Intent(context, AttachFileActivity.class);
        Activity activity = (Activity) context;
        activity.startActivityForResult(attachFileIntent,1);
    }
}
