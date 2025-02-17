package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class DateChecker implements IInputChecker {

    /**
     * Checks if the {@link Task} has the key deadline.
     *
     * @param task Task on which the verification is done.
     * @throws WrongFileStructureException If the {@link Task} does not hold the key, this exception is thrown
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {

        // TaskType is checked before, so task that reaches this definitely has taskType
        if(Objects.equals(task.get("taskType"), "Appointment")) {
            if(!task.containsKey("date")) {
                throw new WrongFileStructureException("Task does not have a date.");
            }
        }

        // Potentially add restrictions for deadline, e.g. valid dates
    }
}
