package at.ac.univie.se2.team0204.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myscript.iink.Configuration;
import com.myscript.iink.ContentPackage;
import com.myscript.iink.ContentPart;
import com.myscript.iink.ConversionState;
import com.myscript.iink.Editor;
import com.myscript.iink.EditorError;
import com.myscript.iink.Engine;
import com.myscript.iink.IEditorListener;
import com.myscript.iink.MimeType;
import com.myscript.iink.Renderer;
import com.myscript.iink.uireferenceimplementation.EditorBinding;
import com.myscript.iink.uireferenceimplementation.EditorData;
import com.myscript.iink.uireferenceimplementation.EditorView;
import com.myscript.iink.uireferenceimplementation.FontUtils;
import com.myscript.iink.uireferenceimplementation.InputController;
import com.myscript.iink.uireferenceimplementation.SmartGuideView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.UUID;

import at.ac.univie.se2.team0204.R;
import at.ac.univie.se2.team0204.databinding.ActivitySketchBinding;
import at.ac.univie.se2.team0204.model.EFileType;
import at.ac.univie.se2.team0204.model.iink.IInkApplication;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.viewmodel.AttachmentViewModel;

// adapted from https://github.com/MyScript/interactive-ink-examples-android/blob/master/GetStarted/src/main/java/com/myscript/iink/getstarted/MainActivity.java
public class CreateSketchActivity extends AppCompatActivity {

    private static final String TAG = "SketchActivity";
    private static final String SKETCH_PATH ="/data/data/at.ac.univie.se2.team0204/files/";
    private Engine engine;
    private ContentPackage contentPackage;
    private ContentPart contentPart;

    private EditorData editorData;
    private EditorView editorView;
    private SmartGuideView smartGuideView;

    private ActivitySketchBinding binding;
    private AttachmentViewModel attachmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.attachmentViewModel = new ViewModelProvider(this).get(AttachmentViewModel.class);
        engine = IInkApplication.getEngine();

        // configure recognition
        Configuration conf = engine.getConfiguration();
        String confDir = "zip://" + getPackageCodePath() + "!/assets/conf";
        conf.setStringArray("configuration-manager.search-path", new String[]{confDir});
        String tempDir = getFilesDir().getPath() + File.separator + "tmp";
        conf.setString("content-package.temp-folder", tempDir);

        binding = ActivitySketchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editorView = findViewById(R.id.editor_view);
        smartGuideView = findViewById(R.id.smart_guide_view);

        // load fonts
        AssetManager assetManager = getApplicationContext().getAssets();
        Map<String, Typeface> typefaceMap = FontUtils.loadFontsFromAssets(assetManager);
        editorView.setTypefaces(typefaceMap);

        EditorBinding editorBinding = new EditorBinding(engine, typefaceMap);
        editorData = editorBinding.openEditor(editorView);

        Editor editor = editorData.getEditor();
        setMargins(editor, R.dimen.editor_horizontal_margin, R.dimen.editor_vertical_margin);
        editor.addListener(new IEditorListener()
        {
            @Override
            public void partChanging(@NonNull Editor editor, ContentPart oldPart, ContentPart newPart)
            {
                // no-op
            }

            @Override
            public void partChanged(@NonNull Editor editor)
            {
                invalidateOptionsMenu();
                invalidateIconButtons();
            }

            @Override
            public void contentChanged(@NonNull Editor editor, String[] blockIds)
            {
                invalidateOptionsMenu();
                invalidateIconButtons();
            }

            @Override
            public void onError(@NonNull Editor editor, @NonNull String blockId, @NonNull EditorError error, @NonNull String message)
            {
                Log.e(TAG, "Failed to edit block \"" + blockId + "\"" + message);
            }

            @Override
            public void selectionChanged(@NonNull Editor editor)
            {
                // no-op
            }

            @Override
            public void activeBlockChanged(@NonNull Editor editor, @NonNull String blockId)
            {
                // no-op
            }
        });

        smartGuideView.setEditor(editor);

        setInputMode(InputController.INPUT_MODE_FORCE_PEN); // If using an active pen, put INPUT_MODE_AUTO here

        String packageName = "File1.iink";
        File file = new File(getFilesDir(), packageName);
        try
        {
            contentPackage = engine.createPackage(file);
            // Choose type of content (possible values are: "Text Document", "Text", "Diagram", "Math", "Drawing" and "Raw Content")
            contentPart = contentPackage.createPart("Text");
        }
        catch (IOException | IllegalArgumentException e)
        {
            Log.e(TAG, "Failed to open package \"" + packageName + "\"", e);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && contentPart != null)
        {
            actionBar.setTitle(getString(R.string.main_title, contentPart.getType()));
            actionBar.setSubtitle(R.string.app_name);
        }
        else
        {
            setTitle(R.string.app_name);
        }

        // wait for view size initialization before setting part
        editorView.post(() -> {
            Renderer renderer = editorView.getRenderer();
            if (renderer != null)
            {
                renderer.setViewOffset(0, 0);
                editorView.getRenderer().setViewScale(1);
                editorView.setVisibility(View.VISIBLE);
                editor.setPart(contentPart);
            }
        });

        binding.inputModeForcePenButton.setOnClickListener((v) -> setInputMode(InputController.INPUT_MODE_FORCE_PEN));
        binding.undoButton.setOnClickListener((v) -> editor.undo());
        binding.redoButton.setOnClickListener((v) -> editor.redo());
        binding.clearButton.setOnClickListener((v) -> editor.clear());

        invalidateIconButtons();
    }

    @Override
    protected void onDestroy()
    {
        binding.inputModeForcePenButton.setOnClickListener(null);
        binding.undoButton.setOnClickListener(null);
        binding.redoButton.setOnClickListener(null);
        binding.clearButton.setOnClickListener(null);

        smartGuideView.setEditor(null);

        Editor editor = editorData.getEditor();
        if (editor != null)
        {
            editor.getRenderer().close();
            editor.close();
        }
        editorView.setOnTouchListener(null);
        editorView.setEditor(null);

        if (contentPart != null)
        {
            contentPart.close();
            contentPart = null;
        }
        if (contentPackage != null)
        {
            contentPackage.close();
            contentPackage = null;
        }

        // IInkApplication has the ownership, do not close here
        engine = null;

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_sketch_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * <pre>
     * Handles user interaction with menu items (convert & save)
     * Convert: Converts handwritten text into data that can be worked with internally
     * Save: Writes previously converted text into a .txt file and saves it on the device
     * </pre>
     * @param item The menu item that was selected by the user
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Editor editor = editorData.getEditor();
        if (item.getItemId() == R.id.menu_convert && editor != null && !editor.isClosed())
        {
            ConversionState[] supportedStates = editor.getSupportedTargetConversionStates(null);
            if (supportedStates.length > 0)
                editor.convert(null, supportedStates[0]);
            return true;
        }

        // save the converted text to a file
        if(item.getItemId() == R.id.menu_save){
            String exportedUserInput ="";
            try {
                editor.waitForIdle(); // wait for convert to finish
                exportedUserInput = editor.export_(editor.getRootBlock(),  MimeType.TEXT);
                Log.d(TAG, "User text-input size "+exportedUserInput.length()+"");

                if(exportedUserInput.isEmpty()){
                    showErrorDialog();
                }
                else {
                    String filename = writeStringToTextFile(exportedUserInput);
                    Toast.makeText(this, "File: "+filename+" successfully saved", Toast.LENGTH_SHORT ).show();
                    Log.d(TAG, "Text saved "+exportedUserInput);
                    String filepath = SKETCH_PATH.concat(filename);
                    Log.d(TAG, "Filename "+filepath);
                    long attachmentID = saveSketchAttachment(filepath);
                    super.setResult(Activity.RESULT_OK,new Intent().putExtra("attachmentID", attachmentID));
                    super.finish();

                }
            } catch (Exception e){
                Log.e("Exception", "Saving text to file failed: " + e.toString());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showErrorDialog() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
        aleBuilder.setTitle("Error");
        aleBuilder.setMessage("No text to save!").setCancelable(false)
                .setNegativeButton("Exit", (dialog, which) -> CreateSketchActivity.super.finish())
                .setPositiveButton("Continue", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }

    private void setMargins(Editor editor, @DimenRes int horizontalMarginRes, @DimenRes int verticalMarginRes)
    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration conf = editor.getConfiguration();
        float verticalMarginPX = getResources().getDimension(verticalMarginRes);
        float verticalMarginMM = 25.4f * verticalMarginPX / displayMetrics.ydpi;
        float horizontalMarginPX = getResources().getDimension(horizontalMarginRes);
        float horizontalMarginMM = 25.4f * horizontalMarginPX / displayMetrics.xdpi;
        conf.setNumber("text.margin.top", verticalMarginMM);
        conf.setNumber("text.margin.left", horizontalMarginMM);
        conf.setNumber("text.margin.right", horizontalMarginMM);
        conf.setNumber("math.margin.top", verticalMarginMM);
        conf.setNumber("math.margin.bottom", verticalMarginMM);
        conf.setNumber("math.margin.left", horizontalMarginMM);
        conf.setNumber("math.margin.right", horizontalMarginMM);
    }

    private void setInputMode(int inputMode)
    {
        editorData.getInputController().setInputMode(inputMode);
        binding.inputModeForcePenButton.setEnabled(inputMode != InputController.INPUT_MODE_FORCE_PEN);
    }

    private void invalidateIconButtons()
    {
        Editor editor = editorData.getEditor();
        if (editor == null)
            return;
        final boolean canUndo = editor.canUndo();
        final boolean canRedo = editor.canRedo();
        runOnUiThread(() -> {
            binding.undoButton.setEnabled(canUndo);
            binding.redoButton.setEnabled(canRedo);
            binding.clearButton.setEnabled(contentPart != null);
        });
    }

    /**
     * Takes String, saves it in .txt file and saves it to device storage.
     * @param exportedUserInput The String that contains the user written text.
     * @return Name of the created Textfile.
     * @throws IOException
     */
    @NonNull
    private String writeStringToTextFile(String exportedUserInput) throws IOException {
        String filename = UUID.randomUUID().toString().concat(".txt");
        /* Adapted from https://stackoverflow.com/questions/14376807/read-write-string-from-to-a-file-in-android/14377185#14377185 */
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE));
        outputStreamWriter.write(exportedUserInput);
        outputStreamWriter.close();
        return filename;
    }

    private long saveSketchAttachment(String path){
        Attachment sketch = new Attachment(path, EFileType.TXT);
        return attachmentViewModel.insertAttachment(sketch);
    }

}