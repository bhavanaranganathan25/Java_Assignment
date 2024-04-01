package com.dto;

import java.time.LocalDate;
import java.util.Objects;

public class getstudentdto {
	private int studentId;
	private String firstName; 
    private String lastName;
    private int paymentid;
    private double amount ;
    private LocalDate paymentDate;
    private String courseName;
	public getstudentdto() {
		super();
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public getstudentdto(int studentId, String firstName, String lastName, int paymentId, double amount) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.paymentid = paymentid;
		this.amount = amount;
	}
	
	
	public getstudentdto(String firstName, double amount) {
		super();
		this.firstName = firstName;
		this.amount = amount;
	}
	
	
	public getstudentdto(String firstName, String lastName, double amount, LocalDate paymentDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	public getstudentdto(int studentId, String firstName, String lastName, String courseName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courseName = courseName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "getstudentdto [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", paymentid=" + paymentid + ", amount=" + amount + ", paymentDate=" + paymentDate + ", courseName="
				+ courseName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, courseName, firstName, lastName, paymentDate, paymentid, studentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		getstudentdto other = (getstudentdto) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(courseName, other.courseName) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(paymentDate, other.paymentDate)
				&& paymentid == other.paymentid && studentId == other.studentId;
	}
	
    
}

