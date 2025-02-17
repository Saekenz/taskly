package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;

public class TaskImportIteratorTest {

    TaskImportIterator iterator = new TaskImportIterator(new ArrayList<>());

    @Test
    public void importIterator_AssertFalse_hasNext() {
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void importIterator_AssertTrue_hasNext() {
        List<Map<String, String>> array = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        array.add(map);

        iterator = new TaskImportIterator(array);

        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void importIterator_AssertFalse_hasNext2() {
        List<Map<String, String>> array = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        array.add(map);

        iterator = new TaskImportIterator(array);

        Assert.assertTrue(iterator.hasNext());

        iterator.next();

        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void importIterator_assertSize() {
        List<Map<String, String>> array = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        array.add(map);
        iterator = new TaskImportIterator(array);
        Assert.assertEquals(iterator.getIteratorSize(), 1);

        array.add(map);
        iterator = new TaskImportIterator(array);
        Assert.assertEquals(iterator.getIteratorSize(), 2);

        array.add(map);
        iterator = new TaskImportIterator(array);
        Assert.assertEquals(iterator.getIteratorSize(), 3);

    }

    @Test
    public void importIterator_assertNext() {
        List<Map<String, String>> array = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        array.add(map);
        iterator = new TaskImportIterator(array);
        Assert.assertNotNull(iterator.next());

        Assert.assertThrows(IndexOutOfBoundsException.class, () -> iterator.next());

    }
}

