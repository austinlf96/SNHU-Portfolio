package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import objects.Task;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskTest {
	
	private String validTaskID;
	private String invalidTaskID;
	private String name;
	private String description;
	
	@BeforeAll
	public void initalize() {
		System.out.println("Initializing testing variables . . .");
		
		this.validTaskID = "validTest";
		this.invalidTaskID = "Invalid Test!";
		this.name = "Nemo Qwest";
		this.description = "Just a little positive testing procedure!";
		
		System.out.println("Initalization complete!");
	}
	
	@Test
	public void testValidTask() {
		Task testTask = new Task(validTaskID, name, description);
		assertTrue(testTask.getTaskID() == validTaskID);
		assertTrue(testTask.getName() == name);
		assertTrue(testTask.getDescription() == description);
	}
	
	@Test
	public void testInvalidTask() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Task testTask = new Task(invalidTaskID, name, description);
        });
	}
}
