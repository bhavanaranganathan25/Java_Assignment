// EnrollmentService.java
package com.Service;

import java.sql.SQLException;

import com.Dao.EnrollmentDao;
import com.Dao.EnrollmentDaoImpl;
import com.Exception.StudentNotFoundException;
import com.dto.getenrollmentdto;

public class EnrollmentService {
    private EnrollmentDao enrollmentDao;

    public EnrollmentService() {
        this.enrollmentDao = new EnrollmentDaoImpl();
    }

    public getenrollmentdto getStudent(int enrollmentId) throws SQLException, StudentNotFoundException {
        return enrollmentDao.getStudent(enrollmentId);
    }

	public void getCourse(int enrollmentId) throws SQLException {
	  enrollmentDao.getCourse(enrollmentId);
		
	}
}

