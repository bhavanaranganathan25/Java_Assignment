package com.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.model.*;
import com.Exception.CourseNotFoundException;
import com.Exception.InvalidCourseDataException;
import com.Exception.StudentNotFoundException;
import com.Service.CourseService;
import com.dto.*;

public class CourseController {
	public static void main(String[] args,Scanner sc) throws InvalidCourseDataException, CourseNotFoundException {
        CourseService courseService = new CourseService();
      
            while (true) {
                System.out.println("-------- COURSE OPERATIONS ----------");
                System.out.println("Press 1. To Assign a Teacher To The Course");
                System.out.println("Press 2. To Update The Course Information");
                System.out.println("Press 3. To Display Course Information");
                System.out.println("Press 4. To Retrieve a list of Student Enrolled For Course");
                System.out.println("Press 5. To Retrieve assigned Teacher for Course");
                System.out.println("Press 6. To Calculate the Statistics of course Enrolled");
                System.out.println("Press 0. To Exit");

                int input = sc.nextInt();
                if (input == 0) {
                    System.out.println("Exiting... Thank You!!");
                    break;
                }
                switch (input) {
                
                case 1:
                	try {
                		List<Teacher> list=courseService.DisplayTeacherInfo();
                		System.out.println("The Teacher Details");
                		 for (Teacher teacher : list) {
                             System.out.println(teacher);
                		 }
                        System.out.println("Enter the Course:");
                        String courseName = sc.next();
                        System.out.println("Enter Course Credit");
                        int credits=sc.nextInt();
                        System.out.println("Enter the teacher Id to be Assigned");
                        int teacherId=sc.nextInt();
                        Course course=courseService.assignTeacher(courseName,credits,teacherId);
                        System.out.println("Course Assigned Successfully");
                		 
                	}
                		 catch(SQLException e) {
            				System.out.println(e.getMessage());
                        }
            				break;
                    	
                case 2:
                    System.out.println("Update the Course Information");
                    try {
                        List<Course> list = courseService.fetchAll();
                        System.out.println("Enter Course Id to be updated:");
                        int id = sc.nextInt();
                        sc.nextLine(); 
                        System.out.println();
                        Course c = courseService.getCourseById(list, id);
                        System.out.println(String.format("%-15s%-15s%-15s%-15s", "Id", "Course Name", "Credits", "Teacher Id"));

                        System.out.println(String.format("%-15d%-15s%-15s%-15d", c.getId(), c.getName(), c.getCredits(), c.getTeacherId()));

                        System.out.println("What do you want to update?");
                        String field = sc.nextLine();
                        System.out.println("Enter the new value:");
                        String newVal = sc.next();
                        courseService.updateCourse(id, field, newVal);
                        System.out.println("Record updated Successfully");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                	
                case 3:
                	System.out.println("Displaying Course Information");
                	try {
    					List<Course> list = courseService.DisplayCourseInfo();
                		System.out.println("The Course Details");
               		 for (Course courses : list) {
                            System.out.println(courses);
                        }
               	}
                	catch(SQLException e) {
        				System.out.println(e.getMessage());
        				
                    }
                	break;
                    
                	
                case 4:
                	System.out.println("Enter the Course Id");
                	int CourseId=sc.nextInt();
                	try {
                		List<getenrollmentdto> list=courseService.getEnrollments(CourseId);
                		if(!list.isEmpty()) {
                			System.out.println("Course History");
                			for(getenrollmentdto dto:list) {
                				System.out.println("Course ID: " + dto.getCourseid() +",Course Name:"+dto.getCourseName()+" First Name: " + dto.getFirstName() +", Last Name: " + dto.getLastName());
                				
                			}
                		}
                	else {
                        System.out.println("No Enrolled Course records found for this student.");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                	break;
                	
                case 5:
                	System.out.println("Enter the Course Id");
                	int courseId=sc.nextInt();
                	try {
                		List<getenrollmentdto> list=courseService.getTeacher(courseId);
                		if(!list.isEmpty()) {
                			for(getenrollmentdto dto:list) {
                				System.out.println("Course Name:"+dto.getCourseName()+" ,First Name: " + dto.getFirstName() +", Last Name: " + dto.getLastName());
                				
                			}
                		}
                	else {
                        System.out.println("No Teacher Assigned For This Course.");
                    }
            }
                	catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                	break;
                case 6:
                	
                	System.out.println("Enter the course name");
                	String courseName=sc.next();
                	try {
                		List<getenrollmentdto> list=courseService.CalculateCoursetatistic(courseName);
                		if(!list.isEmpty()) {
                			for(getenrollmentdto dto:list) {
                				System.out.println("Course Name:"+dto.getCourseName()+",\n Course Enrolled: " + dto.getEnrollmentId() +",\n Payment Amount: " + dto.getPaymentAmount()+"\n");
                			}
                		}
                	}catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                	break;
        }
	}
        }
	}


