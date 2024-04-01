package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
	private int id;
	private int amount;
	private LocalDate paymentDate;
	private int studentId;
	
	
	public Payment() {
		}


	public Payment(int id, int amount, LocalDate paymentDate, int studentId) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.studentId = studentId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ", studentId=" + studentId
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, id, paymentDate, studentId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return amount == other.amount && id == other.id && Objects.equals(paymentDate, other.paymentDate)
				&& studentId == other.studentId;
	}
	
	
}
