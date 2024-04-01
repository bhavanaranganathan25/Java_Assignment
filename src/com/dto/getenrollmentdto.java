package com.dto;

import java.util.Objects;

public class getenrollmentdto {
	private int courseid;
	private String courseName;
    private String firstName ;
    private String lastName ;
    private int enrollmentId;
    private int paymentAmount;
    private int studentId;
	

	public getenrollmentdto(String firstName,  int studentId) {
		super();
		this.firstName = firstName;
		this.studentId = studentId;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public getenrollmentdto(String courseName, int enrollmentId, int paymentAmount) {
		super();
		this.courseName = courseName;
		this.enrollmentId = enrollmentId;
		this.paymentAmount = paymentAmount;
	}

	public getenrollmentdto(int courseid, String courseName, String firstName, String lastName) {
		super();
		this.courseid = courseid;
		this.courseName = courseName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public getenrollmentdto(String courseName, String firstName, String lastName) {
		super();
		this.courseName = courseName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	@Override
	public String toString() {
		return "getenrollmentdto [courseid=" + courseid + ", courseName=" + courseName + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseName, courseid, enrollmentId, firstName, lastName, paymentAmount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		getenrollmentdto other = (getenrollmentdto) obj;
		return Objects.equals(courseName, other.courseName) && courseid == other.courseid
				&& enrollmentId == other.enrollmentId && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && paymentAmount == other.paymentAmount;
	}
    
	
  
}
