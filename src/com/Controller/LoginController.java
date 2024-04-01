package com.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.Exception.CourseNotFoundException;
import com.Exception.InvalidCourseDataException;
import com.Exception.InvalidCredentialsException;
import com.Exception.InvalidStudentDataException;
import com.Exception.InvalidTeacherDataException;
import com.Service.LoginService;
import com.Service.StudentService;
import com.Service.TeacherService;
import com.dto.LoginDto;
import com.model.Student;
import com.model.Teacher;

public class LoginController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		LoginService loginservice=new LoginService();
		StudentService studentservice=new StudentService();
		TeacherService teacherservice=new TeacherService();
		while(true) {
			System.out.println();
			System.out.println("********* LOGIN OPERATION *********");
			System.out.println("press 1. Sign up for Student");
			System.out.println("press 2. Sign up for Teacher");
			System.out.println("press 3. Login");
			System.out.println("press 4. Password Reset");
			System.out.println("press 0. Exit");
			int input=sc.nextInt();
			if(input==0) {
				System.out.println("Exiting....Thank you!");
				break;
			}
			switch(input) {
			case 1:
			try {
				List<Student> list=loginservice.fetchAll();
				int flag=0;
				System.out.println("Enter First Name");
				sc.nextLine();
				String firstName=sc.nextLine();
				System.out.println("Enter Last Name");
				String lastName=sc.nextLine();
				System.out.println("Enter Date Of Birth");
				String dateOfBirth=sc.next();
				
				if(loginservice.checkStudentUsername(list,firstName)) {
					System.out.println("Student already Exists...!!");
				}
				else {
				System.out.println("Enter Email Id");
				sc.nextLine();
				String email=sc.nextLine();
				System.out.println("Enter Phone Number");
				String phoneNo=sc.next();
				loginservice.createStudent(firstName,lastName,dateOfBirth,email,phoneNo);
				System.out.println("Signed Up Successfully");
				}
			}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					List<Teacher> list=loginservice.fetchAllteacher();
				
					System.out.println("Enter First Name");
					sc.nextLine();
					String firstName=sc.nextLine();
					System.out.println("Enter Last Name");
					String lastName=sc.nextLine();
					System.out.println("Enter Email Id");
					String email=sc.nextLine();
					if(loginservice.checkTeacherUsername(list,firstName,email)) {
						System.out.println("Teacher already Exists...!!");
					}
					else {
					loginservice.createTeacher(firstName,lastName,email);
					System.out.println("Signed Up Successfully");
					}
				}catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
			case 3:
				try {
					List<Teacher> tlist=teacherservice.fetchAllteacher();
					List<Student> slist=studentservice.fetchAll();
					System.out.println("Enter Name");
					String firstName=sc.next();
					sc.nextLine();
					System.out.println("Enter Email Id");
					String email=sc.nextLine();
					LoginDto logindto=loginservice.loginCheck(tlist,slist,firstName,email);
					if(logindto.getEmail().equals(email)) {
						System.out.println("Logging In....");
						System.out.println("Welcome "+logindto.getFirstName()+" "+logindto.getLastName());
						if(logindto.getRole().equals("student"))
							StudentController.main(args);
						else
							TeacherController.main(args,sc);
					}
					else
						throw new InvalidCredentialsException("Invalid Credentials!");
				} catch (SQLException | InvalidCredentialsException | CourseNotFoundException | InvalidCourseDataException e) {
					System.out.println(e.getMessage());
				}
				break;
				}
		}
	}

	}

