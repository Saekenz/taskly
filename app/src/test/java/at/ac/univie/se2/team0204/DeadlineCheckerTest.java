package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.DateChecker;

public class DeadlineCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    DateChecker deadlineChecker = new DateChecker();


    @Test
    public void deadlineChecker_AssertThrows() {
        Assert.assertThrows("Task does not have a deadline.", WrongFileStructureException.class, () -> {
            deadlineChecker.verifyInput(newTask);
        });
    }

    @Test
    public void deadlineChecker_AssertNoThrow() {
        newTask.put("date", "something");
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    deadlineChecker.verifyInput(newTask);
                } catch (WrongFileStructureException e) {
                    Assert.fail();
                    e.printStackTrace();
                }
            }
        };

        run.run();
    }


}
