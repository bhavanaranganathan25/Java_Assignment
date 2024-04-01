package com.test;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.Assert;
import com.Exception.StudentNotFoundException;
import com.Service.EnrollmentService;
import com.dto.getenrollmentdto;
import com.model.Enrollment;



public class EnrollmentServiceTest {
	EnrollmentService enrollservice=new EnrollmentService();
	Enrollment enroll=new Enrollment();
	
	@Test
	public void getStudentTest() {
		
		getenrollmentdto case1=new getenrollmentdto("Ram 0",1);
		getenrollmentdto student;
		try {
			int id=11;
			student = enrollservice.getStudent(id);
			Assert.assertEquals(case1, student);
			
			int id1=21;
			getenrollmentdto case2=new getenrollmentdto("Ram 0",1);
	        student = enrollservice.getStudent(id);
			Assert.assertEquals(case2, student);
			
			
		} catch (SQLException e) {
			Assert.fail();
		} catch (StudentNotFoundException e) {
			
			Assert.assertEquals("Student is Not Enrolled in Any Course",e.getMessage().toLowerCase());
		}
		
	}
	
	

}
