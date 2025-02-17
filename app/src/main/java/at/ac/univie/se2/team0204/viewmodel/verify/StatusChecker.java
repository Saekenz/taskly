package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;
import java.util.Objects;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class StatusChecker implements IInputChecker {

    /**
     * Checks if the {@link Task} has the key status. If the key exists, its value is checked for integrity.
     *
     * @param task {@link Task} on which the verification is done
     * @throws WrongFileStructureException If the {@link Task} does not hold the key, this exception is thrown. If the key exists and the
     * value is not either Pending, Done or Before, this exception is thrown.
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {

        if(!task.containsKey("status")) {
            throw new WrongFileStructureException("Task does not have a status.");
        }

        String status = Objects.requireNonNull(task.get("status"));

        if(!status.equals("Done") && !status.equals("Pending") && !status.equals("Before")) {
            throw new WrongFileStructureException("Invalid status. Needs to be Before, Pending or Done");
        }
    }
}
