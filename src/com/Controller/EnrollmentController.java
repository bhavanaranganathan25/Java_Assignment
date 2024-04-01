package com.Controller;
import java.sql.SQLException;
import java.util.*;

import com.Exception.StudentNotFoundException;
import com.Service.*;
import com.dto.getenrollmentdto;
import com.model.Enrollment;
public class EnrollmentController {
	public static void main(String[] args) {
		EnrollmentService enrollService=new EnrollmentService();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("************ ENROLLMENT OPERATIONS *************");
			System.out.println("Press 1. To Retreive the Student Associated With Enrollment");
			System.out.println("Press 2. To Retreive the Course Associated With Enrollment");
			System.out.println("Press 0. To Exit");
			int input=sc.nextInt();
			if (input == 0) {
                System.out.println("Exiting... Thank You!!");
                break;
            }
			switch(input) {
			case 1:
				System.out.println("Retreive Student");
				try {
                        System.out.println("Enter Enrollment ID:");
                        int enrollmentId = sc.nextInt();
                        getenrollmentdto dto=enrollService.getStudent(enrollmentId);
                        if(dto!=null) {
                        	System.out.println("Student Id:"+dto.getStudentId()+",Student Name:"+dto.getFirstName());
                        }
				}
                        catch(SQLException | StudentNotFoundException e) {
                        	System.out.println(e.getMessage());
                        }
                        
				break;
			case 2:
				System.out.println("Retreive Course");
				try {
					System.out.println("Enter Enrollment ID:");
                    int enrollmentId = sc.nextInt();
                    enrollService.getCourse(enrollmentId);
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
              break;
			}
				
			
			}
			
		}
		
	}

}
