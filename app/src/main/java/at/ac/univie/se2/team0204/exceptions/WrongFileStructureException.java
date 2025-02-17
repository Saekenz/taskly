package at.ac.univie.se2.team0204.exceptions;

public class WrongFileStructureException extends Exception {

    /**
     * This exception is thrown if an imported file does not have the structure the system needs.
     * @param message The message displayed by the exception.
     */
    public WrongFileStructureException(String message) {
        super(message);
    }

}
