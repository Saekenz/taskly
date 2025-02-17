package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class IdChecker implements IInputChecker {

    /**
     * Checks if the taskId, should the {@link Task} have one, is not null.
     *
     * @param task {@link Task} on which the verification is done
     * @throws WrongFileStructureException If the taskId is null, this exception is thrown
     */
    @Override
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {
        if(task.containsKey("taskId")) {
            if(task.get("taskId") == null) {
                throw new WrongFileStructureException("The task does not contain an Id!");
            }
        }

    }
}
