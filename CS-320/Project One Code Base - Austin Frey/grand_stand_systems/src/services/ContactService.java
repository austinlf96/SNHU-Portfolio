package services;
import objects.Contact;
import java.util.ArrayList;


public class ContactService {
	
	// In-memory data structure to hold Contact objects
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	// The milestone rubric specifies that the phone string must be exactly 10 digits.
	// For this reason, it's assumed that id and address must be alphanumeric characters 
	// and names must be alphabetical characters. 
	
	
	public static void addContact(Contact contact) {
		Contact existingContact = findContact(contact.getContactID());
		if (existingContact.getContactID().compareTo("Unknown") == 0) deleteContact(existingContact.getContactID());
		else contacts.add(contact);
	}
	
	public static Contact findContact(String id) {
		try {
			// Search ArrayList for specified contact by ID
			for (Contact contact : contacts) {
				if (contact.getContactID().compareTo(id) == 0) return contact;
			}
			throw new IllegalArgumentException("Contact does not exist");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return new Contact("Unknown", "Unknown", "Unknown", "0123456789", "Unknown");
		}
	
	}
	
	public static int findContactIndex(String id) {
		
		// Search ArrayList for specified contact by ID
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getContactID().compareTo(id) == 0) return i;
		}
		
		// Contact not found. 
		return -1;
	}
	
	public static void deleteContact(String id) {
		int contactIndex = findContactIndex(id);
		
		if (contactIndex == -1) System.out.println("Contact not found in database. Please try again");
		else contacts.remove(contactIndex);
		
	}
	
	public static void updateContact(String id, String attribute, String newValue) {
		
		// Find contact
		Contact contact =findContact(id);
		// Handle updating each attribute
		switch(attribute) {
			case "first name":
				contact.setFirstName(newValue);
				break;
			case "last name":
				contact.setLastName(newValue);
				break;
			case "phone":
				contact.setPhone(newValue);
				break;
			case "address":
				contact.setAddress(newValue);
				break;
			default:
				System.out.println("Illegal field. Please try again.");
				break;
		}
	}

}
