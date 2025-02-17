package at.ac.univie.se2.team0204.model.room;

import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "todoattachment", primaryKeys = {"taskId", "attachmentID"},
indices = {@Index(value = {"taskId", "attachmentID"}, unique = true)})
public class TodoAttachment {
    public long taskId;
    public long attachmentID;

    public TodoAttachment(long taskId, long attachmentID){
        this.taskId = taskId;
        this.attachmentID = attachmentID;
    }
}
