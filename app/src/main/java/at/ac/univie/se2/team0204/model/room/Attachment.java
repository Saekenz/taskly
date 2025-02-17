package at.ac.univie.se2.team0204.model.room;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

import at.ac.univie.se2.team0204.model.EFileType;

@Entity(tableName = "attachment", indices = {@Index(value = {"path","fileType"}, unique = true)})
public class Attachment {

    @PrimaryKey(autoGenerate = true)
    private int attachmentID;

    private String path;

    private EFileType fileType;

    public Attachment(String path, EFileType fileType){
        this.path = path;
        this.fileType = fileType;
    }

    public int getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(int attachmentID) {
        this.attachmentID = attachmentID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EFileType getFileType() {
        return fileType;
    }

    public void setFileType(EFileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Filetype: %s, Path: %s", attachmentID,fileType,path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return attachmentID == that.attachmentID && path.equals(that.path) && fileType == that.fileType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentID, path, fileType);
    }
}
