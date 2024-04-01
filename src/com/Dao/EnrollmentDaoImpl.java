package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Exception.StudentNotFoundException;
import com.dto.getenrollmentdto;
import com.util.DBUtil;

public class EnrollmentDaoImpl implements EnrollmentDao {

    @Override
    public getenrollmentdto getStudent(int enrollmentId) throws SQLException, StudentNotFoundException {
        String sql = "SELECT s.id AS student_id, CONCAT(s.first_name, ' ', s.last_name) AS student_name " +
                     "FROM enrollment e " +
                     "JOIN student s ON e.student_id = s.id " +
                     "WHERE e.id = ?";
        
              Connection conn = DBUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();
                /*if (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    String studentName = rs.getString("student_name");
                
                    System.out.println("Student ID: " + studentId + ", Student Name: " + studentName);
                } else {
                    System.out.println("No student found for enrollment ID: " + enrollmentId);
                }*/
            
            while(rs.next()) {
            	int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                
                getenrollmentdto dto=new getenrollmentdto(studentName,studentId);
                return dto;
            }
                DBUtil.dbClose();
            throw new StudentNotFoundException("Student is Not Enrolled in Any Course");           
			
    }

	@Override
	public void getCourse(int enrollmentId) throws SQLException {
		 Connection conn = DBUtil.getDBConn();
		 String sql="select c.id as course_id,c.name as course_name from course c join enrollment e on c.id=e.course_id where e.id=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
         ps.setInt(1, enrollmentId);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
             int courseId = rs.getInt("course_id");
             String courseName = rs.getString("course_name");
             System.out.println("Course ID: " + courseId + ", Course Name: " + courseName);
         } else {
             System.out.println("No student found for enrollment ID: " + enrollmentId);
         }
         DBUtil.dbClose();
	}
        
    }

