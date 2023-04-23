package services;
import java.util.ArrayList;
import objects.Task;

public class TaskService {

	private static ArrayList<Task> tasksList = new ArrayList<Task>();
	
	public static boolean addTask(Task newTask) {
		for(Task task : tasksList) {
			if (task.getTaskID() == newTask.getTaskID()) throw new IllegalArgumentException("Duplicate task ID; please try again.");
		}
		
		tasksList.add(newTask);
		
		// Tasks successfully added
		return true;
	}
	
	public static boolean deleteTask(String taskID) {
		
		for(Task task : tasksList) {
			if (task.getTaskID() == taskID) {
				int position = tasksList.indexOf(task);
				tasksList.remove(position);
				return true;
			}
			System.out.println(tasksList.size());
		}
		
		// Delete failed
		return false;
	}
	
	public static boolean updateNameByID(String taskID, String newName) {
		
		for(Task task : tasksList) {
			if(task.getTaskID() == taskID) {
				task.setName(newName);
				return true;
			}
		}
		
		// Update failed
		throw new IllegalArgumentException("Invalid ID.");
	}
	
	public static boolean updateDescriptionByID(String taskID, String newDescription) {
		
		for(Task task : tasksList) {
			if(task.getTaskID() == taskID) {
				task.setDescription(newDescription);
				return true;
			}
		}
		
		// Update failed
		throw new IllegalArgumentException("Invalid ID.");
	}
	
	public static ArrayList<Task> getTasksList() {
		return tasksList;
	}
}
