package at.ac.univie.se2.team0204.model.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "todo")
public class Todo extends Task{

	private int parentID;

	//private Status status;
	//private Priority priority;
	//private List<Attachment> attachments;
	//public boolean checked;
	// public List<Todo> subTodos;

	/**
	 * Creates a new {@link Todo}.
	 * @param title The title of the new {@link Todo}.
	 * @param description The description of the new {@link Todo}.
	 */
	@Ignore
	public Todo(String title, String description) {
		super(title,description);
	}

	/**
	 * Creates a new {@link Todo}.
	 * @param title The title of the new {@link Todo}.
	 * @param description The description of the new {@link Todo}.
	 * @param parentID The id of the parent of this {@link Todo}.
	 */
	public Todo(String title, String description, int parentID) {
		super(title,description);
		this.parentID = parentID;
	}

	/**
	 * Creates a new {@link Todo}.
	 * @param taskId The id of the newly created {@link Todo}.
	 * @param title The title of the new {@link Todo}.
	 * @param description The description of the new {@link Todo}.
	 */
	@Ignore
	public Todo(int taskId, String title, String description) {
		super(taskId, title, description);
	}

	//#Todo check the two following methods if necessary:

	/**
	 * Creates a type specific string representation of the {@link Todo}.
	 * @return A type specific string representation of the {@link Todo}.
	 */
	public String getTypeSpecificDescription(){
		return "Place: " + "[place]" + ", Date: " + "[date]";
	}

	/**
	 * Returns the notification time of this {@link Todo}.
	 * @return
	 */
	public String getNotifyTime(){
		return "Test value";
	}

	/**
	 * Returns the id of this {@link Todo}'s parent.
	 * @return The id of the parent of this {@link Todo}.
	 */
	public int getParentID() {
		return parentID;
	}

	/**
	 * Changes the parent of this {@link Todo}.
	 * @param parentID The id of the new parent.
	 */
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
}
