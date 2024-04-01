package com.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.dto.getstudentdto;
import com.dto.getteacherdto;
import com.model.Student;
import com.model.Teacher;
import java.sql.PreparedStatement;
import com.util.DBUtil;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public List<Teacher> fetchAllteacher() throws SQLException {
		List<Teacher> list = new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql="select*from teacher";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			Teacher t = new Teacher(id, firstName, lastName, email);
			list.add(t);
		}

		DBUtil.dbClose();
		return (list);
	}

	@Override
	public void updateTeacher(int id, String fieldd, String newVal) throws SQLException {
		Connection conn = DBUtil.getDBConn();

		String sql = "update teacher set "+fieldd+"=? where id=?";

		PreparedStatement ps = conn.prepareStatement(sql);
		
		if(fieldd.equals("email"))
			ps.setString(1, String.valueOf(newVal));
		else
			ps.setString(1,newVal);
		
		ps.setInt(2, id);


		ps.executeUpdate();

		DBUtil.dbClose();
	}

	@Override
	public List<Teacher> DisplayTeacherInfo() throws SQLException {
		 List<Teacher> teachers = new ArrayList<>();
	        String sql = "SELECT * FROM teacher";
	             Connection conn = DBUtil.getDBConn();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String firstName = rs.getString("first_name");
	                String lastName = rs.getString("last_name");
	                String email = rs.getString("email");
	                
	                Teacher teacher = new Teacher(id, firstName, lastName,email);
	                teachers.add(teacher);
	            }
	            
	           DBUtil.dbClose();
	        return teachers;
	        
	        
	    }

	@Override
	public getteacherdto GetAssignedCourses(int teacherId) throws SQLException {
        Connection conn = DBUtil.getDBConn();
        String sql = "SELECT c.id, c.name, t.first_name FROM course c JOIN teacher t ON t.id=c.teacher_id WHERE t.id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, teacherId);
        ResultSet rs = ps.executeQuery();
        
        
        if (rs.next()) {
            int courseId = rs.getInt("id");
            String courseName = rs.getString("name");
            String firstName = rs.getString("first_name");
            getteacherdto dto = new getteacherdto(courseId, courseName, firstName);
		    return dto;
        }
        
       DBUtil.dbClose();
	return null;
    
    
		
	}

	@Override
	public void createTeacher(String firstName, String lastName, String email) throws SQLException {
		Connection conn = DBUtil.getDBConn();

		String sql = "insert into teacher(first_name,last_name,email) values(?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, email);
		pstmt.executeUpdate();

		DBUtil.dbClose();
		
	}
	}


