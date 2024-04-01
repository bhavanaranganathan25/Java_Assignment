package com.test;
import com.Exception.InvalidStudentDataException;
import com.Exception.InvalidTeacherDataException;
import com.Service.*;
import com.dto.getteacherdto;
import com.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;
public class TeacherServiceTest {
	TeacherService teacherservice=new TeacherService();
	Teacher teacher=new Teacher();
	@Test
	public void updateTeacherTest() {
		try {
			List<Teacher> list=teacherservice.fetchAllteacher();
			Teacher teacher=list.get(0);
			int id=teacher.getId();
			teacherservice.updateTeacher(id,"email","0");
			Teacher updated=teacherservice.getteacherbyId(list, id);
			Assert.assertEquals("0", updated.getEmail());

		}
		catch(SQLException e) {
			Assert.fail();
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Sorry!! Teacher Id could not be updated :<", e.getMessage());
		}
	}
	
	@Test
	public void displayTeacherTest() {
		try {
			List<Teacher> list=teacherservice.fetchAllteacher();
            Assert.assertTrue(list.size()>0);
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
