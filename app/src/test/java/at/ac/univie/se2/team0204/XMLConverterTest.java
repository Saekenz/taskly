package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;
import at.ac.univie.se2.team0204.viewmodel.convert.IConverter;
import at.ac.univie.se2.team0204.viewmodel.convert.JSONConverter;
import at.ac.univie.se2.team0204.viewmodel.convert.XMLConverter;

public class XMLConverterTest {

    @Test
    public void xmlConverter_AssertIterator_taskType() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("taskType"), "Appointment");
    }

    @Test
    public void xmlConverter_AssertIterator_taskId() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("taskId"), "5");
    }

    @Test
    public void xmlConverter_AssertIterator_title() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>Go Skiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("title"), "Go Skiing");
    }

    @Test
    public void xmlConverter_AssertIterator_description() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("description"), "Date with Meta");
    }

    @Test
    public void xmlConverter_AssertIterator_status() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("status"), "Before");
    }
    @Test
    public void xmlConverter_AssertIterator_priority() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("priority"), "4");
    }

    @Test
    public void xmlConverter_AssertIterator_deadline() throws Exception {

        IConverter xmlConverter = new XMLConverter();

        String rawTask = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tasks>" +
                "<task>" +
                "<taskType>Appointment</taskType>" +
                "<taskId>5</taskId>" +
                "<title>GoSkiing</title>" +
                "<description>Date with Meta</description>" +
                "<status>Before</status>" +
                "<priority>4</priority>" +
                "<deadline>2022-02-02</deadline>" +
                "</task></tasks>";

        TaskImportIterator iterator = xmlConverter.convertToIterator(rawTask);

        Map<String, String> nextTask = iterator.next();

        Assert.assertEquals(nextTask.get("deadline"), "2022-02-02");
    }

    @Test
    public void xmlConverter_AssertString_tasks() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("tasks"));
    }

    @Test
    public void xmlConverter_AssertString_taskId() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("taskId"));
        Assert.assertTrue(convertString.contains("1"));
    }

    @Test
    public void xmlConverter_AssertString_title() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("title"));
        Assert.assertTrue(convertString.contains("Task"));
    }

    @Test
    public void xmlConverter_AssertString_description() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("description"));
        Assert.assertTrue(convertString.contains("newDescription"));
    }

    @Test
    public void xmlConverter_AssertString_status() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("status"));
        Assert.assertTrue(convertString.contains("Done"));
    }

    @Test
    public void xmlConverter_AssertString_priority() {
        Appointment appointment = new Appointment(1, "Task", "newDescription", "Somewhere", "2022-02-02");

        IConverter converter = new XMLConverter();

        String convertString = converter.convertToString(appointment);

        Assert.assertTrue(convertString.contains("priority"));
        Assert.assertTrue(convertString.contains("1"));
    }
}
