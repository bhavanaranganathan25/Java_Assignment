package com.Controller;

import com.Exception.DuplicateEnrollmentException;
import com.Exception.InvalidStudentDataException;
import com.Exception.PaymentValidationException;
import com.Exception.StudentNotFoundException;
import com.Service.StudentService;
import com.dto.getpaymentdto;
import com.dto.getstudentdto;
import com.model.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    public static void main(String[] args){
        StudentService studentService = new StudentService();
        while(true) {
         Scanner sc=new Scanner(System.in);
         System.out.println("Select an option:");
         System.out.println("1. Manage Payment");
         System.out.println("2. Manage Student");
 		System.out.println("0. Go to Login Page");
         int option = sc.nextInt();
         
         if (option == 1) {
             PaymentController.main(args);
         } 
         else if (option == 2) {
         
         
         
            while (true) {
                System.out.println("-------- STUDENT OPERATIONS ----------");
                System.out.println("Press 1. To Enroll The Student In The Course");
                System.out.println("Press 2. To Update The Student Information");
                System.out.println("Press 3. To Make Payment");
                System.out.println("Press 4. To Display Student Information");
                System.out.println("Press 5. To Get a Enrolled Course");
                System.out.println("Press 6. To Get Payment History");
                System.out.println("Press 0. To Exit");

                int input = sc.nextInt();
                if (input == 0) {
                    System.out.println("Exiting... Thank You!!");
                    break;
                }
                switch (input) {
                    case 1:
                        System.out.println("Enroll The Student In Course");
                        try {
                            List<Student> students = studentService.fetchAll();
                            for (Student student : students) {
                                System.out.println(student);
                            }
                            System.out.println("Enter the ID of the student");
                            int studentId = sc.nextInt();
                            System.out.println("Enter the courseId");
                            int courseId = sc.nextInt();

                            studentService.EnrollInCourse(studentId, courseId);
                            System.out.println("Student enrolled successfully.");
                        }catch(SQLException | DuplicateEnrollmentException e) {
            				System.out.println(e.getMessage());
            				break;
                        }
                      
                    case 2:
                        System.out.println("Update the Student Information");
                        try {
                            List<Student> list = studentService.fetchAll();
                            System.out.println("Enter Student Id to be updated:");
                            int id = sc.nextInt();
                            sc.nextLine(); 
                            System.out.println();
                            Student s = studentService.getStudentById(list, id);
                            System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s", "Id",
                                    "First Name", "Last Name", "Date Of Birth", "Email", "Phone Number"));

                            System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s", s.getId(),
                                    s.getFirstName(), s.getLastName(), s.getDateOfBirth(), s.getEmail(), s.getPhoneNumber().toString()));
                            System.out.println("What do you want to update?");
                            String field = sc.nextLine();
                            System.out.println("Enter the new value:");
                            String newVal = sc.next();
                            studentService.updateStudent(id, field, newVal);
                            System.out.println("Record updated Successfully");
                        } catch (SQLException | InvalidStudentDataException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    	
                    case 3:
                    	System.out.println("To Make Payment");
                    	
                    	try {
                    		System.out.println("Enter the student Id");
                    		int studentId=sc.nextInt();
                    		System.out.println("Enter the amount");
                    		int amount=sc.nextInt();
                    		
                    		
                    		studentService.MakePayment(studentId,amount);
                    	}
                    	catch(SQLException | PaymentValidationException  e) {
            				System.out.println(e.getMessage());
                    	} 
            				break;
                    	
                    case 4:
                    	System.out.println("Displaying Student Information");
                    	try {
        					List<Student> list = studentService.DisplayStudentInfo();
                    		System.out.println("The Student Details");
                   		 for (Student student : list) {
                                System.out.println(student);
                            }
                   	}
                    	catch(SQLException e) {
            				System.out.println(e.getMessage());
            				break;
                        }
                    	
                    case 5:
                    		System.out.println("Enter the student Id");
                    		int studentId=sc.nextInt();
                    		try {
                    			getstudentdto dto = studentService.GetEnrolledCourse(studentId);
                                if (dto != null) {
                                	System.out.println("Student Id: " + dto.getStudentId() + "\n Student First Name:  " + dto.getFirstName() + "\n Student Last Name:  " + dto.getLastName() + "\n Enrolled Course:  " + dto.getCourseName());
                                } else {
                                    System.out.println("Student is not enrolled in any course.");
                                }
                    		}
                    		catch(SQLException | StudentNotFoundException e) {
                    			System.out.println(e.getMessage());
                    		}
                    		
                    		
                    case 6:
                    	System.out.println("Enter the Student Id");
                    	int StudentId=sc.nextInt();
                    	try {
                    		List<getpaymentdto> list=studentService.GetPaymentHistory(StudentId);
                    		if(!list.isEmpty()) {
                    			System.out.println("Payment Hiistory");
                    			for(getpaymentdto dto:list) {
                    				System.out.println("Payment ID: " + dto.getPaymentId() +", Amount: " + dto.getAmount() +", Payment Date: " + dto.getPaymentDate() +", Student ID: " + dto.getStudentId() +", Student First Name: " + dto.getFirstName());
                    				
                    			}
                    		}
                    	else {
                            System.out.println("No payment records found for this student.");
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    		
       
}
                
        }
    }
         else if(option==0)
         	break;
         else {
         	System.out.println("Enter a Valid Option");
         }
}
    }
}
