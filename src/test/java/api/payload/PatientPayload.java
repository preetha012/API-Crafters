package api.payload;

import com.fasterxml.jackson.annotation.JsonSetter;

public class PatientPayload {
	
	String FirstName;
	String LastName;
	String ContactNumber;
	String Email;
	String Allergy;
	String FoodCategory;
	String DateOfBirth;
	
	public String getFirstName() {
		return FirstName;
	}
	
	@JsonSetter("FirstName")
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	@JsonSetter("LastName")
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getContactNumber() {
		return ContactNumber;
	}
	@JsonSetter("ContactNumber")
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
	public String getEmail() {
		return Email;
	}
	@JsonSetter("Email")
	public void setEmail(String email) {
		Email = email;
	}
	public String getAllergy() {
		return Allergy;
	}
	@JsonSetter("Allergy")
	public void setAllergy(String allergy) {
		Allergy = allergy;
	}
	public String getFoodCategory() {
		return FoodCategory;
	}
	@JsonSetter("FoodCategory")
	public void setFoodCategory(String foodCategory) {
		FoodCategory = foodCategory;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	@JsonSetter("DateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}	


}
