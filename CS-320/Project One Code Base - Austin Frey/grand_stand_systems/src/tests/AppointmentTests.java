package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import objects.Appointment;
import services.AppointmentService;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentTests {
	
	private String validAppointmentID;
	private String invalidAppointmentID;
	private Date date;
	private String description;
	
	@BeforeAll
	public void initalize() {
		System.out.println("Initializing testing variables . . .");
		
		this.validAppointmentID = "validTest";
		this.invalidAppointmentID = "Invalid Test!";
		this.date = new Date(System.currentTimeMillis()*50000);
		this.description = "Just a little positive testing procedure!";
		
		System.out.println("Initalization complete!");
	}
	
	@Test
	public void testValidAppointment() {
		Appointment testAppointment = new Appointment("newest1", date, description);
		assertTrue(testAppointment.getAppointmentID() == "newest1");
		assertTrue(testAppointment.getDate() == date);
		assertTrue(testAppointment.getDescription() == description);
	}
	
	@Test
	public void testUpdateAppointmentDate() {
		Date testDate = new Date();
		Appointment testAppointment = new Appointment(validAppointmentID + "2", date, description);
		testAppointment.setDate(testDate);
		assertTrue(testAppointment.getDate() == testDate);
	}
	
	@Test
	public void testUpdateAppointmentDescription() {
		Appointment testAppointment = new Appointment(validAppointmentID + "3", date, description);
		testAppointment.setDescription("I'm something new, everyone!");
		assertTrue(testAppointment.getDescription() == "I'm something new, everyone!");
	}
	
	@Test
	public void testGetAppointmentDate() {
		Appointment testAppointment = new Appointment(validAppointmentID + "4", date, description);
		assertTrue(testAppointment.getDate() == date);
	}
	
	@Test
	public void testGetAppointmentDescription() {	
		Appointment testAppointment = new Appointment(validAppointmentID + "5", date, description);
		assertTrue(testAppointment.getDescription() == description);
	}
	
	@Test
	public void testInvalidAppointmentID() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Appointment testAppointment = new Appointment(invalidAppointmentID, date, description);
        });
	}
	
	@Test
	public void testInvalidAppointmentDate() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Appointment testAppointment = new Appointment("ValidID", new Date(1000000), description);
        });
	}
	
	@Test
	public void testInvalidAppointmentDescription() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Appointment testAppointment = new Appointment("VALIDid", date, "I'm quite sorry good sir but I'm a jolly good error but certainly not just a sailor, some even call me a tomator.");
        });
	}
}
