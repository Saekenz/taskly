package at.ac.univie.se2.team0204.model.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * Defines appointment tasks for persistent storage.
 *
 */
@Entity(tableName = "appointment")
public class Appointment extends Task {

    /**
     * The place an {@link Appointment} will take place at.
     */
    public String place;

    /**
     * The date of the {@link Appointment}.
     */
    public String date;

    /**
     * Creates a new {@link Appointment}.
     * @param title The title of the {@link Appointment}.
     * @param description The description of the {@link Appointment}.
     * @param place The place of the {@link Appointment}.
     * @param date The date of the {@link Appointment}.
     */
    public Appointment(String title, String description,String place, String date) {
        super(title, description);
        this.place=place;
        this.date=date;

    }

    /**
     * Creates a new {@link Appointment}.
     * @param taskId The id of the {@link Appointment}.
     * @param title The title of the {@link Appointment}.
     * @param description The description of the {@link Appointment}.
     * @param place The place of the {@link Appointment}.
     * @param date The date of the {@link Appointment}.
     */
    @Ignore
    public Appointment(int taskId, String title, String description, String place, String date) {
        super(taskId, title, description);
        this.place = place;
        this.date = date;
    }

    /**
     * Changes the description of the {@link Appointment}.
     * @param description The new description.
     */
    public void setDescription(String description) {
        super.setDescription(description);
    }

    /**
     * Changes the title of the {@link Appointment}.
     * @param title The new title.
     */
    public void setTitle(@NonNull String title) {
        super.setTitle(title);
    }

    /**
     * Changes the place of the {@link Appointment}.
     * @param place The new place.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Changes the date of the {@link Appointment}.
     * @param date Thew new date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the description of this {@link Appointment}.
     * @return The description of this {@link Appointment}.
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Returns the title of this {@link Appointment}.
     * @return The title of this {@link Appointment}.
     */
    @NonNull
    public String getTitle() {
        return super.getTitle();
    }

    /**
     * Returns the place of this {@link Appointment}.
     * @return The place of this {@link Appointment}.
     */
    public String getPlace() {
        return place;
    }

    /**
     * Returns the date of this {@link Appointment}.
     * @return The date of this {@link Appointment}.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns a string representation of this {@link Appointment}.
     * @return A string representing the {@link Appointment}.
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("ID: %s, Title: %s, Place: %s, Date: %s", super.getTaskId(), super.getTitle(), place, date);
    }

    /**
     * Returns a type specific description of this {@link Appointment}.
     * @return A string representing this {@link Appointment} in a type specific way.
     */
    public String getTypeSpecificDescription(){
        return "Place: " + place + ", Date: " + date;
    }

    /**
     * Returns the deadline of this {@link Appointment}.
     * @return The {@link Appointment}.
     */
    public String getNotifyTime(){
        return date;
    }
}
