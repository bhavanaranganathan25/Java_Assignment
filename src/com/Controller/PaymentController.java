package com.Controller;
import com.Exception.PaymentValidationException;
import com.Service.*;
import com.dto.*;
import com.model.Payment;

import java.sql.SQLException;
import java.util.*;
public class PaymentController {
	public static void main(String[] args) {
		PaymentService paymentService=new PaymentService();
		Scanner sc=new Scanner(System.in);
		while (true) {
            System.out.println("-------- PAYMENT OPERATIONS ----------");
            System.out.println("Press 1. To Retrieve the Student associated With Payment");
            System.out.println("Press 2. Retreive the Payment Amount");
            System.out.println("Press 3. Retreive the Payment Date");
            System.out.println("Press 0. To Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting... Thank You!!");
                break;
            }
            switch (input) {
                case 1:
                	System.out.println("Retrieve the Student With Payment");
                	try {
                		System.out.println("Enter payment ID:");
                        int paymentId = sc.nextInt();
                        getstudentdto dto = paymentService.getStudent(paymentId);
                        if (dto != null) {
                            System.out.println("Student ID: " + dto.getStudentId());
                            System.out.println("First Name: " + dto.getFirstName());
                            System.out.println("Payment ID: " + dto.getPaymentid());
                            System.out.println("Amount: " + dto.getAmount());
                        } 
                        
                    } catch (SQLException | PaymentValidationException e) {
                        System.out.println("Error: " + e.getMessage());
                  break;
				}
               break;
                case 2:
                	System.out.println("Retreive the Paymnent amount");
                	try {
                     System.out.println("Enter the payment id");   		
                	int paymentId=sc.nextInt();
                	getstudentdto dto = paymentService.getPaymentAmount(paymentId);
                    if (dto != null) {
                        System.out.println("First Name: " + dto.getFirstName());
                        System.out.println("Amount: " + dto.getAmount());
                    } else {
                        System.out.println("No student pay for payment ID: " + paymentId);
                    }
                    
                } catch (SQLException | PaymentValidationException e) {
                    System.out.println("Error: " + e.getMessage());
              break;
			}
                break;	
                case 3:
                	System.out.println("Retreive the Paymnent date");
                	try {
                     System.out.println("Enter the payment id");   		
                	int paymentId=sc.nextInt();
                	Payment dto = paymentService.getPaymentDate(paymentId);
                    if (dto != null) {
                        
                        System.out.println("Id: " + dto.getId());
                        System.out.println("Payment Date: "+ dto.getPaymentDate());
                    } 
                	}catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
              break;
			}
            
            }
	}

}
}
