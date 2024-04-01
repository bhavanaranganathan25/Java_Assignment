package com.test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.Exception.InvalidStudentDataException;
import com.Exception.PaymentValidationException;
import com.Exception.StudentNotFoundException;
import com.Service.StudentService;
import com.dto.getpaymentdto;
import com.dto.getstudentdto;
import com.model.Student;
public class StudentServiceTest {
	StudentService studentservice=new StudentService();
	Student student=new Student();
	
	@Test
	public void fetchAllTest(){
		try {
			
			List<Student> list=studentservice.fetchAll();
			Assert.assertTrue(list.size()>0);
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void updateStudentTest(){
		try {
			List<Student> list= studentservice.fetchAll();
			Student student=list.get(0);
			int id=student.getId();
			studentservice.updateStudent(id,"last_name","0");
			
			Student updated=studentservice.getStudentById(list, id);
			Assert.assertEquals("0", updated.getLastName());

			}
		
		catch(SQLException e) {
			Assert.fail();
		} catch (InvalidStudentDataException e) {
			Assert.assertEquals("Sorry!! Student Id could not be updated :<", e.getMessage());
		}
	}
	
	
	@Test
	public void makePaymentTest() {
		try {
			int count=studentservice.fetchAll().size();
			studentservice.MakePayment(10,1550);
			int updated=studentservice.fetchAll().size();
			Assert.assertEquals(count, updated);
		}catch(SQLException | PaymentValidationException e) {
			Assert.fail();
		} 
		
	}
	
	@Test
	public void displayStudentInfoTest() {
		try {
			List<Student> list=studentservice.DisplayStudentInfo();
			Assert.assertTrue(list.size()>0);
		}
		catch(SQLException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void enrolledCourseTest() {
	    try {
	        List<Student> list = studentservice.fetchAll();

	        int id=12;
	        getstudentdto expected = new getstudentdto(12, "ram", "0", "java");
	        getstudentdto case1 = new getstudentdto(12, "ram", "0", "java");
	        getstudentdto actual=studentservice.GetEnrolledCourse(id);
	        Assert.assertEquals(expected,actual);

	        int id1 = 10; 
	        getstudentdto case2 = new getstudentdto(1,"no","prasad","python");
	        Assert.assertEquals(case2,actual);
	        
	    } catch (SQLException e ){
	    	System.out.println(e.getMessage());
	       
	    } catch (StudentNotFoundException e) {
			Assert.assertEquals("Student does not Enrolled Any Course", e.getMessage());
		}
	}


}
