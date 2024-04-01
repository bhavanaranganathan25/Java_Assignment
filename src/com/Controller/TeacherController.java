package com.Controller;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

import com.Exception.CourseNotFoundException;
import com.Exception.DuplicateEnrollmentException;
import com.Exception.InvalidCourseDataException;
import com.Exception.InvalidStudentDataException;
import com.Exception.InvalidTeacherDataException;
import com.Exception.PaymentValidationException;
import com.Service.*;

import com.dto.getstudentdto;
import com.dto.getteacherdto;
import com.model.Student;
import com.model.Teacher;

public class TeacherController {
	public static void main(String[] args,Scanner sc) throws InvalidCourseDataException, CourseNotFoundException {
		TeacherService teacherService = new TeacherService();
		while(true) {
		System.out.println("Select an option:");
        System.out.println("1. Manage Student");
        System.out.println("2. Manage Course ");
        System.out.println("3. Manage Enrollment");
		System.out.println("0. Go to Login Page");
        int Option = sc.nextInt();
        
        if (Option == 1) {
            StudentController.main(args);
        } else if (Option == 2) {
            CourseController.main(args,sc);
        } 
        else if (Option == 3) {
            EnrollmentController.main(args);
        } 
        
        else if (Option == 4) {
		while (true) {
			System.out.println("-------- TEACHER OPERATIONS ----------");
			System.out.println("Press 1. To Update The Teacher Information");
			System.out.println("Press 2. Display The Teacher Information");
			System.out.println("Press 3. Retreive a List Of Course Assigned To Teacher");
			System.out.println("Press 0. To Exit");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting... Thank You!!");
				break;
			}
			switch (input) {
			case 1:
				try {
					List<Teacher> list = teacherService.fetchAllteacher();
					System.out.println("Enter Teacher Id to be updated:");
					int id = sc.nextInt();
					System.out.println();
					Teacher t = teacherService.getteacherbyId(list, id);

					System.out.println("id, first_name, last_name, email");
					System.out.println(
							t.getId() + ", " + t.getFirstName() + ", " + t.getLastName() + ", " + t.getEmail());
					System.out.println("What do you want to update?");
					sc.nextLine();
					String field = sc.nextLine();
					System.out.println("Enter the new value:");
					String newVal = sc.next();
					teacherService.updateTeacher(id, field, newVal);
					System.out.println("Record updated Successfully");
				} catch (SQLException | InvalidTeacherDataException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					List<Teacher> list = teacherService.DisplayTeacherInfo();
					System.out.println("The Teacher Details");
					for (Teacher teacher : list) {
						System.out.println(teacher);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				try {
					System.out.println("Enter the Teacher Id");
					int teacherId = sc.nextInt();
					getteacherdto dto = teacherService.GetAssignedCourses(teacherId);

					if (dto != null) {
						System.out.println("Course Id: " + dto.getCourseId());
						System.out.println("Course Name: " + dto.getCourseName());
						System.out.println("Teacher First Name: " + dto.getFirstName());
					} else {
						System.out.println("No courses found for the given teacher Id.");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
        else if(Option==0)
        	break;
        else {
        	System.out.println("Enter a Valid Option");
        }
}}
}