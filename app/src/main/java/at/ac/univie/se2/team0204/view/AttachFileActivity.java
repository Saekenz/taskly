package at.ac.univie.se2.team0204.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import at.ac.univie.se2.team0204.model.EFileType;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.viewmodel.AttachmentViewModel;

public class AttachFileActivity extends AppCompatActivity {

    private static final String TAG = "AttachFileActivity";

    private AttachmentViewModel attachmentViewModel;
    private String filePath;
    private int requestcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.attachmentViewModel = new ViewModelProvider(this).get(AttachmentViewModel.class);
        filePath = "";
        requestcode = 1;
        openFileChooser();
    }

    /**
     * Calls the VM to save an attachment to the DB and gets its ID in return.
     * @param attachment The attachment to be saved to the DB.
     * @return The attachmentID of the inserted attachment.
     */
    private long saveFileAttachment(Attachment attachment){
        return attachmentViewModel.insertAttachmentAndGetID(attachment);
    }

    /**
     * Opens the device's file explorer (only showing .txt, .png & .pdf files).
     */
    private void openFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        // adapted from http://android-er.blogspot.com/2015/09/open-multi-files-using.html
        String[] mimeTypes = {"text/plain","image/png","application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, requestcode);
    }

    @Override
    protected void onActivityResult(int reqcode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(reqcode, resultCode, data);
        if(data == null) return;
        filePath = data.getDataString();
        Attachment attachment = new Attachment(filePath, EFileType.getEFileType(filePath));
        long attachmentRowID = saveFileAttachment(attachment);

        super.setResult(Activity.RESULT_OK,new Intent().putExtra("attachmentID", attachmentRowID));
        Log.d(TAG, attachmentRowID+" in AttachFileActivity");
        finish();
    }
}
