package at.ac.univie.se2.team0204.viewmodel;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import  at.ac.univie.se2.team0204.view.MainActivity;

public class FileManager {

    /**
     * Tag used by the logger to identify this class
     */
    private static final String TAG = "FileManager";

    /**
     * Reads the file from the input stream.
     * @param inputStream The stream created in the {@link MainActivity}. Denotes the file that should be read.
     * @return A string containing the information of the file.
     */
    public static String readFile(InputStream inputStream) {

        String rawTasks = "";
        try {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader fileReader = new BufferedReader(streamReader);

            Log.d(TAG, "Reading file");

            StringBuilder fileContent = new StringBuilder();
            String nextLine = fileReader.readLine();
            while(nextLine != null) {
                fileContent.append(nextLine.trim());
                Log.d(TAG, "Next line read: " + nextLine);
                nextLine = fileReader.readLine();
            }
            rawTasks = fileContent.toString();

            Log.d(TAG, "Finished reading file");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return rawTasks;
    }

    /**
     * Writes the contents of the passed parameter to a file.
     * @param contentType The file ending of the written file.
     * @param convertedTask The contents of the written file.
     */
    public static void writeFile(String contentType, String convertedTask) {

        // THIS METHOD IS INFLUENCED BY AN EXTERNAL SOURCE
        // SRC https://stackoverflow.com/questions/8330276/write-a-file-in-external-storage-in-android
        // AUTHOR mH16, hasanghaforian
        try {
            File sdCardRoot = Environment.getExternalStorageDirectory();
            File fileDirectory = new File(sdCardRoot.getAbsolutePath() + "/download");

            boolean directoryCreated = fileDirectory.mkdirs();
            Log.d(TAG, "Directory created? " + directoryCreated);

            String fileName = "newTasks." + contentType;
            File file = new File(fileDirectory, fileName);

            Log.d(TAG, "Created file");

            FileOutputStream fileStream = new FileOutputStream(file);
            PrintWriter fileWriter = new PrintWriter(fileStream);
            fileWriter.println(convertedTask);
            fileWriter.flush();

            Log.d(TAG, "Wrote to file");

            fileWriter.close();
            fileStream.close();

            Log.d(TAG, "Closed stream and writer");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        // END CITATION
    }
}
