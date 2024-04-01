package com.Dao;

import java.sql.SQLException;

import com.Exception.PaymentValidationException;
import com.dto.getstudentdto;
import com.model.Payment;

public interface PaymentDao {

	getstudentdto getStudent(int paymentId) throws SQLException, PaymentValidationException;

	getstudentdto getPaymentAmount(int paymentId) throws SQLException, PaymentValidationException;

	Payment getPaymentDate(int paymentId) throws SQLException;

}
