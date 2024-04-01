package com.test;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.Exception.PaymentValidationException;
import com.Service.PaymentService;
import com.dto.getstudentdto;
import com.model.Payment;

public class PaymentServiceTest {
	PaymentService paymentservice=new PaymentService();
	Payment pay=new Payment();
	@Test
	public void getStudentTest() {
		getstudentdto case1=new getstudentdto(1,"Ram","0",1,10000);
		getstudentdto student;
		try {
			int id=1;
			student=paymentservice.getStudent(id);
			Assert.assertEquals(case1, student);
			
			int id1=30;
			getstudentdto case2=new getstudentdto(1,"Ram","0",1,10000);
			Assert.assertEquals(case2, student);
		}
		catch(SQLException e) {
			Assert.fail();
		} catch (PaymentValidationException e) {
			Assert.assertEquals("No Student Found",e.getMessage());
		}
		
	}
	
	@Test
	public void getPaymentAmountTest() {
		getstudentdto student;
		try {
			int id=1;
			getstudentdto case1=new getstudentdto("Ram",10000);
			student=paymentservice.getPaymentAmount(id);
			Assert.assertEquals(case1, student);
			
			int id1=30;
			getstudentdto case2=new getstudentdto("Ram",10000);
			Assert.assertEquals(case2, student);
		}
		catch(SQLException e) {
			Assert.fail();
		} catch (PaymentValidationException e) {
			Assert.assertEquals("No Payment Found",e.getMessage());
		}
	}
	
	@Test
	public void getPaymentDateTest() {
		Payment p1=new Payment(1,1,LocalDate.of(2023,8,03),1);
		try {
			int id=1;
			
			Payment p=paymentservice.getPaymentDate(id);
			Assert.assertEquals(p1,p);
		}
		catch(SQLException e) {
			Assert.fail();
		}
	}

}
