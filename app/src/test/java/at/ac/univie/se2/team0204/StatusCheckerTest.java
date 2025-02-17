package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.IInputChecker;
import at.ac.univie.se2.team0204.viewmodel.verify.StatusChecker;

public class StatusCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    IInputChecker checker = new StatusChecker();

    @Test
    public void statusChecker_AssertThrows_key() {
        Assert.assertThrows("Task does not have a status.", WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void statusChecker_AssertNoThrow_key() {
        newTask.put("status", "Pending");
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
    public void statusChecker_AssertNoThrow_pending() {
        newTask.put("status", "Pending");
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
    public void statusChecker_AssertNoThrow_done() {
        newTask.put("status", "Done");
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
    public void statusChecker_AssertNoThrow_before() {
        newTask.put("status", "Before");
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
    public void statusChecker_AssertThrows_lowValue() {
        newTask.put("status", "This is fine");
        Assert.assertThrows("Invalid status. Needs to be Before, Pending or Done", WrongFileStructureException.class, () ->
                checker.verifyInput(newTask));
    }

}
