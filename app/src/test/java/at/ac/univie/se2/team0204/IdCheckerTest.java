package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.IInputChecker;
import at.ac.univie.se2.team0204.viewmodel.verify.IdChecker;

public class IdCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    IInputChecker checker = new IdChecker();

    @Test
    public void idChecker_AssertThrows() {
        newTask.put("taskId", null);
        Assert.assertThrows(WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void idChecker_AssertNoThrow() {
        newTask.put("taskId", "1");
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
    public void idChecker_AssertNoThrow_noId() {
        Runnable run = () -> {
            try {
                checker.verifyInput(newTask);
            } catch (WrongFileStructureException e) {
                e.printStackTrace();
            }
        };

        run.run();
    }
}
