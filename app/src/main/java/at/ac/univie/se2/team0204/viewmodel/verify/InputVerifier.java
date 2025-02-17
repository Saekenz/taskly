package at.ac.univie.se2.team0204.viewmodel.verify;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.ac.univie.se2.team0204.exceptions.WrongFileStructureException;
import at.ac.univie.se2.team0204.model.room.Task;

public class InputVerifier {

    /**
     * This list contains all {@link IInputChecker} implementations.
     */
    List<IInputChecker> inputVerifiers;

    /**
     * An identifier used by the logger.
     */
    private static final String TAG = "InputVerifier";

    /**
     * Creates and initializes all tests required for importing a {@link Task}.
     */
    public InputVerifier() {

        // The Interface/ArrayList structure is supposed to implement the Open-Close Principle
        inputVerifiers = new ArrayList<>();

        inputVerifiers.add(new TypeChecker());
        inputVerifiers.add(new IdChecker());
        inputVerifiers.add(new DateChecker());
        inputVerifiers.add(new PriorityChecker());
        inputVerifiers.add(new StatusChecker());
        inputVerifiers.add(new TypeChecker());
        inputVerifiers.add(new TitleChecker());
        inputVerifiers.add(new DescriptionChecker());
    }

    /**
     * Iterates through all {@link IInputChecker}s.
     *
     * @param task The {@link Task} checks should be done on.
     * @throws WrongFileStructureException If a check does not go through, an exception is thrown.
     */
    public void verifyInput(Map<String, String> task) throws WrongFileStructureException {
        Log.i(TAG, "Verifying input");
        for(IInputChecker verifier: inputVerifiers) {
            verifier.verifyInput(task);
        }
        Log.i(TAG, "Verification successful");
    }
}
