package com.Service;

import java.sql.SQLException;
import java.util.List;

import com.Dao.StudentDao;
import com.Dao.StudentDaoImpl;
import com.Dao.TeacherDao;
import com.Dao.TeacherDaoImpl;
import com.Exception.InvalidCredentialsException;
import com.dto.LoginDto;
import com.model.Student;
import com.model.Teacher;

public class LoginService {
	StudentDao studentdao=new StudentDaoImpl();
	TeacherDao teacherdao=new TeacherDaoImpl();
	public List<Student> fetchAll() throws SQLException {
		List<Student> list=studentdao.fetchAll();
		return list;
	}
	public boolean checkStudentUsername(List<Student> list, String firstName) {
		for(Student s:list) {
			if(s.getFirstName().equals(firstName))
				return true;
		}
		return false;
	}
	public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNo) throws SQLException {
		studentdao.createStudent(firstName, lastName, dateOfBirth, email, phoneNo);
		
	}
	public List<Teacher> fetchAllteacher() throws SQLException {
		List<Teacher> list=teacherdao.fetchAllteacher();
		return list;
	}
	public boolean checkTeacherUsername(List<Teacher> list, String firstName, String email) {
		for(Teacher t:list) {
			if(t.getFirstName().equals(firstName) && (t.getEmail().equals(email)))
				return true;
		}
		return false;
	}
	public void createTeacher(String firstName, String lastName, String email) throws SQLException {
		teacherdao.createTeacher(firstName, lastName, email);
	}
	public LoginDto loginCheck(List<Teacher> tlist, List<Student> slist, String firstName, String email) throws InvalidCredentialsException {
		for(Student s:slist) {
			if(s.getFirstName().equals(firstName)) {
				return new LoginDto(s.getId(),s.getFirstName(),s.getLastName(),s.getEmail(),"student");
			}
		}
		for(Teacher t:tlist)
		{
			if(t.getFirstName().equals(firstName))
			{
				return new LoginDto(t.getId(),t.getFirstName(),t.getLastName(),t.getEmail(),"teacher");
			}
		}
		throw new InvalidCredentialsException("Invalid Credentials!");
	}
	}
