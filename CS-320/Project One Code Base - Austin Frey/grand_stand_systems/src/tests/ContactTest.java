package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import objects.Contact;

public class ContactTest {

	@Test
	public void testContactClass() {
		Contact testContact = new Contact("NemoQwest", "Nemo", "Frey", "7171234567", "42 Wallaby Way Sydney");
		assertTrue(testContact.getContactID().equals("NemoQwest"));
		assertTrue(testContact.getFirstName().equals("Nemo"));
		assertTrue(testContact.getLastName().equals("Frey"));
		assertTrue(testContact.getPhone().equals("7171234567"));
		assertTrue(testContact.getAddress().equals("42 Wallaby Way Sydney"));
	}
	
	// Input validation doesn't happen within Contact, but a truncation fail-safe was put into place to ensure that data entered fits the limits imposed by the prompt
	// Thus, thus test just ensures that the truncation behaves properly
	@Test
	public void testContactClassTooLong() {
		Contact testContact = new Contact("NemoQwestiez", "NemoNemoNemo", "FreyFreyFrey", "717123456712", "42 Wallaby Way Sydney baby fishies");
		assertTrue(testContact.getContactID().equals("NemoQwesti"));
		assertTrue(testContact.getFirstName().equals("NemoNemoNe"));
		assertTrue(testContact.getLastName().equals("FreyFreyFr"));
		assertTrue(testContact.getPhone().equals("7171234567"));
		assertTrue(testContact.getAddress().equals("42 Wallaby Way Sydney baby fis"));
	}
}
