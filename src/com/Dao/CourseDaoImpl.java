package com.Dao;

import java.util.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.model.Course;
import com.model.Enrollment;
import com.model.Student;
import com.model.Teacher;
import com.mysql.jdbc.Statement;
import com.dto.*;
import java.sql.PreparedStatement;
import com.util.DBUtil;
import com.Exception.*;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> fetchAll() throws SQLException {
		
		List<Course> list = new ArrayList<>();
		
		Connection conn = DBUtil.getDBConn();

		String sql = "select * from course";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id = rst.getInt("id");
			String courseName = rst.getString("name");
			int credits=rst.getInt("credits");
			int TeacherId=rst.getInt("teacher_id");
			Course c = new Course(id,courseName,credits,TeacherId);
			list.add(c);
		}

		DBUtil.dbClose();
		return (list);
	}

	@Override
	public void updateCourse(int id, String fieldd, String newVal) throws SQLException {
		Connection conn = DBUtil.getDBConn();

		String sql = "update course set "+fieldd+"=? where id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		if(fieldd.equals("teacher_id"))
			pstmt.setInt(1, Integer.parseInt(newVal));
		else
			pstmt.setString(1,newVal);
		
		pstmt.setInt(2, id);


		pstmt.executeUpdate();

		DBUtil.dbClose();
		
	}

	@Override
	public List<Course> DisplayCourseInfo() throws SQLException {
		List<Course> course = new ArrayList<>();
	    String sql = "SELECT * FROM course";
	         Connection conn = DBUtil.getDBConn();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String courseName = rs.getString("name");
	            int credits=rs.getInt("credits");
	            int TeacherId=rs.getInt("teacher_id");
	            
	            Course courses = new Course(id, courseName,credits,TeacherId);
	            course.add(courses);
	        }
	        
	       DBUtil.dbClose();
	    return course;
	}

	@Override
	public List<getenrollmentdto> getEnrollments(int courseId) throws SQLException {
		List<getenrollmentdto> list=new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql="select c.id,c.name,s.first_name,s.last_name from enrollment e join student s on e.student_id=s.id join course c on c.id=e.course_id where c.id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, courseId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
        	int courseid=rs.getInt("id");
        	String courseName=rs.getString("name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            getenrollmentdto dto=new getenrollmentdto(courseid,courseName,firstName,lastName);
            list.add(dto);
        
        }
        DBUtil.dbClose();
		return list;
	}

	@Override
	public List<getenrollmentdto> getTeacher(int courseId) throws SQLException {
		
		List<getenrollmentdto> list=new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql=" select c.name,t.first_name,t.last_name from course c join teacher t on t.id=c.teacher_id where c.id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, courseId);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
        	String courseName=rs.getString("name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            getenrollmentdto dto=new getenrollmentdto(courseName,firstName,lastName);
            list.add(dto);
        
        }
        DBUtil.dbClose();
		return list;
    
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
	public Course assignTeacher(String courseName, int credits, int teacherId) throws CourseNotFoundException, SQLException {
	    Connection conn = DBUtil.getDBConn();
	    String sql = "INSERT INTO course (name, credits, teacher_id) VALUES (?, ?, ?)";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, courseName);
	    ps.setInt(2, credits);
	    ps.setInt(3, teacherId);
	    
	   
	    if ((ps.executeUpdate()) > 0) {
	         return null;
	    } else {
	        throw new CourseNotFoundException("Insertion failed, no rows affected.");
	    }
	}

	@Override
	public List<getenrollmentdto> CalculateCourseStatistic(String courseName) throws SQLException {
		List<getenrollmentdto> list=new ArrayList<>();
		Connection conn = DBUtil.getDBConn();
		String sql="select c.id,c.name,count(e.id)as course_enrolled,sum(p.amount)as Total_payment from course c join enrollment e on c.id=e.course_id join payment p on e.student_id=p.student_id where c.name=? group by c.name;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, courseName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
        	String coursename=rs.getString("name");
            int enrolledId=rs.getInt("course_enrolled");
            int paymentAmount=rs.getInt("total_payment");
            getenrollmentdto dto=new getenrollmentdto(courseName,enrolledId,paymentAmount);
            list.add(dto);
        
        }
        DBUtil.dbClose();
		return list;
	}

}
