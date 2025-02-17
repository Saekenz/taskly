package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class TypeChecker implements IInputChecker {

    /**
     * Checks if the {@link Task} has the key taskType. If the key exists it is checked for the correct value.
     *
     * @param task {@link Task} on which the verification is done
     * @throws WrongFileStructureException If the {@link Task} does not hold the key, this exception is thrown. If the value is not
     * correct, it is also thrown.
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {
        if(!task.containsKey("taskType")) {
            throw new WrongFileStructureException("Task does not contain a task type.");
        }

        String taskType = Objects.requireNonNull(task.get("taskType"));

        if(!taskType.equals("Appointment") && !taskType.equals("ToDo")) {
            throw new WrongFileStructureException("Task does not have the right type. Needs to be" +
                    "Appointment or ToDo");
        }
    }
}
