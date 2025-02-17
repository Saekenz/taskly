package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import org.junit.Assert.*;

import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;
import at.ac.univie.se2.team0204.viewmodel.convert.IConverter;
import at.ac.univie.se2.team0204.viewmodel.convert.JSONConverter;

public class JSONConverterTest {

    @Test
    public void jsonConverter_AssertThrows_NoTasksKey() {

        IConverter jsonConverter = new JSONConverter();

        String rawTask = "{" +
                "\"taskType\": \"ToDo\"," +
                "\"taskId\": \"1\"," +
                "\"title\": \"Go Shopping\"," +
                "\"description\": \"Need new shoes\"," +
                "\"status\": \"Pending\"," +
                "\"priority\": 2," +
                "\"deadline\": \"10/02/2022\"" +
                "}";

        Assert.assertThrows(Exception.class, () -> {
            jsonConverter.convertToIterator(rawTask);
        });
    }

    @Test
    public void jsonConverter_AssertIterator() throws Exception {

        IConverter jsonConverter = new JSONConverter();

        String rawTask = "{\"tasks\": [{" +
                "\"taskType\": \"ToDo\"," +
                "\"taskId\": \"1\"," +
                "\"title\": \"Go Shopping\"," +
                "\"description\": \"Need new shoes\"," +
                "\"status\": \"Pending\"," +
                "\"priority\": 2," +
                "\"deadline\": \"10/02/2022\"" +
                "}]}";

        TaskImportIterator iterator = jsonConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("taskType"), "ToDo");
        Assert.assertEquals(nextTask.get("taskId"), "1");
        Assert.assertEquals(nextTask.get("title"), "Go Shopping");
        Assert.assertEquals(nextTask.get("description"), "Need new shoes");
        Assert.assertEquals(nextTask.get("status"), "Pending");
        Assert.assertEquals(nextTask.get("priority"), "2");
        Assert.assertEquals(nextTask.get("deadline"), "10/02/2022");
    }

    @Test
    public void jsonConverter_AssertString() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new JSONConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("tasks"));


        Assert.assertTrue(convertString.contains("taskId"));
        Assert.assertTrue(convertString.contains("1"));

        Assert.assertTrue(convertString.contains("title"));
        Assert.assertTrue(convertString.contains("Task"));

        Assert.assertTrue(convertString.contains("title"));
        Assert.assertTrue(convertString.contains("Task"));

        Assert.assertTrue(convertString.contains("description"));
        Assert.assertTrue(convertString.contains("newDescription"));

        Assert.assertTrue(convertString.contains("place"));
        Assert.assertTrue(convertString.contains("Somewhere"));

        Assert.assertTrue(convertString.contains("place"));
        Assert.assertTrue(convertString.contains("2022-02-02"));
    }
}
