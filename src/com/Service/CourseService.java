package com.Service;

import java.sql.SQLException;
import java.util.List;
import com.Dao.*;
import com.model.Course;
import com.model.Enrollment;
import com.model.Teacher;
import com.Exception.*;
import com.dto.getenrollmentdto;
public class CourseService {
	CourseDao courseDao=new CourseDaoImpl();
	public List<Course> fetchAll() throws SQLException {
		List<Course> list=courseDao.fetchAll();
		return list;
	}
	public Course getCourseById(List<Course> list, int id) throws InvalidCourseDataException {
		for (Course c : list) {
			if (c.getId() == id)
				return c;
		
	}
		throw new InvalidCourseDataException("Course Id not Found");
        
}
	public void updateCourse(int id, String field, String newVal) throws InvalidCourseDataException, SQLException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidCourseDataException("Sorry!! Course Id could not be updated :<");
		else
			courseDao.updateCourse(id,fieldd,newVal);
		
	}
	public List<Course> DisplayCourseInfo() throws SQLException {
		
		return courseDao.DisplayCourseInfo();
	}
	public List<getenrollmentdto> getEnrollments(int courseId) throws SQLException {
		
		return courseDao.getEnrollments(courseId);
	}
	public List<getenrollmentdto> getTeacher(int courseId) throws SQLException {
		
		return courseDao.getTeacher(courseId);
	}
	public List<Teacher> DisplayTeacherInfo() throws SQLException {
		
		return courseDao.DisplayTeacherInfo();
	}
	public Course assignTeacher(String courseName,int credits,int teacherId) throws SQLException, CourseNotFoundException {
		
		return courseDao.assignTeacher(courseName,credits,teacherId);
		}
	public List<getenrollmentdto> CalculateCoursetatistic(String courseName) throws SQLException {
		
		return courseDao.CalculateCourseStatistic(courseName);
	}
}
