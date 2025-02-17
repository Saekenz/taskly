package at.ac.univie.se2.team0204.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "task")
public abstract class Task {

	/**
	 * Identifier of the {@link Task}.
	 */
	@PrimaryKey(autoGenerate = true)
	private int taskId;

	/**
	 * Title of the {@link Task}.
	 */
	@NonNull
	private String title;

	/**
	 * Description of the {@link Task}.
	 */
	private String description;

	/**
	 * Creates a new {@link Task}.
	 * @param title The title of the {@link Task}.
	 * @param description The description of the {@link Task}.
	 */
	public Task(@NonNull String title, String description) {
		this.title = title;
		this.description = description;
	}

	/**
	 * Creates a new {@link Task}.
	 * @param taskId Identifier of the {@link Task}.
	 * @param title Title of the {@link Task}.
	 * @param description Description of the {@link Task}.
	 */
	public Task(int taskId, @NonNull String title, String description) {
		this.taskId = taskId;
		this.title = title;
		this.description = description;
	}

	/**
	 * Changes the id of the {@link Task}.
	 * @param appointmentId The new id of the {@link Task}.
	 */
	public void setTaskId(int appointmentId) {
		this.taskId = appointmentId;
	}

	/**
	 * Changes the description of the {@link Task}.
	 * @param description The new description of the {@link Task}.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Changes the title of the {@link Task}.
	 * @param title The new title of the {@link Task}.
	 */
	public void setTitle(@NonNull String title) {
		this.title = title;
	}

	/**
	 * Returns the {@link Task} identifier.
	 * @return The {@link Task} identifier.
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * Returns the {@link Task} description.
	 * @return The {@link Task} description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the {@link Task} title.
	 * @return The {@link Task} title.
	 */
	@NonNull
	public String getTitle() {
		return title;
	}

	/**
	 * Returns a string representation of the {@link Task}.
	 * @return The string representation of the {@link Task}.
	 */
	public String getTextDescription() {
		String des = "Title: " + title + ", Description: " + description;
		des = des + ", Time: " + getNotifyTime() + ", ";
		des = des + getTypeSpecificDescription();
		return des;
	}

	/**
	 * Returns a description of the {@link Task} in a type specific way.
	 * @return A representation of the {@link Task}.
	 */
	public abstract String getTypeSpecificDescription();

	/**
	 * Returns the date of the {@link Task}.
	 * @return The date of the {@link Task}.
	 */
	public abstract String getNotifyTime();

}
