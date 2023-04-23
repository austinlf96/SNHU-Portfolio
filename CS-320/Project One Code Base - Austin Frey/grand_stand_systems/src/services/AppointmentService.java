package services;
import java.util.ArrayList;
import objects.Appointment;
// import java.util.Date;

public class AppointmentService {

	private static ArrayList<Appointment> appointmentsList = new ArrayList<Appointment>();
	
	public static boolean addAppointment(Appointment newAppointment) {
		for(Appointment appointment : appointmentsList) {
			if (appointment.getAppointmentID() == newAppointment.getAppointmentID()) throw new IllegalArgumentException("Duplicate appointment ID; please try again.");
		}
		
		appointmentsList.add(newAppointment);
		
		// Appointments successfully added
		return true;
	}
	
	public static boolean deleteAppointment(String appointmentID) {
		
		for(Appointment appointment : appointmentsList) {
			if (appointment.getAppointmentID() == appointmentID) {
				int position = appointmentsList.indexOf(appointment);
				appointmentsList.remove(position);
				return true;
			}
			System.out.println(appointmentsList.size());
		}
		
		// Delete failed
		return false;
	}
	
	public static ArrayList<Appointment> getAppointmentsList() {
		return appointmentsList;
	}
	
	/*
	 * Unnecessary methods kept from using the tasks package as a template
	
	public static boolean updateDateByID(String appointmentID, Date newDate) {
		
		for(Appointment appointment : appointmentsList) {
			if(appointment.getAppointmentID() == appointmentID) {
				appointment.setDate(newDate);
				return true;
			}
		}
		
		// Update failed
		throw new IllegalArgumentException("Invalid ID.");
	}
	
	public static boolean updateDescriptionByID(String appointmentID, String newDescription) {
		
		for(Appointment appointment : appointmentsList) {
			if(appointment.getAppointmentID() == appointmentID) {
				appointment.setDescription(newDescription);
				return true;
			}
		}
		
		// Update failed
		throw new IllegalArgumentException("Invalid ID.");
	}
	*/
	
}
