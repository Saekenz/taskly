package at.ac.univie.se2.team0204.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.se2.team0204.viewmodel.AttachmentStrategy;

public class AddAttachmentActivity extends AppCompatActivity {

    private static final String TAG = "AddAttachmentActivity";

    private AttachmentStrategy strategy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setStrategy(AttachmentStrategy strategy){
        this.strategy = strategy;
    }

    public AttachmentStrategy getStrategy() {
        return strategy;
    }

    /**
     * Calls the addAttachment() method of the strategy object.
     */
    public void addAttachment(){
        strategy.addAttachment();
    }
}

