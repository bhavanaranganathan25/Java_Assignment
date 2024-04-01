package com.Dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Exception.PaymentValidationException;
import com.dto.getstudentdto;
import com.model.Payment;
import com.util.DBUtil;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public getstudentdto getStudent(int paymentId) throws SQLException, PaymentValidationException {
		Connection conn = DBUtil.getDBConn();
		String sql="select s.id as student_id,s.first_name,s.last_name,p.id,p.amount from student s left join payment p on s.id=p.student_id where p.id=?";
		
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, paymentId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	int studentId=rs.getInt("student_id");
        	String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int paymentid=rs.getInt("id");
            double amount = rs.getDouble("amount");
            getstudentdto e=new getstudentdto(studentId,firstName,lastName,paymentid,amount);
        
		return e;
		
        }
        DBUtil.dbClose();
        throw new PaymentValidationException("No Student Found");
        
		
	}

	@Override
	public getstudentdto getPaymentAmount(int paymentId) throws SQLException, PaymentValidationException {
		Connection conn = DBUtil.getDBConn();
		String sql="select s.first_name,p.amount from student s left join payment p on s.id=p.student_id where p.id=?";
		
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, paymentId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	String firstName = rs.getString("first_name");
            double amount = rs.getDouble("amount");
            getstudentdto e=new getstudentdto(firstName,amount);
            return e;
        }
     
        DBUtil.dbClose();
        throw new PaymentValidationException("No Payment Found");
        
	}

	@Override
	public Payment getPaymentDate(int paymentId) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql="select id,payment_date from payment where id=?";
		
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, paymentId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	int paymentid=rs.getInt(1);
            Date sqlDate = rs.getDate("payment_date");
            LocalDate paymentDate = sqlDate.toLocalDate();
            Payment e=new Payment(paymentid,paymentid, paymentDate, paymentid);
            return e;
        }
     
        DBUtil.dbClose();
		return null;
	}
    
}

