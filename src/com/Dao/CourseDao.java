package com.Dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.CourseNotFoundException;
import com.dto.getenrollmentdto;
import com.model.Course;
import com.model.Enrollment;
import com.model.Teacher;

public interface CourseDao {

	List<Course> fetchAll() throws SQLException;

	void updateCourse(int id, String fieldd, String newVal) throws SQLException;

	List<Course> DisplayCourseInfo() throws SQLException;

	List<getenrollmentdto> getEnrollments(int courseId) throws SQLException;

	List<getenrollmentdto> getTeacher(int courseId) throws SQLException;

	List<Teacher> DisplayTeacherInfo() throws SQLException;

	Course assignTeacher(String courseName,int credits,int teacherId) throws SQLException, CourseNotFoundException;

	List<getenrollmentdto> CalculateCourseStatistic(String courseName) throws SQLException;

}
