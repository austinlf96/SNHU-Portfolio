package objects;

public class Contact {
	
	// Define required attributes
	private String contactID;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	// Full constructor - ContactService should enforce using this constructor
	public Contact(String id, String fName, String lName, String phoneNum, String streetAddress) {
		
		// Enforce character limit on Contact attributes. Note: will truncate passed in parameters.
		this.contactID = id.length() <= 10 ? id : id.substring(0,10);
		this.firstName = fName.length() <= 10 ? fName : fName.substring(0,10);
		this.lastName = lName.length() <= 10 ? lName : lName.substring(0,10);
		this.phone = phoneNum.length() <= 10 ? phoneNum : phoneNum.substring(0,10);
		this.address = streetAddress.length() <= 30 ? streetAddress : streetAddress.substring(0,30);
	}
	
	public static boolean validate(String attribute, String testingString) {

		// Handle individual field validations
		try {
			switch(attribute) {
			
				// ID must be unique and be composed only of alphanumeric characters
				case "id":
					if (testingString.length() > 10 || testingString.length() < 1 || testingString == null) throw new IllegalArgumentException(testingString + " is an invalid entry.");
					for (int i = 0; i < testingString.length(); i++) {
						if (Character.isLetter(testingString.charAt(i)) == false && Character.isDigit(testingString.charAt(i)) == false) throw new IllegalArgumentException("Invalid entry. Please try again.");
					}
					return true;
				
				// First name must be letters
				case "first":
					if (testingString.length() > 10 || testingString.length() < 1 || testingString == null) throw new IllegalArgumentException(testingString + " is an invalid entry.");
					for (int i = 0; i < testingString.length(); i++) {
						if (Character.isLetter(testingString.charAt(i)) == false) throw new IllegalArgumentException("Invalid entry. Please try again.");
					}
					return true;
					
				// Last name must be letters
				case "last":
					if (testingString.length() > 10 || testingString.length() < 1 || testingString == null) throw new IllegalArgumentException(testingString + " is an invalid entry.");
					for (int i = 0; i < testingString.length(); i++) {
						if (Character.isLetter(testingString.charAt(i)) == false) throw new IllegalArgumentException("Invalid entry. Please try again.");
					}
					return true;
				
				// Phone number must be numbers
				case "phone":				
					if (testingString.length() != 10 || testingString == null) throw new IllegalArgumentException(testingString + " is an invalid entry.");
					for (int i = 0; i < testingString.length(); i++) {
						if (Character.isDigit(testingString.charAt(i)) == false) throw new IllegalArgumentException("Invalid entry. Please try again.");
					}
					return true;
				
				// Address must be alphanumeric characters
				case "address":
					if (testingString.length() > 30 || testingString.length() < 1 || testingString == null) throw new IllegalArgumentException(testingString + " is an invalid entry.");
					for (int i = 0; i < testingString.length(); i++) {
						if (Character.isLetter(testingString.charAt(i)) == false && Character.isDigit(testingString.charAt(i)) == false && testingString.charAt(i) != ' ') throw new IllegalArgumentException("Invalid entry. Please try again.");
					}
					return true;
				
				default:
					System.out.println("Unrecognized value. Please try again.");
					throw new IllegalArgumentException("Invalid entry. Please try again.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	// Getter & Setter Functions
	public String getContactID() { return this.contactID; }
	public void setFirstName(String firstName) { if (validate("first name", firstName)) this.firstName = firstName; } 
	public String getFirstName() { return this.firstName; }
	public void setLastName(String lastName) { if (validate("last name", lastName)) this.lastName = lastName; } 
	public String getLastName() { return this.lastName; }
	public void setPhone(String phone) {if (validate("phone", phone)) this.phone = phone; } 
	public String getPhone() { return this.phone; }
	public void setAddress(String address) {if (validate("address", address)) this.address = address; } 
	public String getAddress() { return this.address; }
}
