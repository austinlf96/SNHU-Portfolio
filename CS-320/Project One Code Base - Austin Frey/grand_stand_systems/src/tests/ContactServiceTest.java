package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import objects.Appointment;
import objects.Contact;
import services.AppointmentService;
import services.ContactService;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactServiceTest {

	@BeforeAll
	public void initialize() {

	}
	
	@Test
	public void addAppointmentTest() {

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Test
	public void validateTestId() {
        assertTrue(Contact.validate("id","Nem0N0b0dy"));
	}
	
	@Test
	public void validateTestIdInvalid() {

        // Invalid character entry
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("id", "Nemo-Nope");
        });

        // ID length exceeds limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("id", "NemoNopeyNope");
        });
	}
	
	@Test
	public void validateTestFirstName() {
        assertTrue(ContactService.validate("first","Nemo"));
	}
	
	@Test
	public void validateTestFirstNameInvalid() {

        // Invalid character entry
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("first", "Nemo-Nope");
        });

        // Name length exceeds limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("first", "NemoNopeyNope");
        });
	}

	@Test
	public void validateTestLastName() {
        assertTrue(ContactService.validate("last","Nobody"));
	}
	
	@Test
	public void validateTestLastNameInvalid() {

        // Invalid character entry
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("last", "Nemo-Nope");
        });

        // Name length exceeds limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("last", "NemoNopeyNope");
        });
	}
    
	@Test
	public void validateTestPhone() {
		assertTrue(ContactService.validate("phone","7171234567"));
	}
	
	@Test
	public void validateTestPhoneInvalid() {
		       
        // Invalid character entry
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("phone", "717-234567");
        });

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("phone", "717A234567");
        });
        
        // Phone length exceeds limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("phone", "71712345678");
        });

        // Phone length beneath limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("phone", "71712345678");
        });
	}
    
	@Test
	public void validateTestAddress() {
		assertTrue(ContactService.validate("address","42 Wallaby Way Sydney"));
	}
	
	@Test
	public void validateTestAddressInvalid() {
		
        // Invalid character entry
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("address", "Nemo-Nopey Nope");
        });

        // Name length exceeds limit
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.validate("address", "42 Wallaby Way Too Many Letters In this Address Sydney");
        });
	}

    @Test
    public void addContactTest(){
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        assertTrue(ContactService.contacts.get(0).getContactID().compareTo("Nem0N0b0dy") == 0);
        assertTrue(ContactService.contacts.get(0).getFirstName().compareTo("Nemo") == 0);
        assertTrue(ContactService.contacts.get(0).getLastName().compareTo("Nobody") == 0);
        assertTrue(ContactService.contacts.get(0).getPhone().compareTo("7171234567") == 0);
        assertTrue(ContactService.contacts.get(0).getAddress().compareTo("42 Wallaby Way Sydney") == 0);
    }
    
    @Test
    public void addContactTestInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            ContactService.addContact("Nem0Nob0dy", "Nemo0", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        });
    }
    
    @Test
    public void findContactTest(){
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        Contact testContact = ContactService.findContact("Nem0N0b0dy");
        assertTrue(testContact.getContactID().compareTo("Nem0N0b0dy") == 0);
    }
    
    @Test
    public void findContactTestInvalid(){

    }
    
    @Test
    public void findContactIndexTest(){

    }
    
    @Test
    public void findContactIndexTestInvalid(){

    }
 
	@Test
	public void updateContactTestFirstName() {
		ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "first name", "Dory");
        assertTrue(ContactService.contacts.get(0).getFirstName().compareTo("Dory") == 0);
	}
	
	@Test
	public void updateContactTestFirstNameInvalid() {
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "first name", "Sorry");
        assertTrue(ContactService.contacts.get(0).getFirstName().compareTo("Dory") != 0);
	}

	@Test
	public void updateContactTestLastName() {
		ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "last name", "Ydobon");
        assertTrue(ContactService.contacts.get(0).getLastName().compareTo("Ydobon") == 0);
	}
	
	@Test
	public void updateContactTestLastNameInvalid() {
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "last name", "Ydobo");
        assertTrue(ContactService.contacts.get(0).getLastName().compareTo("Ydobon") != 0);
	}
    
	@Test
	public void updateContactTestPhone() {
		ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "phone", "7177654321");
        assertTrue(ContactService.contacts.get(0).getPhone().compareTo("7177654321") == 0);
	}
	
	@Test
	public void updateContactTestPhoneInvalid() {
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "phone", "7177754321");
        assertTrue(ContactService.contacts.get(0).getPhone().compareTo("7177654321") != 0);
	}
    
	@Test
	public void updateContactTestAddress() {
		ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "address", "24 Sydney Wallaway Bee");
        assertTrue(ContactService.contacts.get(0).getAddress().compareTo("24 Sydney Wallaway Bee") == 0);
	}
	
	@Test
	public void updateContactTestAddressInvalid() {
        ContactService.addContact("Nem0N0b0dy", "Nemo", "Nobody", "7171234567", "42 Wallaby Way Sydney");
        ContactService.updateContact("Nem0N0b0dy", "address", "22 Sydney Wallaway Bee");
        assertTrue(ContactService.contacts.get(0).getAddress().compareTo("24 Sydney Wallaway Bee") != 0);
	}
        
    @Test
    public void deleteContactTest(){

    }
    
    @Test
    public void deleteContactTestInvalid(){
    
    }

    @Test
    public void createArrayListTest(){

    }
    */
}
