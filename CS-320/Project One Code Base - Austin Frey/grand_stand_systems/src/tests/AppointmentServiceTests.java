package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import objects.Appointment;
import services.AppointmentService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentServiceTests {

	private String invalidTestID;
	private Date initialDate;
	private String initialDescription;
	private String newDescription;
	
	@BeforeAll
	public void initialize() {
		
		this.invalidTestID = "Idonotexist";
		this.initialDate = new Date(System.currentTimeMillis()*50000);
		this.initialDescription = "A wooden doll of a clownfish";
		this.newDescription = "A wooden doll of a young lad";
	}
	
	@Test
	public void addAppointmentTest() {
		Appointment testAppointment = new Appointment("addTest1", initialDate, initialDescription);
		AppointmentService.addAppointment(testAppointment);
		assertTrue(AppointmentService.getAppointmentsList().get(0).getAppointmentID() == "addTest1");
		assertTrue(AppointmentService.getAppointmentsList().get(0).getDate() == initialDate);
		assertTrue(AppointmentService.getAppointmentsList().get(0).getDescription() == initialDescription);
	}
	
	@Test
	public void invalidAddAppointmentTest() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			AppointmentService.addAppointment(new Appointment(invalidTestID, initialDate, initialDescription));
        });
		assertTrue(AppointmentService.getAppointmentsList().size() == 0);
	}
	
	@Test
	public void deleteAppointmentTest() {
		// We do not need to add a appointment because there is still one in the local memory from prior tests
		AppointmentService.deleteAppointment("addTest1");
		assertTrue(AppointmentService.getAppointmentsList().size() == 0);
	}
	
	@Test
	public void invalidDeleteAppointmentTest() {
		AppointmentService.deleteAppointment("addTest1");
		assertTrue(AppointmentService.getAppointmentsList().size() == 0);
	}
	
	/*
	@Test
	public void updateDateTest() {
		int position;
		
		// Re-add testAppointment to the appointmentsList of AppointmentService
		Appointment testAppointment = new Appointment(validTestID, initialDate, initialDescription);
		AppointmentService.addAppointment(testAppointment);
		AppointmentService.updateDateByID(validTestID, newDate);
		
		position = AppointmentService.getAppointmentsList().indexOf(testAppointment);
		
		assertTrue(AppointmentService.getAppointmentsList().get(position).getDate() == newDate);
	}
	
	@Test
	public void invalidUpdateDateTest() {

		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			AppointmentService.updateDateByID(invalidTestID, newDate);
        });
	}
	
	@Test
	public void updateDescriptionTest() {
		int position;
		
		// Re-add testAppointment to the appointmentsList of AppointmentService
		Appointment testAppointment = new Appointment(validTestID, initialDate, initialDescription);
		AppointmentService.addAppointment(testAppointment);
		AppointmentService.updateDescriptionByID(validTestID, newDescription);
		
		position = AppointmentService.getAppointmentsList().indexOf(testAppointment);
		
		assertTrue(AppointmentService.getAppointmentsList().get(position).getDescription() == newDescription);
	}
	
	@Test
	public void invalidUpdateDescriptionTest() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			AppointmentService.updateDescriptionByID(invalidTestID, newDescription);
        });
	}
	*/
}
