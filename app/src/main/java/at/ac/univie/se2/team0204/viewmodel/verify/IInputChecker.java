package at.ac.univie.se2.team0204.viewmodel.verify;

import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public interface IInputChecker {

    /**
     * Each implementation of this method checks the imported {@link Task} for a specific constraint.
     * @param task The {@link Task} the check should be done on.
     * @throws WrongFileStructureException If a constraint is not enforced, this exception is thrown.
     */
    void verifyInput(Map<String, String> task) throws WrongFileStructureException;
}
