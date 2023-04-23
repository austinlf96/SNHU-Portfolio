package objects;

public class Task {
	private String taskID;
	private String name;
	private String description;
	
	public Task(String taskID, String name, String description) {
		
		// Validate entries using respective validation functions
		this.taskID = validate("taskID", taskID) ? taskID : "The task ID " + taskID + " is invalid. Please try again.";
		this.name = validate("name", name) ? name : "The task ID " + name + " is invalid. Please try again.";
		this.description = validate("description", description) ? description : "The description " + description + " is invalid. Please try again.";
	}
	
	// Validate passed in values with varTest indicating which type of variable should be validated.
	public boolean validate(String varTest, String testValue) {
		
		switch(varTest) {
			
		case "taskID":
			// Validate character limits
			if (testValue.length() > 10 || testValue.length() < 1 || testValue == null) throw new IllegalArgumentException(testValue + " is an invalid entry.");
			break;
			
		case "name": 
			// Validate character limits
			if (testValue.length() > 20 || testValue.length() < 1 || testValue == null) throw new IllegalArgumentException(testValue + " is an invalid entry.");
			break;
			
		case "description":
			// Validate character limits
			if (testValue.length() > 50 || testValue.length() < 1 || testValue == null) throw new IllegalArgumentException(testValue + " is an invalid entry.");
			break;
		
		// Unknown parameter - thus false
		default:
			System.out.println("Failed to read switch statement.");
			return false;		
		}
		
		// All tests for the respective variable succeed
		return true;
	}

	// Getters/Setters- TaskID has no setter function
	public String getTaskID() {
		return taskID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = validate("name", name) ? name : "The task ID " + name + " is invalid. Please try again.";
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = validate("description", description) ? description : "The description " + description + " is invalid. Please try again.";
	}
}
