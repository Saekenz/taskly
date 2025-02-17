package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class DescriptionChecker implements IInputChecker {

    /**
     * Checks if the {@link Task} has the key description.
     *
     * @param task {@link Task} on which the verification is done
     * @throws WrongFileStructureException If the {@link Task} does not hold the key, this exception is thrown
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {
        if(!task.containsKey("description")) {
            throw new WrongFileStructureException("Task does not have a description");
        }

        // Potentially add restrictions, e.g. length
    }
}
