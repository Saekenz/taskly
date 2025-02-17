package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class PriorityChecker implements IInputChecker {

    /**
     * Checks if the {@link Task} has the key priority. If the key exists, it furthermore makes sure the value is valid.
     *
     * @param task {@link Task} on which the verification is done
     * @throws WrongFileStructureException If the {@link Task} does not hold the key, this exception is thrown. If the key exists and the
     * value is below 1 or over 5, this exception is thrown.
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {
        if(!task.containsKey("priority")) {
            throw new WrongFileStructureException("Task does not have a priority");
        }

        int taskPriority = Integer.parseInt(Objects.requireNonNull(task.get("priority")));

        if(taskPriority > 5 || taskPriority < 1) {
            throw new WrongFileStructureException("Task priority is invalid. Needs to be between (including) 1 and 5.");
        }
    }
}
