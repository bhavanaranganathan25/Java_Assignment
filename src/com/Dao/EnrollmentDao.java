// EnrollmentDao.java
package com.Dao;

import java.sql.SQLException;

import com.Exception.StudentNotFoundException;
import com.dto.getenrollmentdto;

public interface EnrollmentDao {
    getenrollmentdto getStudent(int enrollmentId) throws SQLException, StudentNotFoundException;

	void getCourse(int enrollmentId) throws SQLException;
}
