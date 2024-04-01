package com.Service;
import java.sql.SQLException;

import com.Dao.*;
import com.Exception.PaymentValidationException;
import com.dto.getstudentdto;
import com.model.Payment;
public class PaymentService {
	PaymentDao paymentDao=new PaymentDaoImpl();
	public getstudentdto getStudent(int paymentId) throws SQLException, PaymentValidationException {
		return paymentDao.getStudent(paymentId);
		
		
	}
	public getstudentdto getPaymentAmount(int paymentId) throws SQLException, PaymentValidationException {
		
		return paymentDao.getPaymentAmount(paymentId);
	}
	public Payment getPaymentDate(int paymentId) throws SQLException {
		return paymentDao.getPaymentDate(paymentId);
	}

}
