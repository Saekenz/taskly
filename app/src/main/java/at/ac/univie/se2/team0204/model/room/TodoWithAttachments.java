package at.ac.univie.se2.team0204.model.room;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TodoWithAttachments {
    @Embedded public Todo todo;
    @Relation(
            parentColumn = "taskId",
            entityColumn = "attachmentID",
            associateBy = @Junction(TodoAttachment.class)
    )
    public List<Attachment> attachments;
}
