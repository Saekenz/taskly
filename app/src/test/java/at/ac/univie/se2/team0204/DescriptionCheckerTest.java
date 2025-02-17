package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.DescriptionChecker;
import at.ac.univie.se2.team0204.viewmodel.verify.IInputChecker;

public class DescriptionCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    IInputChecker checker = new DescriptionChecker();


    @Test
    public void descriptionChecker_AssertThrows() {
        Assert.assertThrows(WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void descriptionChecker_AssertNoThrow() {
        newTask.put("description", "something");
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
