package com.Service;

import java.sql.SQLException;
import java.util.List;

import com.model.Teacher;
import com.Dao.*;
import com.Exception.InvalidTeacherDataException;
import com.dto.getteacherdto;
public class TeacherService {
	TeacherDao teacherDao=new TeacherDaoImpl();
	public List<Teacher> fetchAllteacher() throws SQLException {
		List<Teacher> list=teacherDao.fetchAllteacher();
		return list;
	}
	public Teacher getteacherbyId(List<Teacher> list, int id) {
		for(Teacher t:list) {
			if(t.getId()==id)
				return t;
		}
		return null;
		
	}
	public void updateTeacher(int id, String field, String newVal) throws SQLException, InvalidTeacherDataException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidTeacherDataException("Sorry!! Teacher Id could not be updated :<");
		else
			teacherDao.updateTeacher(id,fieldd,newVal);
		
	}
	public List<Teacher> DisplayTeacherInfo() throws SQLException {
		return teacherDao.DisplayTeacherInfo();
		
	}
	public getteacherdto GetAssignedCourses(int teacherId) throws SQLException {
		return teacherDao.GetAssignedCourses(teacherId);
		
		
	}
		
	}
