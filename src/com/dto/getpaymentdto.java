package com.dto;

import java.time.LocalDate;
import java.util.Objects;

public class getpaymentdto {
	private int paymentId;
    private int amount;
    private LocalDate paymentDate;
    private int studentId;
    private String firstName;
	public getpaymentdto(int paymentId, int amount, LocalDate paymentDate, int studentId, String firstName) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.studentId = studentId;
		this.firstName = firstName;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
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
	@Override
	public String toString() {
		return "getpaymentdto [paymentId=" + paymentId + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ ", studentId=" + studentId + ", firstName=" + firstName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, firstName, paymentDate, paymentId, studentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		getpaymentdto other = (getpaymentdto) obj;
		return amount == other.amount && Objects.equals(firstName, other.firstName)
				&& Objects.equals(paymentDate, other.paymentDate) && paymentId == other.paymentId
				&& studentId == other.studentId;
	}
	
    
    
}
