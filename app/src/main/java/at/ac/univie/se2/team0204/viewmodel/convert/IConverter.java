package at.ac.univie.se2.team0204.viewmodel.convert;

import at.ac.univie.se2.team0204.model.room.Task;
import at.ac.univie.se2.team0204.viewmodel.TaskImportIterator;

public interface IConverter {

    /**
     * This method converts the string representation of a file into the system internal iterator.
     * @param rawTask The string of the imported tasks.
     * @return A new iterator object holding all imported tasks.
     * @throws Exception Might throw a parsing error, if the string does not have a valid file format.
     */
    TaskImportIterator convertToIterator(String rawTask) throws Exception;

    /**
     * Converts task into a file format, ready to be written to a file.
     * @param task The task that will be converted.
     * @return A string with the task in a specific file format.
     */
    String convertToString(Task task);
}
