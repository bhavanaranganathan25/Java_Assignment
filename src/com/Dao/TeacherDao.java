package com.Dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.getteacherdto;
import com.model.Teacher;

public interface TeacherDao {

	List<Teacher> fetchAllteacher() throws SQLException;

	void updateTeacher(int id, String fieldd, String newVal) throws SQLException;

	List<Teacher> DisplayTeacherInfo() throws SQLException;

	public getteacherdto GetAssignedCourses(int teacherId) throws SQLException;

	void createTeacher(String firstName, String lastName, String email) throws SQLException;

}
