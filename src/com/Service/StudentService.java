package com.Service;

import com.Dao.StudentDao;
import com.Dao.StudentDaoImpl;
import com.Exception.*;
import com.dto.getpaymentdto;
import com.dto.getstudentdto;
import com.model.Course;
import com.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    public List<Student> fetchAll() throws SQLException {
        return studentDao.fetchAll();
    }

    public void EnrollInCourse(int studentId, int courseId) throws SQLException,DuplicateEnrollmentException {
        studentDao.EnrollInCourse(studentId, courseId);
    }


	public void MakePayment(int studentId, int amount) throws SQLException, PaymentValidationException {
		studentDao.MakePayment(studentId,  amount);
	      	
	
	}


	public Student getStudentById(List<Student> list, int id) throws InvalidStudentDataException {
		for(Student s:list) {
			if(s.getId()==id)
				return s;
	}
		throw new InvalidStudentDataException("Updated Email or Date Of Birth is not Correct");
	}

	public void updateStudent(int id, String field, String newVal) throws SQLException, InvalidStudentDataException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidStudentDataException("Sorry!! Student Id could not be updated :<");
		else
			studentDao.updateStudent(id,fieldd,newVal);
		
	
		
	}

	public List<Student> DisplayStudentInfo() throws SQLException {
		
		return studentDao.DisplayStudentInfo();
	}

	public getstudentdto GetEnrolledCourse(int studentId) throws SQLException, StudentNotFoundException {
		getstudentdto enrollcourse=studentDao.GetEnrolledCourse(studentId);
	if(enrollcourse==null) {	
	throw new StudentNotFoundException("Student does not Enrolled Any Course");
	}
	return enrollcourse;
	}

	public List<getpaymentdto> GetPaymentHistory(int studentId) throws SQLException {
		return studentDao.GetPaymentHistory(studentId);
	}
}
