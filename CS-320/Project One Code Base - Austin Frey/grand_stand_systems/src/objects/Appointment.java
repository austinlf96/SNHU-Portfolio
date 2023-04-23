package objects;
import java.util.Date;

public class Appointment {
	private String appointmentID;
	private Date date;
	private String description;
	
	public Appointment(String appointmentID, Date date, String description) {
		
		// Validate entries using respective validation functions
		this.appointmentID = validate("appointmentID", appointmentID) ? appointmentID : "The appointment ID " + appointmentID + " is invalid. Please try again.";
		this.description = validate("description", description) ? description : "The description " + description + " is invalid. Please try again.";
		if (date.before(new Date())) throw new IllegalArgumentException("Invalid date.");
		this.date = date;
		
	}
	
	// Validate passed in values with varTest indicating which type of variable should be validated.
	public boolean validate(String varTest, String testValue) {
		
		switch(varTest) {
			
		case "appointmentID":
			// Validate character limits
			if (testValue.length() > 10 || testValue.length() < 1 || testValue == null) throw new IllegalArgumentException(testValue + " is an invalid entry.");
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

	// Getters/Setters- AppointmentID has no setter function
	public String getAppointmentID() {
		return appointmentID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if (date.before(new Date())) throw new IllegalArgumentException("Invalid date.");
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = validate("description", description) ? description : "The description " + description + " is invalid. Please try again.";
	}
}
