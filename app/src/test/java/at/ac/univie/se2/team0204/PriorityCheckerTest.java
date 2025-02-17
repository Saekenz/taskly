package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.IInputChecker;
import at.ac.univie.se2.team0204.viewmodel.verify.PriorityChecker;

public class PriorityCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    IInputChecker checker = new PriorityChecker();

    @Test
    public void priorityChecker_AssertThrows_key() {
        Assert.assertThrows("Task does not have a priority", WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void priorityChecker_AssertNoThrow_key() {
        newTask.put("priority", "1");
        Runnable run = () -> {
            try {
                checker.verifyInput(newTask);
            } catch (WrongFileStructureException e) {
                Assert.fail();
                e.printStackTrace();
            }
        };

        run.run();
    }

    @Test
    public void priorityChecker_AssertThrows_highValue() {
        newTask.put("priority", "6");
        Assert.assertThrows("Task priority is invalid. Needs to be between (including) 1 and 5.", WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void priorityChecker_AssertThrows_lowValue() {
        newTask.put("priority", "-1");
        Assert.assertThrows("Task priority is invalid. Needs to be between (including) 1 and 5.", WrongFileStructureException.class, () ->
                checker.verifyInput(newTask));
    }

    @Test
    public void priorityChecker_AssertNoThrow_value() {
        newTask.put("priority", "1");
        Runnable run = () -> {
            try {
                checker.verifyInput(newTask);
            } catch (WrongFileStructureException e) {
                Assert.fail();
                e.printStackTrace();
            }
        };

        run.run();
    }
}
