package com.Dao;

import com.Exception.DuplicateEnrollmentException;
import com.Exception.InvalidStudentDataException;
import com.Exception.PaymentValidationException;
import com.dto.getpaymentdto;
import com.dto.getstudentdto;
import com.model.Course;
import com.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> fetchAll() throws SQLException;

    void EnrollInCourse(int studentId, int courseId) throws SQLException, DuplicateEnrollmentException;

	void MakePayment(int studentId, int amount) throws SQLException, PaymentValidationException;


	void updateStudent(int id, String fieldd, String newVal) throws SQLException;

	List<Student> DisplayStudentInfo() throws SQLException;

	getstudentdto GetEnrolledCourse(int studentId) throws SQLException;

	List<getpaymentdto> GetPaymentHistory(int studentId) throws SQLException;

	void createStudent(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber) throws SQLException;

}
