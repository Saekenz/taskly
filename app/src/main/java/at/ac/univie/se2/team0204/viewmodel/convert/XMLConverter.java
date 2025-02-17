package at.ac.univie.se2.team0204.viewmodel.convert;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;

public class XMLConverter implements IConverter {

    /**
     * Tag used by the logger to identify this class
     */
    private static final String TAG = "XMLConverter";

    /**
     * Converts an XML String to a an iterator of Map<String, String> objects. These objects contain a task each.
     * <p>
     *     The application assumes the file has the following structure: It must an object with the key "tasks", which
     *     holds an array of tasks to be imported. Each task needs the fields "taskType", "title", "description", "status",
     *     "priority" and "deadline".
     * </p>
     * @param rawTask The raw string of all tasks that need importing.
     * @return An {@link TaskImportIterator} object holding all converted tasks.
     */
    @Override
    public TaskImportIterator convertToIterator(String rawTask) {
        List<Map<String, String>> tasks = new ArrayList<>();
        Document xmlDocument;
        Log.i(TAG, "Converting XML file to Iterator");
        try {
            // CODE PARTIALLY TAKEN FROM EXTERNAL SOURCE
            // SRC https://stackoverflow.com/questions/32735474/convert-xml-string-to-map-and-get-the-key-value-pairs-using-java
            // BY AUTHOR VIRENDRAO
            DocumentBuilderFactory xmlBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = xmlBuilderFactory.newDocumentBuilder();
            xmlDocument = builder.parse(new InputSource(new StringReader(rawTask)));

            NodeList xmlTasks = xmlDocument.getFirstChild().getChildNodes();

            for(int i = 0; i < xmlTasks.getLength(); i++) {
                Map<String, String> newTask = new HashMap<>();
                NodeList nextTaskList = xmlTasks.item(i).getChildNodes();

                for(int j = 0; j < nextTaskList.getLength(); j++) {
                    Node nextTaskAttribute = nextTaskList.item(j);
                    newTask.put(nextTaskAttribute.getNodeName(), nextTaskAttribute.getTextContent());
                }
                System.out.println(newTask);
                tasks.add(newTask);
            }
            // CITATION END
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return new TaskImportIterator(tasks);
    }

    /**
     * Converts a {@link Task} object into an XML string.
     *
     * @param task A {@link Task} object that should be converted to a string.
     * @return A string representing the task in XML.
     */
    @Override
    public String convertToString(Task task) {

        Log.i(TAG, "Converting Task to XML string");

        StringBuilder xmlString = new StringBuilder();

        xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks><task>");
        xmlString.append("<taskId>").append(task.getTaskId()).append("</taskId>");
        xmlString.append("<description>").append(task.getDescription()).append("</description>");
        xmlString.append("<title>").append(task.getTitle()).append("</title>");

        // This is a mock for now, since resp 1 is not done yet.
        xmlString.append("<status>Done</status>");
        xmlString.append("<priority>1</priority>");
        xmlString.append("<date>2022-02-10</date>");

        // Add task type.
        if(task instanceof Appointment) {
            xmlString.append("<taskType>Appointment</taskType>");
        } else {
            xmlString.append("<taskType>ToDo</taskType>");
        }

        xmlString.append("</task></tasks>");

        return xmlString.toString();
    }
}
