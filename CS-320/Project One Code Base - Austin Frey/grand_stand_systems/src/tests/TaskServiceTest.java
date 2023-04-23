package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import objects.Task;
import services.TaskService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskServiceTest {

	private String validTestID;
	private String invalidTestID;
	private String initialName;
	private String newName;
	private String initialDescription;
	private String newDescription;
	
	@BeforeAll
	public void initialize() {
		this.validTestID = "Imarealboy";
		this.invalidTestID = "Idonotexist";
		this.initialName = "Nemocchio";
		this.newName = "Pinocchio";
		this.initialDescription = "A wooden doll of a clownfish";
		this.newDescription = "A wooden doll of a young lad";
	}
	
	@Test
	public void addTaskTest() {
		TaskService.deleteTask(validTestID);
		Task testTask = new Task(validTestID, initialName, initialDescription);
		TaskService.addTask(testTask);
		assertTrue(TaskService.getTasksList().indexOf(testTask) != -1);
	}
	
	@Test
	public void deleteTaskTest() {
		// We do not need to add a task because there is still one in the local memory from prior tests
		TaskService.deleteTask(validTestID);
		assertTrue(TaskService.getTasksList().size() == 0);
	}
	
	@Test
	public void invalidDeleteTaskTest() {
		TaskService.deleteTask(validTestID);
		assertTrue(TaskService.getTasksList().size() == 0);
	}
	
	@Test
	public void updateNameTest() {
		int position;
		
		// Re-add testTask to the tasksList of TaskService
		Task testTask = new Task(validTestID, initialName, initialDescription);
		TaskService.addTask(testTask);
		TaskService.updateNameByID(validTestID, newName);
		
		position = TaskService.getTasksList().indexOf(testTask);
		
		assertTrue(TaskService.getTasksList().get(position).getName() == newName);
	}
	
	@Test
	public void invalidUpdateNameTest() {

		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			TaskService.updateNameByID(invalidTestID, newName);
        });
	}
	
	@Test
	public void updateDescriptionTest() {
		int position;
		
		// Re-add testTask to the tasksList of TaskService
		Task testTask = new Task(validTestID, initialName, initialDescription);
		TaskService.addTask(testTask);
		TaskService.updateDescriptionByID(validTestID, newDescription);
		
		position = TaskService.getTasksList().indexOf(testTask);
		
		assertTrue(TaskService.getTasksList().get(position).getDescription() == newDescription);
	}
	
	@Test
	public void invalidUpdateDescriptionTest() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			TaskService.updateDescriptionByID(invalidTestID, newDescription);
        });
	}
}
