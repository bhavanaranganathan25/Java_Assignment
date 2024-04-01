package com.test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.Exception.CourseNotFoundException;
import com.Exception.InvalidCourseDataException;
import com.Service.CourseService;
import com.dto.getenrollmentdto;
import com.model.Course;
public class CourseServiceTest {
	CourseService courseservice=new CourseService();
	Course course=new Course();
	
	@Test
	public void assignTeacherTest() {
		
		try {
			int count=courseservice.fetchAll().size();
			courseservice.assignTeacher("java",85,2);
			int updated=courseservice.fetchAll().size();
			Assert.assertEquals(count+1,updated);
		} catch (SQLException | CourseNotFoundException e) {
			Assert.fail("Not valid course");
		}
		}
	
	@Test
	public void updateCourseTest() {
		try {
			List<Course> list=courseservice.fetchAll();
			Course course=list.get(0);
			int id=course.getId();
			courseservice.updateCourse(id,"name" ,"0");
			
			Course updated=courseservice.getCourseById(list, id);
			Assert.assertEquals("0", updated.getName());
		}
		 catch (SQLException e) {
				Assert.fail("Not valid course");
			} catch (InvalidCourseDataException e) {
				Assert.assertEquals("Sorry!! Course Id could not be updated :<", e.getMessage());
		}
	}
	
	@Test
	public void displayCourseInfoTest() {
		try {
			List<Course> list=courseservice.DisplayCourseInfo();
			Assert.assertTrue(list.size()>0);
		}
		catch(SQLException e) {
			Assert.fail();
		}
	}
	
}
