package at.ac.univie.se2.team0204;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.ac.univie.se2.team0204.model.EFileType;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.model.room.AttachmentDao;
import at.ac.univie.se2.team0204.model.room.TaskDatabase;

@RunWith(AndroidJUnit4.class)
public class AttachmentTest {
    private AttachmentDao attachmentDao;
    private TaskDatabase database;
    private Attachment attachmentPNG;
    private Attachment attachmentPDF;
    private Attachment attachmentTXT;
    private static final int INSERTED_ATTACHMENT_COUNT = 3;

    /* Database testing adapted from https://developer.android.com/training/data-storage/room/testing-db */
    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, TaskDatabase.class).build();
        attachmentDao = database.attachmentDao();
        attachmentPNG = new Attachment("/data/file/testAttachment1.png", EFileType.PNG);
        attachmentPDF= new Attachment("/data/file/testAttachment2.pdf", EFileType.PDF);
        attachmentTXT = new Attachment("/data/file/testAttachment3.txt", EFileType.TXT);
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void getAttachmentByPath_shouldReturnCorrectAttachment() {
        String path = "/data/file/testAttachment1.png";
        Attachment attachment = new Attachment(path, EFileType.PNG);
        attachment.setAttachmentID(1);
        attachmentDao.insert(attachment);
        Attachment attachmentByPath = attachmentDao.getAttachmentByPath(path);
        assertThat(attachmentByPath, equalTo(attachment));
    }

    @Test
    public void getAllAttachments_shouldReturnStoredAttachments() {
        attachmentDao.insert(attachmentPNG);
        attachmentDao.insert(attachmentPDF);
        attachmentDao.insert(attachmentTXT);
        int attachmentCount = attachmentDao.getAllAttachments().size();
        assertEquals(INSERTED_ATTACHMENT_COUNT,attachmentCount);

    }

    /**
     * Normally if an object already exists in the Database -1 would be returned on another insert.
     * The goal of this method is to instead return the ID of the already existing Object.
     */
    @Test
    public void insertAndGetID_shouldReturnIDIfAlreadyInserted(){
        long newlyCreatedAttachmentID = attachmentDao.insertAndGetID(attachmentPDF);
        long alreadyExistingAttachmentID = attachmentDao.insertAndGetID(attachmentPDF);
        assertEquals(newlyCreatedAttachmentID, alreadyExistingAttachmentID);
    }
}
