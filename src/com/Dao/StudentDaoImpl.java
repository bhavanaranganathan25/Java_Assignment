package com.Dao;

import com.Exception.DuplicateEnrollmentException;
import com.Exception.InvalidStudentDataException;
import com.Exception.PaymentValidationException;
import com.dto.getpaymentdto;
import com.dto.getstudentdto;
import com.model.*;
import com.mysql.jdbc.Statement;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> fetchAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DBUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                Student student = new Student(id, firstName, lastName, dateOfBirth, email, phoneNumber);
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public void EnrollInCourse(int studentId, int courseId) throws SQLException, DuplicateEnrollmentException {
        try (Connection conn = DBUtil.getDBConn()) {
            String selectSql = "SELECT * FROM enrollment WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement selectPs = conn.prepareStatement(selectSql)) {
                selectPs.setInt(1, studentId);
                selectPs.setInt(2, courseId);
                try (ResultSet rs = selectPs.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Student is already enrolled in the course.");
                        return;
                    }
                }
            }

            String insertSql = "INSERT INTO enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setInt(1, studentId);
                insertPs.setInt(2, courseId);
                insertPs.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                insertPs.executeUpdate();
                System.out.println("Student enrolled successfully.");
            }
        }
        DBUtil.dbClose();
        throw new DuplicateEnrollmentException("Invalid Id given");
    	
    }

@Override
public void MakePayment(int studentId, int amount) throws SQLException, PaymentValidationException {
    try (Connection conn = DBUtil.getDBConn()) {
        String sql = "INSERT INTO payment (amount, payment_date, student_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
            ps.setInt(1, amount);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setInt(3, studentId);
            ps.executeUpdate();

            ResultSet rst= ps.getGeneratedKeys() ;
                if (rst.next()) {
                    int paymentId = rst.getInt(1);
                    System.out.println("Payment made successfully");
                }
                
            }
 
        DBUtil.dbClose();
        
}
@Override
public void updateStudent(int id, String fieldd, String newVal) throws SQLException {
	Connection conn = DBUtil.getDBConn();

	String sql = "update student set "+fieldd+"=? where id=?";

	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	if(fieldd.equals("date_of_birth"))
		pstmt.setDate(1, Date.valueOf(newVal));
	else
		pstmt.setString(1,newVal);
	
	pstmt.setInt(2, id);


	pstmt.executeUpdate();

	DBUtil.dbClose();
	
}

@Override
public List<Student> DisplayStudentInfo() throws SQLException {
	List<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM student";
         Connection conn = DBUtil.getDBConn();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date dateOfBirth = rs.getDate("date_of_birth");
            String email = rs.getString("email");
            String phoneNumber=rs.getString("phone_number");
            
            Student student = new Student(id, firstName, lastName,dateOfBirth, email, phoneNumber);
            students.add(student);
        }
        
       DBUtil.dbClose();
    return students;
    
}

@Override
public getstudentdto GetEnrolledCourse(int studentId) throws SQLException {
	Connection conn = DBUtil.getDBConn();
	String sql="select s.id,s.first_name,s.last_name,c.name from course c join enrollment e on c.id=e.course_id join student s on s.id=e.student_id where s.id=?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setInt(1, studentId);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
    	int StudentId=rs.getInt("id");
    	String firstName = rs.getString("first_name");
    	String lastName = rs.getString("last_name");
    	String courseName = rs.getString("name");
    	
        getstudentdto e=new getstudentdto(StudentId,firstName,lastName,courseName);
        DBUtil.dbClose();
        return e;
       
}
	return null;
}

@Override
public List<getpaymentdto> GetPaymentHistory(int studentId) throws SQLException {
	List<getpaymentdto> list = new ArrayList<>();
	    String sql = "SELECT p.id, p.amount, p.payment_date, p.student_id, s.first_name FROM payment p JOIN student s ON p.student_id = s.id WHERE p.student_id = ?";
	    Connection conn = DBUtil.getDBConn();
	         PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, studentId);
	        ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                int paymentId = rs.getInt("id");
	                int amount = rs.getInt("amount");
	                LocalDate paymentDate = rs.getDate("payment_date").toLocalDate();
	                int StudentId = rs.getInt("student_id");
	                String firstName = rs.getString("first_name");
	                getpaymentdto p = new getpaymentdto(paymentId, amount, paymentDate, studentId, firstName);
	                list.add(p);
	            }
	            DBUtil.dbClose();
	            return list;
	        }

@Override
public void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) throws SQLException {
	Connection conn = DBUtil.getDBConn();

	String sql = "insert into student(first_name,last_name,date_of_birth,email,phone_number) values(?,?,?,?,?)";

	PreparedStatement pstmt = conn.prepareStatement(sql);

	pstmt.setString(1, firstName);
	pstmt.setString(2, lastName);
	pstmt.setDate(3, Date.valueOf(dateOfBirth));
	pstmt.setString(4, email);
	pstmt.setString(5, phoneNumber);

	pstmt.executeUpdate();

	DBUtil.dbClose();
	
}
	    
	    
}





























