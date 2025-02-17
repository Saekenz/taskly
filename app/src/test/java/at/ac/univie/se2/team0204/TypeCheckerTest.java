package at.ac.univie.se2.team0204;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.viewmodel.verify.IInputChecker;
import at.ac.univie.se2.team0204.viewmodel.verify.TypeChecker;

public class TypeCheckerTest {

    Map<String, String> newTask = new HashMap<>();
    IInputChecker checker = new TypeChecker();

    @Test
    public void typeChecker_AssertThrows() {
        Assert.assertThrows("Task does not contain a task type.", WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void typeChecker_AssertThrows_value() {
        newTask.put("taskType", "todo");
        Assert.assertThrows("Task does not have the right type. Needs to be" +
                "Appointment or ToDo", WrongFileStructureException.class, () -> {
            checker.verifyInput(newTask);
        });
    }

    @Test
    public void typeChecker_AssertNoThrow() {
        newTask.put("taskType", "ToDo");
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
    public void typeChecker_AssertNoThrow_valueTodo() {
        newTask.put("taskType", "Appointment");
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
    public void typeChecker_AssertNoThrow_valueAppointment() {
        newTask.put("taskType", "ToDo");
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
