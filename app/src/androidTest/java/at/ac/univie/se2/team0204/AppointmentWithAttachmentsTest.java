package at.ac.univie.se2.team0204;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import at.ac.univie.se2.team0204.model.EFileType;
import at.ac.univie.se2.team0204.model.room.Appointment;
import at.ac.univie.se2.team0204.model.room.AppointmentAttachment;
import at.ac.univie.se2.team0204.model.room.AppointmentDao;
import at.ac.univie.se2.team0204.model.room.AppointmentWithAttachments;
import at.ac.univie.se2.team0204.model.room.Attachment;
import at.ac.univie.se2.team0204.model.room.AttachmentDao;
import at.ac.univie.se2.team0204.model.room.TaskDatabase;

@RunWith(AndroidJUnit4.class)
public class AppointmentWithAttachmentsTest {
    private AttachmentDao attachmentDao;
    private AppointmentDao appointmentDao;
    private TaskDatabase database;
    private static final int INSERTED_APPOINTMENTS_COUNT = 3;
    private static final int APPOINTMENT1_ATTACHMENTS_COUNT = 3;
    private static final int APPOINTMENT2_ATTACHMENTS_COUNT = 1;
    private static final int APPOINTMENT3_ATTACHMENTS_COUNT = 0;
    private Attachment attachmentPNG;
    private Attachment attachmentPDF;
    private Attachment attachmentTXT;

    /* Database testing adapted from https://developer.android.com/training/data-storage/room/testing-db */
    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, TaskDatabase.class).build();
        attachmentDao = database.attachmentDao();
        appointmentDao = database.appointmentDao();
        initAttachments();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void getAllAppointments_shouldReturnEveryAppointmentWithAttachments(){
       insertTestAppointments();
       insertTestAttachments();
       insertTestAppointmentAttachments();
       List<AppointmentWithAttachments> appointmentWithAttachments = appointmentDao.getAppointmentsWithAttachments();

       assertEquals(INSERTED_APPOINTMENTS_COUNT,appointmentWithAttachments.size());
       assertEquals(APPOINTMENT1_ATTACHMENTS_COUNT,appointmentWithAttachments.get(0).attachments.size());
       assertEquals(APPOINTMENT2_ATTACHMENTS_COUNT,appointmentWithAttachments.get(1).attachments.size());
       assertEquals(APPOINTMENT3_ATTACHMENTS_COUNT,appointmentWithAttachments.get(2).attachments.size());
    }

    @Test
    public void getAllAppointments_shouldReturnEveryAddedAttachment() {
      insertTestAppointments();
      insertTestAttachments();
      insertTestAppointmentAttachments();
      List<AppointmentWithAttachments> appointmentWithAttachments = appointmentDao.getAppointmentsWithAttachments();

      List<Attachment> appointment1Attachments = appointmentWithAttachments.get(0).attachments;
      List<Attachment> appointment2Attachments = appointmentWithAttachments.get(1).attachments;
      List<Attachment> appointment3Attachments = appointmentWithAttachments.get(2).attachments;

     assert(appointment1Attachments.contains(attachmentPNG));
     assert(appointment1Attachments.contains(attachmentPDF));
     assert(appointment1Attachments.contains(attachmentTXT));
     assert(appointment2Attachments.contains(attachmentPDF));
     assert(appointment3Attachments.isEmpty());

    }

    private void insertTestAppointments() {
        Appointment appointment1 = new Appointment("appTest1","for testing","WS-29","18-01-2023");
        Appointment appointment2 = new Appointment("appTest2","for testing","Unicampus","01-01-2023");
        Appointment appointment3 = new Appointment("appTest3","for testing","Hauptuni","24-12-2054");
        appointmentDao.insert(appointment1);
        appointmentDao.insert(appointment2);
        appointmentDao.insert(appointment3);
    }

    private void insertTestAttachments() {
        attachmentDao.insert(attachmentPNG);
        attachmentDao.insert(attachmentPDF);
        attachmentDao.insert(attachmentTXT);
    }

    private void insertTestAppointmentAttachments() {
        AppointmentAttachment appointmentAttachment1 = new AppointmentAttachment(1,1);
        AppointmentAttachment appointmentAttachment2 = new AppointmentAttachment(1,2);
        AppointmentAttachment appointmentAttachment3 = new AppointmentAttachment(1,3);
        AppointmentAttachment appointmentAttachment4 = new AppointmentAttachment(2,2);
        attachmentDao.insertAppointmentAttachment(appointmentAttachment1);
        attachmentDao.insertAppointmentAttachment(appointmentAttachment2);
        attachmentDao.insertAppointmentAttachment(appointmentAttachment3);
        attachmentDao.insertAppointmentAttachment(appointmentAttachment4);
    }

    private void initAttachments() {
        attachmentPNG = new Attachment("/data/file/testAttachment1.png", EFileType.PNG);
        attachmentPDF= new Attachment("/data/file/testAttachment2.pdf", EFileType.PDF);
        attachmentTXT = new Attachment("/data/file/testAttachment3.txt", EFileType.TXT);
        attachmentPNG.setAttachmentID(1);
        attachmentPDF.setAttachmentID(2);
        attachmentTXT.setAttachmentID(3);
    }
}
