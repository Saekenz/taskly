package at.ac.univie.se2.team0204.model.room;

import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "appointmentattachment", primaryKeys = {"taskId", "attachmentID"},
        indices = {@Index(value = {"taskId", "attachmentID"}, unique = true)})
public class AppointmentAttachment {
    public long taskId;
    public long attachmentID;

    public AppointmentAttachment(long taskId, long attachmentID){
        this.taskId = taskId;
        this.attachmentID = attachmentID;
    }
}
